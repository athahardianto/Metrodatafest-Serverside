/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.service;

import co.id.mii.serversidemetrodatafest.model.News;
import co.id.mii.serversidemetrodatafest.repository.NewsRepository;
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
public class NewsService {
    
    NewsRepository newsRepository;
    
    ///GetAll
    public List<News> getAll(){
        return newsRepository.findAll();
    }
    
    ///GetById
    public News getById(Long id){
        return newsRepository.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"News not found!") );
    }
    
    ///Create
    public News create (News news){
        if(news.getId() != null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "News id already exists!");
        }
        return newsRepository.save(news);
    }
    
    ///update
    public News Update(Long id, News news){
        getById(id);
        news.setId(id);
        return newsRepository.save(news);
    }
    
    ///delete
    public News delete(Long id){
        News news = getById(id);
        newsRepository.delete(news);
        return news;
    }
    
}
