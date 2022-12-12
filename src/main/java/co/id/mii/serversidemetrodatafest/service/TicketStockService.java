/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.service;

import co.id.mii.serversidemetrodatafest.model.TicketStock;
import co.id.mii.serversidemetrodatafest.repository.TicketStockRepository;
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
public class TicketStockService {
    
    private TicketStockRepository ticketStockRepository;
    
    ///GetAll
    public List<TicketStock> getAll(){
        return ticketStockRepository.findAll();
    }
    
    ///GetById
    public TicketStock getById(Long id){
        return ticketStockRepository.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Ticket Stock not found!") );
    }
    
    ///Create
    public TicketStock create (TicketStock ticketStock){
        if(ticketStock.getId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ticket id already exists!");
        }
        return ticketStockRepository.save(ticketStock);
    }
    
    ///update
    public TicketStock Update(Long id, TicketStock ticketStock){
        getById(id);
        ticketStock.setId(id);
        return ticketStockRepository.save(ticketStock);
    }
    
    ///delete
    public TicketStock delete(Long id){
        TicketStock ticketStock = getById(id);
        ticketStockRepository.delete(ticketStock);
        return ticketStock;
    }
}
