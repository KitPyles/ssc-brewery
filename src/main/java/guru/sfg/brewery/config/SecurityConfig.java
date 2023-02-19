package guru.sfg.brewery.config;

import guru.sfg.brewery.security.SfgPasswordEncoderFactories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> {
                    authorize
                            .antMatchers("/h2-console/**").permitAll() //do not use in production!
                            .antMatchers("/", "/webjars/**", "/login", "/resources/**").permitAll()
                            .antMatchers(HttpMethod.GET, "/api/v1/beer/**")
                                .hasAnyRole("ADMIN", "CUSTOMER", "USER")
                            .mvcMatchers(HttpMethod.GET, "/api/v1/beerUpc/{upc}")
                                .hasAnyRole("ADMIN", "CUSTOMER", "USER")
                            .mvcMatchers("/brewery/breweries")
                                .hasAnyRole("ADMIN", "CUSTOMER")
                            .mvcMatchers(HttpMethod.GET, "/brewery/api/v1/breweries")
                                .hasAnyRole("ADMIN", "CUSTOMER")
                            .mvcMatchers("/beers/find", "/beers/{beerId}")
                                .hasAnyRole("ADMIN", "CUSTOMER", "USER");
                } )
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin().and()
                .httpBasic()
                .and().csrf().disable();
        http.headers().frameOptions().sameOrigin();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return SfgPasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

//        auth.inMemoryAuthentication()
//                .withUser("spring")
//                .password("{bcrypt}$2a$10$7tYAvVL2/KwcQTcQywHIleKueg4ZK7y7d44hKyngjTwHCDlesxdla")
//                .roles("ROLE_ADMIN")
//                .and()
//                .withUser("user")
//                .password("{sha256}1296cefceb47413d3fb91ac7586a4625c33937b4d3109f5a4dd96c79c46193a029db713b96006ded")
//                .roles("ROLE_USER");
//
//        auth.inMemoryAuthentication().withUser("scott").password("{bcrypt}$2a$10$jv7rEbL65k4Q3d/mqG5MLuLDLTlg5oKoq2QOOojfB3e2awo.nlmgu").roles("ROLE_CUSTOMER");
//    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("spring")
//                .password("guru")
//                .roles("ROLE_ADMIN")
//                .build();
//
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("ROLE_USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }
}