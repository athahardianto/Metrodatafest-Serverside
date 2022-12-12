/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name="Faqs")
public class Faqs {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="faq_id")
    private Long id;
    
    @Column(nullable = false)
    private String question;
    
    @Column(nullable = false)
    private String answer;
    
    @Column(nullable = false, name="is_publish")
    private int isPublish;
}
