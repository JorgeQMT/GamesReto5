/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usa.ciclo3.reto_3.service;

import usa.ciclo3.reto_3.model.Score;
import usa.ciclo3.reto_3.repository.ScoreRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SISTEMAS
 */
@Service
public class ScoreService {
    
    @Autowired
    private ScoreRepository scoreRepository;
    
    public List<Score> getAll(){
        return scoreRepository.getAll();
    }
    
    public Optional<Score> getScore(int id){
        return scoreRepository.getScore(id);
    }
    
    public Score save(Score score){
        if(score.getId() == null){
            return scoreRepository.save(score);
        }else { 
            Optional<Score> saux=scoreRepository.getScore(score.getId());
            if (saux.isEmpty()) {
                return scoreRepository.save(score);
            }else{
                return score;
            }
        }  
    }
    
    public Score update(Score score){
        if(score.getId()!= null){
            Optional<Score> saux=scoreRepository.getScore(score.getId());
            if (!saux.isEmpty()) {
                if (score.getMessageText()!= null) {
                    saux.get().setMessageText(score.getMessageText());   
                }
                if (score.getStars()!= null) {
                    saux.get().setStars(score.getStars());   
                }
                scoreRepository.save(saux.get());
                return saux.get();
            }else{
                return score;
            }
        }else {
            return score;
        }
    }
    
    public boolean deleteScore(int idScore){
        Boolean aBoolean = getScore(idScore).map(score ->{
            scoreRepository.delete(score);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}
