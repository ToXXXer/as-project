<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 05.10.2024
  Time: 18:44
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Объявления</title>
    <link rel="stylesheet" type="text/css" href="css/main/section.css">
    <link rel="stylesheet" type="text/css" href="css/main/leftbar.css">
    <link rel="stylesheet" type="text/css" href="css/navbar.css">
</head>
<body>
<div class="main">
    <header class="navbar">
        <div class="drop" style="height: 5%">
            <button class="nav-button">&#9776</button>
            <div class="nav-list">
                <c:if test="${not empty sessionScope.user}">
                    <a href="${pageContext.request.contextPath}/profile">Профиль</a>
                    <a href="index.html">Избранное</a>
                    <a href="${pageContext.request.contextPath}/main">Объявления</a>
                    <a href="${pageContext.request.contextPath}/logout">Выйти</a>
                </c:if>
                <c:if test="${empty sessionScope.user}">
                    <a href="${pageContext.request.contextPath}/login">Профиль</a>
                    <a href="${pageContext.request.contextPath}/login">Избранное</a>
                    <a href="${pageContext.request.contextPath}/main">Объявления</a>
                    <a href="${pageContext.request.contextPath}/login">Войти</a>
                </c:if>
            </div>
        </div>
    </header>
    <section class="left-right">
        <form method="post">
            <div class="left-bar">
                <div class="bar">
                    <label class="first-name"><span>Стоимость</span></label>
                    <br>
                    <label >От:
                    <input type="number" name="price_from" class="price" autocomplete="off"/>
                    </label>
                    <br>
                    <label>До:
                    <input type="number" name="price_to" class="price" autocomplete="off"/>
                    </label>
                </div>
                <div class="bar">
                    <label class="first-name"><span>Пробег</span></label>
                    <br>
                    <label>От:
                    <input type="number" name="mileage_from" class="price" autocomplete="off"/>
                    </label>
                    <br>
                    <label>До:
                    <input type="number" name="mileage_to" class="price" autocomplete="off"/>
                    </label>
                </div>
                <div class="bar">
                    <label class="first-name"><span>Год выпуска</span></label>
                    <br>
                    <label>От:
                    <input type="number" name="year_from" class="price" autocomplete="off"/>
                    </label>
                    <br>
                    <label>До:
                    <input type="number" name="year_to" class="price" autocomplete="off"/>
                    </label>
                </div>
                <div class="bar">
                    <label class="first-name"><span>Виды топлива</span></label>
                    <br>
                    <c:forEach var="type_of_engine" items="${requestScope.types_of_engine}">
                        <p>
                            <input class="choose" type="checkbox" checked name="type_of_engine" value="${type_of_engine}"/>
                            <span>${type_of_engine}</span>
                        </p>
                    </c:forEach>
                </div>
                <div class="bar">
                        <label class="first-name"><span>Марка авто</span></label>
                    <br>
                    <c:forEach var="brand" items="${requestScope.brands}">
                        <p>
                            <input class="choose" type="checkbox" checked name="brand" value="${brand.brand_name}"/>
                            <span>${brand.brand_name}</span>
                        </p>
                    </c:forEach>
                </div>
            </div>
            <button class="find" type="submit">Результат</button>
        </form>
        <div class="right-bar">
            <div class="page">
                <c:forEach var="car" items="${requestScope.cars}">
                    <div class="card">
                        <a href=${pageContext.request.contextPath}/car?vin=${car.vin} target="_self">
                            <div class="image">
                                <img class="car-card" src="${pageContext.request.contextPath}/images/car/${car.picture}.jpg"
                                     alt="Car">
                                <img class="hover-car-card" src="${pageContext.request.contextPath}/images/car/${car.picture}.jpg"
                                     alt="Car">
                            </div>
                        </a>
                        <div class="info">
                            <a href="${pageContext.request.contextPath}/car?vin=${car.vin}" target="_self"><div class="price">${car.price}₽</div></a>
                            <a href="${pageContext.request.contextPath}/car?vin=${car.vin}" target="_self"><div>${car.brand.brand_name} ${car.model}</div></a>
                            <a href="${pageContext.request.contextPath}/car?vin=${car.vin}" target="_self">
                                <div>
                                    <span class="one" class="two">${car.date_model} / ${car.mileage}км</span>
                                </div>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
</div>
</body>
</html>
