package by.itclass.model.services;

import by.itclass.model.dao.TvDao;
import by.itclass.model.entities.Tv;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static by.itclass.constants.AppConst.*;

public class TvService implements Service {
    private TvDao dao;

    public TvService() {
        dao = new TvDao();
    }

    public List<Tv> getTvs(Map<String, String[]> params) {
        var allTvs = dao.selectAllTv();
        if (params.isEmpty()) {
            return allTvs;
        }
        var vendors = params.get(VENDOR_PARAM);
        var screens = params.get(SCREEN_PARAM);
        var from = params.get(PRICE_FROM_PARAM)[0];
        var to = params.get(PRICE_TO_PARAM)[0];
        return allTvs.stream()
                .filter(tv -> vendors == null || ArrayUtils.contains(vendors, tv.getVendor()))
                .filter(tv -> screens == null || ArrayUtils.contains(screens, String.valueOf(tv.getScreenSize())))
                .filter(tv -> from.isEmpty() || tv.getPrice() > Double.parseDouble(from))
                .filter(tv -> to.isEmpty() || tv.getPrice() < Double.parseDouble(to))
                .toList();
    }
}
