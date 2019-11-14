package testgroup.filmography.service;

import testgroup.filmography.model.Film;
import java.util.List;
/**
 Теперь добавим сервисный слой.
 В принципе в данном примере вполне можно обойтись и без него, ограничившись DAO, приложение будет очень простое и какой-то сложной логики в сервисе не планируется.
 Но вдруг потом в будущем захочется добавить в проект всяких сложностей и интересностей, поэтому для полноты картины все-таки пусть будет.
 Пока же в нем просто будут вызываться методы из DAO.
 В пакете service создадим интерфейс FilmService.
 */
public interface FilmService {
    List<Film> allFilms();
    void add(Film film);
    void delete(Film film);
    void edit(Film film);
    Film getById(int id);
}
