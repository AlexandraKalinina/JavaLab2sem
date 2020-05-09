package ru.spring.stompproject.services;


import ru.spring.stompproject.dto.SignInDto;
import ru.spring.stompproject.model.User;

import java.util.Optional;

public interface SignInService {
    Optional<User> signIn(SignInDto signInDto);
}
