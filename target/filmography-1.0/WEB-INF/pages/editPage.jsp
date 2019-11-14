<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!--
<form> — форма для сбора и отправки данных, с указанием кто их будет обрабатывать (/edit)
<input> — элементы интерфейса для взаимодействия с пользователем (кнопки, поля ввода и т.д.)
<label> — текстовая метка
при нажатии кнопки <input type="submit" value="Edit film"> данные из формы будут отправлены на
сервер (специально добавлено невидимое поле
со значением id, чтобы сервер знал какую именно запись в БД нужно обновить).
В методе editFilm(@ModelAttribute("film") Film film) они будут присвоены соответствующим полям атрибута film.
Затем мы вернемся на главную страницу с обновленным списком.
-->
    <html>
        <!--
        Для добавления нового фильма мы решили использовать ту же страницу, что и для редактирования.
        Но там ведь данные отправляются на адрес "/edit"
        Нам нужно немного подправить страницу, чтобы она вела себя по-разному для добавления и редактирования.
        Для решения этого вопроса воспользуемся условиями из все той же библиотеки JSTL core.
        Т.е. мы просто проверяем поле film.title.
        Если оно пустое, значит это новый фильм, мы должны заполнить для него все данные и добавить в список.
        Если это поле не пустое, значит это фильм из списка и его нужно просто изменить
        -->
        <head>
            <c:if test="${empty film.title}">
                <title>Add</title>
            </c:if>
            <c:if test="${!empty film.title}">
                <title>Edit</title>
            </c:if>
        </head>

            <body>
            <c:if test="${empty film.title}">
                <c:url value="/add" var="var"/>
            </c:if>
            <c:if test="${!empty film.title}">
                <c:url value="/edit" var="var"/>
            </c:if>
                <form action="${var}" method="POST">
                    <c:if test="${!empty film.title}">
                        <input type="hidden" name="id" value="${film.id}">
                    </c:if>
                    <label for="title">Title</label>
                    <input type="text" name="title" id="title">
                    <label for="year">Year</label>
                    <input type="text" name="year" id="year">
                    <label for="genre">Genre</label>
                    <input type="text" name="genre" id="genre">
                    <label for="watched">Watched</label>
                    <input type="text" name="watched" id="watched">
                    <c:if test="${empty film.title}">
                        <input type="submit" value="Add new film">
                    </c:if>
                    <c:if test="${!empty film.title}">
                        <input type="submit" value="Edit film">
                    </c:if>
                </form>
            </body>

    </html>