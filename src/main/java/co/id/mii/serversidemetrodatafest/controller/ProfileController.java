/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.controller;

import co.id.mii.serversidemetrodatafest.model.Profile;
import co.id.mii.serversidemetrodatafest.model.User;
import co.id.mii.serversidemetrodatafest.model.dto.request.ProfileRequest;
import co.id.mii.serversidemetrodatafest.service.ProfileService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author athab
 */
@AllArgsConstructor
@RestController
@RequestMapping("/profile")
public class ProfileController {
    
    private ProfileService profileService;
    
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public ResponseEntity<List<Profile>> getAll(){
        return new ResponseEntity(profileService.getAll(), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Profile> getById(@PathVariable Long id){
        return new ResponseEntity(profileService.getById(id), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @GetMapping("/profilename")
    public ResponseEntity<Profile> getByFullname(@RequestParam String fullname){
        return new ResponseEntity(profileService.getByFullName(fullname), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyAuthority('READ_USER','READ_ADMIN')")
    @GetMapping("/username")
    public ResponseEntity<Profile> getByUsername(@RequestParam String username){
        return new ResponseEntity(profileService.getByUsername(username), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Profile> createUser(@RequestBody ProfileRequest profileRequest){
        return new ResponseEntity(profileService.createUser(profileRequest), HttpStatus.CREATED);
    }
    
    @PostMapping("/admin")
    public ResponseEntity<Profile> createAdmin(@RequestBody ProfileRequest profileRequest){
        return new ResponseEntity(profileService.createAdmin(profileRequest), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasAnyAuthority('UPDATE_USER','UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Profile> update(@PathVariable Long id, @RequestBody Profile profile){
        return new ResponseEntity(profileService.Update(id, profile), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Profile> delete(@PathVariable Long id){
        return new ResponseEntity(profileService.Delete(id), HttpStatus.OK);
    }
}
