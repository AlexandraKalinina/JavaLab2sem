package ru.spring.stompproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.spring.stompproject.dto.SignUpDto;
import ru.spring.stompproject.services.SignUpService;

import java.nio.file.AccessDeniedException;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @GetMapping("/registration")
    public String registrationCreate() {
        return "signUp";
    }

    @PostMapping("/registration")
    public String registration(SignUpDto signUpDto) throws AccessDeniedException {
        if (signUpDto.getRole().equals("PRODUCER") || signUpDto.getRole().equals("CONSUMER")) {
            if (signUpService.signUp(signUpDto)) {
                return "redirect:/signIn";
            } else return "redirect:/registration";
        } else throw new AccessDeniedException("Wrong role");

    }
}
