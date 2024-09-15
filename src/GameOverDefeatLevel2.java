
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rocco, Suchana, Cheliyan
 */
public class GameOverDefeatLevel2 extends javax.swing.JFrame {

    /**
     * Creates new form GameOver
     */
    public GameOverDefeatLevel2() {
        initComponents();
        setLocationRelativeTo(this);
        //playDefeatMusic("src\\wav\\4.Who's There(P4).wav");
    }
    
    
     /**
     * Plays defeat music
     * Pre: None 
     * Post: Plays defeat music
     */
    public void playDefeatMusic(String musicLocation){
         try{
            File musicPath = new File(musicLocation); 
             
            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);    
            }
         }catch(Exception e){
             JOptionPane.showMessageDialog(null, e);
         }
    }//end playDefateMusic

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameOverPanel = new javax.swing.JPanel();
        lblGameOver = new javax.swing.JLabel();
        lblDesc = new javax.swing.JLabel();
        btnRetry = new javax.swing.JButton();
        btnReturnEnemySelection = new javax.swing.JButton();
        btnReturnMenu = new javax.swing.JButton();
        btnQuit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        gameOverPanel.setBackground(new java.awt.Color(153, 204, 255));
        gameOverPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblGameOver.setFont(new java.awt.Font("Tahoma", 1, 72)); // NOI18N
        lblGameOver.setText("Game Over");
        gameOverPanel.add(lblGameOver, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, 410, 70));

        lblDesc.setFont(new java.awt.Font("Tahoma", 0, 26)); // NOI18N
        lblDesc.setText("You died.");
        gameOverPanel.add(lblDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 110, 110));

        btnRetry.setBackground(new java.awt.Color(255, 204, 102));
        btnRetry.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnRetry.setText("Retry");
        btnRetry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRetryActionPerformed(evt);
            }
        });
        gameOverPanel.add(btnRetry, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 280, 270, 70));

        btnReturnEnemySelection.setBackground(new java.awt.Color(255, 204, 102));
        btnReturnEnemySelection.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnReturnEnemySelection.setText("Return To Enemy Selection");
        btnReturnEnemySelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnEnemySelectionActionPerformed(evt);
            }
        });
        gameOverPanel.add(btnReturnEnemySelection, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 380, 540, 70));

        btnReturnMenu.setBackground(new java.awt.Color(255, 204, 102));
        btnReturnMenu.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnReturnMenu.setText("Return To Menu");
        btnReturnMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnMenuActionPerformed(evt);
            }
        });
        gameOverPanel.add(btnReturnMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 480, 410, 70));

        btnQuit.setBackground(new java.awt.Color(255, 204, 102));
        btnQuit.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        btnQuit.setText("Quit");
        btnQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitActionPerformed(evt);
            }
        });
        gameOverPanel.add(btnQuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 580, 270, 70));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gameOverPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gameOverPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitActionPerformed
        //Quits the application
        System.exit(0);     
    }//GEN-LAST:event_btnQuitActionPerformed

    private void btnRetryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRetryActionPerformed
        //Returns the player back to the battle screen
        new BattleLevel2().setVisible(true);
        this.setVisible(false);
        clip.stop();
        
    }//GEN-LAST:event_btnRetryActionPerformed

    private void btnReturnMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnMenuActionPerformed
        //Returns the player back to the main menu
        new PixelHeroMenu().setVisible(true);
        this.setVisible(false);
        clip.stop();
    }//GEN-LAST:event_btnReturnMenuActionPerformed

    private void btnReturnEnemySelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnEnemySelectionActionPerformed
        //Return to enemy selection
        new EnemySelectionScreen().setVisible(true);
        this.setVisible(false);
        clip.stop();  
    }//GEN-LAST:event_btnReturnEnemySelectionActionPerformed

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
            java.util.logging.Logger.getLogger(GameOverDefeatLevel2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameOverDefeatLevel2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameOverDefeatLevel2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameOverDefeatLevel2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameOverDefeatLevel2().setVisible(true);
            }
        });
    }
    
    Clip clip;
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnQuit;
    private javax.swing.JButton btnRetry;
    private javax.swing.JButton btnReturnEnemySelection;
    private javax.swing.JButton btnReturnMenu;
    private javax.swing.JPanel gameOverPanel;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblGameOver;
    // End of variables declaration//GEN-END:variables
}