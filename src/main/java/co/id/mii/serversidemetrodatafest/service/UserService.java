/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.service;

import co.id.mii.serversidemetrodatafest.model.User;
import co.id.mii.serversidemetrodatafest.repository.UserRepository;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
    
    ///GetAll
    public List<User> getAll(){
        return userRepository.findAll();
    }
    
    ///GetById
    public User getById(Long id){
        return userRepository.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found!") );
    }
    
    ///Create
    public User create (User user){
         
        User users = new User();
        users.setEmail(user.getEmail());
        users.setUsername(user.getUsername());
        users.setPassword(passwordEncoder.encode(user.getPassword()));
        users.setPhone(user.getPhone());
        users.setRole(user.getRole());
        userRepository.save(users);
        
        String subject = "Akun aktif";
        String body = "Akun "+ user.getUsername()+" sudah aktif";
        
        try {
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setTo(user.getEmail());
            helper.setSubject(subject);
            helper.setText(body);
            
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email");
        }
        
        return userRepository.save(users);
    }
    
    ///update
    public User Update(Long id, User user){
        getById(id);
        user.setId(id);
        return userRepository.save(user);
    }
    
    ///delete
    public User delete(Long id){
        User lineup = getById(id);
        userRepository.delete(lineup);
        return lineup;
    }
}
