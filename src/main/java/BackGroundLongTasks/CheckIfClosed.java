/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackGroundLongTasks;

import BaseDeDatos.ConexionMySQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class CheckIfClosed {
    public boolean CheckIfClosed(Integer idPlan){
        boolean response=false;
        //SE OBTIENE FECHA DE HOY Y SE DA FORMATO
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String fechaHoy = dateFormat.format(cal.getTime());
        //CONEXION A LA DB
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        //SELECT A LA DB
        String qryCheckIfClosed="select count(*) as cantidad from cierre where id_plan_descuento="+idPlan+ " and date_format(fecha_cierre,'%Y-%m-%d')='"+fechaHoy+"'";
        Statement stCount;
        try {
            stCount = cn.createStatement();
            ResultSet rsCount= stCount.executeQuery(qryCheckIfClosed);
            while(rsCount.next()){
                //SI EXISTE CIERRE SE DEVUELVE TRUE, SINO SE DEVUELVE FALSE PARA PROCESAR EL CIERRE
                if(rsCount.getInt("cantidad")==0){
                    response=false;
                }else{
                    response=true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckIfClosed.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
    public Boolean fixClosed(){
        Boolean rta=false;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
        String fechaHoy=dateFormat.format(date);
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
            String qry="select count(*) as total from cierre where tipo_plan='FIJO' and date_format(fecha_cierre,'%Y-%m-%d')='"+fechaHoy+"'";
            //System.out.println("CONSULTA DE CIERRE");
            //System.out.println(qry);
            Statement st;
        try {
            st = cn.createStatement();
            ResultSet rsCount= st.executeQuery(qry);
            System.out.println("CONSULTA DE CIERRE");
            
            
            while(rsCount.next()){
                if(rsCount.getInt("total")>0){
                    rta=true;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CheckIfClosed.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return rta;
    }
}
