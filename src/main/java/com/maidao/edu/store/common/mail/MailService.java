package com.maidao.edu.store.common.mail;

import com.maidao.edu.store.common.util.L;
import com.sunnysuperman.commons.config.PropertiesConfig;
import com.sunnysuperman.commons.util.JSONUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;

@Service
public class MailService implements IMailService {
    @Value("${mailservice}")
    private String mailConfig;
    private MailHelper.MailConnectionInfo connectionInfo = new MailHelper.MailConnectionInfo();
    private String from = null;

    @PostConstruct
    public void init() {
        L.warn("Mail config: " + mailConfig);
        PropertiesConfig config = new PropertiesConfig(JSONUtil.parseJSONObject(mailConfig));
        connectionInfo.setHost(config.getString("host"));
        connectionInfo.setPort(config.getInt("port", 25));
        connectionInfo.setUsername(config.getString("username"));
        connectionInfo.setPassword(config.getString("password"));
        from = config.getString("from", connectionInfo.getFrom());
    }

    @Override
    public boolean send(MailHelper.MailInfo mail) {
        mail.setFromAddress(from);
        try {
            MailHelper.sendMail(connectionInfo, mail);
            if (L.isInfoEnabled()) {
                L.info("Mail has been delivered to: " + mail.getToAddress());
            }
            return true;
        } catch (MessagingException e) {
            L.error(e);
            return false;
        }
    }
}