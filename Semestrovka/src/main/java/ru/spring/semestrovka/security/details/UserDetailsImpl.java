package ru.spring.semestrovka.security.details;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.spring.semestrovka.model.State;
import ru.spring.semestrovka.model.User;

import java.util.Collection;
import java.util.Collections;

@Builder
public class UserDetailsImpl implements UserDetails {
    private Long userId;
    private String role;
    private String name;

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
        this.userId = null;
        this.role = null;
        this.name = null;
    }

    public User getUser() {
        return user;
    }

    public UserDetailsImpl(Long userId, String role, String name) {
        this.userId = userId;
        this.role = role;
        this.name = name;
        this.user = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (user!=null) {
            String authority = user.getRole().toString();
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
            return Collections.singleton(simpleGrantedAuthority);
        } else {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
            return Collections.singletonList(authority);
        }
    }

    @Override
    public String getPassword() {
        if (user != null) {
            return user.getHashPassword();
        } else return null;
    }

    @Override
    public String getUsername() {
        if (user != null) {
            return user.getEmail();
        } else return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if (user != null) {
            return user.getState().equals(State.CONFIRMED);
        } else return true;
    }

    public Long getUserId() {
        if (userId != 0) {
            return userId;
        } else return null;
    }

}
