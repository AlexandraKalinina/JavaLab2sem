package ru.spring.project.hw3.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.util.Map;

public class MessageCreateImpl implements MessageCreate {

    @Override
    public String createMessageFtl(String name, Map<String, Object> root, Configuration cfg) {
        try {
            Template t = cfg.getTemplate(name);
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, root);
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }
    }
}
