package BaseDeDatos;

import Modelos.DatabaseConfig;
import java.sql.*;
import javax.swing.JOptionPane;
  
///clase que conecta con la base de datos potigianBase en MySQL
public class ConexionMySQL {
    //variableq que guarda los datos de conexion
    /*
    public String db="potigianbase";
    public String URL="jdbc:mysql://localhost/"+db;
    public String user= "root";
    public String pass="1234qwer";
    */

public ConexionMySQL()
{

}
    //clase para Conectar a la base de datos con url guardada en variable
   //Devuelve objeto del tipo Connection
    public Connection Conectar(){
       
        Connection link= null;
        try
        {
            //cargamos el driver mysql que permite conectarse
            //Class.forName("org.gjt.mm.mysql.Driver");
            
            //enlace hacia la base de datos
            //link= DriverManager.getConnection(this.URL, this.user,this.pass);
            //System.out.println("conectado correctamente");
            link = DatabaseConfig.getInstanceConnection(DatabaseConfig.MYSQL);
        }
        catch (Exception e)
        {
            //JOptionPane.showMessageDialog(null, pass);
            JOptionPane.showMessageDialog(null, "Error al conectar a base de datos", 
                "ERROR", JOptionPane.ERROR_MESSAGE);    
        }
    return link;
    }
    
    public void Desconectar(){
    //ACA DEBO CERRAR LA SESION
    }
}   

