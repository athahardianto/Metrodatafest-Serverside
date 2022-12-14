/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
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
@Table(name="Orders")
public class Orders {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long id;
    
    @Column(nullable = false)
    private int quantity;
    
    @Column(nullable = false)
    private Date orderDate;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(mappedBy = "order")
    @JoinColumn(name = "order_id")
    private Payments payment;
    
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//    name = "orders_tickets", 
//    joinColumns = @JoinColumn(name = "order_id"), 
//    inverseJoinColumns = @JoinColumn(name = "ticket_id"))
    @ManyToOne
    private Tickets ticket;
    
}
