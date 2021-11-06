/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usa.ciclo3.reto_3.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import usa.ciclo3.reto_3.model.Reservation;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author SISTEMAS
 */

public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer>{
    
    //JPQL
    @Query("select c.client, COUNT(c.client) from Reservation as c group by c.client order by count(c.client) desc")
    public List<Object[]> countTotalReservationByClient();
    
    // QUERY METHODS!
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo);
    
    public List<Reservation> findAllByStatus (String Status);

    
}
