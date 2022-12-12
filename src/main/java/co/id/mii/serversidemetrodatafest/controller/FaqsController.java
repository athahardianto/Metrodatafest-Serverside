/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.controller;

import co.id.mii.serversidemetrodatafest.model.Faqs;
import co.id.mii.serversidemetrodatafest.service.FaqsService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/faqs")
public class FaqsController {
    
    private FaqsService faqsService;
    
    @Autowired
    public FaqsController(FaqsService faqsService) {
        this.faqsService = faqsService;
    }
    
    @GetMapping
    public ResponseEntity<List<Faqs>> getAll(){
        return new ResponseEntity(faqsService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Faqs> getById(@PathVariable Long id){
        return new ResponseEntity(faqsService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Faqs> create(@RequestBody Faqs faqs){
        return new ResponseEntity(faqsService.create(faqs), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Faqs> update(@PathVariable Long id, @RequestBody Faqs faqs){
        return new ResponseEntity(faqsService.Update(id, faqs), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Faqs> delete(@PathVariable Long id){
        return new ResponseEntity(faqsService.delete(id), HttpStatus.OK);
    }
}
