package ru.spring.project.hw3.servlets;

import freemarker.template.Configuration;
import org.springframework.context.ApplicationContext;
import ru.spring.project.hw3.dto.SignUpDto;
import ru.spring.project.hw3.helpers.FreeMarkerConfiguration;
import ru.spring.project.hw3.helpers.Helpers;
import ru.spring.project.hw3.service.MessageCreate;
import ru.spring.project.hw3.service.MessageCreateImpl;
import ru.spring.project.hw3.service.SignUpService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private SignUpService signUpService;

    private MessageCreateImpl messageCreate;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext context = config.getServletContext();
        ApplicationContext springContext = (ApplicationContext) context.getAttribute("springContext");
        signUpService = springContext.getBean(SignUpService.class);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        Map<String, Object> root = new HashMap<>();
        Helpers.render(req, resp, "registration.ftl", root);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        Configuration cfg = Helpers.getConfig(req);
        SignUpDto sup = new SignUpDto();
        sup.setName(name);
        sup.setEmail(email);
        sup.setPassword(password);
        signUpService.signUp(sup, cfg);
        session.setAttribute("user", sup);
        resp.sendRedirect("/registration");
    }
}
