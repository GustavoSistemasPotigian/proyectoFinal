/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import BaseDeDatos.ConexionMySQL;
import BaseDeDatos.conexionSQLServer;
import java.awt.Color;
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
import javax.swing.table.DefaultTableModel;
import BackGroundLongTasks.TaskArticulos;
import BackGroundLongTasks.TaskCierrePlanesFijos;
import BackGroundLongTasks.TaskCierrePlanesVar;
import DateManager.DateProvider;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;

/**
 *
 * @author usuario
 */
public class CierreDiarioEspeciales extends javax.swing.JFrame {
    /**
     * Creates new form Articulos
     */
    DefaultTableModel modelo; 
    public CierreDiarioEspeciales() {
        initComponents();
        getCantidadCierres();
        disableStatus();
        
    }
    private void getCantidadCierres(){
        //OBTENGO FECHA DEL DIA Y SACO CUANTOS PLANES TENGO QUE IMPUTAR HOY
        Integer diaSemana,diaMes,cantFijos,cantVariables;
        cantFijos=0;
        cantVariables=0;
        DateProvider dateProv = new DateProvider();
        diaSemana=dateProv.getDayofWeek();
        diaMes=dateProv.getDayofMonth();
        //OBTENGO CANTIDAD DE PLANES
       //cantVariables=getCantVariables(diaSemana,diaMes);
        cantFijos=getCantFijos(diaSemana,diaMes);
        //ACTUALIZAR TOTAL
        Integer total=cantFijos+cantVariables;
        lblTotal.setText(total.toString());
    }
   /* private Integer getCantVariables(Integer diaS,Integer diaM){
        Integer cDiaM=0;
        Integer cDiaS=0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
        String fechaHoy=dateFormat.format(date);
        //CONECTO DB MYSQL
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        String query="select aplicacion,count(*) as cantidad from plan_descuento " +
                        "where tipo_plan='PENDIENTE' and situacion='ACTIVO' " +
                        "and fecha_devengamiento_desde<='"+fechaHoy+"' " +
                        "and fecha_devengamiento_hasta>='"+fechaHoy+"' "+
                        "and ((aplicacion='Mensual' and dia_devengamiento="+diaM+") " +
                        "or (aplicacion='Semanal' and dia_devengamiento="+diaS+"))" +
                        "group by 1;";
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(query);
            while (rs.next())///recorre cada valor de la consulta y la guarda en las variables.
            {
                //lblFechaP.setText(rs.getString("fecha"));
                if(rs.getString("aplicacion").equals("Mensual")){
                    cDiaM=rs.getInt("cantidad");
                }else{
                    cDiaS=rs.getInt("cantidad");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblPVariables.setText(cDiaM+cDiaS+ "  ( "+cDiaM+" Mensual + "+ cDiaS+ " Semanal )");
        return cDiaM+cDiaS;
    }*/
    private Integer getCantFijos(Integer diaS,Integer diaM){
        Integer cDiaM=0;
        Integer cDiaS=0;
        //OBTENGO LA FECHA DE HOY
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date date = new Date();
        String fechaHoy=dateFormat.format(date);
        
        //CONECTO DB MYSQL
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        String query="select aplicacion,count(*) as cantidad from plan_descuento " +
                        "where tipo_plan='PENDIENTE' and situacion='ACTIVO' "+
                        "and fecha_devengamiento_desde<='"+fechaHoy+"' " +
                        "and fecha_devengamiento_hasta>='"+fechaHoy+"' "+
                        "and ((aplicacion='Mensual' and dia_devengamiento="+diaM+") " +
                        "or (aplicacion='Semanal' and dia_devengamiento="+diaS+"))" +
                        "group by 1;";
        System.out.println(query);
        try {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(query);
            while (rs.next())///recorre cada valor de la consulta y la guarda en las variables.
            {
                //lblFechaP.setText(rs.getString("fecha"));
                if(rs.getString("aplicacion").equals("Mensual")){
                    cDiaM=rs.getInt("cantidad");
                }else{
                    cDiaS=rs.getInt("cantidad");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblPFijos.setText(cDiaM+cDiaS+ "  ( "+cDiaM+" Mensual + "+ cDiaS+ " Semanal )");
        return cDiaM+cDiaS;
    }
    private void disableStatus(){
        //lblTitEstado.setEnabled(false);
        //lblEstado.setEnabled(false);
    }
    private void enableStatus(){
        this.lblTitEstado.setEnabled(true);
        this.lblEstado.setEnabled(true);
        this.lblEstado.setText("Iniciando Actualización...");
    }   
    
       /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnEditar = new javax.swing.JMenuItem();
        mnEliminar = new javax.swing.JMenuItem();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnNuevoArticulo = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblTitEstado = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblPFijos = new javax.swing.JLabel();
        pbProgreso = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();

        jPopupMenu1.setComponentPopupMenu(jPopupMenu1);

        mnEditar.setText("Modificar");
        mnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnEditarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnEditar);

        mnEliminar.setText("Eliminar");
        mnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnEliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnEliminar);

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Potigian- Cierre Diario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("Planes Especiales:");

        btnNuevoArticulo.setText("Generar");
        btnNuevoArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoArticuloActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblTitEstado.setText("Estado:");

        lblEstado.setText("Generación pendiente");
        lblEstado.setDebugGraphicsOptions(javax.swing.DebugGraphics.BUFFERED_OPTION);

        jLabel7.setText("Datos de cierre para la fecha:");

        lblPFijos.setText("0");

        jLabel3.setText("A GENERAR:");

        lblTotal.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pbProgreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNuevoArticulo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblTitEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lblPFijos, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                                    .addComponent(lblTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 37, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblPFijos))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblTotal))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTitEstado)
                    .addComponent(lblEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pbProgreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevoArticulo)
                    .addComponent(btnSalir)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
        
    
    
    private void mnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnEditarActionPerformed
       
    }//GEN-LAST:event_mnEditarActionPerformed

    private void mnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnEliminarActionPerformed
         
    }//GEN-LAST:event_mnEliminarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnNuevoArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoArticuloActionPerformed
        //DESHABILITARME
        this.lblEstado.setEnabled(true);
        this.lblTitEstado.setEnabled(true);
        btnNuevoArticulo.setEnabled(false);
        //EJECUTAR UPDATE
        System.out.println("-----GENERANDO CIERRES-----");
        //PROCESO EN BACKGROUND
        ConsultaPendientes ventanaConsulta= new ConsultaPendientes();
        ventanaConsulta.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventanaConsulta.setVisible(true);


        //TaskCierrePlanesVar taskPV = new TaskCierrePlanesVar(lblEstado,lblPFijos,pbProgreso);
        //taskPV.execute();
    }//GEN-LAST:event_btnNuevoArticuloActionPerformed
    
    void BuscarUsuarioEliminar(String idArticulo)
   {
      
    
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Articulos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevoArticulo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblPFijos;
    private javax.swing.JLabel lblTitEstado;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JMenuItem mnEditar;
    private javax.swing.JMenuItem mnEliminar;
    private javax.swing.JProgressBar pbProgreso;
    // End of variables declaration//GEN-END:variables
}
