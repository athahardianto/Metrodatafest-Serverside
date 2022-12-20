/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.service;

import co.id.mii.serversidemetrodatafest.model.Faqs;
import co.id.mii.serversidemetrodatafest.model.dto.request.FaqsRequest;
import co.id.mii.serversidemetrodatafest.repository.FaqsRespository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author athab
 */
@Service
@AllArgsConstructor
public class FaqsService {
    
    private FaqsRespository faqsRespository;
    
    ///GetAll
    public List<Faqs> getAll(){
        return faqsRespository.findAll();
    }
    
    ///GetById
    public Faqs getById(Long id){
        return faqsRespository.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Faqs not found!") );
    }
    
    ///Create
    public Faqs create (FaqsRequest faqsRequest){
        
        Faqs faqs = new Faqs();
        faqs.setQuestion(faqsRequest.getQuestion());
        faqs.setAnswer(faqsRequest.getAnswer());
        faqs.setIsPublish(1);
        
        return faqsRespository.save(faqs);
    }
    
    ///update
    public Faqs Update(Long id, Faqs faqs){
        getById(id);
        faqs.setId(id);
        faqs.setIsPublish(1);
        return faqsRespository.save(faqs);
    }
    
    ///delete
    public Faqs delete(Long id){
        Faqs faqs = getById(id);
        faqsRespository.delete(faqs);
        return faqs;
    }
}
