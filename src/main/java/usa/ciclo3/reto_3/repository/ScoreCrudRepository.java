/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usa.ciclo3.reto_3.repository;

import usa.ciclo3.reto_3.model.Score;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author SISTEMAS
 */
public interface ScoreCrudRepository extends CrudRepository<Score, Integer>{
    
}
