/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author athab
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Lineup")
public class Lineup {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lineup_id")
    private Long id;
    
    private String guestStar;
    
    private Date schedule;
    
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
}
