package steamworks.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import steamworks.msgmanage.entity.MsgManage;
import steamworks.msgmanage.repository.MsgManageRepository;

import java.text.MessageFormat;
import java.util.Locale;

@Configuration
public class DatabaseMessageSource extends ReloadableResourceBundleMessageSource {
    @Autowired
    private MsgManageRepository msgManageRepository;

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        MsgManage msgManage = msgManageRepository.findByMsgCd(code);

        MessageFormat format = null;

        if (msgManage != null) {

            String language = locale.getLanguage();
            if ("en".equalsIgnoreCase(language)) {
                format = new MessageFormat(msgManage.getMsgEn(), locale);
            } else if ("jp".equalsIgnoreCase(language)) {
                format = new MessageFormat(msgManage.getMsgJp(), locale);
            } else if ("cn".equalsIgnoreCase(language)) {
                format = new MessageFormat(msgManage.getMsgCn(), locale);
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
        MsgManage msgManage = msgManageRepository.findByMsgCd(code);

        String format = null;

        if (msgManage != null) {

            String language = locale.getLanguage();
            if ("en".equalsIgnoreCase(language)) {
                format = msgManage.getMsgEn();
            } else if ("jp".equalsIgnoreCase(language)) {
                format = msgManage.getMsgJp();
            } else if ("cn".equalsIgnoreCase(language)) {
                format = msgManage.getMsgCn();
            } else {
                format = super.resolveCodeWithoutArguments(code, locale);
            }

        } else {
            format = super.resolveCodeWithoutArguments(code, locale);
        }

        return format;
    }


}


