package ru.spring.stompproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProducerController {
    @GetMapping("/producer")
    public String producer() {
        return "producer";
    }
}
