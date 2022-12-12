/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.service;

import co.id.mii.serversidemetrodatafest.model.Tickets;
import co.id.mii.serversidemetrodatafest.repository.TicketsRepository;
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
public class TicketsService {
    
    private TicketsRepository ticketsRepository;
    
    ///GetAll
    public List<Tickets> getAll(){
        return ticketsRepository.findAll();
    }
    
    ///GetById
    public Tickets getById(Long id){
        return ticketsRepository.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Ticket not found!") );
    }
    
    ///Create
    public Tickets create (Tickets tickets){
        if(tickets.getId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ticket id already exists!");
        }
        return ticketsRepository.save(tickets);
    }
    
    ///update
    public Tickets Update(Long id, Tickets tickets){
        getById(id);
        tickets.setId(id);
        return ticketsRepository.save(tickets);
    }
    
    ///delete
    public Tickets delete(Long id){
        Tickets tickets = getById(id);
        ticketsRepository.delete(tickets);
        return tickets;
    }
}
