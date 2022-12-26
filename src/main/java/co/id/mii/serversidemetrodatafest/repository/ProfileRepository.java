/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.repository;

import co.id.mii.serversidemetrodatafest.model.Profile;
import co.id.mii.serversidemetrodatafest.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author athab
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>{
    Optional<Profile> findByFullname(String username);
    
    @Query(value = "SELECT * FROM profile a , user b WHERE b.username=? AND a.user_user_id=b.user_id", nativeQuery = true)
    public Profile getByUsername(String username);
}
