/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package br.org.irede.loja;

import br.org.irede.loja.dao.ProdutoDAO;
import br.org.irede.loja.model.Produto;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author macmini
 */
public class Loja {

    public static void main(String[] args) {
        ProdutoDAO dao = new ProdutoDAO();
        try{
            Produto caneta = new Produto("Caneta Azul", "CA", new BigDecimal("1.99"));
            dao.salvar(caneta);
            System.out.println("Item salvo! " + caneta);
            
            Produto mouse = new Produto("Mouse Gamer", "ELE-001", new BigDecimal("149.90"));
            mouse.setDescricao("Mouse muito resistente azul");
            mouse.setQuantidadeEstoque(15);
            dao.salvar(mouse);
            
            Produto primeiro = dao.buscarPorId(2L);
            System.out.println("Produto id2: " + primeiro);
            
            Produto monitor = new Produto("Monitor Gamer", "MO-001", new BigDecimal("1000.00"));
            dao.salvar(monitor);
            
            buscarTodos(dao);
            
            monitor.setDescricao("Monitor Curvado");
            monitor.setPreco(new BigDecimal("1500.00"));
            monitor.setQuantidadeEstoque(30);
            dao.atualizar(monitor);
            buscarTodos(dao);
            
            dao.remover(monitor.getId());
            buscarTodos(dao);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void buscarTodos(ProdutoDAO dao) {
        System.out.println("Todos os Produtos");
        List<Produto> produtos = dao.buscarTodos();
        for(Produto p : produtos){
            System.out.println("-> "+p);
        }
    }
}
