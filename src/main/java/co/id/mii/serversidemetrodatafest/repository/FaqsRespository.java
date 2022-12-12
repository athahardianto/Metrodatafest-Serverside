/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.repository;

import co.id.mii.serversidemetrodatafest.model.Faqs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author athab
 */
@Repository
public interface FaqsRespository extends JpaRepository<Faqs, Long>{
    
}
