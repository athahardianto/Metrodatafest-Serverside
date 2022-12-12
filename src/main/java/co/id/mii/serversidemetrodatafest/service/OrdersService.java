/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.service;

import co.id.mii.serversidemetrodatafest.model.Orders;
import co.id.mii.serversidemetrodatafest.repository.OrdersRepository;

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
public class OrdersService {
    
    private OrdersRepository ordersRepository;
    
     ///GetAll
    public List<Orders> getAll(){
        return ordersRepository.findAll();
    }
    
    ///GetById
    public Orders getById(Long id){
        return ordersRepository.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Order not found!") );
    }
    
    ///Create
    public Orders create (Orders orders){
        if(orders.getId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Order id already exists!");
        }
        return ordersRepository.save(orders);
    }
    
    ///update
    public Orders Update(Long id, Orders orders){
        getById(id);
        orders.setId(id);
        return ordersRepository.save(orders);
    }
    
    ///delete
    public Orders delete(Long id){
        Orders orders = getById(id);
        ordersRepository.delete(orders);
        return orders;
    }
}
