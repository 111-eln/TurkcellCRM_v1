package com.turkcell.TurkcellCRM.identityService.core;

import com.turkcell.TurkcellCRM.CoreService.configuration.BaseSecurityService;
import com.turkcell.TurkcellCRM.commonPackage.enums.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final BaseSecurityService baseSecurityService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        baseSecurityService.configureCoreSecurity(http);
        http
                .authorizeHttpRequests(req -> req
                        .requestMatchers(HttpMethod.POST,"/individualcustomerservice/api/v1/customers").hasAnyAuthority(Roles.ADMIN.toString())
                        .anyRequest().permitAll()
                );
        return http.build();
    }

}
