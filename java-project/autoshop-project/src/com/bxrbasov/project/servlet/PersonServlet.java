package com.bxrbasov.project.servlet;

import com.bxrbasov.project.dto.PersonDto;
import com.bxrbasov.project.service.PersonService;
import com.bxrbasov.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class PersonServlet extends HttpServlet {

    private final PersonService personService = PersonService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PersonDto user = (PersonDto) req.getSession().getAttribute("user");
        req.setAttribute("user", user);
        req.getRequestDispatcher(JspHelper.getPath("profile"))
                .forward(req, resp);
    }
}
