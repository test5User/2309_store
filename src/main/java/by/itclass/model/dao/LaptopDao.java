package by.itclass.model.dao;

import by.itclass.model.db.ConnectionManager;
import by.itclass.model.entities.Laptop;
import by.itclass.model.entities.Tv;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.itclass.constants.AppConst.*;

public class LaptopDao {
    private static final String SELECT_ALL_LAPTOP = "SELECT * FROM laptop";
    private static LaptopDao dao;

    private LaptopDao() {
        ConnectionManager.init();
    }

    public static LaptopDao getInstance() {
        if (dao == null) {
            dao = new LaptopDao();
        }
        return dao;
    }

    public List<Laptop> selectAllLaptop() {
        var laptops = new ArrayList<Laptop>();
        try (var cn = ConnectionManager.getConnection();
             var ps = cn.prepareStatement(SELECT_ALL_LAPTOP)) {
            var rs = ps.executeQuery();
            while (rs.next()) {
                var id = rs.getInt(ID_PARAM);
                var vendor = rs.getString(VENDOR_PARAM);
                var model = rs.getString(MODEL_PARAM);
                var cpu = rs.getString(CPU_PARAM);
                var memory = rs.getInt(MEMORY_PARAM);
                var price = rs.getDouble(PRICE_PARAM);
                laptops.add(new Laptop(id, vendor, model, cpu, memory, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return laptops;
    }
}
