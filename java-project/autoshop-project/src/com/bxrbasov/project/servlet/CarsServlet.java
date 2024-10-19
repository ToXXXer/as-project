package com.bxrbasov.project.servlet;

import com.bxrbasov.project.dto.BrandDto;
import com.bxrbasov.project.dto.CarDto;
import com.bxrbasov.project.service.BrandService;
import com.bxrbasov.project.service.CarService;
import com.bxrbasov.project.util.ConnectionManager;
import com.bxrbasov.project.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import java.io.*;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@WebServlet("/main")
public class CarsServlet extends HttpServlet {

    private static final CarService carService = CarService.getInstance();
    private static final BrandService brandService = BrandService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = ConnectionManager.takeConnection();
        try {
            req.setAttribute("cars", carService.findAll(connection));
        } finally {
            ConnectionManager.putConnection(connection);
        }

        connection = ConnectionManager.takeConnection();
        try {
            req.setAttribute("brands", brandService.findAll(connection));
        } finally {
            ConnectionManager.putConnection(connection);
        }

        req.setAttribute("types_of_engine", List.of("Бензин", "Дизель", "Гибрид", "Электрокар"));
        req.getRequestDispatcher(JspHelper.getPath("main"))
                .forward(req, resp);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long priceFrom;
        Long priceTo;
        Long mileageFrom;
        Long mileageTo;
        Integer yearFrom;
        Integer yearTo;

        if(req.getParameter("price_from") != null && !req.getParameter("price_from").equals("")) {
            priceFrom = Long.valueOf(req.getParameter("price_from"));
        } else {
            priceFrom = -1L;
        }
        if(req.getParameter("price_to") != null && !req.getParameter("price_to").equals("")) {
            priceTo = Long.valueOf(req.getParameter("price_to"));
        } else {
            priceTo = Long.MAX_VALUE;
        }

        if(req.getParameter("mileage_from") != null && !req.getParameter("mileage_from").equals("")) {
            mileageFrom = Long.valueOf(req.getParameter("mileage_from"));
        } else {
            mileageFrom = -1L;
        }
        if(req.getParameter("mileage_to") != null && !req.getParameter("mileage_to").equals("")) {
            mileageTo = Long.valueOf(req.getParameter("mileage_to"));
        } else {
            mileageTo = Long.MAX_VALUE;
        }

        if(req.getParameter("year_from") != null && !req.getParameter("year_from").equals("")) {
            yearFrom = Integer.valueOf(req.getParameter("year_from"));
        } else {
            yearFrom = -1;
        }
        if(req.getParameter("year_to") != null && !req.getParameter("year_to").equals("")) {
            yearTo = Integer.valueOf(req.getParameter("year_to"));
        } else {
            yearTo = LocalDate.now().getYear();
        }

        List<String> typesOfEngine;
        if(req.getParameterValues("type_of_engine") != null) {
            typesOfEngine = List.of(req.getParameterValues("type_of_engine"));
        } else {
            typesOfEngine = new ArrayList<>();
        }

        List<String> brands;
        if(req.getParameterValues("brand") != null) {
            brands = List.of(req.getParameterValues("brand"));
        } else {
            brands = new ArrayList<>();
        }

        Connection connection = ConnectionManager.takeConnection();
        try {
            req.setAttribute("cars",  carService.findAllByParameters(priceFrom, priceTo, mileageFrom, mileageTo, yearFrom, yearTo, typesOfEngine, brands, connection));
        } finally {
            ConnectionManager.putConnection(connection);
        }

        connection = ConnectionManager.takeConnection();
        try {
            req.setAttribute("brands", brandService.findAll(connection));
        } finally {
            ConnectionManager.putConnection(connection);
        }

        req.setAttribute("types_of_engine", List.of("Бензин", "Дизель", "Гибрид", "Электрокар"));
        req.getRequestDispatcher(JspHelper.getPath("main"))
                .forward(req, resp);
    }
}
