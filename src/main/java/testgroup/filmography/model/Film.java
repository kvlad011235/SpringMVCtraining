package testgroup.filmography.model;
/**
У нас уже есть представления и контроллер, но в MVC есть еще и 3-я буква, поэтому для полноты картины добавим еще и модель.
 В пакете model создадим класс Film ну, например, c такими полями: int id, String title (название),
 int year (год выхода), String genre (жанр) и
 boolean watched (т.е. смотрели уже этот фильм или нет).

 Это самый обыкновенный класс, приватные поля, геттеры и сеттеры.
 Объекты таких классов еще называют POJO (Plain Old Java Object), ну т.е. "простой джава объект".
 */
public class Film {
    private int id;
    private String title;
    private int year;
    private String genre;
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
