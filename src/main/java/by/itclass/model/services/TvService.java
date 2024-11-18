package by.itclass.model.services;

import by.itclass.model.dao.TvDao;
import by.itclass.model.entities.Tv;

import java.util.List;

public class TvService {
    private static TvService service;
    private TvDao dao;

    private TvService() {
        dao = TvDao.getInstance();
    }

    public static TvService getInstance() {
        if (service == null) {
            service = new TvService();
        }
        return service;
    }

    public List<Tv> getAllTv() {
        return dao.selectAllTv();
    }
}
