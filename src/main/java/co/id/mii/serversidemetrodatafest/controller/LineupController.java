/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.controller;

import co.id.mii.serversidemetrodatafest.model.Lineup;
import co.id.mii.serversidemetrodatafest.service.LineupService;
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
@RestController
@RequestMapping("/lineup")
public class LineupController {
    
    private LineupService lineupService;

    public LineupController(LineupService lineupService) {
        this.lineupService = lineupService;
    }
    
    @GetMapping
    public ResponseEntity<List<Lineup>> getAll(){
        return new ResponseEntity(lineupService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Lineup> getById(@PathVariable Long id){
        return new ResponseEntity(lineupService.getById(id), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Lineup> create(@RequestBody Lineup lineup){
        return new ResponseEntity(lineupService.create(lineup), HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Lineup> update(@PathVariable Long id, @RequestBody Lineup lineup){
        return new ResponseEntity(lineupService.Update(id, lineup), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Lineup> delete(@PathVariable Long id){
        return new ResponseEntity(lineupService.delete(id), HttpStatus.OK);
    }
}
