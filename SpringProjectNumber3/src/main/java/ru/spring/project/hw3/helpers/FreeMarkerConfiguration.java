package ru.spring.project.hw3.helpers;


import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.ServletContext;

public class FreeMarkerConfiguration {
    public static FreeMarkerConfiguration instance;

    public FreeMarkerConfiguration(ServletContext servletContext) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setServletContextForTemplateLoading(servletContext, "/WEB-INF/templates/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        servletContext.setAttribute("cfg", cfg);
    }

    public static synchronized void getInstance(ServletContext servletContext) {
        if (instance == null) {
            instance = new FreeMarkerConfiguration(servletContext);
        }
    }
}
