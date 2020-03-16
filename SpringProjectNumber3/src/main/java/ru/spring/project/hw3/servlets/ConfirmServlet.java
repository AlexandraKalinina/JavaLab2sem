package ru.spring.project.hw3.servlets;

import org.springframework.context.ApplicationContext;
import ru.spring.project.hw3.helpers.Helpers;
import ru.spring.project.hw3.service.ConfirmService;
import ru.spring.project.hw3.service.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/confirm/*")
public class ConfirmServlet extends HttpServlet {

    private ConfirmService confirmService;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        confirmService = springContext.getBean(ConfirmService.class);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException  {
        String s = req.getPathInfo();
        String[] data = s.split("/");
        confirmService.confirm(data[1]);
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        Helpers.render(req, resp, "confirm.ftl", root);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException  {

    }
}
