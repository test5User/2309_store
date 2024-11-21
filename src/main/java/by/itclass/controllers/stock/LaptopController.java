package by.itclass.controllers.stock;

import by.itclass.controllers.AbstractController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.itclass.constants.AppConst.*;

@WebServlet(LAPTOP_CONTROLLER)
public class LaptopController extends AbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var laptops = laptopService.getLaptops(req.getParameterMap());
        req.setAttribute(LAPTOPS_ATTR, laptops);
        forward(req, resp, LAPTOP_PAGE_JSP);
    }
}
