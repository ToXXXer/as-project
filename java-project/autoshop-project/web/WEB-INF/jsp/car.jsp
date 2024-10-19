<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 05.10.2024
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Машина</title>
    <link rel="stylesheet" type="text/css" href="css/pages/page.css">
    <link rel="stylesheet" type="text/css" href="css/pages/car/leftbar.css">
    <link rel="stylesheet" type="text/css" href="css/pages/car/rightbar.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
    </style>
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
        <div class="name">${requestScope.car.brand.brand_name} ${requestScope.car.model}/ ${requestScope.car.date_model}</div>
        <div class="price">${requestScope.car.price}₽</div>
    </div>
    <section class="page">
        <div class="left-bar">
            <div class="car">
                <label>Характеристики</label>
                <div class="card">
                    <div class="info">
                        <label class="car">Бренд:</label>
                    </div>
                    <div class="info-text">
                        ${requestScope.car.brand.brand_name}
                    </div>
                </div>
                <div class="card">
                    <div class="info">
                        <label class="car">Модель:</label>
                    </div>
                    <div class="info-text">
                        ${requestScope.car.model}
                    </div>
                </div>
                <div class="card">
                    <div class="info">
                        <label class="car">Год выпуска:</label>
                    </div>
                    <div class="info-text">
                        ${requestScope.car.date_model}
                    </div>
                </div>
                <div class="card">
                    <div class="info">
                        <label class="car">Вид топлива:</label>
                    </div>
                    <div class="info-text">
                        ${requestScope.car.type_of_engine}
                    </div>
                </div>
                <div class="card">
                    <div class="info">
                        <label class="car">Пробег:</label>
                    </div>
                    <div class="info-text">
                        ${requestScope.car.mileage}км
                    </div>
                </div>
                <div class="card">
                    <div class="info">
                        <label class="car">Местонахождение:</label>
                    </div>
                    <div class="info-text">
                        ${requestScope.car.person.adress.country}, ${requestScope.car.person.adress.town}
                    </div>
                </div>
            </div>
            <div class="person">
                <label>Владелец</label>
                <div class="card">
                    <div class="info">
                        <label class="car">Полное имя:</label>
                    </div>
                    <div class="info-text">
                        ${requestScope.car.person.first_name} ${requestScope.car.person.last_name}
                    </div>
                </div>
                <div class="card">
                    <div class="info">
                        <label class="car">Почта:</label>
                    </div>
                    <div class="info-text">
                        ${requestScope.car.person.email}
                    </div>
                </div>
                <div class="card">
                    <div class="info">
                        <label class="car">Номер телефона:</label>
                    </div>
                    <div class="info-text">
                        ${requestScope.car.person.phone}
                    </div>
                </div>
            </div>
        </div>
        <div class="right-bar">
            <div class="image">
                <img class="car-card" src="${pageContext.request.contextPath}/images/car/${car.picture}.jpg" alt="Car">
                <img class="hover-car-card" src="${pageContext.request.contextPath}/images/car/${car.picture}.jpg" alt="Car">
            </div>
            <div class="car-info">
                <label>Комментарий автора</label>
                <div class="info-text" class="comment">
                    ${requestScope.car.car_info}
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>
