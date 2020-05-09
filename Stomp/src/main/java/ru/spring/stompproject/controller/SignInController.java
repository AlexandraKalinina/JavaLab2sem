package ru.spring.stompproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.spring.stompproject.dto.SignInDto;
import ru.spring.stompproject.dto.SignUpDto;
import ru.spring.stompproject.model.Role;
import ru.spring.stompproject.model.User;
import ru.spring.stompproject.services.SignInService;

import java.util.Optional;

@Controller
public class SignInController {
    @Autowired
    private SignInService signInService;

    @GetMapping("/signIn")
    public String signInCreate() {
        return "signIn";
    }
    @PostMapping("/signIn")
    public String signIn(SignInDto signInDto) {
        Optional<User> user = signInService.signIn(signInDto);
        if (user.isPresent()) {
            if (user.get().getRole().equals(Role.CONSUMER)) {
                return "redirect:/consumer";
            } else return "redirect:/producer";
        } else return "redirect:/signIn";
    }
}
