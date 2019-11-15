package testgroup.filmography.model;

import javax.persistence.*;

/**
 * 1.
У нас уже есть представления и контроллер, но в MVC есть еще и 3-я буква, поэтому для полноты картины добавим еще и модель.
 В пакете model создадим класс Film ну, например, c такими полями: int id, String title (название),
 int year (год выхода), String genre (жанр) и
 boolean watched (т.е. смотрели уже этот фильм или нет).

 Это самый обыкновенный класс, приватные поля, геттеры и сеттеры.
 Объекты таких классов еще называют POJO (Plain Old Java Object), ну т.е. "простой джава объект".

 2.
 Итак, мы хотим, чтобы объекты класса Film могли быть сохранены в базе данных.
 Для этого класс должен удовлетворять ряду условий.
 В JPA для этого есть такое понятие как Сущность (Entity).
 Класс-сущность это обыкновенный POJO класс, с приватными полями и геттерами и сеттерами для них.
 У него обязательно должен быть не приватный конструктор без параметров (или конструктор по-умолчанию), и он должен иметь первичный
 ключ, т.е. то что будет однозначно идентифицировать каждую запись этого класса в БД.
 Сделаем наш класс Film сущностью при помощи JPA аннотаций:
 */
@Entity //указывает на то, что данный класс является сущностью.
@Table(name = "films") //указывает на конкретную таблицу для отображения этой сущности.
public class Film {
    @Id //указывает, что данное поле является первичным ключом, т.е. это свойство будет использоваться для идентификации каждой уникальной записи.
    @Column(name="id") //связывает поле со столбцом таблицы. Если имена поля и столбца таблицы совпадают, можно не указывать.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //свойство будет генерироваться автоматически, в скобках можно указать каким образом. Не будем сейчас разбираться как именно работают разные стратегии. Достаточно знать, что в данном случае каждое новое значение будет увеличиваться на 1 от предыдущего.
    private int id;

    @Column(name="title")
    private String title;

    @Column(name = "year")
    private int year;

    @Column(name = "genre")
    private String genre;

    @Column(name = "watched")
    private boolean watched;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

/*
для понятного вывода объекта у класса Film был переопределен toString()
 */
    @Override
    public String toString() {
        return title +" "+ year +" "+ genre +" "+watched;
    }
}
