package com.bxrbasov.project.servlet;

import com.bxrbasov.project.dto.PersonDto;
import com.bxrbasov.project.dto.RedactorPersonDto;
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

@WebServlet("/redactor")
public class PersonRedactorServlet extends HttpServlet {

    private static final PersonService personService = PersonService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(JspHelper.getPath("redactor"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonDto user = (PersonDto) req.getSession().getAttribute("user");
        RedactorPersonDto build = RedactorPersonDto.builder()
                .surname(user.getSurname())
                .surnameNew(req.getParameter("surname"))
                .first_name(req.getParameter("first_name"))
                .last_name(req.getParameter("last_name"))
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .passwordConfirm(req.getParameter("confirmPassword"))
                .phone(req.getParameter("phone"))
                .country(req.getParameter("country"))
                .town(req.getParameter("town"))
                .user_info(req.getParameter("user_info"))
                .build();
        Connection connection = ConnectionManager.openConnection();
        try {
            PersonDto newUser = personService.redactorProfile(build, connection);
            req.getSession().setAttribute("user", newUser);
            resp.sendRedirect("/app/profile");
        } catch (ValidationException exception){
            req.setAttribute("error", exception.getErrorList());
            doGet(req, resp);
        } finally {
            ConnectionManager.putConnection(connection);
        }
    }
}
