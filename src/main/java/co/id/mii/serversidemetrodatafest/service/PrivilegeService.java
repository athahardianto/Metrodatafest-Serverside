/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.service;

import co.id.mii.serversidemetrodatafest.model.Privilege;
import co.id.mii.serversidemetrodatafest.repository.PrivilegeRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author athab
 */
@Service
@AllArgsConstructor
public class PrivilegeService {
    
    private PrivilegeRepository privilegeRepository;
    
    ///GetAll
    public List<Privilege> getAll(){
        return privilegeRepository.findAll();
    }
    
    ///GetById
    public Privilege getById(Long id){
        return privilegeRepository.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Privilege not found!") );
    }
    
    ///Create
    public Privilege create (Privilege privilege){
        if(privilege.getId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Privilege id already exists!");
        }
        return privilegeRepository.save(privilege);
    }
    
    ///update
    public Privilege Update(Long id, Privilege privilege){
        getById(id);
        privilege.setId(id);
        return privilegeRepository.save(privilege);
    }
    
    ///delete
    public Privilege delete(Long id){
        Privilege privilege = getById(id);
        privilegeRepository.delete(privilege);
        return privilege;
    }
}
