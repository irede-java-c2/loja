package br.org.irede.loja.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class JPAUtil {
    
    private static EntityManagerFactory FACTORY = 
            Persistence.createEntityManagerFactory("ormPU");
    
    private JPAUtil (){
        
    }
    
    public static EntityManager getEntityManager(){
        return FACTORY.createEntityManager();
    }
}
