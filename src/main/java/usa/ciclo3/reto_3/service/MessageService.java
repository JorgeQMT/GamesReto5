/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usa.ciclo3.reto_3.service;

import usa.ciclo3.reto_3.model.Message;
import usa.ciclo3.reto_3.repository.MessageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SISTEMAS
 */
@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;
    
    public List<Message> getAll(){
        return messageRepository.getAll();
    }
    
    public Optional<Message> getMessage(int idMessage){
        return messageRepository.getMessage(idMessage);
    }
    
    public Message save(Message message){
        if(message.getIdMessage()== null){
            return messageRepository.save(message);
        }else { 
            Optional<Message> maux=messageRepository.getMessage(message.getIdMessage());
            if (maux.isEmpty()) {
                return messageRepository.save(message);
            }else{
                return message;
            }
        }  
    }
    
    public Message update(Message message){
        if(message.getIdMessage()!= null){
            Optional<Message> maux=messageRepository.getMessage(message.getIdMessage());
            if (!maux.isEmpty()) {
                if (message.getMessageText()!= null) {
                    maux.get().setMessageText(message.getMessageText());   
                }
                messageRepository.save(maux.get());
                return maux.get();
            }else{
                return message;
            }
        }else {
            return message;
        }
    }
    
    public boolean deleteMessage(int idMessage){
        Boolean aBoolean = getMessage(idMessage).map(message ->{
            messageRepository.delete(message);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
}
