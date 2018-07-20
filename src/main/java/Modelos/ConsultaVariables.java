/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import BaseDeDatos.ConexionMySQL;
import Reportes.GenerarReportes;
import Utils.ExcelUtils;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author usuario
 */
public class ConsultaVariables extends javax.swing.JFrame {

    /**
     * Creates new form ConsultaVariables
     */
    DefaultTableModel modelo;
    private String UID;
    public ConsultaVariables(String userId) {
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        UID=userId;
        initComponents();
        CargarTablaPlanesVariables("");
        //CARGAR COMBO PROVEEDORES
        loadCmbProveedores();
        //CARGAR COMBO SECTOR
        loadCmbSector();
        //CARGAR COMBO MOTIVO
        loadCmbMotivo();
        //CARGAR COMBO VENDEDOR
        loadCmbComprador();
        
        tblConsultaPlanes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
private void loadCmbProveedores(){
        cmbProveedor.removeAllItems();
        this.cmbProveedor.addItem("--SELECCIONAR--");
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        String sSQL= "SELECT idproveedor, razon_social FROM proveedor order by 1";
        try 
        {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sSQL);
            while (rs.next())///recorre cada valor de la consulta y la guarda en las variables.
            {
                this.cmbProveedor.addItem(rs.getInt("idproveedor") + " - " + rs.getString("razon_social"));
            }               
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
        
    }
    private void loadCmbSector(){
        cmbSector.removeAllItems();
        this.cmbSector.addItem("--SELECCIONAR--");
        String prov;
            //cadena a separar
            prov= cmbSector.getSelectedItem().toString();
         String[] parts=new String[3];
         
        ///ESTO DESCONCATENA EL STRING PROVEEDOR QUE VIENE CONCATENADO CON EL ID_PROV 
            
         if (!prov.equals("Item 1")){
                   int i=0;
                   parts[0]="PENDIENTE";
                   parts[1]="ENTREGADO";
                   parts[2]="DESCONTADO";
                   System.out.println(parts.length);
                   while (i<parts.length)///recorre cada valor de la consulta y la guarda en las variables.
                {
                        
                       String texto=(parts[i]);
                         this.cmbSector.addItem(texto);
                         i++;
                    }
            }          
        /*ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        String sSQL= "select descripcion from sector order by 1";
        try 
        {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sSQL);
            
            while (rs.next())///recorre cada valor de la consulta y la guarda en las variables.
            {
                this.cmbSector.addItem(rs.getString("descripcion")); 
            }               
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }*/
    }
    private void loadCmbMotivo(){
        cmbMotivo.removeAllItems();
        this.cmbMotivo.addItem("--SELECCIONAR--");
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        String sSQL= "select descripcion from motivo_snc order by 1";
        try 
        {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sSQL);
            while (rs.next())///recorre cada valor de la consulta y la guarda en las variables.
            {
                this.cmbMotivo.addItem(rs.getString("descripcion")); 
            }               
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    private void loadCmbComprador(){
        cmbComprador.removeAllItems();
        this.cmbComprador.addItem("--SELECCIONAR--");
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        String sSQL="";
        sSQL= "select concat (apellido,\" \", nombre) as comprador from usuario order by comprador";
        try 
        {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sSQL);
            while (rs.next())///recorre cada valor de la consulta y la guarda en las variables.
            {
                this.cmbComprador.addItem(rs.getString("comprador")); 
            }               
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuItemArticulos = new javax.swing.JMenuItem();
        jMenuItemCambio = new javax.swing.JMenuItem();
        jMenuItemModificacion = new javax.swing.JMenuItem();
        jMenuItemBaja = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscarPlan = new javax.swing.JTextField();
        btnBuscarPlan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsultaPlanes = new javax.swing.JTable();
        btnSalirConsulta = new javax.swing.JButton();
        cmbProveedor = new javax.swing.JComboBox<>();
        cmbSector = new javax.swing.JComboBox<>();
        cmbMotivo = new javax.swing.JComboBox<>();
        dpFInicio = new org.jdesktop.swingx.JXDatePicker();
        dpFFin = new org.jdesktop.swingx.JXDatePicker();
        cmbComprador = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        Rubro = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jMenuItemArticulos.setText("Ver Articulos");
        jMenuItemArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemArticulosActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItemArticulos);

        jMenuItemCambio.setText("Cambiar Estado");
        jMenuItemCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCambioActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItemCambio);

        jMenuItemModificacion.setText("Modificación");
        jMenuItemModificacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemModificacionActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItemModificacion);

        jMenuItemBaja.setText("Baja del Plan");
        jMenuItemBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBajaActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuItemBaja);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consulta Plan Variable", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel1.setText("Buscar Plan: ");

        txtBuscarPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarPlanActionPerformed(evt);
            }
        });

        btnBuscarPlan.setText("Buscar");
        btnBuscarPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPlanActionPerformed(evt);
            }
        });

        tblConsultaPlanes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblConsultaPlanes.setComponentPopupMenu(jPopupMenu2);
        tblConsultaPlanes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tblConsultaPlanes);

        btnSalirConsulta.setText("Salir");
        btnSalirConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirConsultaActionPerformed(evt);
            }
        });

        cmbProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbSector.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbMotivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbComprador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Proveedor:");

        Rubro.setText("Estado:");

        jLabel4.setText("Motivo:");

        jLabel5.setText("Fecha Inicio:");

        jLabel6.setText("Fecha Fin:");

        jLabel7.setText("Comprador");

        jButton2.setText("Exportar a Excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Ver Comprobante");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtBuscarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Rubro, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSector, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dpFInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dpFFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(28, 28, 28)
                .addComponent(btnBuscarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalirConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(120, 120, 120))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(Rubro)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbSector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpFInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpFFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarPlan)
                    .addComponent(btnSalirConsulta))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel1.getAccessibleContext().setAccessibleName("Consulta Planes Variables");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarPlanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarPlanActionPerformed
///METODOS DE LOS BOTONES BUSCAR Y SALIR
    private void btnBuscarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPlanActionPerformed
       String valor="";
        valor= txtBuscarPlan.getText();
        CargarTablaPlanesVariables(valor);
    }//GEN-LAST:event_btnBuscarPlanActionPerformed

    private void btnSalirConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirConsultaActionPerformed
       ConsultaVariables.this.dispose();
    }//GEN-LAST:event_btnSalirConsultaActionPerformed

    private void jMenuItemArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemArticulosActionPerformed
         // TODO add your handling code here:
        int filasel;
        int idplanVariable=0;
        String idString="";
        GenerarReportes repVariable= new GenerarReportes(); 
        //modelo= (DefaultTableModel) tblArticulosVariables.getModel();
        try
           {
               filasel=tblConsultaPlanes.getSelectedRow();
               idString=(String)modelo.getValueAt(filasel, 0);
               ConsultaArticulos articulosVentana= new ConsultaArticulos();
               articulosVentana.CargarTablaPlanes(idString);
               articulosVentana.setExtendedState(JFrame.MAXIMIZED_BOTH);
               articulosVentana.setVisible(true);

               if (filasel==-1)
               {
                   //MENSAJE SI NO SELECCIONA NADA
                   JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
               }
               }
        catch (Exception e)
                {
                }
        
    }//GEN-LAST:event_jMenuItemArticulosActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        List<String> headers = new ArrayList<>();
        List<List<String>> content = new ArrayList<>();

        JFileChooser chooser = new JFileChooser()
        {
            @Override
            public void approveSelection() {
                File f = getSelectedFile();
                if (f.exists() && getDialogType() == SAVE_DIALOG) {
                    int result = JOptionPane.showConfirmDialog(this,
                        "El archivo ya existe, ¿desea sobreescribir?", "Archivo ya existente",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                    switch (result) {
                        case JOptionPane.YES_OPTION:
                        super.approveSelection();
                        return;
                        case JOptionPane.CANCEL_OPTION:
                        cancelSelection();
                        return;
                        default:
                        return;
                    }
                }
                super.approveSelection();
            }
        };
        chooser.setFileFilter(new FileNameExtensionFilter("Archivo XLS", "xls", "excel"));
        chooser.setSelectedFile(new File("planes_variables.xls"));
        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            File dest = chooser.getSelectedFile();

            for (int i = 0; i < tblConsultaPlanes.getColumnCount(); i++)
            headers.add(tblConsultaPlanes.getColumnName(i));

            for (int i = 0; i < tblConsultaPlanes.getRowCount(); i++)
            {
                List<String> rowContent = new ArrayList<>();
                for (int j = 0; j < tblConsultaPlanes.getColumnCount(); j++)
                rowContent.add((String) tblConsultaPlanes.getValueAt(i, j));
                content.add(rowContent);
            }

            try
            {
                ExcelUtils.exportToExcel(dest, headers, content, "Planes Variables");
                JOptionPane.showMessageDialog(this, "Archivo creado exitosamente",
                        "Creación OK", JOptionPane.INFORMATION_MESSAGE);
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "Hubo un error al intentar exportar: \n" +
                    e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int filasel;
        int idPlanVariable=0;
        String idString="";
        GenerarReportes repVariable= new GenerarReportes();
        modelo= (DefaultTableModel) tblConsultaPlanes.getModel();
        try
        {

            filasel=tblConsultaPlanes.getSelectedRow();

            if (filasel==-1)
            {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
            }
            else
            {
                //accion="Modificar";

                idString= (String)modelo.getValueAt(filasel, 0);
                System.out.println(idString);

                //habilitar();
                idPlanVariable= Integer.valueOf(idString);
                System.out.println(idPlanVariable);
                repVariable.reportePlanVariable(idPlanVariable);
            }
        }
        catch (HeadlessException | NumberFormatException e)
        {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItemCambioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCambioActionPerformed
     //   CambiarEstado
          
     int filasel;
        int idPlanVariable=0;
        String idString="";
       // GenerarReportes repVariable= new GenerarReportes();
        modelo= (DefaultTableModel) tblConsultaPlanes.getModel();
        try
        {

            filasel=tblConsultaPlanes.getSelectedRow();

            if (filasel==-1)
            {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
            }
            else
            {
                //accion="Modificar";

                idString= (String)modelo.getValueAt(filasel, 0);
                System.out.println(idString);

                //habilitar();
                idPlanVariable= Integer.valueOf(idString);
                System.out.println(idPlanVariable);
               // repVariable.reportePlanVariable(idPlanVariable);
               
                CambiarEstado ventanaCambiar = new CambiarEstado(idPlanVariable,UID);
                ventanaCambiar.setExtendedState(JFrame.NORMAL);
                ventanaCambiar.setVisible(true);
            
               }
        }
        catch (HeadlessException | NumberFormatException e)
        {

        }
    }//GEN-LAST:event_jMenuItemCambioActionPerformed

    private void jMenuItemModificacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemModificacionActionPerformed
        // TODO add your handling code here:
        
        //   CambiarEstado
          
     int filasel;
        int idPlanVariable=0;
        String idString="";
       // GenerarReportes repVariable= new GenerarReportes();
        modelo= (DefaultTableModel) tblConsultaPlanes.getModel();
        try
        {

            filasel=tblConsultaPlanes.getSelectedRow();

            if (filasel==-1)
            {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
            }
            else
            {
                //accion="Modificar";

                idString= (String)modelo.getValueAt(filasel, 0);
                System.out.println(idString);

                //habilitar();
               // idPlanVariable= Integer.valueOf(idString);
                System.out.println(idPlanVariable);
               // repVariable.reportePlanVariable(idPlanVariable);
               
             /*   ModificacionBajaPlan ventanaModificacion = new ModificacionBajaPlan(idString);
                ventanaModificacion.setExtendedState(JFrame.NORMAL);
                ventanaModificacion.setVisible(true);*/
            }
        }
        catch (HeadlessException | NumberFormatException e)
        {

        }
        
    }//GEN-LAST:event_jMenuItemModificacionActionPerformed

    private void jMenuItemBajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemBajaActionPerformed
        //   CambiarEstado
          
     int filasel;
        int idPlanVariable=0;
        String idString="";
       // GenerarReportes repVariable= new GenerarReportes();
        modelo= (DefaultTableModel) tblConsultaPlanes.getModel();
        try
        {

            filasel=tblConsultaPlanes.getSelectedRow();

            if (filasel==-1)
            {
                JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila");
            }
            else
            {
                //accion="Modificar";

                idString= (String)modelo.getValueAt(filasel, 0);
                System.out.println(idString);

                //habilitar();
                idPlanVariable= Integer.valueOf(idString);
                System.out.println(idPlanVariable);
               // repVariable.reportePlanVariable(idPlanVariable);
               
               AltaBajaModificacion ventanaABM = new AltaBajaModificacion(idPlanVariable,UID);
                ventanaABM.setExtendedState(JFrame.NORMAL);
                ventanaABM.setVisible(true);
            
               }
        }
        catch (HeadlessException | NumberFormatException e)
        {

        }
    }//GEN-LAST:event_jMenuItemBajaActionPerformed
        
    ///////CLASES GENERALES
    //carga tabla Articulos
    private void CargarTablaPlanesVariables(String valor){
        String sSQL="";
        String newString,pPlan, pProveedor,pRubro,pMotivo,pComprador;
        Date pFechaInicio, pFechaFin;
        pPlan=txtBuscarPlan.getText();
        pProveedor=(String) cmbProveedor.getSelectedItem();
        pRubro=(String) cmbSector.getSelectedItem();
        pMotivo=(String) cmbMotivo.getSelectedItem();
        pComprador=(String) cmbComprador.getSelectedItem();
        pFechaInicio= dpFInicio.getDate();
        pFechaFin=dpFFin.getDate();
        ///configuramos la tabla.
        String [] titulos= {"Nro de Plan","ID","Proveedor","Fecha Alta","Sucursal","MotivoSNC",
                             "Dev. Desde","Dev. Hasta","Estado","Acción Comercial","Aplicación", "Comprador",
                              "M_Impacto","M_Cancelacion","Canal"};
        String [] registro= new String[15];
        modelo = new DefaultTableModel(null,titulos){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        ///realizamos la conexion con la bdd.
        ConexionMySQL mysql= new ConexionMySQL();
        Connection cn= mysql.Conectar();
        String vPlan="";
        String sqlCond="where pl.tipo_plan='VARIABLE'";
        if(pPlan.equals(null)==false && pPlan.equals("")==false){
                sqlCond= sqlCond+" and pl.idPlan_descuento="+pPlan;
        }
        if(cmbProveedor.getSelectedItem().equals("--SELECCIONAR--")==false){
            String prov;
            //cadena a separar
            prov= cmbProveedor.getSelectedItem().toString();
            //arreglo para guardar las cadenas separadas
            String[] parts;
         
            ///ESTO DESCONCATENA EL STRING PROVEEDOR QUE VIENE CONCATENADO CON EL ID_PROV 
            
         if (!prov.equals("Item 1")){
                   
                   parts =prov.split(" - ");
                  // System.out.println (parts.length);
                  // System.out.println (parts[1]);
                   prov=parts[1];
            }     
            sqlCond=sqlCond+ " and pr.razon_social='"+prov+"'";
        }
        if(cmbSector.getSelectedItem().equals("--SELECCIONAR--")==false){
            sqlCond=sqlCond+ " and pl.estado='"+cmbSector.getSelectedItem()+"'";
       }
        if(cmbMotivo.getSelectedItem().equals("--SELECCIONAR--")==false){
            String motiv;
            //cadena a separar
            motiv= cmbMotivo.getSelectedItem().toString();
            //arreglo para guardar las cadenas separadas
            String[] parts;
            ///ESTO DESCONCATENA EL STRING MOTIVO QUE VIENE CONCATENADO CON UN GUION - 
            if (!motiv.equals("Item 1")){
                   
                if (motiv.charAt(0) == '-'){///revisamos el primer caracter
                      parts =motiv.split("-");
                     // System.out.println (parts.length);
                     //System.out.println (parts[1]);
                   motiv=parts[1];
                   System.out.println(motiv);}
                
            }
                       
            sqlCond=sqlCond+ " and mot.descripcion LIKE '%"+motiv+"%'";
            System.out.println(sqlCond);
        }
        if(cmbComprador.getSelectedItem().equals("--SELECCIONAR--")==false){
            sqlCond=sqlCond+ " and concat (usr.apellido, ' ' , usr.nombre) LIKE '%"+cmbComprador.getSelectedItem()+"%'";
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        if(dpFInicio.getDate()!=null){
            sqlCond=sqlCond+ " and pl.fechaActual>='"+sdf1.format(dpFInicio.getDate()).toString()+"'";
        }
        if(dpFFin.getDate()!=null){
            sqlCond=sqlCond+ " and pl.fechaActual<='"+sdf1.format(dpFFin.getDate()).toString()+"'";
        }
       ///ingresamos la consulta
        sSQL="select pl.idPlan_descuento as Plan_Nro, pl.Proveedor_idproveedor as ID, pr.razon_social as Proveedor, "+
             "pl.fechaActual as Fecha,suc.descripcion as Sucursal, mot.descripcion as MotivoSNC,  "+
             "pl.fecha_Devengamiento_Desde as Desde,pl.fecha_Devengamiento_Hasta as Hasta,pl.estado as Estado ,TRUNCATE (pl.accion_comercial, 2) as Accion, pl.aplicacion as Aplicacion, "+
             "concat (usr.apellido, ' ' , usr.nombre) as Comprador, "+
             "pl.Modo_Cancelacion as M_Cancelacion, pl.Modo_Impacto as M_Impacto, pl.Modo_Canal as Canal " +
             "from plan_descuento as pl "+ 
             "INNER JOIN proveedor as pr ON pr.idproveedor = pl.Proveedor_idProveedor "+
             "INNER JOIN motivo_snc as mot ON mot.idMotivo_SNC= pl.Motivo_SNC_idMotivo_SNC "+
             "INNER JOIN usuario as usr ON usr.idUsuario= pl.Usuario_idUsuario "+
             "INNER JOIN sucursal as suc ON suc.idSucursal= pl.Sucursal_idSucursal "+ sqlCond +
                " and (pl.situacion<>'INACTIVO') order by 1";
            System.out.println(sSQL);
        try 
        {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sSQL);
            
            while (rs.next())
            {
                
                registro[0]=rs.getString("Plan_Nro");
                registro[1]=rs.getString("ID");
                registro[2]=rs.getString("Proveedor");
                registro[3]=rs.getString("Fecha");
                registro[4]=rs.getString("Sucursal");
                registro[5]=rs.getString("MotivoSNC");
                registro[6]=rs.getString("Desde");
                registro[7]=rs.getString("Hasta");
                registro[8]=rs.getString("Estado");
                registro[9]=rs.getString("Accion");
                registro[10]=rs.getString("Aplicacion");
                registro[11]=rs.getString("Comprador");
                registro[12]=rs.getString("M_Cancelacion");
                registro[13]=rs.getString("M_Impacto");
                registro [14]=rs.getString("Canal");
                modelo.addRow(registro);
              }
            tblConsultaPlanes.setModel(modelo);
                        
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        //tblConsultaPlanes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //tblConsultaPlanes.getColumnModel().getColumn(0).setPreferredWidth(100);
        
        //new JScrollPane(tblConsultaPlanes, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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
            java.util.logging.Logger.getLogger(ConsultaVariables.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaVariables.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaVariables.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaVariables.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            private String userId;
            public void run() {
                new ConsultaVariables(userId).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Rubro;
    private javax.swing.JButton btnBuscarPlan;
    private javax.swing.JButton btnSalirConsulta;
    private javax.swing.JComboBox<String> cmbComprador;
    private javax.swing.JComboBox<String> cmbMotivo;
    private javax.swing.JComboBox<String> cmbProveedor;
    private javax.swing.JComboBox<String> cmbSector;
    private org.jdesktop.swingx.JXDatePicker dpFFin;
    private org.jdesktop.swingx.JXDatePicker dpFInicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuItem jMenuItemArticulos;
    private javax.swing.JMenuItem jMenuItemBaja;
    private javax.swing.JMenuItem jMenuItemCambio;
    private javax.swing.JMenuItem jMenuItemModificacion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblConsultaPlanes;
    private javax.swing.JTextField txtBuscarPlan;
    // End of variables declaration//GEN-END:variables
}
