/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usa.ciclo3.reto_3.repository;

import usa.ciclo3.reto_3.model.Admin;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SISTEMAS
 */
@Repository
public class AdminRepository {
    
    @Autowired
    private AdminCrudRepository adminCrudRepository;
    
    public List<Admin> getAll(){
        return (List<Admin>) adminCrudRepository.findAll();  
    }
    
    public Optional<Admin> getAdmin(int idAdmin){
        return adminCrudRepository.findById(idAdmin);
    }
    
    public Admin save(Admin admin){
        return adminCrudRepository.save(admin);
    }
    
    public void delete(Admin admin){
        adminCrudRepository.delete(admin);
    }
    
}
