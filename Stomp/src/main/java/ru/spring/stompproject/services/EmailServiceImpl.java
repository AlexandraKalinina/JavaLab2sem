package ru.spring.stompproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MessageCreate messageCreate;

    @Override
    public void sendEmail(String subject, String email, String userName, String ftl, String fileName) {
        Map<String, Object> data = new HashMap<>();
        data.put("userName", userName);
        data.put("fileName", fileName);
        String html = messageCreate.createMessageFtl(ftl, data);
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
