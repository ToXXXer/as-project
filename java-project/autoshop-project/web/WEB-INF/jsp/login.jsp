<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 13.10.2024
  Time: 3:44
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Авторизация</title>
    <link rel="stylesheet" type="text/css" href="css/pages/reglog/pageLog.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
  </head>
  <body>
  <div>
    <header class="navbar">
      <div class="drop">
        <button class="nav-button">&#9776</button>
        <div class="nav-list">
          <a href="${pageContext.request.contextPath}/login">Профиль</a>
          <a href="${pageContext.request.contextPath}/login">Избранное</a>
          <a href="${pageContext.request.contextPath}/main">Объявления</a>
          <a href="${pageContext.request.contextPath}/login">Войти</a>
        </div>
      </div>
    </header>
    <div class="main">
      <a class="reg" href="${pageContext.request.contextPath}/registration">Регистрация</a>
      <a class="log" href="${pageContext.request.contextPath}/login">Авторизация</a>
    </div>
    <section class="page">
      <form method="post">
        <p><input type="text" name="surname" placeholder="Введите имя" size="32" autocomplete="off"/></p>
        <p><input type="text" name="password" placeholder="Введите пароль" size="32" autocomplete="off"/></p>
        <p>
          <a href="profilePage.html"><button type="submit" style="display: inline-block;">Отправить</button></a>
          <button type="reset" style="display: inline-block;">Отмена</button>
        </p>
      </form>
      <c:if test="${not empty requestScope.errors}">
        <div style="color: darkred">
          <c:forEach var="error" items="${requestScope.errors}">
            <p>${error.code} ${error.message}</p>
          </c:forEach>
        </div>
      </c:if>
    </section>
  </div>
  </body>
</html>
