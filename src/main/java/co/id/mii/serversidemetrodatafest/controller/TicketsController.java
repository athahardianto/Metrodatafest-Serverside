/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.controller;

import co.id.mii.serversidemetrodatafest.model.Tickets;
import co.id.mii.serversidemetrodatafest.service.TicketsService;
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
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author athab
 */
@AllArgsConstructor
@RestController
@RequestMapping("/tickets")
public class TicketsController {
    
    private TicketsService ticketsService;
    
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @GetMapping
    public ResponseEntity<List<Tickets>> getAll(){
        return new ResponseEntity(ticketsService.getAll(), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyAuthority('READ_ADMIN','READ_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Tickets> getById(@PathVariable Long id){
        return new ResponseEntity(ticketsService.getById(id), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAuthority('CREATE_ADMIN')")
    @PostMapping
    public ResponseEntity<Tickets> create(@RequestBody Tickets tickets){
        return new ResponseEntity(ticketsService.create(tickets), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasAuthority('UPDATE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Tickets> update(@PathVariable Long id, @RequestBody Tickets tickets){
        return new ResponseEntity(ticketsService.Update(id, tickets), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasAuthority('DELETE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Tickets> delete(@PathVariable Long id){
        return new ResponseEntity(ticketsService.delete(id), HttpStatus.OK);
    }
}
