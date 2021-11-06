/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usa.ciclo3.reto_3.service;

import usa.ciclo3.reto_3.model.Client;
import usa.ciclo3.reto_3.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SISTEMAS
 */
@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    public List<Client> getAll(){
        return clientRepository.getAll();
    }
    
    public Optional<Client> getClient(int idClient){
        return clientRepository.getClient(idClient);
    }
    
    public Client save(Client client){
        if(client.getIdClient()== null){
            return clientRepository.save(client);
        }else { 
            Optional<Client> caux=clientRepository.getClient(client.getIdClient());
            if (caux.isEmpty()) {
                return clientRepository.save(client);
            }else{
                return client;
            }
        }  
    }
    
    public Client update(Client client){
        if(client.getIdClient()!= null){
            Optional<Client> claux=clientRepository.getClient(client.getIdClient());
            if (!claux.isEmpty()) {
                if (client.getName() != null) {
                    claux.get().setName(client.getName());   
                }
                if (client.getEmail()!= null) {
                    claux.get().setEmail(client.getEmail());   
                }
                if (client.getAge()!= null) {
                    claux.get().setAge(client.getAge());   
                }
                if (client.getPassword()!= null) {
                    claux.get().setPassword(client.getPassword());   
                }
                clientRepository.save(claux.get());
                return claux.get();
            }else{
                return client;
            }
        }else {
            return client;
        }
    }
    
    public boolean deleteClient(int idClient){
        Boolean aBoolean = getClient(idClient).map(client ->{
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
