<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 13.10.2024
  Time: 4:45
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Редактор</title>
  <link rel="stylesheet" href="css/pages/person/leftbar.css">
  <link rel="stylesheet" href="css/navbar.css">
  <link rel="stylesheet" href="css/pages/page.css">
  <link rel="stylesheet" href="css/pages/person/redactor/rightbar.css">
</head>
<body>
<form method="post">
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
    <div class="name">Редактирование профиля</div>
  </div>
  <section class="page">
      <div class="left-bar">
        <div class="person">
          <label>Владелец</label>
          <div class="card">
            <div class="info">
              <label class="car">Username:</label>
            </div>
            <input class="info-text" type="text" name="surname" placeholder="Логин" autocomplete="off"/>
          </div>
          <div class="card">
            <div class="info">
              <label class="car">Имя:</label>
            </div>
            <input class="info-text" type="text" name="first_name" placeholder="Имя" autocomplete="off"/>
          </div>
          <div class="card">
            <div class="info">
              <label class="car">Фамилия:</label>
            </div>
            <input class="info-text" type="text" name="last_name" placeholder="Фамилия" autocomplete="off"/>
          </div>
          <div class="card">
            <div class="info">
              <label class="car">Почта:</label>
            </div>
            <input class="info-text" type="email" name="email" placeholder="Адресс почты" autocomplete="off"/>
          </div>
          <div class="card">
            <div class="info">
              <label class="car">Номер телефона:</label>
            </div>
            <input class="info-text" type="tel" name="phone" placeholder="Номер телефона" autocomplete="off"/>
          </div>
          <div class="card">
            <div class="info">
              <label class="car">Страна:</label>
            </div>
            <input class="info-text" type="text" name="country" placeholder="Страна проживания" autocomplete="off"/>
          </div>
          <div class="card">
            <div class="info">
              <label class="car">Город:</label>
            </div>
            <input class="info-text" type="text" name="town" placeholder="Город проживания" autocomplete="off"/>
          </div>
          <div class="card">
            <div class="info">
              <label class="car">Пароль:</label>
            </div>
            <p><input class="info-text" type="text" name="password" placeholder="Введите пароль" autocomplete="off"/></p>
            <p><input class="info-text" type="text" name="confirmPassword" placeholder="Повторите пароль" autocomplete="off"/></p>
          </div>
          <div class="card">
            <div class="info">
              <label class="car">Изменить данные:</label>
            </div>
            <a><button class="find" type="submit">Сохранить</button></a>
          </div>
        </div>
      </div>
      <div class="right-bar">
        <div class="image">
          <img class="profile-card" src=""
               alt="Image">
          <label>Изображение</label>
          <input class="info-text" type="file" name="picture" />
        </div>
        <div class="car-info">
          <label>Информация о профиле</label>
          <textarea name="user_info" class="info-text-profile"></textarea>
        </div>
      </div>
  </section>
</form>
<c:if test="${not empty requestScope.errors}">
  <div style="color: darkred">
    <c:forEach var="error" items="${requestScope.errors}">
      <p>${error.code} ${error.message}</p>
    </c:forEach>
  </div>
</c:if>
</body>
</html>