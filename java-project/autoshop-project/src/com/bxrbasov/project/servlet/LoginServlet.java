package com.bxrbasov.project.servlet;

import com.bxrbasov.project.dto.FindUserDto;
import com.bxrbasov.project.dto.PersonDto;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final PersonService personService = PersonService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String surname = req.getParameter("surname");
        String password = req.getParameter("password");
        FindUserDto findUserDto = FindUserDto.builder()
                .surname(surname)
                .password(password)
                .build();
        Connection connection = ConnectionManager.takeConnection();
        try {
            PersonDto user = personService.login(findUserDto, connection);
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/app/main");
        } catch (ValidationException exception) {
            req.setAttribute("errors", exception.getErrorList());
            doGet(req, resp);
        } finally {
            ConnectionManager.putConnection(connection);
        }
    }
}
