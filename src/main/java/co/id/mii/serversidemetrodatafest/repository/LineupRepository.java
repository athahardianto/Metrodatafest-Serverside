/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.repository;

import co.id.mii.serversidemetrodatafest.model.Lineup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author athab
 */
@Repository
public interface LineupRepository extends JpaRepository<Lineup, Long>{
    
}
