<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 05.10.2024
  Time: 18:46
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Профиль</title>
    <link rel="stylesheet" href="css/pages/person/leftbar.css">
    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/pages/page.css">
    <link rel="stylesheet" href="css/pages/person/profile/rightbar.css">
</head>
<body>
<div>
    <header class="navbar">
        <div class="drop">
            <button class="nav-button">&#9776</button>
            <div class="nav-list">
                <c:if test="${not empty sessionScope.user}">
                    <a href="${pageContext.request.contextPath}/profile">Профиль</a>
                    <a href="index.html">Избранное</a>
                    <a href="${pageContext.request.contextPath}/main">Объявления</a>
                    <a href="${pageContext.request.contextPath}/logout">Выйти</a>
                </c:if>
            </div>
        </div>
    </header>
    <div class="main">
        <div class="name">Мой профиль</div>
    </div>
    <section class="page">
        <div class="left-bar">
            <div class="person">
                <label>Владелец</label>
                <div class="card">
                    <div class="info">
                        <label class="car">Username:</label>
                    </div>
                    <div class="info-text">
                        ${requestScope.user.surname}
                    </div>
                </div>
                <div class="card">
                    <div class="info">
                        <label class="car">Полное имя:</label>
                    </div>
                    <div class="info-text">
                        ${requestScope.user.first_name} ${requestScope.user.last_name}
                    </div>
                </div>
                <div class="card">
                    <div class="info">
                        <label class="car">Почта:</label>
                    </div>
                    <div class="info-text">
                        ${requestScope.user.email}
                    </div>
                </div>
                <div class="card">
                    <div class="info">
                        <label class="car">Номер телефона:</label>
                    </div>
                    <div class="info-text">
                        ${requestScope.user.phone}
                    </div>
                </div>
                <div class="card">
                    <div class="info">
                        <label class="car">Адрес:</label>
                    </div>
                    <div class="info-text">
                        ${requestScope.user.adress.country}, г.${requestScope.user.adress.town}
                    </div>
                </div>
                <div class="card">
                    <div class="info">
                        <label class="car">Статус:</label>
                    </div>
                    <div class="info-text">
                        Пользователь
                    </div>
                </div>
                <div class="card">
                    <div class="info">
                        <label class="car">Изменить данные:</label>
                    </div>
                    <a href="${requestScope.contextPath}/app/redactor"><button class="find">Редактировать</button></a>
                </div>
            </div>
        </div>
        <div class="right-bar">
            <div class="image">
                <img class="profile-card" src="${pageContext.request.contextPath}/images/person/${requestScope.user.picture}.jpg"
                     alt="Image">
            </div>
            <div class="car-info">
                <label>Информация о профиле</label>
                <div class="info-text" class="comment">
                    ${requestScope.user.user_info}
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>
