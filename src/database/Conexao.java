/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Instrutor
 */
public class Conexao {
    
    // Nome do usuário do Mysql - Banco de Dados 
    private static final String USERNAME = "root";
    
    // Senha de Acesso ao Banco de Dados MySql
    private static final String PASSWORD = "";
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/crud_java_ui";
    
    // Método de Conexao com o Banco de Dados MySql
    
    public static Connection createConnectionToMySQL() throws Exception {
        
        // Carrega a classe de conexao java na biblioteca jdbc mysql
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        // Cria a conexao com o Banco de Dados
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        
        return connection;
        
    }
    
    public static void main(String[] args) throws Exception {
        
       // Criando uma conexao com o Banco de Dados 
       Connection conn = createConnectionToMySQL();
       
        if (conn != null) {
            System.out.println("Conexão Estabelecida com Sucesso!");
            conn.close();
        } else {
            System.out.println("Erro na Conexão com o banco de dados!");
        }
        
    }
    
}
