/**
 *
 * @author elena
 */

package GUI;
import deepspace.HangarToUI;
import deepspace.WeaponToUI;
import deepspace.ShieldToUI;

import java.util.ArrayList;
import java.awt.Component;

public class HangarView extends javax.swing.JPanel {

    /**
     * Creates new form HangarView
     */
    public HangarView() {
        initComponents();
    }
    
    void setHangar(HangarToUI h){
        if(h != null){
            panel_items.removeAll();
            ArrayList<WeaponToUI> weapons= h.getWeapons();
            ArrayList<ShieldToUI> shields= h.getShieldBoosters();
            WeaponView wv;
            ShieldBoosterView sv;

            max_elements.setText(Integer.toString(h.getMaxElements()));

            for(WeaponToUI w: weapons){
              wv= new WeaponView();
              wv.setWeapon(w);
              panel_items.add(wv);
            }

            for(ShieldToUI s: shields){
                sv= new ShieldBoosterView();
                sv.setShieldBooster(s);
                panel_items.add(sv);
            }
        }
        
        repaint();
        revalidate();   
    }
    
    ArrayList<Integer> getSelectedShields(){
        ArrayList<Integer> selected_shields = new ArrayList();
        int i = 0;
        for (Component c : getComponents()) {
           try{
                if (((ShieldBoosterView) c).isSelected()) 
                    selected_shields.add(i);
                
            }
           catch( Exception e){}
           i++;
        }
        return selected_shields; 
    }
    
    ArrayList<Integer> getSelectedWeapons(){
        ArrayList<Integer> selected_weapons= new ArrayList();
        int i= 0;
        for (Component c : getComponents()){
            try{
                if(((WeaponView) c).isSelected())
                    selected_weapons.add(i);
            }
            catch(Exception e){}
            i++;
        }
        return selected_weapons;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        max_elements = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        panel_items = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        max_elements.setText("jLabel2");

        panel_items.setBorder(new javax.swing.border.MatteBorder(null));
        jScrollPane2.setViewportView(panel_items);

        jLabel1.setText("Número máximo de elementos:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(max_elements)
                .addGap(0, 144, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(max_elements))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel max_elements;
    private javax.swing.JPanel panel_items;
    // End of variables declaration//GEN-END:variables
}
