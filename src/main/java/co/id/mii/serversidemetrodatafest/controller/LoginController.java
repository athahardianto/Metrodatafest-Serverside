/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.controller;

import co.id.mii.serversidemetrodatafest.model.dto.request.LoginRequest;
import co.id.mii.serversidemetrodatafest.model.dto.response.LoginResponse;
import co.id.mii.serversidemetrodatafest.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author athab
 */
@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class LoginController {
 
    private LoginService loginService;
    
    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return loginService.login(loginRequest);
    }
}
