/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Interface.InterfaceManager;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
//import java.awt.Image;


public class VentanaPrincipalPanel extends javax.swing.JFrame {

    /**
     * Creates new form VentanaPrincipalPanel
     */
    public String user;
    public String userId;
    private InterfaceManager interfaceMgr = new InterfaceManager(this);
    public static final String Version="2.0.0";
    public VentanaPrincipalPanel(loginData userData) {
        //System.out.println("EL USUARIO EN LA CLASE MAIN ES: " + userData.getUsers());
        initComponents();
        lblVersion.setText("Version: "+ Version);
        user=userData.getUsers();
        userId=userData.getIDusuario();
        if(userData.getPermiso().equals("1")){
            System.out.println("EL USUARIO EN LA CLASE MAIN ES: " + userData.getPermiso());
            jMenu7.setEnabled(true);
            jMenu6.setEnabled(true);
        }else{
            jMenu7.setEnabled(false);
            jMenu6.setEnabled(false);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblVersion = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jmOperaciones = new javax.swing.JMenu();
        jMenu8 = new javax.swing.JMenu();
        jmOpGenNotasDebito = new javax.swing.JMenuItem();
        jmCierrePorFecha = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItemInactivos = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PLANES DE DESCUENTO");
        setBackground(new java.awt.Color(0, 102, 153));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\PlanesPotigian\\logo.png")); // NOI18N

        lblVersion.setText("jLabel2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(170, 170, 170)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addGap(143, 143, 143))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblVersion)
                .addGap(113, 113, 113)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(262, 262, 262))
        );

        jMenu1.setText("Planes");

        jMenu4.setText("Nuevo Plan");

        jMenuItem1.setText("Plan Fijo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuItem2.setText("Plan Variable");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem2);

        jMenuItem7.setText("Plan Especial");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuItem15.setText("Plan Variable V.2");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem15);

        jMenu1.add(jMenu4);

        jMenu5.setText("Consulta");

        jMenuItem3.setText("Plan Fijo");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenuItem4.setText("Plan Variable");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenu1.add(jMenu5);

        jMenuBar1.add(jMenu1);

        jmOperaciones.setText("Cierres");

        jMenu8.setText("Generar");

        jmOpGenNotasDebito.setText("Notas de crédito");
        jmOpGenNotasDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmOpGenNotasDebitoActionPerformed(evt);
            }
        });
        jMenu8.add(jmOpGenNotasDebito);

        jmCierrePorFecha.setText("Cierre Por Fecha");
        jmCierrePorFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmCierrePorFechaActionPerformed(evt);
            }
        });
        jMenu8.add(jmCierrePorFecha);

        jmOperaciones.add(jMenu8);

        jMenuBar1.add(jmOperaciones);

        jMenu2.setText("Reportes");

        jMenuItem11.setText("Notas de crédito");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem14.setText("Listado de Cerrados");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem14);

        jMenuBar1.add(jMenu2);

        jMenu6.setText("Interfaces");

        jMenuItem8.setText("Planes");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem8);

        jMenuItem9.setText("Artículos");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem9);

        jMenuItem10.setText("Exportar todo");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem10);

        jMenuItem12.setText("Interfaz PBI");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem12);

        jMenuItem13.setText("InterfazCierre PBI");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem13);

        jMenuBar1.add(jMenu6);

        jMenu7.setText("Administrar");

        jMenuItem5.setText("Usuarios");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem5);

        jMenuItemInactivos.setText("Planes Inactivos");
        jMenuItemInactivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemInactivosActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItemInactivos);

        jMenuBar1.add(jMenu7);

        jMenu3.setText("Salir");

        jMenuItem6.setText("Salir del sistema");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        PlanDeDescuentoFijo ventanaImporteFijo = new PlanDeDescuentoFijo(userId);
        ventanaImporteFijo.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        PlanVariable ventanaPlanVariable = new PlanVariable(userId);
        ventanaPlanVariable.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        System.out.println(userId);
        ConsultaPlanes ventanaConsulta= new ConsultaPlanes(userId);
        ventanaConsulta.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventanaConsulta.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        Usuarios ventanaUsuarios = new Usuarios();
        ventanaUsuarios.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        VentanaPrincipalPanel.this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        ConsultaVariables ventanaConsulta= new ConsultaVariables(userId);
        ventanaConsulta.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventanaConsulta.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
       // TODO add your handling code here:
        onPlansRequest(evt);
    }//GEN-LAST:event_jMenuItem8ActionPerformed
    
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        onArticlesRequest(evt);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        onExportAllRequested(evt);
    }//GEN-LAST:event_jMenuItem10ActionPerformed
    private void onArticlesRequest(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onArticlesRequest
        try 
        {
            if (interfaceMgr.onArticlesRequest(null))
                JOptionPane.showMessageDialog(this, "Archivo generado exitosamente", "INFO", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error en generacion de interfaz", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_onArticlesRequest

    private void onPlansRequest(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onPlansRequest
        try 
        {
            if (interfaceMgr.onPlansRequest(null))
                JOptionPane.showMessageDialog(this, "Archivo generado exitosamente", "INFO", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error en generacion de interfaz", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_onPlansRequest

    private void jmOpGenNotasDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmOpGenNotasDebitoActionPerformed
        // TODO add your handling code here:
        CierreDiario cDiario = new CierreDiario();
        cDiario.setVisible(true);
    }//GEN-LAST:event_jmOpGenNotasDebitoActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        NotasCredito nCred = new NotasCredito();
        nCred.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        PendienteDeCarga ventanaImportePendiente = new PendienteDeCarga(userId);
        ventanaImportePendiente.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItemInactivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemInactivosActionPerformed
        // TODO add your handling code here:
        ListadoDeInactivos ventanaInactivo= new ListadoDeInactivos(userId);
        ventanaInactivo.setVisible(true);
        
    }//GEN-LAST:event_jMenuItemInactivosActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        InterfazPBI ventanaPBI= new InterfazPBI(userId);
        ventanaPBI.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
         InterfazCerradosPBI ventanaCerradosPBI= new InterfazCerradosPBI(userId);
        ventanaCerradosPBI.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        ConsultaCerrados ventanaCerrados= new ConsultaCerrados(userId);
        ventanaCerrados.setVisible(true);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jmCierrePorFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmCierrePorFechaActionPerformed
         CierrePorFecha ventanaCierre= new CierrePorFecha();
        ventanaCierre.setVisible(true);
    }//GEN-LAST:event_jmCierrePorFechaActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        PlanVariableSinDev ventanaPlanVariableSinDev = new PlanVariableSinDev(userId);
        ventanaPlanVariableSinDev.setVisible(true);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void onExportAllRequested(java.awt.event.ActionEvent evt) {                                      
        try
        {
            if (interfaceMgr.onExportAllRequested())
                JOptionPane.showMessageDialog(this, "Archivos generados exitosamente", "INFO", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error en generacion de interfaz", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST
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
            java.util.logging.Logger.getLogger(VentanaPrincipalPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipalPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipalPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipalPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            private loginData userData;
            public void run() {
                new VentanaPrincipalPanel(userData).setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem jMenuItemInactivos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JMenuItem jmCierrePorFecha;
    private javax.swing.JMenuItem jmOpGenNotasDebito;
    private javax.swing.JMenu jmOperaciones;
    private javax.swing.JLabel lblVersion;
    // End of variables declaration//GEN-END:variables
}
