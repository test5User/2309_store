package by.itclass.model.dao;

import by.itclass.model.db.ConnectionManager;
import by.itclass.model.entities.*;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static by.itclass.constants.AppConst.*;

public class OrderDao {
    public static final String INSERT_ORDER = "INSERT INTO orders(id, date, userId, address) values (?, ?, ?, ?)";
    public static final String INSERT_ORDER_ITEM = "INSERT INTO orderItem(orderId, itemType, itemId, itemPrice, quantity) values (?, ?, ?, ?, ?)";
    public static final String SELECT_ORDERS = "SELECT id, date, address FROM orders WHERE userId = ? ORDER BY id DESC";
    public static final String SELECT_ORDER = "SELECT date, address FROM orders WHERE id = ?";
    public static final String SELECT_ORDER_ITEMS = "SELECT itemType, itemId, itemPrice, quantity FROM orderItem WHERE orderId = ?";

    public boolean saveOrder(HttpSession session, String address) {
        var user = (User) session.getAttribute(USER_ATTR);
        var now = LocalDateTime.now();
        var date = now.format(DateTimeFormatter.ofPattern("y-MM-dd"));
        var time = now.format(DateTimeFormatter.ofPattern("HH-mm"));
        var orderId = String.join("-", user.getName(), date, time);
        return saveOrderToDb(orderId, date, user.getId(), address, session);
    }

    private boolean saveOrderToDb(String orderId, String date, int userId,
                                  String address, HttpSession session) {
        try (var cn = ConnectionManager.getConnection();
             var psSaveOrder = cn.prepareStatement(INSERT_ORDER);
             var psSaveItem = cn.prepareStatement(INSERT_ORDER_ITEM)) {
            cn.setAutoCommit(false);
            firstAction(orderId, date, userId, address, psSaveOrder);
            var items = (List<OrderItem>) session.getAttribute(ORDER_ITEMS_ATTR);
            for (OrderItem item : items) {
                secondAction(orderId, item, psSaveItem);
            }
            cn.commit();
            session.setAttribute(ORDER_ID_ATTR, orderId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void firstAction(String orderId, String date, int userId,
                             String address, PreparedStatement ps) throws SQLException{
        ps.setString(1, orderId);
        ps.setString(2, date);
        ps.setInt(3, userId);
        ps.setString(4, address);
        ps.executeUpdate();
    }

    private void secondAction(String orderId, OrderItem item,
                              PreparedStatement ps) throws SQLException {
        ps.setString(1, orderId);
        ps.setInt(2, item.getItemType());
        ps.setInt(3, item.getItemId());
        ps.setDouble(4, item.getItemPrice());
        ps.setInt(5, item.getQuantity());
        ps.executeUpdate();
    }

    public List<Order> selectOrders(int userId) {
        var orders = new ArrayList<Order>();
        try (var cn = ConnectionManager.getConnection();
             var ps = cn.prepareStatement(SELECT_ORDERS)){
            ps.setInt(1, userId);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var id = rs.getString(ID_PARAM);
                var date = rs.getString(DATE_PARAM);
                var address = rs.getString(ADDRESS_PARAM);
                orders.add(new Order(id, date, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public Receipt buildReceipt(String orderId) {
        var receipt = new Receipt();
        try (var cn = ConnectionManager.getConnection();
             var ps = cn.prepareStatement(SELECT_ORDER)){
            ps.setString(1, orderId);
            var rs = ps.executeQuery();
            if (rs.next()) {
                var date = rs.getString(DATE_PARAM);
                var address = rs.getString(ADDRESS_PARAM);
                receipt.setOrder(new Order(orderId, date, address));
                var items = getItems(orderId, cn);
                receipt.setReceiptItems(items);
                receipt.setTotal(Math.round(getTotalAmount(items)*100)/100d);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return receipt;
    }

    private List<ReceiptItem> getItems(String orderId, Connection cn) throws SQLException {
        var items = new ArrayList<ReceiptItem>();
        try (var ps = cn.prepareStatement(SELECT_ORDER_ITEMS)){
            ps.setString(1, orderId);
            var rs = ps.executeQuery();
            while (rs.next()) {
                var itemType = rs.getInt(ITEM_TYPE_PARAM);
                var itemId = rs.getInt(ITEM_ID_PARAM);
                var itemInfo = getInfo(itemType, itemId, cn);
                var itemPrice = rs.getDouble(ITEM_PRICE_PARAM);
                var quantity = rs.getInt(QUANTITY_PARAM);
                var itemAmount = Math.round(itemPrice * quantity * 100)/100d;
                items.add(new ReceiptItem(itemInfo, itemPrice, quantity, itemAmount));
            }
        }
        return items;
    }

    private String getInfo(int itemType, int itemId, Connection cn) throws SQLException {
        try (var st = cn.createStatement()){
            var tableName = itemType == 1 ? "tv" : "laptop";
            var query = String.format("SELECT vendor, model FROM %s WHERE id = %d", tableName, itemId);
            var rs = st.executeQuery(query);
            rs.next();
            return String.join("-", rs.getString(VENDOR_PARAM), rs.getString(MODEL_PARAM));
        }
    }

    private double getTotalAmount(List<ReceiptItem> items) {
        return items.stream()
                .map(ReceiptItem::getItemAmount)
                .reduce(0d, Double::sum);
    }
}
