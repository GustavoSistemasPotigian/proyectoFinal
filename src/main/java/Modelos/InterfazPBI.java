/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import BaseDeDatos.ConexionMySQL;
import Reportes.GenerarReportes;
import Utils.ExcelUtils;
import java.awt.HeadlessException;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author usuario
 */
public class InterfazPBI extends javax.swing.JFrame {

    /**
     * Creates new form ConsultaPlanes
     */
    //** instancia del modelo tabla**//
    DefaultTableModel modelo;
    private String UID;
    public InterfazPBI(String userId) {
        this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
        initComponents();  
        UID=userId;
      //  System.out.println("Consulta planes "+ UID);
        CargarTablaPlanes("");
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
       cmbSector1.removeAllItems();
        this.cmbSector1.addItem("--SELECCIONAR--");
        String prov;
            //cadena a separar
            prov= cmbSector1.getSelectedItem().toString();
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
                         this.cmbSector1.addItem(texto);
                         i++;
                    }
    }
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItemCambio = new javax.swing.JMenuItem();
        jMenuItemBaja = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscarPlan = new javax.swing.JTextField();
        btnBuscarPlan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsultaPlanes = new javax.swing.JTable();
        btnSalirConsulta = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cmbProveedor = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbMotivo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        dpFInicio = new org.jdesktop.swingx.JXDatePicker();
        jLabel6 = new javax.swing.JLabel();
        dpFFin = new org.jdesktop.swingx.JXDatePicker();
        cmbComprador = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Rubro = new javax.swing.JLabel();
        Rubro1 = new javax.swing.JLabel();
        cmbSector1 = new javax.swing.JComboBox<>();

        jPopupMenu1.setComponentPopupMenu(jPopupMenu1);
        jPopupMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jMenuItemCambio.setText("Cambiar Estado");
        jMenuItemCambio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCambioActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemCambio);

        jMenuItemBaja.setText("Baja del Plan");
        jMenuItemBaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemBajaActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemBaja);

        setTitle("Consulta Planes Fijos");
        setExtendedState(6);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consulta Planes Fijos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        tblConsultaPlanes.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tblConsultaPlanes);

        btnSalirConsulta.setText("Salir");
        btnSalirConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirConsultaActionPerformed(evt);
            }
        });

        jLabel2.setText("Proveedor:");

        cmbProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Motivo:");

        cmbMotivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("Fecha Inicio:");

        jLabel6.setText("Fecha Fin:");

        cmbComprador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Comprador:");

        jButton1.setText("Ver comprobante");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Exportar a Excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        Rubro.setText("Estado:");

        Rubro1.setText("Estado:");

        cmbSector1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1399, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBuscarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbSector1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Rubro1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dpFInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dpFFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cmbComprador, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(btnBuscarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jButton2)
                .addGap(30, 30, 30)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSalirConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(646, 646, 646)
                    .addComponent(Rubro, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(717, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtBuscarPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbSector1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dpFInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(dpFFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbComprador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnBuscarPlan))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(Rubro1)
                        .addComponent(jLabel6)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(btnSalirConsulta))
                .addGap(11, 11, 11))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(221, 221, 221)
                    .addComponent(Rubro)
                    .addContainerGap(248, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleName("ConsultaPlanes");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtBuscarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarPlanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarPlanActionPerformed
//METODOS DE LOS BOTONES
    private void btnBuscarPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPlanActionPerformed
        String valor="";
        valor= txtBuscarPlan.getText();
        CargarTablaPlanes(valor);
    }//GEN-LAST:event_btnBuscarPlanActionPerformed

    private void btnSalirConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirConsultaActionPerformed
       InterfazPBI.this.dispose();
    }//GEN-LAST:event_btnSalirConsultaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int filasel;
        int idplanfijo=0;
        String idString="";
        GenerarReportes repFijo= new GenerarReportes(); 
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
                   idplanfijo= Integer.valueOf(idString);
                   System.out.println(idplanfijo);
                   repFijo.reportePlanFijo(idplanfijo);
               }     
           }
        catch (HeadlessException | NumberFormatException e)
                {
                    
                }
    }//GEN-LAST:event_jButton1ActionPerformed

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
        chooser.setSelectedFile(new File("planes_fijos.xls"));
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
                ExcelUtils.exportToExcel(dest, headers, content, "Planes Fijos");
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
    
    //carga tabla Articulos
    void CargarTablaPlanes(String valor){
        String sSQL="";
        //CARGO VARIABLES PARA HACER EL QUERY
        String newString,pPlan, pProveedor,pRubro,pMotivo,pComprador;
        Date pFechaInicio, pFechaFin;
        pPlan=txtBuscarPlan.getText();
        pProveedor=(String) cmbProveedor.getSelectedItem();
       // pRubro=(String) cmbSector.getSelectedItem();
        pMotivo=(String) cmbMotivo.getSelectedItem();
        pComprador=(String) cmbComprador.getSelectedItem();
        pFechaInicio= dpFInicio.getDate();
        pFechaFin=dpFFin.getDate();
       // valor="Javier";
        ///configuramos la tabla.
        String [] titulos= {"Nro de Plan","Tipo","IDProveedor","Importe","IDMotivo","MesDeVigencia","Estado", "IDComprador" };
        String [] registro= new String[8];
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
        String sqlCond="where pl.tipo_plan='FIJO'";
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
        
        sqlCond=sqlCond+ " and pr.razon_social LIKE '%"+prov+"%'";
            
            System.out.println(sqlCond);
        }
        if(cmbSector1.getSelectedItem().equals("--SELECCIONAR--")==false){
            sqlCond=sqlCond+ " and pl.estado='"+cmbSector1.getSelectedItem()+"'";
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
            sqlCond=sqlCond+ " and pl.fecha_Devengamiento_Desde>='"+sdf1.format(dpFInicio.getDate()).toString()+"'";
        }
        if(dpFFin.getDate()!=null){
            sqlCond=sqlCond+ " and pl.fecha_Devengamiento_Desde<='"+sdf1.format(dpFFin.getDate()).toString()+"'";
        }
       ///ingresamos la consulta
        sSQL=" select pl.idPlan_descuento as Plan_Nro, pl.tipo_plan as tipo, pl.Proveedor_idProveedor as id ,"+
             " TRUNCATE (pl.importe, 2) as Importe, "+ 
             " pl.Motivo_SNC_idMotivo_SNC as MotivoSNC, pl.fecha_devengamiento_desde as MesDeVigencia, "+
             " pl.estado as Estado, "+
             " pl.Usuario_idUsuario as idComprador "+
              
             " from plan_descuento as pl "+ 
                "INNER JOIN proveedor as pr ON pr.idproveedor = pl.Proveedor_idProveedor "+
             "INNER JOIN motivo_snc as mot ON mot.idMotivo_SNC= pl.Motivo_SNC_idMotivo_SNC "+
             "INNER JOIN usuario as usr ON usr.idUsuario= pl.Usuario_idUsuario "+
             "INNER JOIN tipo_iva as ti ON ti.idTipo_IVA= pl.tipo_IVA_idtipo_IVA "+
                     
             " "+ sqlCond + " and (pl.situacion<>'INACTIVO') order by 1";    
        System.out.println(sSQL);
        try 
        {
            Statement st= cn.createStatement();
            ResultSet rs= st.executeQuery(sSQL);
            ///ASIGNAMOS CADA CAMPO DE LA CONSULTA A CADA REGISTRO DE LA TABLA.
            while (rs.next())
            {
               
                registro[0]=rs.getString("Plan_Nro");
                registro[1]=rs.getString("tipo");
                registro[2]=rs.getString("id");
                registro[3]=rs.getString("Importe");
                registro[4]=rs.getString("MotivoSNC");
                registro[5]=rs.getString("MesDeVigencia");
                registro[6]=rs.getString("Estado");
                registro[7]=rs.getString("idComprador");
                
                modelo.addRow(registro);
              }
            tblConsultaPlanes.setModel(modelo);
            
                  
                        
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
  }
    
    
    
    /**
     * @param args the command line arguments
     */private String userId;
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
         ConexionMySQL mysql= new ConexionMySQL();
         Connection cn= mysql.Conectar();
        
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
            java.util.logging.Logger.getLogger(InterfazPBI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazPBI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazPBI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazPBI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        
       java.awt.EventQueue.invokeLater(new Runnable()  {
            private String userId;
            public void run(){
            new InterfazPBI(userId).setVisible(true);
            }
            });
       
         /*java.awt.EventQueue.invokeLater(new Runnable() {
            private String userId;
            public void run() {
                new PlanVariable(userId).setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Rubro;
    private javax.swing.JLabel Rubro1;
    private javax.swing.JButton btnBuscarPlan;
    private javax.swing.JButton btnSalirConsulta;
    private javax.swing.JComboBox<String> cmbComprador;
    private javax.swing.JComboBox<String> cmbMotivo;
    private javax.swing.JComboBox<String> cmbProveedor;
    private javax.swing.JComboBox<String> cmbSector1;
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
    private javax.swing.JMenuItem jMenuItemBaja;
    private javax.swing.JMenuItem jMenuItemCambio;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblConsultaPlanes;
    private javax.swing.JTextField txtBuscarPlan;
    // End of variables declaration//GEN-END:variables
}
