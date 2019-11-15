package testgroup.filmography.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;

/**
 *  класс конфигурации у нас есть WebConfig, но пока что это просто какой-то отдельный класс, он сам по себе и на наше приложение никак не влияет.
 *  Нам нужно зарегистрировать эту конфигурацию в контексте Spring.
 *  Для этого нужен класс AbstractAnnotationConfigDispatcherServletInitializer.
 *  В пакете config создаем его наследника, допустим AppInitializer, и реализуем его методы
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{HibernateConfig.class}; // новая конфигурация готова, осталось добавить ее в наш AppInitializer
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
/*
Возможно будут проблемы с кодировкой, когда при отправке с формы значений с русскими символами в результате будут получаться каракули.
Для решения этой проблемы добавим фильтр, который будет заниматься предварительной обработкой запросов
 */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] {characterEncodingFilter};
    }

}
