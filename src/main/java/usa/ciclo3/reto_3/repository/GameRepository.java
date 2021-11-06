/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usa.ciclo3.reto_3.repository;

import usa.ciclo3.reto_3.model.Game;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SISTEMAS
 */

@Repository
public class GameRepository {
    
    @Autowired
    private GameCrudRepository gameCrudRepository;
    
    public List<Game> getAll(){
        return (List<Game>) gameCrudRepository.findAll();  
    }
    
    public Optional<Game> getGame(int id){
        return gameCrudRepository.findById(id);
    }
    
    public Game save(Game game){
        return gameCrudRepository.save(game);
    }
    
    public void delete(Game game){
        gameCrudRepository.delete(game);
    }
}
