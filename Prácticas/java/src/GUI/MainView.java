/**
 *
 * @author elena
 */

package GUI;

import java.util.ArrayList;
import javax.swing.JOptionPane;

import controller.Controller;
import deepspace.CombatResult;
import deepspace.GameUniverseToUI;
import deepspace.GameState;


public class MainView extends javax.swing.JFrame {
    static Controller controller;
    private String appName= "Deepspace";
    
    public MainView() {
        initComponents();
        //Para que la aplicación finalice cuando se cierre la interfaz
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e){ 
                controller.finish(0); 
            }
        });
        
        setTitle(appName);
        
        setLocationRelativeTo(null);
        
        repaint();
    }
    
    public void updateView(){
        station_panel.removeAll();
        enemy_panel.removeAll();
        
        GameState state = controller.getState();
        GameUniverseToUI g= controller.getUIversion();
        SpaceStationView sv= new SpaceStationView();
        sv.setSpaceStation(g.getCurrentStation());
        station_panel.add(sv);

        EnemyView ev= new EnemyView();
        ev.setEnemy(g.getCurrentEnemy());
        enemy_panel.add(ev);
        
        /*
        combat_button.setEnabled(false);
         next_turn_button.setEnabled(true);
         if((state == GameState.BEFORECOMBAT)||(state == GameState.INIT)){
             combat_button.setEnabled(true);
             next_turn_button.setEnabled(false);
         }

         ev.setVisible(false);
         if(state == GameState.AFTERCOMBAT || controller.isEnemyVisible())
             ev.setVisible(true);
        */
        repaint();
        revalidate();   
    }
    
    
    public ArrayList<String> getNames() {
        NamesCapture namesCapture = new NamesCapture (this, true);
        return namesCapture.getNames();
    }
    
    public void setController(Controller c){
        controller= c;
    }
    
    public void showView(){
        this.setVisible(true);
    }
    
    public boolean confirmExitMessage() {
        return (JOptionPane.showConfirmDialog(this, "¿Estás segur@ que deseas salir?", getAppName(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);
    }
    
    public String getAppName(){
        return appName;
    }
    
    public void showResultMessage(CombatResult cr){
        
        switch (cr) {
              case ENEMYWINS :
                JOptionPane.showMessageDialog(this,"Has PERDIDO el combate.\nCumple tu castigo.",getAppName(),JOptionPane.INFORMATION_MESSAGE);
                break;
              case STATIONESCAPES :
                JOptionPane.showMessageDialog(this,"Has logrado escapar. Eres una Gallina Espacial.",getAppName(),JOptionPane.INFORMATION_MESSAGE);
                break;
              case STATIONWINS :
                JOptionPane.showMessageDialog(this,"Has GANADO el combate. Disfruta de tu botín.",getAppName(),JOptionPane.INFORMATION_MESSAGE);
                if (controller.haveAWinner()) {
                    JOptionPane.showMessageDialog(this,"HAS GANADO LA PARTIDA",getAppName(),JOptionPane.INFORMATION_MESSAGE);
                    System.exit (0);
                }
                break;
              case STATIONWINSANDCONVERTS :
                JOptionPane.showMessageDialog(this,"Has GANADO el combate y tu estación se ha transformado.",getAppName(),JOptionPane.INFORMATION_MESSAGE);
                if (controller.haveAWinner()) {
                    JOptionPane.showMessageDialog(this,"HAS GANADO LA PARTIDA",getAppName(),JOptionPane.INFORMATION_MESSAGE);
                    System.exit (0);
                }
        }
    }
    
    public void showNextTurnMessage(){
        JOptionPane.showMessageDialog(this,"No puedes pasar turno.\nDebes cumplir tu castigo.",getAppName(),JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        station_panel = new javax.swing.JPanel();
        enemy_panel = new javax.swing.JPanel();
        combat_button = new javax.swing.JButton();
        next_turn_button = new javax.swing.JButton();
        exit_button = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        station_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout station_panelLayout = new javax.swing.GroupLayout(station_panel);
        station_panel.setLayout(station_panelLayout);
        station_panelLayout.setHorizontalGroup(
            station_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 253, Short.MAX_VALUE)
        );
        station_panelLayout.setVerticalGroup(
            station_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        enemy_panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout enemy_panelLayout = new javax.swing.GroupLayout(enemy_panel);
        enemy_panel.setLayout(enemy_panelLayout);
        enemy_panelLayout.setHorizontalGroup(
            enemy_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        enemy_panelLayout.setVerticalGroup(
            enemy_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );

        combat_button.setText("Combatir");
        combat_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combat_buttonActionPerformed(evt);
            }
        });

        next_turn_button.setText("SIguiente turno");
        next_turn_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                next_turn_buttonActionPerformed(evt);
            }
        });

        exit_button.setText("Salir");
        exit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(station_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(enemy_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(next_turn_button)
                        .addComponent(combat_button, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                    .addComponent(exit_button))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enemy_panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(combat_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(next_turn_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exit_button)
                        .addGap(0, 25, Short.MAX_VALUE))
                    .addComponent(station_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combat_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combat_buttonActionPerformed
        if(controller.combat() != CombatResult.NOCOMBAT) 
            combat_button.setEnabled(true);
    }//GEN-LAST:event_combat_buttonActionPerformed

    private void next_turn_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_next_turn_buttonActionPerformed
        if(controller.nextTurn())
            next_turn_button.setEnabled(true);
    }//GEN-LAST:event_next_turn_buttonActionPerformed

    private void exit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_buttonActionPerformed
        controller.finish(0);
    }//GEN-LAST:event_exit_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton combat_button;
    private javax.swing.JPanel enemy_panel;
    private javax.swing.JButton exit_button;
    private javax.swing.JButton next_turn_button;
    private javax.swing.JPanel station_panel;
    // End of variables declaration//GEN-END:variables
}
