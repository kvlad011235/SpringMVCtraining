package testgroup.filmography.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testgroup.filmography.dao.FilmDAO;
import testgroup.filmography.dao.FilmDAOImpl;
import testgroup.filmography.model.Film;

import java.util.List;

/**
 * реализация FilmService
 *
 *
 * Дело в том, что сейчас наши хранилище и сервис это просто классы, и чтобы их использовать приходится самим создавать объект класса (new FilmServiceImpl()).
 * Но у нас ведь не просто так подключен Spring, так пусть он сам это дело и контролирует.
 * Чтобы отдать наши классы под управление Spring'а, нужно обозначить, что они являются компонентами.
 * Для этого отметим их специальными аннотациями: @Service
 * Аннотации @Repository и @Service, так же как и @Controller являются производными от @Component
 * эти аннотации сообщают Spring о том, что данные классы являются репозиторием и сервисом соответственно.
 */
@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmDAO filmDAO;

    @Override
    @Transactional //укажет на то, что метод должен выполняться в транзакции (без этого Hibernate работать откажется)
    public List<Film> allFilms() {
        return filmDAO.allFilms();
    }

    @Override
    @Transactional
    public void add(Film film) {
        filmDAO.add(film);
    }

    @Override
    @Transactional
    public void delete(Film film) {
        filmDAO.delete(film);
    }

    @Override
    @Transactional
    public void edit(Film film) {
        filmDAO.edit(film);
    }

    @Override
    @Transactional
    public Film getById(int id) {
        return filmDAO.getById(id);
    }
}
