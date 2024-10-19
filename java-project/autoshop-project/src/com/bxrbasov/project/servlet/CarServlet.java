package com.bxrbasov.project.servlet;

import com.bxrbasov.project.service.CarService;
import com.bxrbasov.project.util.ConnectionManager;
import com.bxrbasov.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/car")
public class CarServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String vin = req.getParameter("vin");

        Connection connection = ConnectionManager.takeConnection();
        req.setAttribute("car", CarService.getInstance().findByVin(vin, connection));
        ConnectionManager.putConnection(connection);

        req.getRequestDispatcher(JspHelper.getPath("car"))
                .forward(req, resp);
    }
}
