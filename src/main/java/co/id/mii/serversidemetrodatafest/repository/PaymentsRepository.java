/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.repository;

import co.id.mii.serversidemetrodatafest.model.Payments;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author athab
 */
@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long>{
    
    @Query(value = "SELECT * FROM payments a ,(SELECT b.order_id FROM orders b, user c WHERE b.user_id = c.user_id AND c.username =?) b WHERE a.order_order_id = b.order_id", nativeQuery = true)
    public List<Payments> getByUsername(String username);
    
}
