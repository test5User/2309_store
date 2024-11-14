package by.itclass.controllers.user;

import by.itclass.controllers.AbstractController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static by.itclass.constants.AppConst.*;

@WebServlet(CHANGE_CONTROLLER)
public class UpdateController extends AbstractController {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = Integer.parseInt(req.getParameter(ID_PARAM));
        var pass = req.getParameter(PASS_PARAM);
        if (userService.changePassword(id, pass)) {
            redirect(resp, LOGIN_JSP);
        } else {
            forward(req, resp, SET_PASS_JSP, "Change Pass is not successfully");
        }
    }
}
