package ru.spring.semestrovka.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier(value = "customUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private AuthenticationProvider authenticationProvider;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
        //auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        //когда пользователь вводит логин и пароль, спринг ищет через userDetailsService
    }

    /*@Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/signIn");
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       /* http.authorizeRequests().antMatchers("/registration").permitAll() // все могут просматривать эту страничку
                .antMatchers("/profile").authenticated() //просмотр этой странички только аутенфицированные пользователи
                .antMatchers("/confirm/*").permitAll()
                .antMatchers("/files").authenticated()
                .antMatchers("/files/file-name:.+").authenticated();
        http.formLogin()
                .loginPage("/signIn")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/profile")
                //если все хорошо, то переходим в корень
                .failureUrl("/signIn?error") // если нет, то ошибка
                .permitAll(); //разрешено всем*/
    }
}
