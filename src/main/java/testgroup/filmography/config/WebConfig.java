package testgroup.filmography.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Перейдем к настройке конфигурации.

 * В нем будет только один метод возвращающий объект типа ViewResolver, он необходим для нахождения представления по имени.
 *
 * @Configuration сообщает Spring что данный класс является конфигурационным и содержит определения и зависимости bean-компонентов.
 * Бины (bean) — это объекты, которые управляются Spring'ом. Для определения бина используется аннотация @Bean.
 *
 * @EnableWebMvc позволяет импортировать конфигурацию Spring MVC из класса WebMvcConfigurationSupport
 *
 *@ComponentScan сообщает Spring где искать компоненты, которыми он должен управлять, т.е. классы, помеченные аннотацией @Component или ее производными, такими как @Controller, @Repository, @Service. Эти аннотации автоматически определяют бин класса.
 *
 * В методе viewResolver() мы создаем его реализацию и определяем где именно искать представления в webapp. Поэтому когда в методе контроллера мы устанавливали имя "films" представление найдется как "/pages/films.jsp"
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "testgroup.filmography")
public class WebConfig {
    @Bean
    ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/pages/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setPrefix("/WEB-INF/pages/");
        return viewResolver;
    }
}
