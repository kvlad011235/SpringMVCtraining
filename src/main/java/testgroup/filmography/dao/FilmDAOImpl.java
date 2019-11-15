package testgroup.filmography.dao;

import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.filmography.model.Film;

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

    /*
    Пришло время заняться наконец нашим DAO.
    Переходим в класс FilmDAOImpl и первым делом удаляем оттуда пробный список, он нам больше не нужен.
    Добавляем фабрику сессий и будем работать через нее
     */
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /*
     изменяем метод для отображения страницы со списком фильмов, в нем
      мы будем получать сессию и делать запрос к бд (вытаскивать все записи и формировать список):
     */

    @Override
    @SuppressWarnings("unchecked")
    public List<Film> allFilms() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Film").list();
    }

    @Override
    public void add(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(film);
    }

    @Override
    public void delete(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(film);
    }

    @Override
    public void edit(Film film) {
        Session session = sessionFactory.getCurrentSession();
        session.update(film);
    }

    @Override
    public Film getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Film.class, id);
    }
}
