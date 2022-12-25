/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.service;

import co.id.mii.serversidemetrodatafest.model.Role;
import co.id.mii.serversidemetrodatafest.model.User;
import co.id.mii.serversidemetrodatafest.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import static org.apache.tomcat.jni.User.username;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author athab
 */
@Service
@AllArgsConstructor
public class UserService {
    
    private UserRepository userRepository;
    private JavaMailSender mailSender;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    
    ///GetAll
    public List<User> getAll(){
        return userRepository.findAll();
    }
    
    ///GetById
    public User getById(Long id){
        return userRepository.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found!") );
    }
    
    ///GetByUsername
    public User getByUserName(String name){
        return userRepository.findByUsername(name)
                .orElseThrow(()-> new UsernameNotFoundException("Username incorrect"));
    }
     
    
    ///delete
    public User delete(Long id){
        User lineup = getById(id);
        userRepository.delete(lineup);
        return lineup;
    }
}
