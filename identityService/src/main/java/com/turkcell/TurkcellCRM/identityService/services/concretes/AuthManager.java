package com.turkcell.TurkcellCRM.identityService.services.concretes;


import com.turkcell.TurkcellCRM.CoreService.jwt.JwtService;
import com.turkcell.TurkcellCRM.commonPackage.enums.Roles;
import com.turkcell.TurkcellCRM.identityService.services.abstracts.AuthService;
import com.turkcell.TurkcellCRM.identityService.services.abstracts.UserService;
import com.turkcell.TurkcellCRM.identityService.services.dtos.LoginRequest;
import com.turkcell.TurkcellCRM.identityService.services.dtos.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class AuthManager implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;


    @Override
    public void register(RegisterRequest request) {
        userService.add(request);
    }

    @Override
    public String login(LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        if(!authentication.isAuthenticated())
            throw new RuntimeException("E-posta veya şifre hatalı.");

        UserDetails user = userService.loadUserByUsername(request.getEmail());

        return jwtService.generateToken(user.getUsername(), user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList());
    }

    @Override
    public boolean tokenControl(String token) {
        String username=jwtService.extractUsername(token);

        List<String> list = jwtService.extractRoles(token);
        for (int i = 0; i<list.size(); i++){
            if (list.get(i).equals(Roles.USER.toString())){
                return false;
            }
        }
        return jwtService.validateToken(token,username);
    }
}
