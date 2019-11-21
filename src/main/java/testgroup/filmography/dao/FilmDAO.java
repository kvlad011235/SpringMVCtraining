package testgroup.filmography.dao;

import testgroup.filmography.model.Film;
import java.util.List;

/**
 *  DAO, отвечает за работу с данными
 *  Смысл в том, чтобы создать специальную прослойку, которая будет отвечать исключительно за доступ к данным (работа с базой данных или другим механизмом хранения).
 *  В пакете dao создадим интерфейс FilmDAO в котором будут такие методы как добавить, удалить и т.д.
 *  Я их назвал несколько иначе, но они соответствуют основным CRUD операциям (Create, Read, Update, Delete).
 */
public interface FilmDAO {
    List<Film> allFilms();
    void add(Film film);
    void delete(Film film);
    void edit(Film film);
    Film getById(int id);
}
