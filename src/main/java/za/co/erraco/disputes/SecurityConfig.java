package za.co.erraco.disputes;
import org.springframework.context.annotation.*; import org.springframework.security.config.Customizer; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.web.SecurityFilterChain; import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
@Configuration class SecurityConfig {
 @Bean SecurityFilterChain security(HttpSecurity http) throws Exception { return http.csrf(c->c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())).authorizeHttpRequests(a->a.requestMatchers("/", "/index.html", "/assets/**", "/api/health").permitAll().anyRequest().authenticated()).oauth2Login(Customizer.withDefaults()).logout(l->l.logoutSuccessUrl("/").deleteCookies("JSESSIONID")).build(); }
}
