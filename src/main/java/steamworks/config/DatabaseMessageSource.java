package steamworks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import steamworks.commoncode.entity.MsgBundle;
import steamworks.commoncode.repository.MsgBundleRepository;

import java.text.MessageFormat;
import java.util.Locale;

@Configuration
public class DatabaseMessageSource extends ReloadableResourceBundleMessageSource {
    @Autowired
    private MsgBundleRepository msgBundleRepository;

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        MsgBundle msgBundle = msgBundleRepository.findByMsgCd(code);

        MessageFormat format = null;

        if (msgBundle != null) {

            String language = locale.getLanguage();
            if ("en".equalsIgnoreCase(language)) {
                format = new MessageFormat(msgBundle.getMsgEn(), locale);
            } else if ("jp".equalsIgnoreCase(language)) {
                format = new MessageFormat(msgBundle.getMsgJp(), locale);
            } else if ("cn".equalsIgnoreCase(language)) {
                format = new MessageFormat(msgBundle.getMsgCn(), locale);
            } else {
                format = super.resolveCode(code, locale);
            }

        } else {
            format = super.resolveCode(code, locale);
        }

        return format;
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        MsgBundle msgBundle = msgBundleRepository.findByMsgCd(code);

        String format = null;

        if (msgBundle != null) {

            String language = locale.getLanguage();
            if ("en".equalsIgnoreCase(language)) {
                format = msgBundle.getMsgEn();
            } else if ("jp".equalsIgnoreCase(language)) {
                format = msgBundle.getMsgJp();
            } else if ("cn".equalsIgnoreCase(language)) {
                format = msgBundle.getMsgCn();
            } else {
                format = super.resolveCodeWithoutArguments(code, locale);
            }

        } else {
            format = super.resolveCodeWithoutArguments(code, locale);
        }

        return format;
    }


}


