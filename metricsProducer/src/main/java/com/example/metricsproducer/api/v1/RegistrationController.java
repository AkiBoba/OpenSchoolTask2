package com.example.metricsproducer.api.v1;

import com.example.metricsproducer.dto.AuthRequest;
import com.example.metricsproducer.dto.UserInfoDTO;
import com.example.metricsproducer.service.JwtService;
import com.example.metricsproducer.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("api/v1/")
public class RegistrationController {
    UserInfoService service;
    JwtService jwtService;
    AuthenticationManager authenticationManager;

    @Operation(description = "Контроллер принимает запрос на добавление нового пользователя")
    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody UserInfoDTO userInfo) {
        return service.addUser(userInfo.toUserInfo());
    }

    @Operation(description = "Контроллер принимает запрос на получение токена доступа")
    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("Данные не соответствуют зарегистрированным пользователям!");
        }
    }


}
