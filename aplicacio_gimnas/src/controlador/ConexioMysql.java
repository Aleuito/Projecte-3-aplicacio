/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.sql.*;

    public class ConexioMysql {

        // Propiedades
        private static Connection conn = null;
        private String driver;
        private String url;
        private String usuario;
        private String password;

        // Constructor
    public ConexioMysql(){

        String url = "jdbc:mysql://192.168.1.129/test";
        String driver = "com.mysql.jdbc.Driver";
        String usuario = "dam2021";
        String password = "Dam2021!";

        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        
    } // Fin constructor

        // Métodos
        public Connection getConnection(){

            if (conn == null){
                new ConexioMysql();
            }

            return conn;
        } // Fin getConnection
        }
