package ru.psychotech.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  // @formatter:off
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .cors().disable()
        .csrf().disable();

    http
        .authorizeRequests((authorize) ->
            authorize
                .antMatchers("/css/**").permitAll()
                .antMatchers("/clients/**", "/admin/**").hasAnyRole("ADMIN", "OPERATOR")
                .antMatchers("/client/**").authenticated()
                .antMatchers("/swagger-ui/").hasRole("ADMIN")
                .antMatchers("/", "/registration").permitAll()
                .anyRequest().authenticated())
            .formLogin()
            .loginPage("/login")
            .permitAll()
//            .successHandler(new DefaultAuthenticationSuccessHandler())
        .and()
            .logout()
            .permitAll()
            .logoutSuccessUrl("/");
  }
  // @formatter:on

  @Bean
  public BCryptPasswordEncoder getBCryptPasswordEncoder(){
    return new BCryptPasswordEncoder(16);
  }
}
