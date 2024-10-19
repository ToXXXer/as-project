package com.bxrbasov.project.servlet;

import com.bxrbasov.project.dto.CreateUserDto;
import com.bxrbasov.project.exception.ValidationException;
import com.bxrbasov.project.service.PersonService;
import com.bxrbasov.project.util.ConnectionManager;
import com.bxrbasov.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private static final PersonService personService = PersonService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("registration"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("surname"));
        CreateUserDto user =CreateUserDto.builder()
                .surname(req.getParameter("surname"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .repeatPassword(req.getParameter("repeatPassword"))
                .build();
        Connection connection = ConnectionManager.takeConnection();
        try {
            personService.create(user, connection);
            resp.sendRedirect("/app/login");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrorList());
            doGet(req, resp);
        } finally {
            ConnectionManager.putConnection(connection);
        }
    }
}
