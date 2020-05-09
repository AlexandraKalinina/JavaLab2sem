package ru.spring.stompproject.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

@Service
public class MessageCreateImpl implements MessageCreate {

    @Autowired
    private FreeMarkerConfigurer cfg;

    @Override
    public String createMessageFtl(String name, Map<String, Object> root) {
        try {
            Template t = cfg.getConfiguration().getTemplate(name);
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, root);
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }
    }
}
