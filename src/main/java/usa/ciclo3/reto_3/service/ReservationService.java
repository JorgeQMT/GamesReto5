/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usa.ciclo3.reto_3.service;

import usa.ciclo3.reto_3.model.Reservation;
import usa.ciclo3.reto_3.repository.ReservationRepository;
import usa.ciclo3.reto_3.reports.CountClient;
import usa.ciclo3.reto_3.reports.StatusAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Jorge Quesada
 */
@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    //Metodo get de todas las Reservation
    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }
    
    //Metodo get de todas las Reservation por id
    public Optional<Reservation> getReservation(int id){
        return reservationRepository.getReservation(id);
    }
    
    //Metodo  que guarda una Reservation
    public Reservation save(Reservation reservation){
        //sentencia if de guardar Reservation
        if(reservation.getIdReservation()== null){
            return reservationRepository.save(reservation);
        }else { 
            Optional<Reservation> raux=reservationRepository.getReservation(reservation.getIdReservation());
            if (raux.isEmpty()) {
                return reservationRepository.save(reservation);
            }else{
                return reservation;
            }
        }  
    }
    
    //Metodo  que actualiza una Reservation
    public Reservation update(Reservation reservation){
        //sentencia if de actualizar Reservation
        if(reservation.getIdReservation()!= null){
            Optional<Reservation> raux=reservationRepository.getReservation(reservation.getIdReservation());
            if (!raux.isEmpty()) {
                if (reservation.getStartDate()!= null) {
                    raux.get().setStartDate(reservation.getStartDate());   
                }
                if (reservation.getDevolutionDate()!= null) {
                    raux.get().setDevolutionDate(reservation.getDevolutionDate());   
                }
                if (reservation.getStatus()!= null) {
                    raux.get().setStatus(reservation.getStatus());   
                }
                reservationRepository.save(raux.get());
                return raux.get();
            }else{
                return reservation;
            }
        }else {
            return reservation;
        }
    }
    
    //Metodo  que borra una Reservation
    public boolean deleteReservatio(int idReservation){
        Boolean aBoolean = getReservation(idReservation).map(reservation ->{
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
    //Metodo que optiene el reporte del top de clientes
    public List<CountClient> getTopClient(){
        return reservationRepository.getTopClients(); 
    }
    
    //Metodo que optiene el reporte por estado 
    public StatusAmount getStatusReport(){
        List<Reservation> completed=reservationRepository.getReservationsByClient("completed");
        List<Reservation> cancelled=reservationRepository.getReservationsByClient("cancelled");

       StatusAmount StaAmt=new StatusAmount(completed.size(), cancelled.size());
       return StaAmt;
    }
    
    //Metodo que optiene el reprote por preriodo entre fechas
    public List<Reservation> getReservationPeriod(String d1, String d2){
        
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date dateOne=new Date();
        Date dateTwo=new Date();
        try {
            dateOne=parser.parse(d1);
            dateTwo=parser.parse(d2);   
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dateOne.before(dateTwo)) {
            return reservationRepository.getReservationPeriod(dateOne, dateTwo);
        }else{
            return new ArrayList<>();
        }
    }
}
