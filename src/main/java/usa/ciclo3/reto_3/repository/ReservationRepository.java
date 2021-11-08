/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usa.ciclo3.reto_3.repository;

import java.util.ArrayList;
import java.util.Date;
import usa.ciclo3.reto_3.model.Reservation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import usa.ciclo3.reto_3.model.Client;
import usa.ciclo3.reto_3.reports.CountClient;

/**
 *
 * @author SISTEMAS
 */

@Repository
public class ReservationRepository {
    
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;
    
    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();  
    }
    
    public Optional<Reservation> getReservation(int idReservation){
        return reservationCrudRepository.findById(idReservation);
    }
    
    public Reservation save(Reservation reservation){
        return reservationCrudRepository.save(reservation);
    }
    
    public void delete(Reservation reservation){
        reservationCrudRepository.delete(reservation);
    }
    
    public List<Reservation> getReservationsByClient(String Status){
        return reservationCrudRepository.findAllByStatus(Status);
    }
    
    public List<Reservation> getReservationPeriod(Date dateOne, Date dateTwo){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }
    
    public List<CountClient> getTopClients(){
        List<CountClient> res=new ArrayList<>();
        
        List<Object[]> report=reservationCrudRepository.countTotalReservationByClient();
        for(int i=0; i<report.size(); i++){
           /*Client clt=(Client) report.get(i)[0];
           Long cantidad=(Long) report.get(i)[1];
           CountClient cc=new CountClient(cantidad, cat);
            res.add(cc);*/
           res.add(new CountClient((Long) report.get(i)[1], (Client)report.get(i)[0]));
        }
        
        return res;
    }
}
