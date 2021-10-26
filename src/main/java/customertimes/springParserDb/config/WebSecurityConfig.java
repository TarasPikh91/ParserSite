package customertimes.springParserDb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//    private final DataSource dataSource;
    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(final UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

//    @Value("DEF_USERS_BY_USERNAME_QUERY")
//    private String DEF_USERS_BY_USERNAME_QUERY;
//
//    @Value("DEF_AUTHORITIES_BY_USERNAME_QUERY")
//    private String DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/login")
                    .access("hasRole('USER')")
                    .antMatchers("/", "/**").access("permitAll")
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).
                passwordEncoder(new BCryptPasswordEncoder());
//                jdbcAuthentication().
//                dataSource(dataSource).
//                usersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY).
//                authoritiesByUsernameQuery(DEF_GROUP_AUTHORITIES_BY_USERNAME_QUERY).
//                passwordEncoder(new BCryptPasswordEncoder());
    }
}