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
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement st;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO func){
        int status;
        try {
            conn = new conectaDAO().connectDB();
            st = conn.prepareStatement("INSERT INTO produtos VALUES(?,?,?,?)");
            st.setInt(1,func.getId());
            st.setString(2,func.getNome());
            st.setInt(3,func.getValor());
            st.setString(4, func.getStatus());
            status = st.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            status = 0;
            return status;
        }
    }
    
    
    public ArrayList<ProdutosDTO> listarProdutos(){
      conn = new conectaDAO().connectDB();
      String sql = "SELECT * FROM produtos";
      try {
                  
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          ResultSet rs = stmt.executeQuery();
        
          ArrayList<ProdutosDTO> listaProdutos = new ArrayList();
          
          while(rs.next()){
          ProdutosDTO produto = new ProdutosDTO();
          produto.setId(rs.getInt("id"));
          produto.setNome(rs.getString("nome"));
          produto.setValor(rs.getInt("valor"));
          produto.setStatus(rs.getString("valor"));
          listaProdutos.add(produto);
          }
          return listaProdutos;
          
          //tratando o erro, caso ele ocorra
      } catch (Exception e) {
          System.out.println("erro: " + e.getMessage());
          return null;
      }
  }
              
    public int venderProduto(ProdutosDTO func) {
    int status;
    try {
        conn = new conectaDAO().connectDB();
        st = conn.prepareStatement("UPDATE produtos SET status = 'Vendido' WHERE id = ?");
        st.setInt(1, func.getId());
        status = st.executeUpdate();
        return status;
    } catch (SQLException ex) {
        System.out.println("Erro ao conectar: " + ex.getMessage());
        status = 0;
        return status;
    }
}
    
    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            ArrayList<ProdutosDTO> listaProdutosVendidos = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listaProdutosVendidos.add(produto);
            }

            return listaProdutosVendidos;

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }
        
}

