package com.bxrbasov.project.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {

    private static final String JSP_FORMAT = "WEB-INF/jsp/%s.jsp";

    public static String getPath(String path) {
        return String.format(JSP_FORMAT, path);
    }
}
