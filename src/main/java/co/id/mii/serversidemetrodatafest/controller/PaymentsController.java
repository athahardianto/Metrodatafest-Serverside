/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.controller;

import co.id.mii.serversidemetrodatafest.model.Payments;
import co.id.mii.serversidemetrodatafest.service.PaymentsService;
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
@RequestMapping("/payments")
public class PaymentsController {
    
    private PaymentsService paymentsService;
    
    @GetMapping
    public ResponseEntity<List<Payments>> getAll(){
        return new ResponseEntity(paymentsService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Payments> getById(@PathVariable Long id){
        return new ResponseEntity(paymentsService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Payments> create(@RequestBody Payments payments){
        return new ResponseEntity(paymentsService.create(payments), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Payments> update(@PathVariable Long id, @RequestBody Payments payments){
        return new ResponseEntity(paymentsService.Update(id, payments), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Payments> delete(@PathVariable Long id){
        return new ResponseEntity(paymentsService.delete(id), HttpStatus.OK);
    }
}
