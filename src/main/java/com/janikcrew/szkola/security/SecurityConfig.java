package com.janikcrew.szkola.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "select email, haslo, active from osoba where email=?"
        );
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "select email, rola from osoba where email=?"
        );
        return jdbcUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/home").hasAnyRole("ADMIN", "NAUCZYCIEL", "RODZIC", "UCZEN")
                        .requestMatchers("/addEvent").hasAnyRole("ADMIN", "NAUCZYCIEL")
                        .requestMatchers("/saveEvent").hasAnyRole("ADMIN", "NAUCZYCIEL")
                        .requestMatchers("/updateEvent").hasAnyRole("ADMIN", "NAUCZYCIEL")
                        .requestMatchers("/deleteEvent").hasAnyRole("ADMIN", "NAUCZYCIEL")
                        .requestMatchers("/addNote").hasAnyRole("ADMIN", "NAUCZYCIEL")
                        .requestMatchers("/addNote2").hasAnyRole("ADMIN", "NAUCZYCIEL")
                        .requestMatchers("/showInsertedNotes").hasAnyRole("ADMIN", "NAUCZYCIEL")
                        .requestMatchers("/deleteNote").hasAnyRole("ADMIN", "NAUCZYCIEL")
                        .requestMatchers("/updateNote").hasAnyRole("ADMIN", "NAUCZYCIEL")
                        .requestMatchers("/saveUpdatedNote").hasAnyRole("ADMIN", "NAUCZYCIEL")
                        .requestMatchers("/saveNote").hasAnyRole("ADMIN", "NAUCZYCIEL")
                        .requestMatchers("/showNotes").hasAnyRole("RODZIC", "UCZEN")
                        .requestMatchers("/showGrades").hasAnyRole("RODZIC", "UCZEN")
                        .requestMatchers("/showGrades2").hasAnyRole("RODZIC", "UCZEN")
                        .requestMatchers("/showInsertedGrades").hasAnyRole("ADMIN", "NAUCZYCIEL")
                        .requestMatchers("/showInsertedGradesTeacher").hasRole("NAUCZYCIEL")
                        .requestMatchers("/showInsertedGradesTeacher2").hasRole("NAUCZYCIEL")
                        .requestMatchers("/showInsertedGradesAdmin").hasRole("ADMIN")
                        .requestMatchers("/showInsertedGradesAdmin2").hasRole("ADMIN")
                        .requestMatchers("/showStudentGradesTeacher").hasRole("NAUCZYCIEL")
                        .requestMatchers("/showStudentGradesAdmin").hasRole("ADMIN")
                        .requestMatchers("/addGrade").hasAnyRole("ADMIN", "NAUCZYCIEL")
                        .requestMatchers("/updateGrade").hasAnyRole("ADMIN", "NAUCZYCIEL")
                        .requestMatchers("/updateGrade1").hasAnyRole("ADMIN", "NAUCZYCIEL")
                        .requestMatchers("/deleteGrade").hasAnyRole("ADMIN", "NAUCZYCIEL").anyRequest().authenticated()).
                formLogin(form -> form.loginPage("/loginPage").loginProcessingUrl("/authenticateTheUser").permitAll()).exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied"))
                .logout(logout -> logout.permitAll());
        return http.build();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/js/**", "/images/**",
                "/css/**", "/landingPage", "/showRegistrationPage", "/registerTheUser");
    }
}
