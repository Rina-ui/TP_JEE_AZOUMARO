package glsi.api.tp_jee_egab.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEndoder() {
        return new BCryptPasswordEncoder();
    }

}
