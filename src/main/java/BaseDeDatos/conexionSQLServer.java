/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDeDatos;

import Modelos.DatabaseConfig;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author Usuario
 */
public class conexionSQLServer {
    /*public String db="databaseName=siga2001";
    public String URL="jdbc:sqlserver://192.168.0.50:1433;"+db;
    public String user= "user_siga";
    public String pass="leer";*/
    
    public Connection Conectar(){
       
        Connection link= null;
        try
        {
            //cargamos el driver mysql que permite conectarse
            //Class.forName("org.gjt.mm.mysql.Driver");
            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                   //enlace hacia la base de datos
            //link= DriverManager.getConnection(this.URL, this.user,this.pass);
            link = DatabaseConfig.getInstanceConnection(DatabaseConfig.MSSQL);
            //System.out.println("conectado correctamente");
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error al conectar a base de datos", 
                "ERROR", JOptionPane.ERROR_MESSAGE);    
        }
    return link;
    }
}
