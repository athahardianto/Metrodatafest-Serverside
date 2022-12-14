/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.service;

import co.id.mii.serversidemetrodatafest.model.Orders;
import co.id.mii.serversidemetrodatafest.model.Payments;
import co.id.mii.serversidemetrodatafest.model.TicketStock;
import co.id.mii.serversidemetrodatafest.model.Tickets;
import co.id.mii.serversidemetrodatafest.model.User;
import co.id.mii.serversidemetrodatafest.model.dto.request.PaymentsRequest;
import co.id.mii.serversidemetrodatafest.repository.PaymentsRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


/**
 *
 * @author athab
 */
@Service
@AllArgsConstructor
public class PaymentsService {
    
    private PaymentsRepository paymentsRepository;
    private UserService userService;
    private TicketsService ticketsService;
    private TicketStockService ticketStockService;
    
    ///GetAll
    public List<Payments> getAll(){
        return paymentsRepository.findAll();
    }
    
    ///GetById
    public Payments getById(Long id){
        return paymentsRepository.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment not found!") );
    }
    
    ///Create
    public Payments create (PaymentsRequest paymentsRequest){
        ///user
        User user = new User();
        user = userService.getById(paymentsRequest.getIdUser());
                      
         ///ticket
        Tickets ticket = new Tickets();
        ticket = ticketsService.getById(paymentsRequest.getIdTicket());
        
        ///order
        Orders order = new Orders();
        order.setOrderDate(paymentsRequest.getOrderDate());
        order.setQuantity(paymentsRequest.getQuantity());
        order.setUsers(user);
        order.setTicket(ticket);
        
        ///total amount
        int total = ticket.getPrice() * paymentsRequest.getQuantity();
        
        ///minus stock
        TicketStock ticketStock = new TicketStock();
        ticketStock = ticketStockService.getById(paymentsRequest.getIdTicket());
        
        if (ticketStock.getStock() > paymentsRequest.getQuantity()) {
            
            int hasil = ticketStock.getStock()-paymentsRequest.getQuantity();
            ticketStock.setStock(hasil);   
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "To Many Orders");
        }
        
        ///payment
        Payments payments = new Payments();
        payments.setMethod(paymentsRequest.getMethod());
        payments.setAmount(total);
        payments.setOrder(order);
        
        return paymentsRepository.save(payments);
    }
    
    ///update
    public Payments Update(Long id, Payments payments){
        getById(id);
        payments.setId(id);
        return paymentsRepository.save(payments);
    }
    
    ///delete
    public Payments delete(Long id){
        Payments payments = getById(id);
        paymentsRepository.delete(payments);
        return payments;
    }
}
