package com.turkcell.TurkcellCRM.CoreService.configuration;

import com.turkcell.TurkcellCRM.CoreService.filters.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@RequiredArgsConstructor
@Service
//@Component

//@CrossOrigin
public class BaseSecurityService {
    private static final String[] WHITE_LIST_URLS = {
            "/swagger-ui/**",
//            "http://localhost:9009/customerservice/api/v1/customers",
            "http://localhost:8083/api/v1/auth/login"

    };
    private final JwtAuthFilter jwtAuthFilter;

//    public BaseSecurityService(JwtAuthFilter jwtAuthFilter) {
//        this.jwtAuthFilter = jwtAuthFilter;
//    }

    public HttpSecurity configureCoreSecurity(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req-> req.requestMatchers(WHITE_LIST_URLS).permitAll())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity;
    }
}
