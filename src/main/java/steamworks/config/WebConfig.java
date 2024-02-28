package steamworks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class WebConfig {

    @Autowired
    private DatabaseMessageSource databaseMessageSource;
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
//        Locale.setDefault(Locale.KOREAN); // 제공하지 않는 언어로 들어왔을 때 처리

        databaseMessageSource.setDefaultEncoding(Encoding.DEFAULT_CHARSET.toString());
//        databaseMessageSource.setDefaultLocale(Locale.getDefault());
        databaseMessageSource.setCacheSeconds(600);

        return databaseMessageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor (
            @Autowired ReloadableResourceBundleMessageSource messageSource) {
        return new MessageSourceAccessor(messageSource);
    }
    @Bean
    public LocaleResolver defaultLocaleResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREAN);

        return localeResolver;
    }
}
