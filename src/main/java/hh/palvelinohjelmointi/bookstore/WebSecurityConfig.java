package hh.palvelinohjelmointi.bookstore;

import hh.palvelinohjelmointi.bookstore.web.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/css/**").permitAll() // enable css
                .and()
                .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/booklist")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    // tehtävä 6.2 ratkaisu
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder.userDetailsService(userDetailService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }


    /* tehtävän 6.1 in-memory käyttäjät
    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        List<UserDetails> users = new ArrayList<>();

        UserDetails user = User.withDefaultPasswordEncoder()
                .username("timo")
                .password("basket")
                .roles("USER")
                .build();

        users.add(user);

        user = User.withDefaultPasswordEncoder()
                .username("ithemmo")
                .password("arvaa")
                .roles("USER", "ADMIN")
                .build();

        users.add(user);

        return new InMemoryUserDetailsManager(users);

    }
    */
}

