/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.controller;

import co.id.mii.serversidemetrodatafest.model.Orders;
import co.id.mii.serversidemetrodatafest.service.OrdersService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/orders")
public class OrdersController {
    
    private OrdersService ordersService;
    
    @GetMapping
    public ResponseEntity<List<Orders>> getAll(){
        return new ResponseEntity(ordersService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getById(@PathVariable Long id){
        return new ResponseEntity(ordersService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Orders> create(@RequestBody Orders orders){
        return new ResponseEntity(ordersService.create(orders), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Orders> update(@PathVariable Long id, @RequestBody Orders orders){
        return new ResponseEntity(ordersService.Update(id, orders), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Orders> delete(@PathVariable Long id){
        return new ResponseEntity(ordersService.delete(id), HttpStatus.OK);
    }
}
