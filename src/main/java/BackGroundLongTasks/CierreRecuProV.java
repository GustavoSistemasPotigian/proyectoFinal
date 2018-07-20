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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class CierreRecuProV {
    
    public CierreRecuProV(Integer idPlan,Integer idSucursal,String aplicacion, Integer idProveedor, BigDecimal accionComer, Integer diaDeveng, String fechaD, String fechaH){
        //OBTENER DATOS DE MYSQL
        //CONTROL DE DATOS ---BORRAR!!!
        System.out.println("idPlan: "+idPlan);
        System.out.println("idSucursal: "+idSucursal);
        System.out.println("aplicacion: "+aplicacion);
        System.out.println("idProveedor: "+idProveedor);
        System.out.println("accion_comercial: "+accionComer);
        System.out.println("diaDeveng: "+diaDeveng);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
  
        fechaD=fechaD+" 00:00:00";
        fechaH=fechaH+" 00:00:00";
        System.out.println("DESDE: " + fechaD + " Hasta: "+ fechaH);
        //OBTENER ARTICULOS DEL PLAN
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
                //OBTENGO ACCION COMERCIAL
                BigDecimal accionCom = getAccionCom(idPlan);
                
                //OBTENGO LA GANANCIA DE ESTE PRODUCTO
                BigDecimal prodVend = getProdGanancia(idProducto,fechaD,fechaH,idSucursal,accionCom);
                
                totalDineroPlan=totalDineroPlan.add(prodVend);
                //totalArticulosPlan=totalArticulosPlan+prodVend;
                //System.out.println("COSTO: " + costoProd + " ACCION COM: "+ accionCom + " MARGEN: "+ margenProd + "VENDIDOS:" +prodVend + "MARGEN ARTICULO TOT: "+ margenArtTot);               
            }
            
            //GRABAR DATOS DEL CIERRE
            String qryInsertCierre="insert into cierre values(null,"+ idPlan+",'VARIABLE',"+idProveedor+",now(),"+totalArticulosPlan+","+totalDineroPlan+","+totalDineroPlan+",0,0,0,0)"; 
            Statement stIns;
            System.out.println(qryInsertCierre);
            stIns = cn.createStatement();
            //UPDATE EN DB
            Integer resultado= stIns.executeUpdate(qryInsertCierre);
            /*
            System.out.println("------------------------");
            System.out.println("PLAN: "+ idPlan);
            System.out.println("TOTAL ARTICULOS PLAN: "+ totalArticulosPlan);
            System.out.println("TOTAL DINERO PLAN $ "+ totalDineroPlan);
            System.out.println("TOTAL ACCION COMERCIAL "+ accionComer);
            //System.out.println("TOTAL DESCUENTO "+descuento );
            System.out.println("------------------------");
            */
        } catch (SQLException ex) {
            Logger.getLogger(CierreBonifSNC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private BigDecimal getProdGanancia(Integer idArticulo,String fDesde,String fHasta,Integer sucursal, BigDecimal accionCom){
        BigDecimal cantidadProd= new BigDecimal(0);
        conexionSQLServer sqlFrom = new conexionSQLServer();
        Connection from = sqlFrom.Conectar();
        Statement stfrom;
        // TOMA SOLO LOS PRODUCTOS QUE TENGAN EL FlagOferta=1
        String qryCantProd="select C.Cli_NroListaPrecio, I.IDProducto, I.Pro_Desc_Producto, " +
                            "       Sum (I.CantidadProducto) As Cantidad,  " +
                            "       Sum (I.ImporteNeto) As ImporteNeto from Comprobante_ITM as I " +
                            "Inner Join Comprobante_CBZ as C on C.IDComprobante=I.IDComprobante " +
                            "Where ((C.FechaEmision) Between Convert(DateTime,'"+fDesde+"',120) And Convert(DateTime,'"+fHasta+"',120)) AND " +
                            "      ((I.IDProveedor) Between 101 And 949) AND " +
                            "      ((C.Cli_NroListaPrecio) Between 1 And 99) AND " +
                            "      ((C.Cli_NroEscalaFacturacion) = 1) AND " +
                            "      ((C.IDSucursalEmisora) <> 88) AND ";
                            if(sucursal!=0){
                                          qryCantProd= qryCantProd+" and C.IDSucursalEmisora="+sucursal;
                            }
                        qryCantProd= qryCantProd +  "      ((C.IDRecurso_Vendedor) <> 20014) AND " +
                            "      ((C.Cli_IDZonaReparto) < 990) and " +
                            "      idproducto=" + idArticulo +
                            "Group by C.Cli_NroListaPrecio, I.IDProducto, I.Pro_Desc_Producto\n" +
                            "Order by C.Cli_NroListaPrecio, Sum (I.CantidadProducto) Desc;";
                            //System.out.println(qryCantProd);
        try {
            stfrom = from.createStatement();
            ResultSet rsFrom= stfrom.executeQuery(qryCantProd);
            while(rsFrom.next()){
                BigDecimal impNeto = rsFrom.getBigDecimal("importeneto");
                
                impNeto=impNeto.divide(new BigDecimal(100));
                impNeto=impNeto.multiply(accionCom);
                cantidadProd=cantidadProd.add(impNeto);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CierreAcuPrecios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidadProd;
    }
    
    private BigDecimal getAccionCom(Integer idPlan){
        //RECIBE ID DE PLAN Y DEVUELVE ACCION COMERCIAL
        BigDecimal accionCom= new BigDecimal(0);
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        Statement st;
        String qryAccionCom=" select accion_comercial from plan_descuento where idPlan_descuento="+idPlan;
        try {
            st = cn.createStatement();
            ResultSet rs= st.executeQuery(qryAccionCom);
            while(rs.next()){
                accionCom=rs.getBigDecimal("accion_comercial");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CierreAcuPrecios.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accionCom;
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
