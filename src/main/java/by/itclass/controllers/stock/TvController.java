package by.itclass.controllers.stock;

import by.itclass.controllers.abstraction.AbstractController;
import by.itclass.model.services.ServiceFactory;
import by.itclass.model.services.ServiceType;
import by.itclass.model.services.TvService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.itclass.constants.AppConst.*;

@WebServlet(TV_CONTROLLER)
public class TvController extends AbstractController {
    private TvService tvService;

    @Override
    public void init() throws ServletException {
        tvService = (TvService) ServiceFactory.getInstance(ServiceType.TV_SERVICE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var tvs = tvService.getTvs(req.getParameterMap());
        req.setAttribute(TVS_ATTR, tvs);
        forward(req, resp, TV_PAGE_JSP);
    }
}
