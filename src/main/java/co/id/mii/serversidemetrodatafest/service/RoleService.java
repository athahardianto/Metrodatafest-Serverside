/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.service;

import co.id.mii.serversidemetrodatafest.model.Role;
import co.id.mii.serversidemetrodatafest.repository.RoleRepository;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author athab
 */
@Service
@AllArgsConstructor
public class RoleService {
    
    private RoleRepository roleRepository;
    
     ///GetAll
    public List<Role> getAll(){
        return roleRepository.findAll();
    }
    
    ///GetById
    public Role getById(Long id){
        return roleRepository.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Role not found!") );
    }
    
    ///Create
    public Role create (Role role){
        if(role.getId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Role id already exists!");
        }
        return roleRepository.save(role);
    }
    
    ///update
    public Role Update(Long id, Role role){
        getById(id);
        role.setId(id);
        return roleRepository.save(role);
    }
    
    ///delete
    public Role delete(Long id){
        Role role = getById(id);
        roleRepository.delete(role);
        return role;
    }
}
