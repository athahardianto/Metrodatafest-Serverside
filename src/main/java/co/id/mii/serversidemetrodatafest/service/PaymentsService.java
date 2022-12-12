/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.service;

import co.id.mii.serversidemetrodatafest.model.Payments;
import co.id.mii.serversidemetrodatafest.repository.PaymentsRepository;
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
public class PaymentsService {
    
    private PaymentsRepository paymentsRepository;
    
    ///GetAll
    public List<Payments> getAll(){
        return paymentsRepository.findAll();
    }
    
    ///GetById
    public Payments getById(Long id){
        return paymentsRepository.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment not found!") );
    }
    
    ///Create
    public Payments create (Payments payments){
        if(payments.getId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Payment id already exists!");
        }
        return paymentsRepository.save(payments);
    }
    
    ///update
    public Payments Update(Long id, Payments payments){
        getById(id);
        payments.setId(id);
        return paymentsRepository.save(payments);
    }
    
    ///delete
    public Payments delete(Long id){
        Payments payments = getById(id);
        paymentsRepository.delete(payments);
        return payments;
    }
}
