package by.itclass.model.services;

import by.itclass.model.dao.OrderDao;
import by.itclass.model.entities.Order;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class OrderService {
    private static OrderService service;
    private OrderDao dao;

    private OrderService() {
        dao = OrderDao.getInstance();
    }

    public static OrderService getInstance() {
        return service == null ? service = new OrderService() : service;
    }

    public boolean saveOrder(HttpSession session, String address) {
        return dao.saveOrder(session, address);
    }

    public List<Order> getOrders(int userId) {
        return dao.selectOrders(userId);
    }
}
