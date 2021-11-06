/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usa.ciclo3.reto_3.service;

import usa.ciclo3.reto_3.model.Game;
import usa.ciclo3.reto_3.repository.GameRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SISTEMAS
 */
@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepository;
    
    public List<Game> getAll(){
        return gameRepository.getAll();
    }
    
    public Optional<Game> getGame(int id){
        return gameRepository.getGame(id);
    }
    
    public Game save(Game game){
        if(game.getId() == null){
            return gameRepository.save(game);
        }else { 
            Optional<Game> jaux=gameRepository.getGame(game.getId());
            if (jaux.isEmpty()) {
                return gameRepository.save(game);
            }else{
                return game;
            }
        }  
    }
    
    public Game update(Game game){
        if(game.getId() != null){
            Optional<Game> gaux=gameRepository.getGame(game.getId());
            if (!gaux.isEmpty()) {
                if (game.getName() != null) {
                    gaux.get().setName(game.getName());   
                }
                if (game.getDeveloper()!= null) {
                    gaux.get().setDeveloper(game.getDeveloper());   
                }
                if (game.getYear()!= null) {
                    gaux.get().setYear(game.getYear());   
                }
                if (game.getDescription()!= null) {
                    gaux.get().setDescription(game.getDescription());   
                }
                if (game.getCategory()!= null) {
                    gaux.get().setCategory(game.getCategory());   
                }
                gameRepository.save(gaux.get());
                return gaux.get();
            }else{
                return game;
            }
        }else {
            return game;
        }
    }
    
    public boolean deleteGame(int id){
        Boolean aBoolean = getGame(id).map(game ->{
            gameRepository.delete(game);
            return true;
        }).orElse(false);
        return aBoolean;
    }  
}
