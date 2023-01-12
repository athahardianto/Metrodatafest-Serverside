/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.service;

import co.id.mii.serversidemetrodatafest.model.Lineup;
import co.id.mii.serversidemetrodatafest.repository.LineupRepository;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author athab
 */
@Service
@AllArgsConstructor
public class LineupService {
    
    LineupRepository lineupRepository;
    
    ///GetAll
    public List<Lineup> getAll(){
        return lineupRepository.findAll();
    }
    
    ///GetById
    public Lineup getById(Long id){
        return lineupRepository.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Lineup not found!") );
    }
    
    ///Create
    public Lineup create (String guestStar, Date schedule, String file){
        
        Lineup lineup = new Lineup();
        lineup.setGuestStar(guestStar);
        lineup.setSchedule(schedule);
        lineup.setImage(file);
        
//        try {
//		lineup.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
//	} catch (IOException e) {
//			e.printStackTrace();
//	}
//        
        return lineupRepository.save(lineup);
    }
    
    ///update
    public Lineup Update(Long id, String guestStar, Date schedule, String file){
        getById(id);
        Lineup lineup = new Lineup();
        lineup.setId(id);
        lineup.setGuestStar(guestStar);
        lineup.setSchedule(schedule);
        lineup.setImage(file);
        return lineupRepository.save(lineup);
    }
    
    ///delete
    public Lineup delete(Long id){
        Lineup lineup = getById(id);
        lineupRepository.delete(lineup);
        return lineup;
    }
}
