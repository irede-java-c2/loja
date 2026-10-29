package br.org.irede.loja.dao;

import br.org.irede.loja.model.Produto;
import br.org.irede.loja.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class ProdutoDAO {
    
    public Produto salvar(Produto produto){
        //manipulador de dados
        EntityManager em = JPAUtil.getEntityManager();
        //ciracao e execucao da transacao
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();//abrindo a transacao
            em.persist(produto); //insert do produto
            tx.commit();//efetiva as alteracoes na base
            return produto;//dado atualizado
        }catch(RuntimeException e){
            e.printStackTrace();
            if(tx.isActive()){//se a transacao estiver ativa
                tx.rollback();//desfaz oq ue foi feito
            }
            throw e;
        }finally{
            em.close();//libera o entityManager
        }
    }
    
    public Produto buscarPorId(Long id){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.find(Produto.class, id);
        }catch(RuntimeException e){
            em.close();
            throw e;
        }
    }
    
    public List<Produto> buscarTodos(){
        EntityManager em = JPAUtil.getEntityManager();
        try{
            return em.createQuery("SELECT p FROM Produto p ORDER BY p.nome", 
                    Produto.class).getResultList();
        }catch(RuntimeException e){
            em.close();
            throw e;
        }
    }
    
    public Produto atualizar(Produto produto){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            Produto atualizado = em.merge(produto); //atualiza o produto
            tx.commit();
            return atualizado;//dado atualizado
        }catch(RuntimeException e){
            e.printStackTrace();
            if(tx.isActive()){
                tx.rollback();
            }
            throw e;
        }finally{
            em.close();
        }
    }
    
    public boolean remover(Long id){
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try{
            tx.begin();
            Produto produto = em.find(Produto.class, id);
            if(produto == null){
                tx.rollback();
                return false;
            }
            em.remove(produto);
            tx.commit();
            return true;//dado atualizado
        }catch(RuntimeException e){
            e.printStackTrace();
            if(tx.isActive()){
                tx.rollback();
            }
            throw e;
        }finally{
            em.close();
        }
    }
    
}
