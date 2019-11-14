package testgroup.filmography.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import testgroup.filmography.model.Film;
import testgroup.filmography.service.FilmService;
import testgroup.filmography.service.FilmServiceImpl;

import java.util.List;

/**
 * Аннотация @Controller сообщает Spring MVC, что данный класс является контроллером, диспетчер будет проверять аннотации @RequestMapping чтобы вызвать подходящий метод.
 * В этом классе создадим методы, которые будут возвращать наши представления в ответ на запросы
 * Аннотация @RequestMapping позволяет задать адреса методам контроллера, по которым они будут доступны в клиенте (браузер)
 *
 * Добавим сервис FilmService и будем вызывать его методы для каждого случая, добавить, удалить и т.д
 */
@Controller
public class FilmController {

    private FilmService filmService = new FilmServiceImpl();
/*
метод для отображения главной страницы со списком фильмов.
Получаем список фильмов из сервиса и добавляем его в модель
 */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView allFilms() {
        List<Film> films = filmService.allFilms();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        modelAndView.addObject("filmsList", films);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

/*
метод, который будет возвращать страницу редактирования конкретного фильма
аннотация @PathVariable указывает на то, что данный параметр (int id) получается из адресной строки.
Чтобы указать место этого параметра в адресной строке используется конструкция {id}
 */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Film film = filmService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("film", film);
        return modelAndView;
    }
/*
 метод editPage(@PathVariable("id") int id) это для получения страницы редактирования,
 теперь нужен метод для самого редактирования.

 с помощью аннотации @ModelAttribute мы получаем этот атрибут и можем его изменить.
 Метод запроса POST потому что здесь мы будем передавать данные.
 "redirect:/" означает, что после выполнения данного метода мы будем перенаправлены на адрес "/", т.е.
 запустится метод allFilms и мы вернемся на главную страницу.


 */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        filmService.edit(film);
        return modelAndView;
    }
/*
Теперь займемся добавлением новых фильмов в список.
Для этого также понадобится форма для ввода и отправки данных.
Можно сделать форму на главной странице или можно сделать отдельную страницу, наподобие editPage.jsp.
Но, с другой стороны, форма для добавления ведь будет точно такая же, как и для редактирования, т.е. 4 поля для ввода и кнопка отправки.
Так зачем тогда создавать новую страницу, снова используем editPage.jsp.
Таким образом в методе editPage мы дополнительно передавали атрибут, чтобы потом его изменить,
а тут мы просто получаем страницу и это метод для получения страницы
 */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }
/*
метод для добавления.
Поскольку атрибут мы сюда не передавали, здесь будет создан новый объект Film.
Стоит также обратить внимание, что у нас оба метода доступны по адресу "/add".
Это возможно благодаря тому, что они реагируют на разные типы запроса.
Переходя по ссылке на главной странице мы делаем GET-запрос, что приводит нас в метод addPage.
А когда на странице добавления мы жмем кнопку отправки данных, делается POST-запрос, за это уже отвечает метод addFilm.
 */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        filmService.add(film);
        return modelAndView;
    }
/*
метод контроллера для удаления фильма из списка
 */
    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFilm(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        Film film = filmService.getById(id);
        filmService.delete(film);
        return modelAndView;
    }

}
