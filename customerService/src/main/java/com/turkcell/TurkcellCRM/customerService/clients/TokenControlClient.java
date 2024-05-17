package com.turkcell.TurkcellCRM.customerService.clients;


import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="identityservice",url = "http://localhost:8083/api/v1/auth")
public interface TokenControlClient {
    @PostMapping("/tokenControl")
    boolean tokenControl(@RequestBody String token);
}
