/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usa.ciclo3.reto_3.service;

import usa.ciclo3.reto_3.model.Admin;
import usa.ciclo3.reto_3.repository.AdminRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SISTEMAS
 */
@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;
    
    public List<Admin> getAll(){
        return adminRepository.getAll();
    }
    
    public Optional<Admin> getAdmin(int idAdmin){
        return adminRepository.getAdmin(idAdmin);
    }
    
    public Admin save(Admin admin){
        if(admin.getIdAdmin()== null){
            return adminRepository.save(admin);
        }else { 
            Optional<Admin> caux=adminRepository.getAdmin(admin.getIdAdmin());
            if (caux.isEmpty()) {
                return adminRepository.save(admin);
            }else{
                return admin;
            }
        }  
    }
    
    public Admin update(Admin admin){
        if(admin.getIdAdmin()!= null){
            Optional<Admin> claux=adminRepository.getAdmin(admin.getIdAdmin());
            if (!claux.isEmpty()) {
                if (admin.getName() != null) {
                    claux.get().setName(admin.getName());   
                }
                if (admin.getEmail()!= null) {
                    claux.get().setEmail(admin.getEmail());   
                }
                if (admin.getPassword()!= null) {
                    claux.get().setPassword(admin.getPassword());   
                }
                adminRepository.save(claux.get());
                return claux.get();
            }else{
                return admin;
            }
        }else {
            return admin;
        }
    }
    
    public boolean deleteAdmin(int idAdmin){
        Boolean aBoolean = getAdmin(idAdmin).map(admin ->{
            adminRepository.delete(admin);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
