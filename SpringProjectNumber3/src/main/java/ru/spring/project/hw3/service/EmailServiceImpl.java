package ru.spring.project.hw3.service;
import freemarker.template.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import ru.spring.project.hw3.config.ConfigEmail;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class EmailServiceImpl implements EmailService {

    public EmailServiceImpl(ConfigEmail configEmail) {
        this.configEmail = configEmail;
    }

    public EmailServiceImpl(MessageCreate messageCreate) {
        this.messageCreate = messageCreate;
    }

    private JavaMailSender javaMailSender;

    private ConfigEmail configEmail;

    private MessageCreate messageCreate;

    @Override
    public void sendEmail(String subject, String email, String confirmCode, String userName, Configuration cfg) {
        javaMailSender = configEmail.getJavaMailSender();
        Map<String, Object> data = new HashMap<>();
        data.put("userName", userName);
        data.put("confirmCode", confirmCode);
        String html = messageCreate.createMessageFtl("email_template.ftl", data, cfg);
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            messageHelper.setFrom("friend");
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);
            messageHelper.setText(html, true);
        };

        javaMailSender.send(messagePreparator);

    }
}
