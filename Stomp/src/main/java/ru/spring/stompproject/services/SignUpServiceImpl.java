package ru.spring.stompproject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.spring.stompproject.dto.SignUpDto;
import ru.spring.stompproject.model.Role;
import ru.spring.stompproject.model.User;
import ru.spring.stompproject.repositories.UserRepositories;

import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService {


    @Autowired
    private UserRepositories userRepositories;

    @Override
    public boolean signUp(SignUpDto form) {
        User current_user = User.builder()
                .name(form.getName())
                .email(form.getEmail())
                .password(form.getPassword())
                .role(Role.valueOf(form.getRole()))
                .build();
        Optional<User> user = userRepositories.findByEmail(form.getEmail());
        if (!user.isPresent()) {
            userRepositories.save(current_user);
            return true;
        }
        return false;
    }
}
