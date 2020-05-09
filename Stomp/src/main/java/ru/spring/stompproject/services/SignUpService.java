package ru.spring.stompproject.services;

import ru.spring.stompproject.dto.SignUpDto;

public interface SignUpService {
    boolean signUp(SignUpDto form);
}
