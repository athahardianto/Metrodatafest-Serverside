/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.service;

import co.id.mii.serversidemetrodatafest.model.Orders;
import co.id.mii.serversidemetrodatafest.model.Payments;
import co.id.mii.serversidemetrodatafest.model.Status;
import co.id.mii.serversidemetrodatafest.model.TicketStock;
import co.id.mii.serversidemetrodatafest.model.Tickets;
import co.id.mii.serversidemetrodatafest.model.User;
import co.id.mii.serversidemetrodatafest.model.dto.request.PaymentsRequest;
import co.id.mii.serversidemetrodatafest.repository.PaymentsRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
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
    private JavaMailSender mailSender;
    
    ///GetAll
    public List<Payments> getAll(){
        return paymentsRepository.findAll();
    }
    
    ///GetById
    public Payments getById(Long id){
        return paymentsRepository.findById(id)
            .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Payment not found!") );
    }
    
    public List<Payments> getByUsername(String username){
        return paymentsRepository.getByUsername(username);
    }
    
    ///Create
    public Payments create (PaymentsRequest paymentsRequest){
        ///user
        User user = new User();
        user = userService.getByUserName(paymentsRequest.getUsername());
                      
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
            
            ///payment
            Payments payments = new Payments();
            payments.setMethod(paymentsRequest.getMethod());
            payments.setAmount(total);
            payments.setStatus(Status.UNPAID);
            payments.setOrder(order);
            payments.setImage("kosong");

            return paymentsRepository.save(payments);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Out of stock");
        }
      
    }
    
    ///update
    public Payments Update(Long id, Payments payments){
        getById(id);
        payments.setId(id);
        payments = paymentsRepository.save(payments);
        if(payments.getStatus()== Status.PAID){
            Orders order = new Orders();
            order = payments.getOrder();
            
            Tickets ticket = new Tickets();
            ticket = order.getTicket();
            
            TicketStock ticketStocks = new TicketStock();
            ticketStocks = ticket.getTicketStock();
            
            int hasil = ticketStocks.getStock() - order.getQuantity() ;
            
            ticketStocks.setStock(hasil);
            
            User user = new User();
            user = order.getUsers();
            
            String subject = "Payments";
            String body = "Pembayaran tiket "+ ticket.getName()+" berhasil";

            try {

                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);

                helper.setTo(user.getEmail());
                helper.setSubject(subject);
                helper.setText(body);

                mailSender.send(message);
            } catch (MessagingException e) {
                throw new IllegalStateException("Failed to send email");
            }
            
        }
        return paymentsRepository.save(payments);
    }
    
    ///update payments buat user
    public Payments updateUser(Long id, MultipartFile file){
        
        Payments payments = new Payments();
        payments = getById(id);
        payments.setId(id);
        
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
		{
			System.out.println("not a a valid file");
		}
		try {
			payments.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        payments.setStatus(Status.REVIEW);
        
        return paymentsRepository.save(payments);
    }
    
    ///delete
    public Payments delete(Long id){
        Payments payments = getById(id);
        paymentsRepository.delete(payments);
        return payments;
    }
}
