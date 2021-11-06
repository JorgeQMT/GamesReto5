
package usa.ciclo3.reto_3.adapterSecurity;

import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class AdapterSecurity extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(a -> a
    .antMatchers("/", "/error", "/webjars/**","/api/**","/h2-console/**","/Admin/**","/Category**","/Client/**","/Game/**","/HomePage/**","/Index/**","/Message/**","/Report/**","/Reservation/**").permitAll().anyRequest().authenticated()
                
        ).exceptionHandling(e -> e
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
        ).oauth2Login().defaultSuccessUrl("/HomePage.html", true);

        http.cors().and().csrf().disable();

    }

}