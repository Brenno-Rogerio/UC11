/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        try{
            conn = new conectaDAO().connectDB();
            String linha = "insert into produtos (nome, valor, status) values (?,?,?)";
            
            prep = conn.prepareStatement(linha);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERRO! Verifique os campos ou tente novamente mais tarde.");
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
        try{
            conn = new conectaDAO().connectDB();
            System.out.println("1");
            String linha = "select * from produtos";
            System.out.println("2");
            
            prep = conn.prepareStatement(linha);
            System.out.println("3");
            resultset = prep.executeQuery();
            System.out.println("4");
            
            System.out.println("5");
            
            while(resultset.next()){
                ProdutosDTO produto = new ProdutosDTO();
                System.out.println("1");
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("nome"));
                produto.setValor(resultset.getInt("valor"));
                produto.setStatus(resultset.getString("Status"));
                
                listagem.add(produto);
                System.out.println("6");
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "ERRO! Tente novamente mais tarde.");
        }
        return listagem;
    }
    
    
    
        
}

