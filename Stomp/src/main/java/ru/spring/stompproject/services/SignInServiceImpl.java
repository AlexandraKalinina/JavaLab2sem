package ru.spring.stompproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.spring.stompproject.dto.SignInDto;
import ru.spring.stompproject.model.User;
import ru.spring.stompproject.repositories.UserRepositories;

import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserRepositories userRepositories;

    @Autowired
    private UserService userService;

    @Override
    public Optional<User> signIn(SignInDto signInDto) {
        String login = signInDto.getEmail();
        Optional<User> user = userRepositories.findByEmail(login);
        return user;
    }
}
