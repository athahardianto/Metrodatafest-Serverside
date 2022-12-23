/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.controller;

import co.id.mii.serversidemetrodatafest.model.User;
import co.id.mii.serversidemetrodatafest.service.UserService;
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
@RequestMapping("/user")
public class UserController {
    
    private UserService userService;
    
    @PreAuthorize("hasAuthority('READ_ADMIN')")
    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return new ResponseEntity(userService.getAll(), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        return new ResponseEntity(userService.getById(id), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @GetMapping("/username")
    public ResponseEntity<User> getByEmail(@RequestParam String username){
        return new ResponseEntity(userService.getByUserName(username), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user){
        return new ResponseEntity(userService.create(user), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasAnyAuthority('UPDATE_USER','UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user){
        return new ResponseEntity(userService.Update(id, user), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Long id){
        return new ResponseEntity(userService.delete(id), HttpStatus.OK);
    }
}
