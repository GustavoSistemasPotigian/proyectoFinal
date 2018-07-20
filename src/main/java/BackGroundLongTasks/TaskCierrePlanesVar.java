/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackGroundLongTasks;
import BaseDeDatos.ConexionMySQL;
import BaseDeDatos.conexionSQLServer;
import DateManager.DateProvider;
import Modelos.Articulos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import LogManager.BitacoraCambios;
import Modelos.NotasCredito;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Usuario
 */
public class TaskCierrePlanesVar extends SwingWorker<Integer,String>{
     private JLabel lblProgreso;
     private JLabel lblProgresoA;
     private JProgressBar pbAvance;
     private Integer avance=0;
     private Integer diaSemana;
     private Integer diaMes;
     @Override
  protected Integer doInBackground() throws Exception {
    // Start
    publish("Ejecutando proceso...");
    setProgress(1);
    publish("Generando planes variables...");
    refreshA();
    publish("FINALIZADO");
    setProgress(100);
    return 1;
  }
  @Override
  protected void process(List< String> chunks) {
    // Messages received from the doInBackground() (when invoking the publish() method)
    for (final String string : chunks) {
     if(string.equals("FINALIZADO")){
       avance=100;
       lblProgresoA.setText("ACTUALIZADO");
       BitacoraCambios bc = new BitacoraCambios();
       bc.insertData("articulos");
     }else{
        avance=avance+5;
     }
     lblProgreso.setText(string);
     pbAvance.setValue(avance);
    }
  }
  public TaskCierrePlanesVar(JLabel etiqueta, JLabel etiquetaA, JProgressBar barraProg){
      this.lblProgreso=etiqueta;
      this.pbAvance=barraProg;
      this.lblProgresoA=etiquetaA;
      DateProvider dateProv = new DateProvider();
        diaSemana=dateProv.getDayofWeek();
        diaMes=dateProv.getDayofMonth();
  }

  private void refreshA(){
      int rs2=0;
      System.out.println("Comienza Cierre planes Variables");
        setProgress(10);
        publish("Generando Variables");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
        String fechaHoy=dateFormat.format(date);
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        //PREPARO CONSULTA PARA OBTENER FECHA
        setProgress(20);
        String queryPF="select idPlan_Descuento,aplicacion,dia_devengamiento,rubro,accion_comercial,Proveedor_idproveedor, sucursal_idsucursal,Motivo_SNC_idMotivo_SNC, tipo_IVA_idtipo_IVA,m.tipo,p.fecha_devengamiento_desde,p.fecha_devengamiento_hasta "+
                        "from plan_descuento p, motivo_snc m " +
                        "where p.tipo_plan='VARIABLE' and m.idMotivo_SNC=p.Motivo_SNC_idMotivo_SNC " +
                        "and p.fecha_devengamiento_desde<='"+fechaHoy+"' " +
                        "and p.fecha_devengamiento_hasta>='"+fechaHoy+"' " +
                        "and ((aplicacion='Mensual' and dia_devengamiento="+diaMes+") " +
                        "or (aplicacion='Semanal' and dia_devengamiento="+diaSemana+"))";
       System.out.println(queryPF);
        //lblProgreso.setText("FUNCIONA");
        publish("Cierre articulos fijos...");
        //OBTENGO FECHA Y LA CARGO EN EL LABEL
        publish("Generando cierres planes variables...");
        setProgress(50);
        try {
            Statement st= cn.createStatement();
            setProgress(75);
            ResultSet rs= st.executeQuery(queryPF);
            while (rs.next())///recorre cada valor de la consulta y la guarda en las variables.
            {
                //RECORRO TODOS LOS PLANES A CERRAR EN ESTE CIERRE
                Integer idPlan=rs.getInt("idPlan_Descuento");
                String aplicacion=rs.getString("aplicacion");
                Integer diaDev=rs.getInt("dia_devengamiento");
                Integer rubro = rs.getInt("rubro");
                //SET BIG DECIMAL
                BigDecimal accionCom = rs.getBigDecimal("accion_comercial");
                Integer idProv = rs.getInt("Proveedor_idproveedor");
                Integer idSuc = rs.getInt("sucursal_idsucursal");
                Integer motivoDesc = rs.getInt("Motivo_SNC_idMotivo_SNC");
                Integer tipoIva = rs.getInt("tipo_IVA_idtipo_IVA");
                Integer tipoPlan = rs.getInt("tipo");
                String fechaD = rs.getString("fecha_devengamiento_desde").toString();
                String fechaH = rs.getString("fecha_devengamiento_hasta").toString();
               // LLAMADA A DISPATCHER DE GENERACION DE PLANES
                publish("Generando planes de tipo "+motivoDesc);
                System.out.println("-----------------> PLAN aplicacion "+ motivoDesc + " plan: "+idPlan);
                CheckIfClosed isClosed = new CheckIfClosed();
                if(!isClosed.CheckIfClosed(idPlan)){
                    getPlanDataCompra(idPlan, motivoDesc, idSuc,  aplicacion, idProv, accionCom, diaDev,fechaD,fechaH);
                }else{
                    System.out.println("EL PLAN "+idPlan + " YA ESTA CERRADO");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
            /*if(rs2==0){
                System.out.println("Cierre planes fijos finalizado OK");
                setProgress(100);
            } */  
    }
    private void getPlanDataCompra(Integer idPlan, Integer tipo_plan,Integer idSucursal, String aplicacion, Integer idProveedor, BigDecimal accionComercial, Integer diaDeveng,String fechaD,String fechaH){
        //BORRAR
        Integer pp=0;
        switch(tipo_plan){
            case 70: //BONIFICACION SOBRE NOTA DE CREDITO
                     System.out.println("ENVIANDO PLAN 70");
                     bonifSNC(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng);
                     break;
            case 71: //BONIFICACION POR VOLUMEN DE COMPRA
                    System.out.println("ENVIANDO PLAN 71");
                     bonifVC(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng);
                     break;
            case 72: //DEVOLUCION DE MERCADERIA
                        System.out.println("ENVIANDO PLAN 72");
                        devMerc(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng,fechaD,fechaH);
                     break;
            case 73: //NUEVOS EMPRENDIMIENTOS
                     descAcord(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng,fechaD,fechaH);
                     break;
            case 74: //ANIVERSARIO
                     descAcord(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng,fechaD,fechaH);
                     break;
            case 75: //FLETES
                     descAcord(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng,fechaD,fechaH);
                     break;
            case 76: //ACUERDO DE PRECIOS
                     acuPrecios(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng,fechaD,fechaH);
                     break;
            case 77: //RECUPERO PROMOCIONAL COSTO X COSTO
                       recuperoPromCto(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng,fechaD,fechaH);
                     break;
            case 78: //RECUPERO PROMOCIONAL VTA X VTA
                        recuperoPromVta(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng,fechaD,fechaH);
                     break;
            case 79: //PRECIO PROMOCIONAL
                     precioProm(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng,fechaD,fechaH);
                     break;
        }
    }
    
    private void bonifSNC(Integer idPlan,Integer idSucursal,String aplicacion, Integer idProveedor, BigDecimal accionComercial, Integer diaDeveng){
       //INGRESO DE MERCAD * COSTO sobre esto CALCULO PROCENTAJE ACORDADO CON PROV
       //GENERA NOTA DE CREDITO
       CierreBonifSNC bonifSnc = new CierreBonifSNC(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng);
    }
    
    private void bonifVC(Integer idPlan,Integer idSucursal,String aplicacion, Integer idProveedor, BigDecimal accionComercial, Integer diaDeveng){
        CierreBonifVC bonifVC = new CierreBonifVC(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng);
    }
    private void devMerc(Integer idPlan,Integer idSucursal,String aplicacion, Integer idProveedor, BigDecimal accionComercial, Integer diaDeveng,String fechaD, String fechaH){
        CierreDevMerc devMerc = new CierreDevMerc(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng,fechaD,fechaH);
    }
    private void descAcord(Integer idPlan,Integer idSucursal,String aplicacion, Integer idProveedor, BigDecimal accionComercial, Integer diaDeveng,String fechaD, String fechaH){
        CierreDescAcord descAco = new CierreDescAcord(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng,fechaD,fechaH);
    }
   
    private void acuPrecios(Integer idPlan,Integer idSucursal,String aplicacion, Integer idProveedor, BigDecimal accionComercial, Integer diaDeveng,String fechaD, String fechaH){
        CierreAcuPrecios acuPre = new CierreAcuPrecios(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng,fechaD,fechaH);
    }
    private void recuperoPromCto(Integer idPlan,Integer idSucursal,String aplicacion, Integer idProveedor, BigDecimal accionComercial, Integer diaDeveng,String fechaD, String fechaH){
        CierreRecuPro acuPromCto = new CierreRecuPro(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng,fechaD,fechaH);
    }
    private void recuperoPromVta(Integer idPlan,Integer idSucursal,String aplicacion, Integer idProveedor, BigDecimal accionComercial, Integer diaDeveng,String fechaD, String fechaH){
        CierreRecuProV acuPromVta = new CierreRecuProV(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng,fechaD,fechaH);
    }
    private void precioProm(Integer idPlan,Integer idSucursal,String aplicacion, Integer idProveedor, BigDecimal accionComercial, Integer diaDeveng,String fechaD, String fechaH){
        CierreAcuPrecios acuPre = new CierreAcuPrecios(idPlan,idSucursal,aplicacion,idProveedor,accionComercial,diaDeveng,fechaD,fechaH);
    }
    public void copyA() throws SQLException {
        conexionSQLServer sqlFrom = new conexionSQLServer();
        Connection from = sqlFrom.Conectar();
        ConexionMySQL mysqlTo= new ConexionMySQL();
        Connection to= mysqlTo.Conectar();  
        System.out.println("Entrando el try");
        try (
         PreparedStatement s1 = from.prepareStatement("select art.IDProducto as idArticulo," +
                    "art.Desc_Producto as descripcion, " +
                    "art.IDContenidoUnidaddeVenta as unidad_de_venta," +
                    "art.FechaAlta as fecha_ingreso_inicial," +
                    "art.FechaCambioPrecio as fecha_ultimo_ingreso," +
                    "null as precio_costo," +
                    "null as precio_vigente," +
                    "null as margen," +
                    "null as proveedor_idproveedor ," +
                    "null as cantidad_total," +
                    "art.IDRubro as idSector " +
                    "from productos art " + 
                    "where art.IDSituacionProducto='AC' " +
                    "order by 1");
         ResultSet rs = s1.executeQuery()) {
         ResultSetMetaData meta = rs.getMetaData();
         System.out.println("Entrando al for");
        List<String> columns = new ArrayList<>();
        for (int i = 1; i <= meta.getColumnCount(); i++)
            columns.add(meta.getColumnName(i));
           setProgress(50);
           publish("Obteniendo datos...");
           System.out.println("Obteniendo datos");
        try (PreparedStatement s2 = to.prepareStatement(
                "INSERT INTO articulo("
              + columns.stream().collect(Collectors.joining(", "))
              + ") VALUES ("
              + columns.stream().map(c -> "?").collect(Collectors.joining(", "))
              + ")"
        )) {
            publish("Actualizando datos de Articulos...");
            System.out.println("Entrando al actualizando art");
            setProgress(80);
            while (rs.next()) {
                for (int i = 1; i <= meta.getColumnCount(); i++)
                    s2.setObject(i, rs.getObject(i));
                    s2.addBatch();
            }
            s2.executeBatch();
        }
        //publish("FINALIZADO");
    }
        
}
    protected void done() {
                Integer bStatus = 0;
                try {
                    bStatus = get();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                System.out.println("Proceso finalizado con estado " + bStatus);
                if (bStatus==1){
                    System.out.println("Debo ejecutar la actualizacion de proveedores");
                    JOptionPane.showMessageDialog(null, "Planes generados correctamente!!!");
                    //LLAMAR A MOSTAR NOTAS DE CREDITO
                    NotasCredito nc = new NotasCredito();
                    nc.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Articulos actualizado con error!");
                }
            }
}
