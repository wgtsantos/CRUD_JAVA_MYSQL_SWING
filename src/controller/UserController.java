/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Usuario;
import view.Tela_Listar;

/**
 *
 * @author Instrutor
 */
public class UserController {
    
    public void Salvar(Usuario user) {
        
        String sql = "INSERT INTO usuario(nome, email, cpf, telefone, senha) VALUES (?, ?, ?, ?, ?)";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            // Criando conexao com o banco de dados 
            conn = Conexao.createConnectionToMySQL();
            
            // Executando a conexao juntamente com a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            // Adicionar os valores que são esperados na query 
            pstm.setString(1, user.getNome());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getCpf());
            pstm.setString(4, user.getTelefone());
            pstm.setString(5, user.getSenha());
            
            // Executar a query
            pstm.execute();
            
            JOptionPane.showMessageDialog(null,
                    "Usuário Cadastrado com Sucesso!!", "Confirmação",
                    JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erro ao Cadastradar Usuário!!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            
        } finally {
            
            try {
                
                if (pstm != null) {
                    pstm.close(); 
                }
                if (conn != null) {
                    conn.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    }
    
    public List<Usuario> ListarUsers() {
        
        String sql = "Select * from usuario";
        
        List<Usuario> users = new ArrayList<Usuario>();
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        // Classe para recuperar os dados do banco ** SELECT ** 
        ResultSet rset = null;
        
        try {
            
            // Criando conexao com o banco de dados 
            conn = Conexao.createConnectionToMySQL();
            
            // Executando a conexao juntamente com a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            rset = pstm.executeQuery();
            
            while (rset.next()) {                
                 
                Usuario user = new Usuario();
                
                user.setId(rset.getInt("id_usuario"));
                user.setNome(rset.getString("nome"));
                user.setEmail(rset.getString("email"));
                user.setCpf(rset.getString("cpf"));
                user.setTelefone(rset.getString("telefone"));
                user.setSenha(rset.getString("senha"));
                
                users.add(user);
                
            }
            
        } catch (Exception e) {
            
            e.printStackTrace();
            System.out.println("Error ao listar Usuários!!");
              
        }  finally {
            
            try {
                
                if (pstm != null) {
                    pstm.close(); 
                }
                if (conn != null) {
                    conn.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
        return users;
        
    }
    
    public void ListUsuarios(Tela_Listar tl) {
        
        DefaultTableModel model = (DefaultTableModel) tl.jTable1.getModel();
         
        Object linhas[] = new Object[6];
        
        for (Usuario usuario : this.ListarUsers()) {

            linhas[0] = usuario.getId();
            linhas[1] = usuario.getNome();
            linhas[2] = usuario.getEmail();
            linhas[3] = usuario.getCpf();
            linhas[4] = usuario.getTelefone();
            linhas[5] = usuario.getSenha();
            
            model.addRow(linhas);
            
        }
        
    }
    
    public void atualizar(Usuario user) {
        
        String sql = "UPDATE usuario SET nome = ?, email = ?, cpf = ?, telefone = ?, senha = ? WHERE id_usuario = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            
            // Criando conexao com o banco de dados 
            conn = Conexao.createConnectionToMySQL();
            
            // Executando a conexao juntamente com a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            pstm.setString(1, user.getNome());
            pstm.setString(2, user.getEmail());
            pstm.setString(3, user.getCpf());
            pstm.setString(4, user.getTelefone());
            pstm.setString(5, user.getSenha());
            
            // Buscar qual ID de usuario irá atualizar
            pstm.setInt(6, user.getId());
            
            // Executar a Query
            pstm.execute();
            
            JOptionPane.showMessageDialog(null,
                    "Usuário Atualizado com Sucesso!!", "Confirmação",
                    JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erro ao tentar atualizar o usuário!!", "Confirmação",
                    JOptionPane.ERROR_MESSAGE);
            
        } finally {
            
            try {
                
                if (pstm != null) {
                    pstm.close(); 
                }
                if (conn != null) {
                    conn.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }
    
    public void excluir(int id) {
        
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        
        Connection conn = null;
        PreparedStatement pstm = null;
        
        try {
            
            // Criando conexao com o banco de dados 
            conn = Conexao.createConnectionToMySQL();
            
            // Executando a conexao juntamente com a query
            pstm = (PreparedStatement) conn.prepareStatement(sql);
            
            // Qual o Registro id deseja deletar
            pstm.setInt(1, id);
            
            pstm.execute();
            
            JOptionPane.showMessageDialog(null,
                    "Usuário excluído com Sucesso!!", "Confirmação",
                    JOptionPane.INFORMATION_MESSAGE);
            
        } catch (Exception e) {
            e.printStackTrace();
            
           JOptionPane.showMessageDialog(null,
                    "Erro ao tentar excluir o usuário!!", "ERROR",
                    JOptionPane.ERROR_MESSAGE);
            
        } finally {
            
            try {
                
                if (pstm != null) {
                    pstm.close(); 
                }
                if (conn != null) {
                    conn.close();
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
    }
    
    public boolean Login(String login, String senha) {
        
        boolean check = false;
        
        for (Usuario usuario : this.ListarUsers()) {
            
            if (usuario.getEmail().equals(login) && 
                usuario.getSenha().equals(senha)) {
                
                check = true;
                
            }
            
        }
        
        return check;
    }
    
}
