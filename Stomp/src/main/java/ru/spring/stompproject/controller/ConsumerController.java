package ru.spring.stompproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsumerController {
    @GetMapping("/consumer")
    public String producer() {
        return "consumer";
    }
}
