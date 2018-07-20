/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackGroundLongTasks;

import BaseDeDatos.ConexionMySQL;
import BaseDeDatos.conexionSQLServer;
import java.math.BigDecimal;
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
public class CierreBonifSNC {
    
    public CierreBonifSNC(Integer idPlan,Integer idSucursal,String aplicacion, Integer idProveedor, BigDecimal accionComer, Integer diaDeveng){
        //OBTENER DATOS DE MYSQL
        //CONTROL DE DATOS ---BORRAR!!!
        System.out.println("idPlan: "+idPlan);
        System.out.println("idSucursal: "+idSucursal);
        System.out.println("aplicacion: "+aplicacion);
        System.out.println("idProveedor: "+idProveedor);
        System.out.println("accion_comercial: "+accionComer);
        System.out.println("diaDeveng: "+diaDeveng);
        String fDesde=getDates(aplicacion);
        System.out.println("dFedes: "+fDesde);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String fHasta = dateFormat.format(cal.getTime());
        //OBTENER ARTICULOS
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        String qryArticulos="select idArticulo from plan_articulo where idPlan_descuento="+idPlan;
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs= st.executeQuery(qryArticulos);
            BigDecimal totalDineroPlan= new BigDecimal(0);
            Integer totalArticulosPlan=0;
            while (rs.next())///recorre cada valor de la consulta y la guarda en las variables.
            {
                Integer idProducto=rs.getInt("idArticulo"); //BUSCARLO DE LA BASE DE DATOS
                
                String qrySNC="SELECT P1.IDPROVEEDOR , P1.NROCOMPROBANTE ,P1.FECHAINGRESO ,P1.IDPRODUCTO, precioUnitario , Sum (P1.Cantidad) As Cantidad" +
                        " From proveedores_ingresos As P1" +
                        " WHERE ((P1.FechaINGRESO) Between Convert(DateTime,'"+fDesde+"',103) And Convert(DateTime,'"+fHasta+"',103)) " +
                        " AND ((P1.IDProducto = "+idProducto+" )) ";
                        if(idSucursal!=0){ qrySNC=qrySNC + " AND  ((P1.IDSucursal ="+idSucursal+"))";} 
                        qrySNC = qrySNC + " AND ((P1.IDPROVEEDOR = "+idProveedor+"))" +
                        " GROUP BY P1.IDPROVEEDOR , P1.NROCOMPROBANTE ,P1.FECHAINGRESO, PrecioUnitario ,P1.IDPRODUCTO";
                //CONECTO A MSSQL
                conexionSQLServer sqlFrom = new conexionSQLServer();
                Connection from = sqlFrom.Conectar();
                Statement stfrom = from.createStatement();
                ResultSet rsFrom= stfrom.executeQuery(qrySNC);
                BigDecimal totalDinero=new BigDecimal(0);
                Integer totalProductos=0;
                while(rsFrom.next()){
                    BigDecimal precioUnitario;
                    Integer cantidad=rsFrom.getInt("Cantidad");
                    BigDecimal cnt = rsFrom.getBigDecimal("cantidad");
                    precioUnitario = rsFrom.getBigDecimal("precioUnitario");
                    totalDinero=totalDinero.add(precioUnitario.multiply(cnt));
                    totalProductos=totalProductos + cantidad;
                    //System.out.println("TOTAL DINERO:" + totalDinero + "TOTAL CANT: "+ cantidad);
                }
                totalDineroPlan=totalDineroPlan.add(totalDinero);
                totalArticulosPlan=totalArticulosPlan+totalProductos;
                //System.out.println(" ARTICULO: "+idProducto +"| DINERO ARTICULO:" + totalDinero + "| TOTAL CANT: "+ totalProductos+"| SUBTOTAL $ PLAN: "+ totalDineroPlan + "| SUBTOTAL # PLAN: "+ totalArticulosPlan);
                //System.out.println(qrySNC);
            }
            BigDecimal descuento=new BigDecimal(0);
            descuento= totalDineroPlan.multiply(accionComer);
            descuento=descuento.divide(new BigDecimal(100));
            //GRABAR DATOS DEL CIERRE
            String qryInsertCierre="insert into cierre values(null,"+ idPlan+",'VARIABLE',"+idProveedor+",now(),"+totalArticulosPlan+","+totalDineroPlan+","+descuento+",0,0,0,0)"; 
            Statement stIns;
            stIns = cn.createStatement();
            Integer resultado= stIns.executeUpdate(qryInsertCierre);
            //System.out.println(qryInsertCierre);
            System.out.println("------------------------");
            System.out.println("PLAN: "+ idPlan);
            System.out.println("TOTAL ARTICULOS PLAN: "+ totalArticulosPlan);
            System.out.println("TOTAL DINERO PLAN $ "+ totalDineroPlan);
            System.out.println("TOTAL ACCION COMERCIAL "+ accionComer);
            System.out.println("TOTAL DESCUENTO "+descuento );
            System.out.println("------------------------");
        } catch (SQLException ex) {
            Logger.getLogger(CierreBonifSNC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getDates(String aplicacion){
        //CALCULO DE FECHA INICIAL DESDE LA CUAL TRAER INFORMACION DEL PLAN
        String result;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        if(aplicacion.equals("Mensual")){
            //APLICACION MENSUAL
            cal.add(Calendar.MONTH, -1);
        }else{
            //SEMANAL
            cal.add(Calendar.DATE, -7);
        }
        result = dateFormat.format(cal.getTime());
        return result;
    }
}
