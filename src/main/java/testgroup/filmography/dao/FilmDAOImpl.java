package testgroup.filmography.dao;

import org.springframework.stereotype.Repository;
import testgroup.filmography.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Теперь нам нужна реализация FilmDAO.
 * Подключать базу данных пока не будем, все еще страшновато.
 * Чтобы потренироваться и попривыкнуть для начала имитируем хранилище в памяти, создадим список с несколькими фильмами.
 * Для хранения списка будем использовать не List, а Map, чтобы было удобно получать конкретный фильм по его id, не перебирая для этого весь список.
 * Для генерации id используем AtomicInteger.
 * Создадим класс FilmDAOImpl, реализуем все методы и заполним мапу.
 * Что-то вроде этого.
 *
 * Дело в том, что сейчас наши хранилище и сервис это просто классы, и чтобы их использовать приходится самим создавать объект класса (new FilmServiceImpl()).
 *  * Но у нас ведь не просто так подключен Spring, так пусть он сам это дело и контролирует.
 *  * Чтобы отдать наши классы под управление Spring'а, нужно обозначить, что они являются компонентами.
 *  * Для этого отметим их специальными аннотациями: @Repository
 *Аннотации @Repository и @Service, так же как и @Controller являются производными от @Component.
 * эти аннотации сообщают Spring о том, что данные классы являются репозиторием и сервисом соответственно.
 *
 */
@Repository
public class FilmDAOImpl implements FilmDAO {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(0);
    private static Map<Integer, Film> films = new HashMap<>();

    static {
        Film film1 = new Film();
        film1.setId(AUTO_ID.getAndIncrement());
        film1.setTitle("Inception");
        film1.setYear(2010);
        film1.setGenre("sci-fi");
        film1.setWatched(true);
        films.put(film1.getId(), film1);

        // + film2, film3, film4, ...
        Film film2 = new Film();
        film2.setId(AUTO_ID.getAndIncrement());
        film2.setTitle("Rock");
        film2.setYear(2000);
        film2.setGenre("action");
        film2.setWatched(true);
        films.put(film2.getId(),film2);

        Film film3 = new Film();
        film3.setId(AUTO_ID.getAndIncrement());
        film3.setTitle("Devil's Advocate");
        film3.setYear(2001);
        film3.setGenre("horror");
        film3.setWatched(true);
        films.put(film3.getId(),film3);
    }

    @Override
    public List<Film> allFilms() {
        return new ArrayList<>(films.values());
    }

    @Override
    public void add(Film film) {
        film.setId(AUTO_ID.getAndIncrement());
        films.put(film.getId(), film);
    }

    @Override
    public void delete(Film film) {
        films.remove(film.getId());
    }

    @Override
    public void edit(Film film) {
        films.put(film.getId(), film);
    }

    @Override
    public Film getById(int id) {
        return films.get(id);
    }
}
