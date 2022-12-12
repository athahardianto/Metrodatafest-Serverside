/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.model;

import java.util.Date;
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
@Table(name="News")
public class News {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="news_id")
    private Long id;
    
    @Column(nullable = false, name ="title")
    private String title;
    
    @Column(nullable = false, name ="content")
    private String content;
    
    @Column(nullable = false, name ="created_at")
    private Date createAt;
}
