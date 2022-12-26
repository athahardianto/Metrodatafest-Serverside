/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.service;

import co.id.mii.serversidemetrodatafest.model.Profile;
import co.id.mii.serversidemetrodatafest.model.Role;
import co.id.mii.serversidemetrodatafest.model.User;
import co.id.mii.serversidemetrodatafest.model.dto.request.ProfileRequest;
import co.id.mii.serversidemetrodatafest.repository.ProfileRepository;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author athab
 */
@Service
@AllArgsConstructor
public class ProfileService {
    
    private ProfileRepository profileRepository;
    private JavaMailSender mailSender;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;
    
    ///getAll
    public List<Profile> getAll(){
        return profileRepository.findAll();
    }
    
    ///getById
    public Profile getById(Long id){
        return profileRepository.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found!") );
    }
    
    ///GetByUsername
    public Profile getByFullName(String fullname){
        return profileRepository.findByFullname(fullname)
                .orElseThrow(()-> new UsernameNotFoundException("Fullname incorrect"));
    }
    
    public Profile getByUsername(String username){
        return profileRepository.getByUsername(username);
    }
    
    public Profile createUser(ProfileRequest profileRequest){
        
        Profile profiles = new Profile();
        profiles.setFullname(profileRequest.getFullname());
        profiles.setPhone(profileRequest.getPhone());
        
        User users = new User();
        users.setUsername(profileRequest.getUsername());
        users.setPassword(passwordEncoder.encode(profileRequest.getPassword()));
        users.setEmail(profileRequest.getEmail());
        List<Role> role = new ArrayList<>();
        role.add(roleService.getById(2L));
        users.setRole(role);
        
        profiles.setUser(users);
        
        profileRepository.save(profiles);
        
        String subject = "Akun aktif";
        String body = "Akun "+ profiles.getFullname()+" sudah aktif";
        
        try {
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setTo(users.getEmail());
            helper.setSubject(subject);
            helper.setText(body);
            
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email");
        }
        
        return profileRepository.save(profiles);
    }
    
    public Profile createAdmin(ProfileRequest profileRequest){
        
        Profile profiles = new Profile();
        profiles.setFullname(profileRequest.getFullname());
        profiles.setPhone(profileRequest.getPhone());
        
        User users = new User();
        users.setUsername(profileRequest.getUsername());
        users.setPassword(passwordEncoder.encode(profileRequest.getPassword()));
        users.setEmail(profileRequest.getEmail());
        List<Role> role = new ArrayList<>();
        role.add(roleService.getById(1L));
        users.setRole(role);
        
        profiles.setUser(users);
        
        profileRepository.save(profiles);
        
        String subject = "Akun aktif";
        String body = "Akun "+ profiles.getFullname()+" sudah aktif";
        
        try {
            
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            
            helper.setTo(profileRequest.getEmail());
            helper.setSubject(subject);
            helper.setText(body);
            
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email");
        }
        
        return profileRepository.save(profiles);
    }
    
    public Profile Update (Long id, Profile profile){
        getById(id);
        profile.setId(id);
        return profileRepository.save(profile);
    }
    
    public Profile Delete (Long id){
        Profile profile = getById(id);
        profileRepository.delete(profile);
        return profile;
    }
    
}
