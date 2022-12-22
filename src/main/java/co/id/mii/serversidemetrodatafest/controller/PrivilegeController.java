/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.controller;

import co.id.mii.serversidemetrodatafest.model.Privilege;
import co.id.mii.serversidemetrodatafest.service.PrivilegeService;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author athab
 */
@RestController
@RequestMapping("/privilege")
@PreAuthorize("hasRole('USER')")
public class PrivilegeController {
    
    PrivilegeService privilegeService;
    
    @GetMapping
    public ResponseEntity<List<Privilege>> getAll(){
        return new ResponseEntity(privilegeService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Privilege> getById(@PathVariable Long id){
        return new ResponseEntity(privilegeService.getById(id), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public ResponseEntity<Privilege> create(@RequestBody Privilege privilege){
        return new ResponseEntity(privilegeService.create(privilege), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Privilege> update(@PathVariable Long id, @RequestBody Privilege privilege){
        return new ResponseEntity(privilegeService.Update(id, privilege), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Privilege> delete(@PathVariable Long id){
        return new ResponseEntity(privilegeService.delete(id), HttpStatus.OK);
    }
}
