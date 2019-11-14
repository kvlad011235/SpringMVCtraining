<!--тут подключается JSTL core, которая включает основные теги создания циклов, условий и т.д-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--Теперь сделаем главную страницу, films.jsp, которую возвращает метод allFilms() из контроллера-->
<html>
    <head>
        <title>FILMS</title>
    </head>
    <body>

    <h2>Films</h2>
    <!--<table> — тег для создания таблицы
    делаем строку-шапку таблицы, с названиями столбцов-->
    <table>
        <!--<tr> — строка таблицы-->
        <tr>
            <!--<th> — заголовок столбца-->
            <th>id</th>
            <th>title</th>
            <th>year</th>
            <th>genre</th>
            <th>watched</th>
            <th>action</th>
        </tr>

        <!--Затем в цикле (который мы взяли из JSTL core) пробегаемся по
        всем элементам переданного списка (filmsList), для каждого элемента (film) создаем новую строку и в
        каждую ячейку записываем соответствующее значение.
        Тут есть один момент, запись вроде film.id нужно понимать как film.getId(), т.е. не напрямую к полю обращение, а
        именно геттер вызывается. В последнем столбце (action) делаем ссылки для удаления и
         редактирования. Ну и внизу ссылка на метод добавления нового фильма.-->

        <c:forEach var="film" items="${filmsList}">
            <tr>
                <!--<td> — ячейка таблицы-->
                <td>${film.id}</td>
                <td>${film.title}</td>
                <td>${film.year}</td>
                <td>${film.genre}</td>
                <td>${film.watched}</td>
                <td>
                    <!--тут мы сделали ссылки для каждого фильма с указанием id.
                    Затем это значение присваивается параметру метода editPage(@PathVariable("id") int id) и далее по
                    нему мы через сервис из репозитория получаем конкретный фильм и добавляем его в модель.
                    -->
                    <a href="/edit/${film.id}">edit</a>
                    <!--
                    тут сслылка на метод deleteFilm(@PathVariable("id") int id)
                    -->
                    <a href="/delete/${film.id}">delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h2>Add</h2>
    <c:url value="/add" var="add"/>
    <a href="${add}">Add new film</a>
    </body>
</html>