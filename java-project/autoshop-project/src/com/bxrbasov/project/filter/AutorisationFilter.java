package com.bxrbasov.project.filter;


import com.bxrbasov.project.dto.PersonDto;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

import static com.bxrbasov.project.util.Paths.*;

public class AutorisationFilter implements Filter {

    private static final Set<String> PUBLIC_PATH = Set.of(LOGIN, REGISTRATION, IMAGE, MAIN);
    private static final Set<String> PRIVATE_PATH = Set.of(PROFILE, REDACTOR, LOGOUT, POST, LIKE);
    private static final Set<String> ADMIN_PATH = Set.of(ADMINPAGE);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if(isPublicPath(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if(isPrivatePath(uri) && isUserLoggedIn(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if(isAdminPath(uri) && isUserLoggedIn(servletRequest) && isUserAdmin(servletRequest)){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse) servletResponse).sendRedirect("/login");
        }
    }

    private boolean isUserAdmin(ServletRequest servletRequest) {
        PersonDto user = (PersonDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user.getPrivilege() == "admin";
    }

    private boolean isAdminPath(String uri) {
        return ADMIN_PATH.stream().allMatch(uri::startsWith);
    }

    private boolean isUserLoggedIn(ServletRequest servletRequest) {
        PersonDto user = (PersonDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        return user != null;
    }

    private boolean isPrivatePath(String uri) {
        return PRIVATE_PATH.stream().allMatch(uri::startsWith);
    }

    private boolean isPublicPath(String uri) {
        return PUBLIC_PATH.stream().allMatch(uri::startsWith);
    }

}
