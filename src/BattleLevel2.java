//Imports for game
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Timer;

//Imports for audio
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
/**
 *
 * @author Rocco, Suchana, Cheliyan
 */
public class BattleLevel2 extends javax.swing.JFrame {

    /**
     * Creates new form Battle
     */
    public BattleLevel2() {
        //Setting up the game
        initComponents();
        hideMenus();
        //playBattleMusic("src\\wav\\8.Time To Make History(P4G).wav");
        hideIndicators();
        setProgressBars();
        setLocationRelativeTo(this);
        
    }//end battle
    
    /**
     * Plays the music file
     * Pre: None 
     * Post: Plays battle music 
     */
    public void playBattleMusic(String musicLocation){
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
    }//end playBattleMusic
    
    /**
     * Hides all the menus.
     * Pre: None
     * Post: All unnecessary menus will not show.
     */
    public void hideMenus(){
       skillsMenuPanel.setVisible(false);
       itemsMenuPanel.setVisible(false);
       usedSkillPanel.setVisible(false);
       oneMoreTurnPanel.setVisible(false);
       itemsMsgPanel.setVisible(false);
       skillsMsgPanel.setVisible(false);
    }//end hideMenus
    
    /**
     * Hides all the indicators
     * Pre: None
     * Post: All unnecessary indicators will not show
     */
    public void hideIndicators(){
       //Hiding indicators for enemy
       lblEnemyCriticalHit.setVisible(false);
       lblEnemyDowned.setVisible(false);
       lblEnemyIceImmune.setVisible(false);
       lblEnemyDefUp.setVisible(false);
       lblEnemyHitEvadeUp.setVisible(false);
       lblEnemyWeak.setVisible(false);
       lblEnemyIceImmune.setVisible(false);
       lblEnemyAtkUp.setVisible(false);
       
       //Hiding indicators for player
       lblPlayerCriticalHit.setVisible(false);
       lblPlayerDowned.setVisible(false);
       lblPlayerAtkUp.setVisible(false);
       lblPlayerDefUp.setVisible(false);
       lblPlayerWeak.setVisible(false);
       lblPlayerMissed.setVisible(false);
       lblPlayerHitEvadeUp.setVisible(false);
       lblWarningHP.setVisible(false);
       lblPlayerHPLow.setVisible(false);
       lblWarningSP.setVisible(false);
       lblPlayerSPLow.setVisible(false);
       lblPlayerDowned.setVisible(false);
       backWarningHPPanel.setVisible(false);
       backWarningSPPanel.setVisible(false);
    }//end hideIndicators
    
    /**
     * Sets all bars to their fullest
     * Pre: None
     * Post: Sets bars to their maximum value.
     */
    public void setProgressBars(){
        progressBarPlayerHP.setValue(430);
        progressBarPlayerSP.setValue(220);
        progressBarEnemyHP.setValue(1000);
        progressBarEnemySP.setValue(300);
    }//end setProgressBars
    
    /**
     * Calculates the difference between two numbers.
     * Pre: num1 > num2
     * Post: Returns the difference.
     */
    public int subtract(int num1, int num2){
        int difference = num1 - num2;
        return difference;
    }//end subtract
    
    /**
     * Generates a random number between 0 and 1.
     * Pre: None
     * Post: Returns a random number
     */
    public double rngGenerator(){
        Random generateNum = new Random();
        double randNum = generateNum.nextDouble();
        return randNum;
    }//end rngGenerator
    
    /**
     * Gets the value of player's HP.
     * Post: None
     * Pre: Returns the value of player's HP.
     */
    public int playerHP(){
        int value = progressBarPlayerHP.getValue();
        return value;
    }//end playerHP
    
    /**
     * Gets the value of player's SP.
     * Post: None
     * Pre: Returns the value of player's SP.
     */
    public int playerSP(){
        int value = progressBarPlayerSP.getValue();
        return value;
    }//end playerSP
    
    /**
     * Gets the value of enemy's HP.
     * Post: None
     * Pre: Returns the value of enemy's HP.
     */
    public int enemyHP(){
        int value = progressBarEnemyHP.getValue();
        return value;
    }//end enemyHP
    
    /**
     * Gets the value of enemy's SP.
     * Pre: None
     * Post: Returns the value of enemy's SP.
     */
    public int enemySP(){
        int value = progressBarEnemySP.getValue();
        return value;
    }//end enemySP
    
    /**
     * Disables the moves of the player when it is the enemy's turn.
     * Pre: None
     * Post: Disables all major decision buttons.
     */
    public void disableMoves(){
        btnAttack.setEnabled(false);
        btnSkill.setEnabled(false);
        btnGuard.setEnabled(false);
        btnItems.setEnabled(false);
        btnEscape.setEnabled(false);
    }//end disableMoves
    
    /**
     * Enables the moves of the player when it is their turn.
     * Pre: None
     * Post: Allows the player to make a decision by enabling all major decision buttons
     */
    public void enableMoves(){
        btnAttack.setEnabled(true);
        btnSkill.setEnabled(true);
        btnGuard.setEnabled(true);
        btnItems.setEnabled(true);
        btnEscape.setEnabled(true);
    }//end enableMoves
    
    /**
     * Brings the player to the victory screen when the enemyHP() equals 0.
     * Pre: None
     * Post: Shows the screen that lets the player know they have won.
     */
    public void victory(){
        if(enemyHP()== 0){
            new VictoryScreen().setVisible(true);
            this.setVisible(false);
            clip.stop();
        }
    }//end victory 
    
    /**
     * Brings the player to the lose screen when the playerHP() equals 0.
     * Pre: None
     * Post: Shows the screen that lets the player know they have lost.
     */
    public void lose(){
        if(playerHP()==0){
            new GameOverDefeatLevel2().setVisible(true);
            this.setVisible(false);
            clip.stop();
        }
    }//end lose
    
    /**
     * Monitors the turn of the battle for player's fortify
     * Pre: None
     * Post: Counts the turns until it reaches 4. Once it hits 4, the counter resets
     */
    public void playerTurnMonitorFortify(){
        playerTurnCounterFortify++;
        if(playerTurnCounterFortify == 4){
            playerTurnCounterFortify = 0;
        } 
        if(playerTurnCounterFortify == 3){
            playerFortify = false;
        }
    }//end playerTurnMonitorFortify
    
    /**
     * Monitors the turn of the battle for player's focus
     * Pre: None
     * Post: Counts the turns until it reaches 4. Once it hits 4, the counter resets
     */
    public void playerTurnMonitorFocus(){
        playerTurnCounterFocus++;
        if(playerTurnCounterFocus == 4){
            playerTurnCounterFocus = 0;
        }
        if(playerTurnCounterFocus == 3){
            playerFocus = false;
        }
    }//end playerTurnMonitorFocus
    
    /**
     * Monitors the turn of the battle for enemy's Fortify
     * Pre: None
     * Post: Counts the turns until it reaches 3. Once it hits 3, the counter resets
     */
    public void enemyTurnMonitorFortify(){
        enemyTurnCounterFortify++;
        if(enemyTurnCounterFortify == 3){
            enemyFortify = false;
            enemyTurnCounterFortify = 0;
        }            
    }//end enemyTurnMonitorForitfy
    
    /**
     * Monitors the turn of the battle for enemy's Focus
     * Pre: None
     * Post: Counts the turns until it reaches 3. Once it hits 3, the counter resets
     */
    public void enemyTurnMonitorFocus(){
        enemyTurnCounterFocus++;
        if(enemyTurnCounterFocus == 3){
            enemyFocus = false;
            enemyTurnCounterFocus = 0;
        }
      
    }//end enemyTurnMonitorForitfy
    
    /**
     * Outputs what move the player used for their turn
     * Pre: Enter the index of the move you want
     * Post: Outputs the move the player used, and subtracts the SP if necessary
     */
    public void playerUsed(int index){
        String[] moveList = {"Attack", "Incinerate", "Blizzard", "Volt", "Power Charge", "Fortify", "Focus", "Guard", "Strawberry Smoothie", "Water"};
        if(index >= 1 && index <= 2){
            progressBarPlayerSP.setValue(subtract(playerSP(), 12));
            lblPlayerSPNum.setText(String.valueOf(playerSP()));
            skillsMenuPanel.setVisible(false);
            usedSkillPanel.setVisible(true);
            lblPlayerSkillName.setText(moveList[index]);
            disableMoves();
        }else if(index >= 4 && index <= 5){
            progressBarPlayerSP.setValue(subtract(playerSP(), 22));
            lblPlayerSPNum.setText(String.valueOf(playerSP()));
            skillsMenuPanel.setVisible(false);
            usedSkillPanel.setVisible(true);
            lblPlayerSkillName.setText(moveList[index]);
            disableMoves();
        }else if(index >= 8 && index <= 9){
            itemsMenuPanel.setVisible(false);
            usedSkillPanel.setVisible(true);
            lblPlayerSkillName.setText(moveList[index]);
            disableMoves();
        }else if(index == 0){
            usedSkillPanel.setVisible(true);
            lblPlayerSkillName.setText(moveList[index]);
            disableMoves();
        }else{
            usedSkillPanel.setVisible(true);
            lblPlayerSkillName.setText(moveList[index]);
            disableMoves();
        }
    }//end playerUsed
    
    /**
     * Outputs what move the enemy used for their turn
     * Pre: Enter the index of the move you want
     * Post: Outputs the move the enemy used, and subtracts the SP if necessary
     */
    public void enemyUsed(int index){
        String[] moveList = {"Attack", "Guard", "Power Charge", "Fortify", "Focus", "Thunderbolt"};
        if(index >= 2 && index <= 5){
            progressBarEnemySP.setValue(subtract(enemySP(), 22));
            lblEnemySPNum.setText(String.valueOf(enemySP()));
            skillsMenuPanel.setVisible(false);
            usedSkillPanel.setVisible(true);
            lblEnemySkillName.setText(moveList[index]);
            disableMoves();
        }else if (index == 0 || index == 1){
            usedSkillPanel.setVisible(true);
            lblEnemySkillName.setText(moveList[index]);
            disableMoves();
        }
    }//end enemyUsed
    
    /**
     * Focuses on what happens when the player attacks
     * Pre: None
     * Post: Outputs what happens when the player attacks
     */
    public void playerAttack(){
        //Declaring variables
        double damage = 60;
        double hitChance = rngGenerator();
               
        //When player has power charge buff
        if(playerPowerCharge == true){
           damage = damage*2;
           playerPowerCharge = false;
        }
        
        //When player has focus buff 
        if(playerFocus == true){
            playerHitRate = 1;
        }else{
            playerHitRate = 0.5;
        }
        
        //When enemy is using fortify
        if(enemyFortify == true){
            damage = damage*0.4;
        }
        
        //When enemy has focus buff
        if(enemyFocus == true && playerFocus == true){
            playerHitRate = 0.5;
        }else if(enemyFocus == true){
            playerHitRate = 0.3;
        }
                
        //Deciding what happens when the player recieves a critical or not, or if the enemy is guarding
        if(hitChance < playerHitRate && playerOneMoreTurn == false && enemyGuard == false){
            damage = damage*2;
            progressBarEnemyHP.setValue(subtract(enemyHP(), (int)damage));
            lblEnemyHPNum.setText(String.valueOf(enemyHP()));
            lblEnemyWeak.setVisible(true);
            lblEnemyDmgIndicator.setText("-"+(int)damage);
            lblEnemyDowned.setVisible(true);
            playerTurn = true;
            enemyTurn = false;
            playerOneMoreTurn = true;
        }else if(hitChance < playerHitRate && playerOneMoreTurn == false && enemyGuard == true){
            damage = damage*2*0.4;
            enemyGuard = false;
            playerOneMoreTurn = false;
            progressBarEnemyHP.setValue(subtract(enemyHP(), (int)damage));
            lblEnemyHPNum.setText(String.valueOf(enemyHP()));
            lblEnemyWeak.setVisible(false);
            lblEnemyDmgIndicator.setText("-"+(int)damage); 
        }else if(hitChance < playerHitRate && playerOneMoreTurn == true && enemyGuard == false){
            damage = damage*2;
            progressBarEnemyHP.setValue(subtract(enemyHP(), (int)damage));
            lblEnemyHPNum.setText(String.valueOf(enemyHP()));
            lblEnemyWeak.setVisible(true);
            lblEnemyDmgIndicator.setText("-"+(int)damage);
            playerOneMoreTurn = false;
        }else if(hitChance < playerHitRate && playerOneMoreTurn == true && enemyGuard == true){
            damage = damage*2*0.2;
            progressBarEnemyHP.setValue(subtract(enemyHP(), (int)damage));
            lblEnemyHPNum.setText(String.valueOf(enemyHP()));
            lblEnemyCriticalHit.setVisible(true);
            lblEnemyDmgIndicator.setText("-"+(int)damage);
            enemyGuard = false;
            playerOneMoreTurn = false;
        }else{
            enemyGuard = false;
            playerOneMoreTurn = false;
            lblEnemyCriticalHit.setVisible(false);
            lblEnemyDmgIndicator.setText("Missed"); 
        }
    }//end playerAttacks
    
    /**
     * Focuses on what happens when player uses a spell attack
     * Pre: None
     * Post: Outputs what happens when the player uses a spell attack
     */
    public void playerSpellAttack(){
        double damage = 70;
                
        //When player has power charge buff
        if(playerPowerCharge == true){
            damage = damage*2;
            playerPowerCharge = false;
        }
        
        //When enemy is defending
        if(enemyGuard == true || enemyFortify == true){
            damage = damage*0.4;
            enemyGuard = false;
        }else if(enemyGuard == true && enemyFortify == true){
            damage = damage*0.2;
            enemyGuard = false;
        }
         
        //Applies the damage to the enemy
        progressBarEnemyHP.setValue(subtract(enemyHP(), (int)damage));
        lblEnemyHPNum.setText(String.valueOf(enemyHP()));
        lblEnemyDmgIndicator.setText("-"+(int)damage);
    }//end playerSpellAttack
    
                    
    /**
     * Focuses on what happens when the enemy attacks
     * Pre: None
     * Post: Outputs what happens when the enemy attacks
     */
    public void enemyAttack(){
        //Declaring variables
        double damage = 60;
        enemyCritHit = false;
        double critChance = rngGenerator();
        double hitChance = rngGenerator();
        
        //when enemy has power charge buff 
        if(enemyPowerCharge == true){
            damage = damage*2;
            enemyPowerCharge = false;
        }
        
        //When player has focus buff 
        if(playerFocus == true){
            enemyHitRate = 0.25;
        }else{
            enemyHitRate = 1;
        }
        
        //When enemy has focus buff
        if(enemyFocus == true && playerFocus == true){
            enemyHitRate = 0.65;
        }
        
        //Decides how much damage the enemy will do to the player
        if(playerFortify == true && playerGuard == false && critChance < critRate && hitChance < enemyHitRate && enemyOneMoreTurn == false){ 
            damage = damage*2*0.4;
            progressBarPlayerHP.setValue(subtract(playerHP(), (int)damage));
            lblPlayerHPNum.setText(String.valueOf(playerHP()));
            lblPlayerDmgIndicator.setText("-"+(int)damage);
            lblPlayerCriticalHit.setVisible(true);
            enemyTurn = true;
            enemyCritHit = true;
            playerTurn = false;
            enemyOneMoreTurn = true;
           
            //When player is defeated
            if(playerHP() == 0){
                playerTurn = true;
                enemyTurn = false;
                enemyOneMoreTurn = false;
                enemyCritHit = false;
            }
           
            //Delays showing the enemy has another turn
            delay2 = new Timer(1000, new ActionListener(){
           
                @Override
                public void actionPerformed(ActionEvent e){
                    //Shows enemy that they have another turn
                    oneMoreTurnPanel.setVisible(true);
                    lblEnemyMoreTurnPanel.setText("+1 Turn");
                    usedSkillPanel.setVisible(false);
                    lblPlayerCriticalHit.setVisible(false);
                    lblPlayerDmgIndicator.setText("");
                    delay2.stop();
                }
            });
            delay2.start();
        }else if(playerFortify == true && playerGuard == true && critChance < critRate && hitChance < enemyHitRate){
            damage = damage*2*0.2;
            progressBarPlayerHP.setValue(subtract(playerHP(), (int)damage));
            lblPlayerHPNum.setText(String.valueOf(playerHP()));
            lblPlayerDmgIndicator.setText("-"+(int)damage);
            lblPlayerCriticalHit.setVisible(true);
            playerGuard = false;
        }else if(playerFortify == false && playerGuard == true && critChance < critRate && hitChance < enemyHitRate){
            damage = damage*2*0.4;
            progressBarPlayerHP.setValue(subtract(playerHP(), (int)damage));
            lblPlayerHPNum.setText(String.valueOf(playerHP()));
            lblPlayerDmgIndicator.setText("-"+(int)damage);
            lblPlayerCriticalHit.setVisible(true);
            playerGuard = false;
        }else if(playerFortify == false && playerGuard == false && critChance < critRate && hitChance < enemyHitRate && enemyOneMoreTurn == false){
            damage = damage*2;
            progressBarPlayerHP.setValue(subtract(playerHP(), (int)damage));
            lblPlayerHPNum.setText(String.valueOf(playerHP()));
            lblPlayerDmgIndicator.setText("-"+(int)damage);
            lblPlayerCriticalHit.setVisible(true);
            lblPlayerDowned.setVisible(true);
            enemyTurn = true;
            enemyCritHit = true;
            playerTurn = false;
            enemyOneMoreTurn = true;
            
            //When player is defeated
            if(playerHP() == 0){
                playerTurn = true;
                enemyTurn = false;
                enemyOneMoreTurn = false;
                enemyCritHit = false;
            }
            
            //Delays showing the enemy has an additional turn
            delay2 = new Timer(1000, new ActionListener(){
           
                @Override
                public void actionPerformed(ActionEvent e){
                    //Shows enemy that they have another turn
                    oneMoreTurnPanel.setVisible(true);
                    lblEnemyMoreTurnPanel.setText("+1 Turn");
                    usedSkillPanel.setVisible(false);
                    lblPlayerCriticalHit.setVisible(false);
                    lblPlayerDmgIndicator.setText("");
                    delay2.stop();
                
                }
            });
            delay2.start();
        }else if(playerFortify == true && playerGuard == false && critChance < critRate && hitChance < enemyHitRate && enemyOneMoreTurn == true){ 
            damage = damage*2*0.4;
            progressBarPlayerHP.setValue(subtract(playerHP(), (int)damage));
            lblPlayerHPNum.setText(String.valueOf(playerHP()));
            lblPlayerDmgIndicator.setText("-"+(int)damage);
            lblPlayerCriticalHit.setVisible(true);
            enemyTurn = false;
            enemyCritHit = false;
            playerTurn = true;
            enemyOneMoreTurn = false;
           
            //Delays the player losing in the enemy's additional attack
            delay14 = new Timer(1000, new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e){
                    lose();
                    delay14.stop();
                }
            });
            delay14.start(); 
           
           //Delays hiding of indicators
            delay2 = new Timer(1000, new ActionListener(){
           
                @Override
                public void actionPerformed(ActionEvent e){
                    //Hides downed label when enemy does not get a critical 
                    lblPlayerDowned.setVisible(false);    
                    delay2.stop();
                
                }
            });
            delay2.start();
        }else if(playerFortify == false && playerGuard == false && critChance < critRate && hitChance < enemyHitRate && enemyOneMoreTurn == true){
            damage = damage*2;
            progressBarPlayerHP.setValue(subtract(playerHP(), (int)damage));
            lblPlayerHPNum.setText(String.valueOf(playerHP()));
            lblPlayerDmgIndicator.setText("-"+(int)damage);
            lblPlayerCriticalHit.setVisible(true);
            enemyTurn = false;
            enemyCritHit = false;
            playerTurn = true;
            enemyOneMoreTurn = false;
            
            //Delays the player losing in the enemy's additional attack
            delay14 = new Timer(1000, new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e){
                    lose();
                    delay14.stop();
                }
            });
            delay14.start(); 
            
            //Delays hiding of indicators
            delay2 = new Timer(1000, new ActionListener(){
           
                @Override
                public void actionPerformed(ActionEvent e){
                    //Hides downed label when enemy does not get a critical 
                    lblPlayerDowned.setVisible(false);     
                    delay2.stop();                
                }
            });
            delay2.start();
        }else if(playerFortify == true && playerGuard == false && hitChance < enemyHitRate){
           damage = damage*0.4;
           progressBarPlayerHP.setValue(subtract(playerHP(), (int)damage));
           lblPlayerHPNum.setText(String.valueOf(playerHP()));
           lblPlayerDmgIndicator.setText("-"+(int)damage);
        }else if(playerFortify == true && playerGuard == true && hitChance < enemyHitRate){
            damage = damage*0.2;
            progressBarPlayerHP.setValue(subtract(playerHP(), (int)damage));
            lblPlayerHPNum.setText(String.valueOf(playerHP()));
            lblPlayerDmgIndicator.setText("-"+(int)damage);
            playerGuard = false;
        }else if(playerFortify == false && playerGuard == true && hitChance < enemyHitRate){
            damage = damage*0.4;
            progressBarPlayerHP.setValue(subtract(playerHP(), (int)damage));
            lblPlayerHPNum.setText(String.valueOf(playerHP()));
            lblPlayerDmgIndicator.setText("-"+(int)damage);
            playerGuard = false;
        }else if(hitChance < enemyHitRate) {
            progressBarPlayerHP.setValue(subtract(playerHP(), (int)damage));
            lblPlayerHPNum.setText(String.valueOf(playerHP()));
            lblPlayerDmgIndicator.setText("-"+(int)damage);
        }else{
           lblPlayerMissed.setVisible(true);
        } 
    }//end enemyAttack
    
    /**
     * Focuses on what happens when the enemy uses the attack, thunderbolt 
     * Pre: None
     * Post: Outputs what happens when enemy uses electric attack
     */
    public void enemyElectricAttack(){
        double damage = 70;
        
        //When enemy has power charge
        if(enemyPowerCharge == true){
            damage = damage*2;
            enemyPowerCharge = false;
        }
        
        //When player is guarding, has defense buff, or both
        if(playerGuard == true || playerFortify == true){
            damage = damage*0.4;
        }else if(playerGuard == true && playerFortify == true){
            damage = damage*0.2;
        }
        
        //Applies the damage to the player
        progressBarPlayerHP.setValue(subtract(playerHP(), (int)damage));
        lblPlayerHPNum.setText(String.valueOf(playerHP()));
        lblPlayerDmgIndicator.setText("-"+(int)damage);
    }//end enemyElectricAttack
     
    /**
     * Focuses on what happens when the enemy uses the buff power charge
     * Pre: None
     * Post: Outputs what happens when enemy uses power charge
     */
    public void enemyAtkUp(){
        //Calculates the rate of when enemy will use power charge
        if(enemyHP()> 720 && enemyHP()<= 1000){
            enemyPowerChargeRate = 0.10;
        }else if(enemyHP() > 580 && enemyHP() <= 720){
            enemyPowerChargeRate = 0.25;
        }else if(enemyHP() > 350 && enemyHP() <= 580){
            enemyPowerChargeRate = 0.35;
        }else if(enemyHP() > 0 && enemyHP() <= 250){
            enemyPowerChargeRate = 0.05;
        }
 
        //When another buff is active
        if(enemyPowerCharge == true || enemyFortify == true || enemyFocus == true || iceImmune == true || enemyGuard == true){
            enemyPowerChargeRate = 0.85;
        }
        
        double enemyPowerChargeChance = rngGenerator();
        
        if(enemyPowerChargeChance < enemyPowerChargeRate && subtract(enemySP(), 22) >= 0){
           enemyPowerCharge = true;
        }
        
        //Outputs the buff
        if(enemyTurn == true && enemyPowerCharge == true){
            //Keeping track of enemy buffs
            if(enemyTurnCounterFortify >= 1){
                enemyTurnMonitorFortify();
            }
            if(enemyTurnCounterFocus >= 1){
                enemyTurnMonitorFocus();
            }
            
            enemyUsed(2);
            enemyTurn = false;
            playerTurn = true;
            playerGuard = false;
            enemyOneMoreTurn = false;
        
            delay9 = new Timer(1000, new ActionListener(){
           
                @Override
                public void actionPerformed(ActionEvent e){
                    lblEnemyAtkUp.setVisible(true);
                    delay9.stop();  
                }
            });
            delay9.start(); 
        }
    }//end enemyAtkUp
    
    /**
     * Focuses on what happens when the enemy uses the buff fortify
     * Pre: None
     * Post: Outputs what happens when enemy uses fortify
     */
    public void enemyDefUp(){
        //Calculates the rate of when enemy will use fortify
        if(enemyHP() > 580 && enemyHP()<= 720){
            enemyFortifyRate = 0.10;
        }else if(enemyHP() > 200 && enemyHP() <= 580){
            enemyFortifyRate = 0.80;
        }else if(enemyHP() > 0 && enemyHP() <= 200){
            enemyFortifyRate = 0.90;
        }
        
        //When another buff is active
        if(enemyFortify == true || enemyFocus == true || iceImmune == true || enemyGuard == true){
            enemyFortifyRate = 0.45;
        }
        if(enemyPowerCharge == true){
            enemyFortifyRate = 0;
        }
        
        double enemyFortifyChance = rngGenerator();
        
        if(enemyFortifyChance < enemyFortifyRate && subtract(enemySP(), 22) >= 0){
           enemyFortify = true;
        }
        //Outputs the buff
        if(enemyTurn == true && enemyFortify == true){
            //Keeping track of enemy buffs
            if(enemyTurnCounterFocus >= 1){
                enemyTurnMonitorFocus();
            }
            enemyTurnMonitorFortify();
            enemyUsed(3);
            enemyTurn = false;
            playerTurn = true;
            enemyPowerCharge = false;
            enemyOneMoreTurn = false;
            playerGuard = false;
        
            delay10 = new Timer(1000, new ActionListener(){
           
                @Override
                public void actionPerformed(ActionEvent e){
                    lblEnemyDefUp.setVisible(true);
                    delay10.stop();  
                }
            });
            delay10.start();  
        }
    }//end enemyDefUp
    
    /**
     * Focuses on what happens when the enemy uses the buff focus
     * Pre: None
     * Post: Outputs what happens when enemy uses focus
     */
    public void enemyHitEvadeUp(){ 
        //Calculates the rate of when enemy will use focus
        if(enemyHP() > 580 && enemyHP()<= 720){
            enemyFocusRate = 0.15;
        }else if(enemyHP() > 350 && enemyHP() <= 580){
            enemyFocusRate = 0.35;
        }else if(enemyHP() > 0 && enemyHP() <= 350){
            enemyFocusRate = 0.55;
        }
        
        //When another buff is active
        if(enemyFortify == true || enemyFocus == true || iceImmune == true || enemyGuard == true){
            enemyFocusRate = 0.55;
        }
        if(enemyPowerCharge == true){
            enemyFocusRate = 0;
        }
        
        double enemyFocusChance = rngGenerator();
        
        if(enemyFocusChance < enemyFocusRate && subtract(enemySP(), 22) >= 0){
           enemyFocus = true;
        } 
        
        //Outputs the buff
        if(enemyTurn == true && enemyFocus == true){
            //Keeping track of enemy buffs
            if(enemyTurnCounterFortify >= 1){
                enemyTurnMonitorFortify();
            }
            
            enemyTurnMonitorFocus();
            enemyUsed(4);
            enemyTurn = false;
            playerTurn = true;
            enemyCritHit = false;
            enemyPowerCharge = false;
            enemyOneMoreTurn = false;
            playerGuard = false;
        
            delay11 = new Timer(1000, new ActionListener(){
           
                @Override
                public void actionPerformed(ActionEvent e){
                    lblEnemyHitEvadeUp.setVisible(true);
                    delay11.stop();  
                }
            });
            delay11.start();  
        }
    }//end enemyHitEvadeUp
    
    /**
     * Focuses on the enemy guarding
     * Pre: None
     * Post: Shows what happens when the enemy guards
     */
    public void enemyGuard(){      
        //Calculating chances
        if(enemyHP() >= 500 && enemyHP()<= 1000){
            enemyGuardRate = 0.15;
        }else if (enemyHP() > 200 && enemyHP() < 500){
            enemyGuardRate = 0.50;
        }else if (enemyHP() > 0 && enemyHP() <= 200){
            enemyGuardRate = 0.45;
        }
        
        //When another buff is active
        if(enemyFortify == true || enemyFocus == true || iceImmune == true || enemyGuard == true){
            enemyGuardRate = 0.35;
        }
        if(enemyPowerCharge == true){
            enemyGuardRate = 0;
        }
      
        double enemyGuardChance = rngGenerator();
        
        if(enemyGuardChance < enemyGuardRate){
            enemyGuard = true;
        } 
        
        if(enemyTurn == true && enemyGuard == true){
            //Keeping track of enemy buffs
            if(enemyTurnCounterFortify >= 1){
                enemyTurnMonitorFortify();
            }
            if(enemyTurnCounterFocus >= 1){
                enemyTurnMonitorFocus();
            }
            
            enemyUsed(1);
            enemyPowerCharge = false;
            enemyOneMoreTurn = false;
            enemyTurn = false;
            playerTurn = true;
            playerGuard = false;
        }       
    }//end enemyGuard
    
    /**
     * Focuses on what happens when player's skill points is low
     * Pre: None
     * Post: Show an alert notifying the player when their skill points is 75 and lower
     */
    public void warnPlayerSPLow(){
        //Warning for when player's sp is low
        if(playerSP() <= 37){
            backWarningSPPanel.setVisible(true);
            lblPlayerSPLow.setVisible(true);
            for(int i = 1; i <= 5; i++){
                if(i == 1){
                    delay12 = new Timer(5000, new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e){
                            lblWarningHP.setVisible(true);
                            delay12.stop();
                        }
                    });
                    delay12.start();
                }
                if(i == 5){
                    delay12 = new Timer(5000, new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e){
                            lblWarningSP.setVisible(false);
                            delay12.stop();
                        }
                    });
                    delay12.start();
                }
                if(i == 3 || i == 2|| i == 4){

                    delay12 = new Timer(1000, new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e){
                            lblWarningSP.setVisible(true);
                            delay12.stop();
                    }
                });
                delay12.start();

                    delay13 = new Timer(1500, new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e){
                            lblWarningSP.setVisible(false);
                            delay13.stop();
                        }
                    });
                    delay13.start();
                }
            }
        }
    }// end warnPlayerSPLow
    
      /**
     * Focuses on what happens when player's health points is low
     * Pre: None
     * Post: Show an alert notifying the player when their health points is 200 and lower
     */
    public void warnPlayerHPLow(){
        //Warning for when player's sp is low
        if(playerHP() <= 200){
            backWarningHPPanel.setVisible(true);
            lblPlayerHPLow.setVisible(true);
            for(int i = 1; i <= 5; i++){
                if(i == 1){
                    delay12 = new Timer(5000, new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e){
                            lblWarningHP.setVisible(true);
                            delay12.stop();
                        }
                    });
                    delay12.start();
                }
                if(i == 5){
                    delay12 = new Timer(5000, new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e){
                            lblWarningHP.setVisible(false);
                            delay12.stop();
                        }
                    });
                    delay12.start();
                }
                if(i == 3 || i == 2|| i == 4){

                    delay12 = new Timer(1000, new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e){
                            lblWarningHP.setVisible(true);
                            delay12.stop();
                    }
                });
                delay12.start();

                    delay13 = new Timer(1500, new ActionListener(){

                        @Override
                        public void actionPerformed(ActionEvent e){
                            lblWarningHP.setVisible(false);
                            delay13.stop();
                        }
                    });
                    delay13.start();
                }
            }
        }     
    }// end warnPlayerHPLow
    
    /**
     * End the player's turn
     * Pre: None
     * Post: Hides the indicators to show that the player's turn has ended
     */
    public void endPlayerTurn(){
        //Hides the the power charge label when player does not have buff
        if(playerPowerCharge == false){
           lblPlayerAtkUp.setVisible(false); 
        }
        
        //Re-enables the buttons when the player has enough SP
        if(subtract(playerSP(), 12) >= 0){
            btnFireAtk.setEnabled(true);
            btnIceAtk.setEnabled(true);
            btnElectricAtk.setEnabled(true);
        }
        if(subtract(playerSP(), 22) >= 0){
            btnAtkUp.setEnabled(true);
            btnDefUp.setEnabled(true);
            btnHitUp.setEnabled(true);
        }
        
        //Hides the warning message of the player's health and skill points
        if(playerHP() > 200){
            backWarningHPPanel.setVisible(false);
            lblPlayerHPLow.setVisible(false);
        }
        if(playerSP() > 75){
            backWarningSPPanel.setVisible(false);
            lblPlayerSPLow.setVisible(false);
        }
        
        warnPlayerSPLow();
        usedSkillPanel.setVisible(false);
        lblEnemyCriticalHit.setVisible(false);
        lblEnemyWeak.setVisible(false);
        lblPlayerDmgIndicator.setText("");
        lblEnemyDmgIndicator.setText("");
        lblPlayerSkillName.setText("");
        victory();
    }//end endPlayerTurn
    
    
    /**
     * End the enemy's turn
     * Pre: None
     * Post: Hide all indicators to show that the enemy's turn has ended
     */
    public void endEnemyTurn(){
        //Hides the appropriate label when the buff is ended
        if(playerTurnCounterFortify == 2){
            lblPlayerDefUp.setVisible(false);
        }
        if(playerTurnCounterFocus == 2){
            lblPlayerHitEvadeUp.setVisible(false);
        }
        if(enemyCritHit == false || enemyOneMoreTurn == false){
            lblPlayerDowned.setVisible(false);    
        }
        if(enemyPowerCharge == false){
            lblEnemyAtkUp.setVisible(false);
        }
        if(enemyTurnCounterFortify == 0){
            lblEnemyDefUp.setVisible(false);
        }
        if(enemyTurnCounterFocus == 0){
            lblEnemyHitEvadeUp.setVisible(false);
        }
        if(enemyTurnCounterIce == 0){
            lblEnemyIceImmune.setVisible(false);
        }
        
        warnPlayerHPLow();
        lose();
        oneMoreTurnPanel.setVisible(false);
        lblPlayerWeak.setVisible(false);
        lblPlayerMissed.setVisible(false);
        lblPlayerMoreTurnPanel.setText("");
        lblEnemyMoreTurnPanel.setText("");
        lblPlayerSkillName.setText("");
        lblEnemySkillName.setText("");
        usedSkillPanel.setVisible(false);
        lblPlayerDmgIndicator.setText("");
        lblPlayerCriticalHit.setVisible(false);
        enableMoves();
    }// end endnemyTurn
    
    /**
     * Artificial intelligence of the enemy
     * Pre: None
     * Post: Enemy will decide what to do to the player.
     */
    public void CPUEnemy(){
        //Decidng when the enemy should guard or use a buff
        if(enemyPowerCharge == false && enemyTurn == true){
            enemyAtkUp();
        }
        if(enemyFortify == false && enemyTurn == true){
            enemyDefUp();
        }
        if(enemyFocus == false && enemyTurn == true){
            enemyHitEvadeUp();
        }
        if(enemyGuard == false && enemyTurn == true){
            enemyGuard();
        }
        
        //When enemy has additional turn
        if(enemyOneMoreTurn == true && playerFortify == true){
            playerFortify = false;
        }else if(enemyOneMoreTurn == true && playerFocus == true){
            playerFocus = false;
        }
        
        //Deciding when enemy uses an attack
        if(enemyTurn == true){
            double enemyElectricAtkChance = rngGenerator();
            if(enemyElectricAtkChance < enemyElectricAtkRate && subtract(enemySP(), 22) >= 0){
                //Keeping track of enemy buffs
                if(enemyTurnCounterFortify >= 1){
                    enemyTurnMonitorFortify();
                }
                if(enemyTurnCounterFocus >= 1){
                    enemyTurnMonitorFocus();
                }
                //enemy uses ElectricAttack
                enemyUsed(5);
                enemyTurn = false;
                playerTurn = true;
            
                delay1 = new Timer(1000, new ActionListener(){
           
                    @Override
                    public void actionPerformed(ActionEvent e){
                        enemyElectricAttack(); 
                        delay1.stop();  
                    }
                });
                delay1.start();
            }else{
                //Keeping track of enemy buffs
                if(enemyTurnCounterFortify >= 1){
                    enemyTurnMonitorFortify();
                }
                if(enemyTurnCounterFocus >= 1){
                    enemyTurnMonitorFocus();
                }
                
                //enemyAttacks
                enemyUsed(0);
                enemyTurn = false;
                playerTurn = true;
                delay1 = new Timer(1000, new ActionListener(){
           
                    @Override
                    public void actionPerformed(ActionEvent e){
                        enemyAttack(); 
                        delay1.stop();  
                    }
                });
                delay1.start();
               
            }    
        }  
    }//end CPUEnemy
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        battleBackPanel = new javax.swing.JPanel();
        oneMoreTurnPanel = new javax.swing.JPanel();
        lblPlayerMoreTurnPanel = new javax.swing.JLabel();
        lblEnemyMoreTurnPanel = new javax.swing.JLabel();
        movesMenuPanel = new javax.swing.JPanel();
        btnAttack = new javax.swing.JButton();
        btnSkill = new javax.swing.JButton();
        btnGuard = new javax.swing.JButton();
        btnItems = new javax.swing.JButton();
        btnEscape = new javax.swing.JButton();
        usedSkillPanel = new javax.swing.JPanel();
        lblPlayerSkillName = new javax.swing.JLabel();
        lblEnemySkillName = new javax.swing.JLabel();
        skillsMsgPanel = new javax.swing.JPanel();
        lblSkillMsg = new javax.swing.JLabel();
        lblPlayerSkillMsg = new javax.swing.JLabel();
        btnSkillsMsgQuit = new javax.swing.JButton();
        skillsMenuPanel = new javax.swing.JPanel();
        lblSkillsList = new javax.swing.JLabel();
        btnFireAtk = new javax.swing.JButton();
        btnIceAtk = new javax.swing.JButton();
        btnElectricAtk = new javax.swing.JButton();
        btnAtkUp = new javax.swing.JButton();
        btnDefUp = new javax.swing.JButton();
        btnHitUp = new javax.swing.JButton();
        lblFireDesc = new javax.swing.JLabel();
        lblIceDesc = new javax.swing.JLabel();
        lblElectricDesc = new javax.swing.JLabel();
        lblAtkDesc = new javax.swing.JLabel();
        lblDefDesc = new javax.swing.JLabel();
        lblHitDesc1 = new javax.swing.JLabel();
        lblHitDesc2 = new javax.swing.JLabel();
        lblFireSPCost = new javax.swing.JLabel();
        lblIceSPCost = new javax.swing.JLabel();
        lblElectricSPCost = new javax.swing.JLabel();
        lblDefSPCost = new javax.swing.JLabel();
        lblAtkSPCost = new javax.swing.JLabel();
        lblHitSPCost = new javax.swing.JLabel();
        btnSkillsMenuQuit = new javax.swing.JButton();
        itemsMsgPanel = new javax.swing.JPanel();
        lblItemMsg = new javax.swing.JLabel();
        lblPlayerItemMsg = new javax.swing.JLabel();
        btnItemsMsgQuit = new javax.swing.JButton();
        itemsMenuPanel = new javax.swing.JPanel();
        lblItemsList = new javax.swing.JLabel();
        btnStrawberrySmoothie = new javax.swing.JButton();
        btnWater = new javax.swing.JButton();
        lblStrawberrySmoothieDesc = new javax.swing.JLabel();
        lblWaterDesc = new javax.swing.JLabel();
        lblAmountOfStrawberrySmoothie = new javax.swing.JLabel();
        lblAmountOfWater = new javax.swing.JLabel();
        btnItemsMenuQuit = new javax.swing.JButton();
        lbRagnaroklEnemy = new javax.swing.JLabel();
        lblPlayerHPNum = new javax.swing.JLabel();
        lblPlayerSPNum = new javax.swing.JLabel();
        lblEnemyHPNum = new javax.swing.JLabel();
        lblEnemySPNum = new javax.swing.JLabel();
        progressBarPlayerHP = new javax.swing.JProgressBar();
        progressBarPlayerSP = new javax.swing.JProgressBar();
        progressBarEnemyHP = new javax.swing.JProgressBar();
        progressBarEnemySP = new javax.swing.JProgressBar();
        lblPlayerHP = new javax.swing.JLabel();
        lblPlayerSP = new javax.swing.JLabel();
        lblEnemyName = new javax.swing.JLabel();
        lblEnemyHP = new javax.swing.JLabel();
        lblEnemySP = new javax.swing.JLabel();
        lblEnemyDmgIndicator = new javax.swing.JLabel();
        lblEnemyCriticalHit = new javax.swing.JLabel();
        lblEnemyDowned = new javax.swing.JLabel();
        lblEnemyIceImmune = new javax.swing.JLabel();
        lblEnemyAtkUp = new javax.swing.JLabel();
        lblEnemyDefUp = new javax.swing.JLabel();
        lblEnemyHitEvadeUp = new javax.swing.JLabel();
        lblEnemyWeak = new javax.swing.JLabel();
        lblPlayerDmgIndicator = new javax.swing.JLabel();
        lblPlayerCriticalHit = new javax.swing.JLabel();
        lblPlayerWeak = new javax.swing.JLabel();
        lblPlayerMissed = new javax.swing.JLabel();
        lblPlayerDowned = new javax.swing.JLabel();
        lblPlayerHitEvadeUp = new javax.swing.JLabel();
        lblPlayerAtkUp = new javax.swing.JLabel();
        lblPlayerDefUp = new javax.swing.JLabel();
        backWarningHPPanel = new javax.swing.JPanel();
        lblWarningHP = new javax.swing.JLabel();
        backWarningSPPanel = new javax.swing.JPanel();
        lblWarningSP = new javax.swing.JLabel();
        lblPlayerHPLow = new javax.swing.JLabel();
        lblPlayerSPLow = new javax.swing.JLabel();
        lblDungeonBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        battleBackPanel.setPreferredSize(new java.awt.Dimension(1000, 720));
        battleBackPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        oneMoreTurnPanel.setBackground(new java.awt.Color(153, 204, 255));
        oneMoreTurnPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPlayerMoreTurnPanel.setFont(new java.awt.Font("Tahoma", 0, 100)); // NOI18N
        lblPlayerMoreTurnPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        oneMoreTurnPanel.add(lblPlayerMoreTurnPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 590, 290));

        lblEnemyMoreTurnPanel.setFont(new java.awt.Font("Tahoma", 0, 100)); // NOI18N
        lblEnemyMoreTurnPanel.setForeground(new java.awt.Color(204, 0, 0));
        lblEnemyMoreTurnPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        oneMoreTurnPanel.add(lblEnemyMoreTurnPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 590, 290));

        battleBackPanel.add(oneMoreTurnPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 650, 380));

        movesMenuPanel.setBackground(new java.awt.Color(153, 204, 255));
        movesMenuPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnAttack.setBackground(new java.awt.Color(255, 204, 102));
        btnAttack.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAttack.setText("Attack");
        btnAttack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAttackActionPerformed(evt);
            }
        });
        movesMenuPanel.add(btnAttack, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, -1, -1));

        btnSkill.setBackground(new java.awt.Color(255, 204, 102));
        btnSkill.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnSkill.setText("Skill");
        btnSkill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkillActionPerformed(evt);
            }
        });
        movesMenuPanel.add(btnSkill, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, -1, -1));

        btnGuard.setBackground(new java.awt.Color(255, 204, 102));
        btnGuard.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnGuard.setText("Guard");
        btnGuard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardActionPerformed(evt);
            }
        });
        movesMenuPanel.add(btnGuard, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 30, -1, -1));

        btnItems.setBackground(new java.awt.Color(255, 204, 102));
        btnItems.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnItems.setText("Items");
        btnItems.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItemsActionPerformed(evt);
            }
        });
        movesMenuPanel.add(btnItems, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, -1, -1));

        btnEscape.setBackground(new java.awt.Color(255, 204, 102));
        btnEscape.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnEscape.setText("Escape");
        btnEscape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscapeActionPerformed(evt);
            }
        });
        movesMenuPanel.add(btnEscape, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 30, -1, -1));

        battleBackPanel.add(movesMenuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 600, 1000, 120));

        usedSkillPanel.setBackground(new java.awt.Color(153, 204, 255));
        usedSkillPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPlayerSkillName.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblPlayerSkillName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usedSkillPanel.add(lblPlayerSkillName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 220, 30));

        lblEnemySkillName.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        lblEnemySkillName.setForeground(new java.awt.Color(204, 0, 0));
        lblEnemySkillName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usedSkillPanel.add(lblEnemySkillName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 220, 30));

        battleBackPanel.add(usedSkillPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 300, 60));

        skillsMsgPanel.setBackground(new java.awt.Color(153, 204, 255));
        skillsMsgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSkillMsg.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblSkillMsg.setText("Message");
        skillsMsgPanel.add(lblSkillMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblPlayerSkillMsg.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblPlayerSkillMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        skillsMsgPanel.add(lblPlayerSkillMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 390, 50));

        btnSkillsMsgQuit.setBackground(new java.awt.Color(255, 204, 102));
        btnSkillsMsgQuit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSkillsMsgQuit.setText("X");
        btnSkillsMsgQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkillsMsgQuitActionPerformed(evt);
            }
        });
        skillsMsgPanel.add(btnSkillsMsgQuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, -1, -1));

        battleBackPanel.add(skillsMsgPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 600, 320));

        skillsMenuPanel.setBackground(new java.awt.Color(153, 204, 255));
        skillsMenuPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSkillsList.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblSkillsList.setText("Skills List");
        skillsMenuPanel.add(lblSkillsList, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 220, 40));

        btnFireAtk.setBackground(new java.awt.Color(255, 204, 102));
        btnFireAtk.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnFireAtk.setText("Incinerate");
        btnFireAtk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFireAtkActionPerformed(evt);
            }
        });
        skillsMenuPanel.add(btnFireAtk, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        btnIceAtk.setBackground(new java.awt.Color(255, 204, 102));
        btnIceAtk.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnIceAtk.setText("Blizzard");
        btnIceAtk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIceAtkActionPerformed(evt);
            }
        });
        skillsMenuPanel.add(btnIceAtk, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 160, -1));

        btnElectricAtk.setBackground(new java.awt.Color(255, 204, 102));
        btnElectricAtk.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnElectricAtk.setText("Volt");
        btnElectricAtk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnElectricAtkActionPerformed(evt);
            }
        });
        skillsMenuPanel.add(btnElectricAtk, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 160, -1));

        btnAtkUp.setBackground(new java.awt.Color(255, 204, 102));
        btnAtkUp.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAtkUp.setText("Power Charge");
        btnAtkUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtkUpActionPerformed(evt);
            }
        });
        skillsMenuPanel.add(btnAtkUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 210, -1));

        btnDefUp.setBackground(new java.awt.Color(255, 204, 102));
        btnDefUp.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnDefUp.setText("Fortify");
        btnDefUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDefUpActionPerformed(evt);
            }
        });
        skillsMenuPanel.add(btnDefUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 170, -1));

        btnHitUp.setBackground(new java.awt.Color(255, 204, 102));
        btnHitUp.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnHitUp.setText("Focus");
        btnHitUp.setActionCommand("Fortify");
        btnHitUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHitUpActionPerformed(evt);
            }
        });
        skillsMenuPanel.add(btnHitUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, 170, -1));

        lblFireDesc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblFireDesc.setText("Deals fire damage.");
        skillsMenuPanel.add(lblFireDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 250, 40));

        lblIceDesc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblIceDesc.setText("Deals ice damage.");
        skillsMenuPanel.add(lblIceDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 250, 40));

        lblElectricDesc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblElectricDesc.setText("Deals electric damage.");
        skillsMenuPanel.add(lblElectricDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 270, 40));

        lblAtkDesc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblAtkDesc.setText("Doubles your ATK for your next turn.");
        skillsMenuPanel.add(lblAtkDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 450, 40));

        lblDefDesc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblDefDesc.setText("Increase your DEF for 3 turns.");
        skillsMenuPanel.add(lblDefDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 380, 40));

        lblHitDesc1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblHitDesc1.setText("Increases your HIT/EVADE RATE for ");
        skillsMenuPanel.add(lblHitDesc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 400, 460, 40));

        lblHitDesc2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblHitDesc2.setText("3 turns.");
        skillsMenuPanel.add(lblHitDesc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 440, 400, 40));

        lblFireSPCost.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblFireSPCost.setText("12 SP");
        skillsMenuPanel.add(lblFireSPCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, -1, -1));

        lblIceSPCost.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblIceSPCost.setText("12 SP");
        skillsMenuPanel.add(lblIceSPCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 140, -1, -1));

        lblElectricSPCost.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblElectricSPCost.setText("12 SP");
        skillsMenuPanel.add(lblElectricSPCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 200, -1, -1));

        lblDefSPCost.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblDefSPCost.setText("22 SP");
        skillsMenuPanel.add(lblDefSPCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 330, -1, -1));

        lblAtkSPCost.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblAtkSPCost.setText("22 SP");
        skillsMenuPanel.add(lblAtkSPCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 270, -1, -1));

        lblHitSPCost.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblHitSPCost.setText("22 SP");
        skillsMenuPanel.add(lblHitSPCost, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 400, -1, -1));

        btnSkillsMenuQuit.setBackground(new java.awt.Color(255, 204, 102));
        btnSkillsMenuQuit.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnSkillsMenuQuit.setText("X");
        btnSkillsMenuQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSkillsMenuQuitActionPerformed(evt);
            }
        });
        skillsMenuPanel.add(btnSkillsMenuQuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 90, 30));

        battleBackPanel.add(skillsMenuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 940, 500));

        itemsMsgPanel.setBackground(new java.awt.Color(153, 204, 255));
        itemsMsgPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblItemMsg.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblItemMsg.setText("Message");
        itemsMsgPanel.add(lblItemMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        lblPlayerItemMsg.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblPlayerItemMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        itemsMsgPanel.add(lblPlayerItemMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 390, 50));

        btnItemsMsgQuit.setBackground(new java.awt.Color(255, 204, 102));
        btnItemsMsgQuit.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnItemsMsgQuit.setText("X");
        btnItemsMsgQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItemsMsgQuitActionPerformed(evt);
            }
        });
        itemsMsgPanel.add(btnItemsMsgQuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, -1, -1));

        battleBackPanel.add(itemsMsgPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 600, 320));

        itemsMenuPanel.setBackground(new java.awt.Color(153, 204, 255));
        itemsMenuPanel.setPreferredSize(new java.awt.Dimension(940, 226));
        itemsMenuPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblItemsList.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lblItemsList.setText("Items List");
        itemsMenuPanel.add(lblItemsList, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 220, 40));

        btnStrawberrySmoothie.setBackground(new java.awt.Color(255, 204, 102));
        btnStrawberrySmoothie.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnStrawberrySmoothie.setText("Strawberry Smoothie");
        btnStrawberrySmoothie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStrawberrySmoothieActionPerformed(evt);
            }
        });
        itemsMenuPanel.add(btnStrawberrySmoothie, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        btnWater.setBackground(new java.awt.Color(255, 204, 102));
        btnWater.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnWater.setText("Water");
        btnWater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWaterActionPerformed(evt);
            }
        });
        itemsMenuPanel.add(btnWater, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 160, -1));

        lblStrawberrySmoothieDesc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblStrawberrySmoothieDesc.setText("Recover 300 HP when consumed.");
        itemsMenuPanel.add(lblStrawberrySmoothieDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 410, 40));

        lblWaterDesc.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblWaterDesc.setText("Recover 100 SP when consumed.");
        itemsMenuPanel.add(lblWaterDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 410, 40));

        lblAmountOfStrawberrySmoothie.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblAmountOfStrawberrySmoothie.setText("x3");
        itemsMenuPanel.add(lblAmountOfStrawberrySmoothie, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 80, -1, -1));

        lblAmountOfWater.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblAmountOfWater.setText("x3");
        itemsMenuPanel.add(lblAmountOfWater, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 140, -1, -1));

        btnItemsMenuQuit.setBackground(new java.awt.Color(255, 204, 102));
        btnItemsMenuQuit.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnItemsMenuQuit.setText("X");
        btnItemsMenuQuit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnItemsMenuQuitActionPerformed(evt);
            }
        });
        itemsMenuPanel.add(btnItemsMenuQuit, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 10, 90, 30));

        battleBackPanel.add(itemsMenuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 940, 226));

        lbRagnaroklEnemy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monsterLevelTwo.png"))); // NOI18N
        battleBackPanel.add(lbRagnaroklEnemy, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 400, 400));

        lblPlayerHPNum.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        lblPlayerHPNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayerHPNum.setText("430");
        battleBackPanel.add(lblPlayerHPNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 60, 30));

        lblPlayerSPNum.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        lblPlayerSPNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayerSPNum.setText("220");
        battleBackPanel.add(lblPlayerSPNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 60, 30));

        lblEnemyHPNum.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        lblEnemyHPNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEnemyHPNum.setText("1000");
        battleBackPanel.add(lblEnemyHPNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 150, 60, 30));

        lblEnemySPNum.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        lblEnemySPNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEnemySPNum.setText("300");
        battleBackPanel.add(lblEnemySPNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, 60, 30));

        progressBarPlayerHP.setBackground(new java.awt.Color(255, 255, 255));
        progressBarPlayerHP.setForeground(new java.awt.Color(255, 51, 0));
        progressBarPlayerHP.setMaximum(430);
        battleBackPanel.add(progressBarPlayerHP, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 200, 30));

        progressBarPlayerSP.setMaximum(220);
        battleBackPanel.add(progressBarPlayerSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 200, 30));

        progressBarEnemyHP.setMaximum(1000);
        battleBackPanel.add(progressBarEnemyHP, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 180, 30));

        progressBarEnemySP.setMaximum(300);
        battleBackPanel.add(progressBarEnemySP, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 190, 180, 30));

        lblPlayerHP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPlayerHP.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerHP.setText("HP");
        battleBackPanel.add(lblPlayerHP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 30, 30));

        lblPlayerSP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblPlayerSP.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerSP.setText("SP");
        battleBackPanel.add(lblPlayerSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 30, 30));

        lblEnemyName.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N
        lblEnemyName.setForeground(new java.awt.Color(255, 255, 255));
        lblEnemyName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEnemyName.setText("Ragnarok");
        battleBackPanel.add(lblEnemyName, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 110, 120, 30));

        lblEnemyHP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblEnemyHP.setForeground(new java.awt.Color(255, 255, 255));
        lblEnemyHP.setText("HP");
        battleBackPanel.add(lblEnemyHP, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 150, 30, 30));

        lblEnemySP.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblEnemySP.setForeground(new java.awt.Color(255, 255, 255));
        lblEnemySP.setText("SP");
        battleBackPanel.add(lblEnemySP, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 30, 30));

        lblEnemyDmgIndicator.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblEnemyDmgIndicator.setForeground(new java.awt.Color(255, 255, 255));
        battleBackPanel.add(lblEnemyDmgIndicator, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 280, 190, 40));

        lblEnemyCriticalHit.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblEnemyCriticalHit.setForeground(new java.awt.Color(255, 255, 255));
        lblEnemyCriticalHit.setText("Critical Hit");
        battleBackPanel.add(lblEnemyCriticalHit, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 130, 40));

        lblEnemyDowned.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblEnemyDowned.setForeground(new java.awt.Color(255, 255, 255));
        lblEnemyDowned.setText("Downed");
        battleBackPanel.add(lblEnemyDowned, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 110, 40));

        lblEnemyIceImmune.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblEnemyIceImmune.setForeground(new java.awt.Color(255, 255, 255));
        lblEnemyIceImmune.setText("↑ Ice Immune");
        battleBackPanel.add(lblEnemyIceImmune, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 380, 210, 40));

        lblEnemyAtkUp.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblEnemyAtkUp.setForeground(new java.awt.Color(255, 255, 255));
        lblEnemyAtkUp.setText("↑ ATK");
        battleBackPanel.add(lblEnemyAtkUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 430, 150, 40));

        lblEnemyDefUp.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblEnemyDefUp.setForeground(new java.awt.Color(255, 255, 255));
        lblEnemyDefUp.setText("↑ DEF");
        battleBackPanel.add(lblEnemyDefUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 480, 150, 40));

        lblEnemyHitEvadeUp.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblEnemyHitEvadeUp.setForeground(new java.awt.Color(255, 255, 255));
        lblEnemyHitEvadeUp.setText("↑ HIT/EVADE");
        battleBackPanel.add(lblEnemyHitEvadeUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 530, 170, 40));

        lblEnemyWeak.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblEnemyWeak.setForeground(new java.awt.Color(255, 255, 255));
        lblEnemyWeak.setText("Weak");
        battleBackPanel.add(lblEnemyWeak, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 420, 100, 40));

        lblPlayerDmgIndicator.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPlayerDmgIndicator.setForeground(new java.awt.Color(255, 255, 255));
        battleBackPanel.add(lblPlayerDmgIndicator, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 80, 30));

        lblPlayerCriticalHit.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPlayerCriticalHit.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerCriticalHit.setText("Critcial Hit");
        battleBackPanel.add(lblPlayerCriticalHit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 140, 30));

        lblPlayerWeak.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPlayerWeak.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerWeak.setText("Weak");
        battleBackPanel.add(lblPlayerWeak, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 140, 30));

        lblPlayerMissed.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPlayerMissed.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerMissed.setText("Missed");
        battleBackPanel.add(lblPlayerMissed, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        lblPlayerDowned.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPlayerDowned.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerDowned.setText("Downed");
        battleBackPanel.add(lblPlayerDowned, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 100, 30));

        lblPlayerHitEvadeUp.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPlayerHitEvadeUp.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerHitEvadeUp.setText("↑ HIT/EVADE");
        battleBackPanel.add(lblPlayerHitEvadeUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 170, 40));

        lblPlayerAtkUp.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPlayerAtkUp.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerAtkUp.setText("↑ ATK");
        battleBackPanel.add(lblPlayerAtkUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 150, 40));

        lblPlayerDefUp.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPlayerDefUp.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerDefUp.setText("↑ DEF");
        battleBackPanel.add(lblPlayerDefUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 150, 40));

        backWarningHPPanel.setOpaque(false);
        backWarningHPPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblWarningHP.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblWarningHP.setForeground(new java.awt.Color(255, 255, 255));
        lblWarningHP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dangerSign(Resize).png"))); // NOI18N
        backWarningHPPanel.add(lblWarningHP, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 60));

        battleBackPanel.add(backWarningHPPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 70, 60));

        backWarningSPPanel.setOpaque(false);
        backWarningSPPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblWarningSP.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblWarningSP.setForeground(new java.awt.Color(255, 255, 255));
        lblWarningSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dangerSign(Resize).png"))); // NOI18N
        backWarningSPPanel.add(lblWarningSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 60, 50));

        battleBackPanel.add(backWarningSPPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, 100, -1));

        lblPlayerHPLow.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPlayerHPLow.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerHPLow.setText("Player HP is Low!!!!");
        battleBackPanel.add(lblPlayerHPLow, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, -1, -1));

        lblPlayerSPLow.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblPlayerSPLow.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayerSPLow.setText("Player SP is Low!!!!");
        battleBackPanel.add(lblPlayerSPLow, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 80, -1, -1));

        lblDungeonBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DungeonBackDrop.jpg"))); // NOI18N
        battleBackPanel.add(lblDungeonBackground, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 720));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(battleBackPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(battleBackPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSkillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkillActionPerformed
        //Prompting skill menu
        skillsMenuPanel.setVisible(true);
        disableMoves();
    }//GEN-LAST:event_btnSkillActionPerformed

    private void btnSkillsMenuQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkillsMenuQuitActionPerformed
        //Quitting skill menu
        skillsMenuPanel.setVisible(false);
        enableMoves();
    }//GEN-LAST:event_btnSkillsMenuQuitActionPerformed

    private void btnItemsMenuQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItemsMenuQuitActionPerformed
        //Quitting items menu
        itemsMenuPanel.setVisible(false);    
        enableMoves();
    }//GEN-LAST:event_btnItemsMenuQuitActionPerformed

    private void btnEscapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscapeActionPerformed
        //Ending the game
        new GameOverDefeatLevel2().setVisible(true);
        this.setVisible(false);
        clip.stop();
    }//GEN-LAST:event_btnEscapeActionPerformed

    private void btnItemsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItemsActionPerformed
        //Prompting items menu
        itemsMenuPanel.setVisible(true);
        disableMoves();
        
    }//GEN-LAST:event_btnItemsActionPerformed

    private void btnAttackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAttackActionPerformed
        //Keeping track of player buffs
        if(playerTurnCounterFortify >= 1){
            playerTurnMonitorFortify();
        }
        if(playerTurnCounterFocus >= 1){
            playerTurnMonitorFocus();
        }
        //Outputs what happens when the player presses the button, and ends the player's turn
        playerTurn = false;
        enemyTurn = true;
        playerUsed(0);
        
        //Delays the attack of the player
        delay1 = new Timer(1000, new ActionListener(){
           
            @Override
            public void actionPerformed(ActionEvent e){
                playerAttack();
                delay1.stop();  
            }
        });
        delay1.start();
        
        //Delays the hiding of indicators
        delay2 = new Timer(2000, new ActionListener(){
           
            @Override
            public void actionPerformed(ActionEvent e){
                //Hides downed label if player does not get a critical 
                if(playerOneMoreTurn == false){
                    lblEnemyDowned.setVisible(false);    
                }
                //Shows player that they have another turn
                if(enemyTurn == false){
                    oneMoreTurnPanel.setVisible(true);
                    lblPlayerMoreTurnPanel.setText("+1 Turn");
                }
                endPlayerTurn();
                delay2.stop();
                
            }
        });
        delay2.start();
        
        //Delays the enemy's turn until the player's turn is complete
        delay3 = new Timer(4000, new ActionListener(){
           
            @Override
            public void actionPerformed(ActionEvent e){
                CPUEnemy();
                delay3.stop();  
            }
        });
        delay3.start();
        
        //Delays the set up for the player's next turn
        delay4 = new Timer(6000, new ActionListener(){   
            @Override
            public void actionPerformed(ActionEvent e){
                endEnemyTurn();
                
                //When enemy has an additional turn
                if(playerTurn == false){
                    disableMoves();
                    
                //Delays disabling the player if the enemy's turn loops
                delay5 = new Timer(1000, new ActionListener(){
           
                    @Override
                    public void actionPerformed(ActionEvent e){
                        oneMoreTurnPanel.setVisible(false); 
                        delay5.stop();  
                    }   
                });
                delay5.start();
                
                //Delays the enemy's attack
                delay6 = new Timer(3000, new ActionListener(){
           
                    @Override
                    public void actionPerformed(ActionEvent e){
                        CPUEnemy();
                        delay6.stop();  
                    }
                });
                delay6.start();
                
                //Delays ending the enemy's turn
                delay7 = new Timer(5000, new ActionListener(){
           
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if (playerHP() > 0){
                                endEnemyTurn(); 
                                }
                        delay7.stop();  
                    }
                });
                delay7.start();
                }
                delay4.stop();  
            }
        });
        delay4.start();
            
    }//GEN-LAST:event_btnAttackActionPerformed

    private void btnFireAtkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFireAtkActionPerformed
       //Keeping track of player buffs
        if(playerTurnCounterFortify >= 1){
            playerTurnMonitorFortify();
        }
        if(playerTurnCounterFocus >= 1){
            playerTurnMonitorFocus();
        }
        //When player does not have enough SP
        if(subtract(playerSP(), 12 ) < 0){
            skillsMenuPanel.setVisible(false);
            skillsMsgPanel.setVisible(true);
            lblPlayerSkillMsg.setText("You don't have enough SP.");
            btnFireAtk.setEnabled(false);    
        }else{
            playerTurn = false;
            enemyTurn = true;
            critHit = false;
            enemyGuard = false;
            playerUsed(1);
              
            //Delays how much damage the player did
            delay1 = new Timer(1000, new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e){
                    playerSpellAttack();
                    delay1.stop();
                }
            });
            delay1.start();
        
            //Delays hiding the indicators that was shown for the player
            delay2 = new Timer(2000, new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e){
                    if(playerOneMoreTurn == true){
                        lblEnemyDowned.setVisible(false);
                        playerOneMoreTurn = false;
                    }
                    endPlayerTurn();
                    delay2.stop();
             
                }
            });
            delay2.start();
        
            //Delays the enemy's turn
            delay3 = new Timer(4000, new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e){
                    CPUEnemy();
                    delay3.stop();
                }
            });
            delay3.start();
        
            //Delays the set up for the player's next turn
            delay4 = new Timer(6000, new ActionListener(){   
                @Override
                public void actionPerformed(ActionEvent e){
                    endEnemyTurn();
                    
                    //When enemy gets additional turn
                    if(playerTurn == false){
                        disableMoves();
                        
                    //Delays disabling the player if the enemy's turn loops
                    delay5 = new Timer(1000, new ActionListener(){
           
                        @Override
                        public void actionPerformed(ActionEvent e){
                            oneMoreTurnPanel.setVisible(false); 
                            delay5.stop();  
                        }   
                    });
                    delay5.start();
                
                    //Delays the enemy's attack
                    delay6 = new Timer(3000, new ActionListener(){
           
                        @Override
                        public void actionPerformed(ActionEvent e){
                            CPUEnemy();
                            delay6.stop();  
                        }
                    });
                    delay6.start();
                
                    //Delays ending the enemy's turn
                    delay7 = new Timer(5000, new ActionListener(){
           
                        @Override
                        public void actionPerformed(ActionEvent e){
                            if (playerHP() > 0){
                                endEnemyTurn(); 
                                }
                            delay7.stop();  
                        }
                    });
                    delay7.start();
                    }
                    delay4.stop();  
                }
            });
            delay4.start(); 
        }   
    }//GEN-LAST:event_btnFireAtkActionPerformed

    private void btnIceAtkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIceAtkActionPerformed
        //When player hits the button
        if(playerTurnCounterFortify >= 1){
            playerTurnMonitorFortify();
        }
        if(playerTurnCounterFocus >= 1){
            playerTurnMonitorFocus();
        }
        
        //When player has not enough SP
        if(subtract(playerSP(), 12 ) < 0){
            skillsMenuPanel.setVisible(false);
            skillsMsgPanel.setVisible(true);
            lblPlayerSkillMsg.setText("You don't have enough SP.");
            btnIceAtk.setEnabled(false);    
        }else{
            playerTurn = false;
            enemyTurn = true;
            critHit = false;
            playerUsed(2);
        
            //Delays how much damage the player did
            delay1 = new Timer(1000, new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e){
                    playerSpellAttack();
                    delay1.stop();
                }
            });
            delay1.start();
        
            //Delays the hiding of indicators
            delay2 = new Timer(2000, new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e){
                    //Hides label when player does not have an additional turn
                    if(playerOneMoreTurn == false){
                        lblEnemyDowned.setVisible(false);
                    }
                    if(enemyTurn == false){
                        oneMoreTurnPanel.setVisible(true);
                        lblPlayerMoreTurnPanel.setText("+1 Turn");
                    }
                    endPlayerTurn();
                    delay2.stop();
                }
            });
            delay2.start();
        
            //Delays enemy's turn
            delay3 = new Timer(4000, new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e){
                    CPUEnemy();
                    delay3.stop();
                }
            });
            delay3.start();
        
            //Delays the set up for the player's next turn
            delay4 = new Timer(6000, new ActionListener(){   
                @Override
                public void actionPerformed(ActionEvent e){
                    endEnemyTurn();
                    
                    //When enemy gets additional turn
                    if(playerTurn == false){
                        disableMoves();
                        
                    //Delays hiding the one more turn message when the enemy's turn loops
                    delay5 = new Timer(1000, new ActionListener(){
           
                        @Override
                        public void actionPerformed(ActionEvent e){
                            oneMoreTurnPanel.setVisible(false); 
                            delay5.stop();  
                        }   
                    });
                    delay5.start();
                
                    //Delays the enemy's attack
                    delay6 = new Timer(3000, new ActionListener(){
           
                        @Override
                        public void actionPerformed(ActionEvent e){
                            CPUEnemy();
                            delay6.stop();  
                        }
                    });
                    delay6.start();
                
                    //Delays ending the enemy's turn
                    delay7 = new Timer(5000, new ActionListener(){
           
                        @Override
                        public void actionPerformed(ActionEvent e){
                            if (playerHP() > 0){
                                endEnemyTurn(); 
                                } 
                            delay7.stop();  
                        }
                    });
                    delay7.start();
                    }
                    delay4.stop();  
                }
            });
            delay4.start();
        }    
    }//GEN-LAST:event_btnIceAtkActionPerformed

    private void btnElectricAtkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnElectricAtkActionPerformed
        //When player hits the button
        if(playerTurnCounterFortify >= 1){
            playerTurnMonitorFortify();
        }
        if(playerTurnCounterFocus >= 1){
            playerTurnMonitorFocus();
        }
        
        //When player has not enough SP
        if(subtract(playerSP(), 12 ) < 0){
            skillsMenuPanel.setVisible(false);
            skillsMsgPanel.setVisible(true);
            lblPlayerSkillMsg.setText("You don't have enough SP.");
            btnElectricAtk.setEnabled(false);    
        }else{
            skillsMenuPanel.setVisible(false);
            playerTurn = false;
            enemyTurn = true;
            critHit = false;
            playerUsed(3);
        
            //Delays how much damage the player did
            delay1 = new Timer(1000, new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e){
                    playerSpellAttack();
                    delay1.stop();
                }
            });
            delay1.start();
        
            //Delays the hiding of indicators
            delay2 = new Timer(2000, new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e){
                    //Hides label and sets player to not have an additional turn when player uses volt with their additional turn
                    if(playerOneMoreTurn == true){
                        lblEnemyDowned.setVisible(false);
                        playerOneMoreTurn = false;
                    }
                    endPlayerTurn();
                    delay2.stop();
                }
            });
            delay2.start();
        
            //Delays enemy's turn
            delay3 = new Timer(4000, new ActionListener(){
            
                @Override
                public void actionPerformed(ActionEvent e){
                    CPUEnemy();
                    delay3.stop();
                }
            });
            delay3.start();
        
            //Delays the set up for the player's next turn
            delay4 = new Timer(6000, new ActionListener(){   
                @Override
                public void actionPerformed(ActionEvent e){
                    endEnemyTurn();
                    
                    //When the enemy gains an additional turn
                    if(playerTurn == false){
                        disableMoves();
                        
                    //Delays hiding the one more turn message when the enemy's turn loops
                    delay5 = new Timer(1000, new ActionListener(){
           
                        @Override
                        public void actionPerformed(ActionEvent e){
                            oneMoreTurnPanel.setVisible(false); 
                            delay5.stop();  
                        }   
                    });
                    delay5.start();
                
                    //Delays the enemy's attack
                    delay6 = new Timer(3000, new ActionListener(){
           
                        @Override
                        public void actionPerformed(ActionEvent e){
                            CPUEnemy();
                            delay6.stop();  
                        }
                    });
                    delay6.start();
                
                    //Delays ending the enemy's turn
                    delay7 = new Timer(5000, new ActionListener(){
           
                        @Override
                        public void actionPerformed(ActionEvent e){
                            if (playerHP() > 0){
                                endEnemyTurn(); 
                            } 
                            delay7.stop();  
                        }
                    });
                    delay7.start();
                    }
                    delay4.stop();  
                }
            });
            delay4.start();
        
        }    
        
    }//GEN-LAST:event_btnElectricAtkActionPerformed

    private void btnGuardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardActionPerformed
        //When the player presses the button
        if(playerTurnCounterFortify >= 1){
            playerTurnMonitorFortify();
        }
        if(playerTurnCounterFocus >= 1){
            playerTurnMonitorFocus();
        }
        playerGuard = true;
        playerTurn = false;
        enemyTurn = true;
        critHit = false;
        playerPowerCharge = false;
        enemyGuard = false;
        playerUsed(7);
        
        delay1 = new Timer(1000, new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                //Hides label when player uses guard in their additional turn
                if(playerOneMoreTurn == true){
                    lblEnemyDowned.setVisible(false);
                    playerOneMoreTurn = false;
                }
                endPlayerTurn();
                delay1.stop();
            }
        });
        delay1.start();
        
        //Delays enemy's turn
        delay2 = new Timer(2000, new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                CPUEnemy();
                delay2.stop();
            }
        });
        delay2.start();
        
        //Delays the set up for the player's next turn
        delay3 = new Timer(4000, new ActionListener(){ 
            
            @Override
            public void actionPerformed(ActionEvent e){
                endEnemyTurn();
                
                //When enemy has an additional turn
                if(playerTurn == false){
                    disableMoves();
                    
                //Delays hiding the one more turn message when the enemy's turn loops
                delay4 = new Timer(1000, new ActionListener(){
           
                    @Override
                    public void actionPerformed(ActionEvent e){
                        oneMoreTurnPanel.setVisible(false); 
                        delay4.stop();  
                    }   
                });
                delay4.start();
                
                //Delays the enemy's attack
                delay5 = new Timer(3000, new ActionListener(){
           
                    @Override
                    public void actionPerformed(ActionEvent e){
                        CPUEnemy();
                        delay5.stop();  
                    }
                });
                delay5.start();
                
                //Delays ending the enemy's turn
                delay6 = new Timer(5000, new ActionListener(){
           
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if (playerHP() > 0){
                            endEnemyTurn(); 
                        }
                        delay6.stop();  
                    }
                });
                delay6.start();
                }
                delay3.stop();  
            }
        });
        delay3.start();
        
    }//GEN-LAST:event_btnGuardActionPerformed

    private void btnStrawberrySmoothieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStrawberrySmoothieActionPerformed
        //When player's health is full
        if(playerHP() == 430){
            itemsMenuPanel.setVisible(false);
            itemsMsgPanel.setVisible(true);
            lblPlayerItemMsg.setText("Your health is already full.");     
        }else{
            //Keeps track of player buffs
            if(playerTurnCounterFortify >= 1){
                playerTurnMonitorFortify();
            }
            if(playerTurnCounterFocus >= 1){
                playerTurnMonitorFocus();
            }
            
            playerTurn = false;
            enemyTurn = true;
            critHit = false;
            enemyGuard = false;
            playerPowerCharge = false;
            playerUsed(8);
            
            //When player has no more of this item
            if(amountOfStrawberry == 1){
                btnStrawberrySmoothie.setEnabled(false);
            }

            //Delays how much HP the player regained
            delay1 = new Timer(1000, new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e){
                    int HPRegain = 300;
                    amountOfStrawberry--;
                    lblAmountOfStrawberrySmoothie.setText("x"+amountOfStrawberry);
                    progressBarPlayerHP.setValue(playerHP() + HPRegain);
                    lblPlayerHPNum.setText(String.valueOf(playerHP()));
                    delay1.stop();
                }
            });
            delay1.start();

            //Delays the hiding of indicators
            delay2 = new Timer(2000, new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e){
                    //Hides label and sets player to not have an additional turn when player uses this item with their additional turn
                    if(playerOneMoreTurn == true){
                        lblEnemyDowned.setVisible(false);
                        playerOneMoreTurn = false;
                    }
                    endPlayerTurn();
                    delay2.stop();
                }
            });
            delay2.start();

            //Delays enemy's turn
            delay3 = new Timer(4000, new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e){
                    CPUEnemy();
                    delay3.stop();
                }
            });
            delay3.start();

            //Delays the set up for the player's next turn
            delay4 = new Timer(6000, new ActionListener(){   
                @Override
                public void actionPerformed(ActionEvent e){
                    endEnemyTurn();
                    
                    //When enemy has an additional turn
                    if (playerTurn == false) {
                        disableMoves();

                        //Delays hiding the one more turn message when the enemy's turn loops
                        delay5 = new Timer(1000, new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                oneMoreTurnPanel.setVisible(false);
                                delay5.stop();
                            }
                        });
                        delay5.start();

                        //Delays the enemy's attack
                        delay6 = new Timer(3000, new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                CPUEnemy();
                                delay6.stop();
                            }
                        });
                        delay6.start();

                        //Delays ending the enemy's turn
                        delay7 = new Timer(5000, new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (playerHP() > 0) {
                                    endEnemyTurn();
                                }
                                delay7.stop();
                            }
                        });
                        delay7.start();
                    }
                    delay4.stop();
                }
            });
            delay4.start();
        }

    }//GEN-LAST:event_btnStrawberrySmoothieActionPerformed

    private void btnItemsMsgQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnItemsMsgQuitActionPerformed
        //When the button is pressed
        lblPlayerItemMsg.setText("");
        itemsMsgPanel.setVisible(false);
        itemsMenuPanel.setVisible(true);        
    }//GEN-LAST:event_btnItemsMsgQuitActionPerformed

    private void btnWaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWaterActionPerformed
        //When player hits button
        if(playerSP() == 220){
            itemsMenuPanel.setVisible(false);
            itemsMsgPanel.setVisible(true);
            lblPlayerItemMsg.setText("Your skill points is already full.");     
        }else{
            //Keeps track of player buffs
            if(playerTurnCounterFortify >= 1){
                playerTurnMonitorFortify();
            }
            if(playerTurnCounterFocus >= 1){
                playerTurnMonitorFocus();
            }
            playerTurn = false;
            enemyTurn = true;
            critHit = false;
            enemyGuard = false;
            playerPowerCharge = false;
            playerUsed(9);
            
            //When player has no more of this item
            if(amountOfWater == 1){
                btnWater.setEnabled(false);
            }

            //Delays how much SP the player regained
            delay1 = new Timer(1000, new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e){
                    int SPRegain = 100;
                    amountOfWater--;
                    lblAmountOfWater.setText("x"+amountOfWater);
                    progressBarPlayerSP.setValue(playerSP() + SPRegain);
                    lblPlayerSPNum.setText(String.valueOf(playerSP()));
                    delay1.stop();
                }
            });
            delay1.start();

            //Delays the hiding of indicators
            delay2 = new Timer(2000, new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e){
                    //Hides label and sets player to not have an additional turn when player uses this item with their additional turn
                    if(playerOneMoreTurn == true){
                        lblEnemyDowned.setVisible(false);
                        playerOneMoreTurn = false;
                    }
                    endPlayerTurn();
                    delay2.stop();
                }
            });
            delay2.start();

            //Delays enemy's turn
            delay3 = new Timer(4000, new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e){
                    CPUEnemy();
                    delay3.stop();
                }
            });
            delay3.start();

            //Delays the set up for the player's next turn
            delay4 = new Timer(6000, new ActionListener(){   
                @Override
                public void actionPerformed(ActionEvent e){
                    endEnemyTurn();
                    
                    //When enemy gets an additional turn
                    if(playerTurn == false){
                        disableMoves();
                        
                        //Delays hiding the one more turn message when the enemy's turn loops
                        delay5 = new Timer(1000, new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                oneMoreTurnPanel.setVisible(false);
                                delay5.stop();
                            }
                        });
                        delay5.start();

                        //Delays the enemy's attack
                        delay6 = new Timer(3000, new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                CPUEnemy();
                                delay6.stop();
                            }
                        });
                        delay6.start();

                        //Delays ending the enemy's turn
                        delay7 = new Timer(5000, new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (playerHP() > 0) {
                                    endEnemyTurn();
                                }
                                delay7.stop();
                            }
                        });
                        delay7.start();
                    }
                    delay4.stop();
                }
            });
            delay4.start();
        }

    }//GEN-LAST:event_btnWaterActionPerformed

    private void btnAtkUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtkUpActionPerformed
        //When player hits the button
        if(playerPowerCharge == true){
            skillsMenuPanel.setVisible(false);
            skillsMsgPanel.setVisible(true);
            lblPlayerSkillMsg.setText("Power Charge is already active."); 
        }else{
            //When player has not enough SP
            if(subtract(playerSP(), 22 ) < 0){
                skillsMenuPanel.setVisible(false);
                skillsMsgPanel.setVisible(true);
                lblPlayerSkillMsg.setText("You don't have enough SP.");
                btnAtkUp.setEnabled(false);    
            }else{
                //Keeping track of player buffs
                if(playerTurnCounterFortify >= 1){
                    playerTurnMonitorFortify();
                }
                if(playerTurnCounterFocus >= 1){
                    playerTurnMonitorFocus();
                }
                playerTurn = false;
                enemyTurn = true;
                critHit = false;
                playerPowerCharge = true;
                enemyGuard = false;
                playerUsed(4);
            
                //Delays showing the attack buff
                delay1 = new Timer(1000, new ActionListener(){
            
                    @Override
                    public void actionPerformed(ActionEvent e){
                        lblPlayerAtkUp.setVisible(true);
                        delay1.stop();
                    }
                });
                delay1.start();
            
                //Delays hiding the enemy indicators for the player
                delay2 = new Timer(2000, new ActionListener(){
            
                    @Override
                    public void actionPerformed(ActionEvent e){
                        //Hides label when player uses power charge in their additional turn
                        if(playerOneMoreTurn == true){
                            lblEnemyDowned.setVisible(false);
                            playerOneMoreTurn = false;
                        }
                        endPlayerTurn();
                        delay2.stop();
                    }
                });
                delay2.start();
            
                //Delays enemy's turn
                delay3 = new Timer(4000, new ActionListener(){
            
                    @Override
                    public void actionPerformed(ActionEvent e){
                        CPUEnemy();
                        delay3.stop();
                    }
                });
                delay3.start();
        
                //Delays the set up for the player's next turn
                delay4 = new Timer(6000, new ActionListener(){   
                    @Override
                    public void actionPerformed(ActionEvent e){
                        endEnemyTurn();
                        
                        //When enemy has an additional turn
                        if(playerTurn == false){
                            disableMoves();
                            
                            //Delays hiding the one more turn message when the enemy's turn loops
                            delay5 = new Timer(1000, new ActionListener(){
           
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    oneMoreTurnPanel.setVisible(false); 
                                    delay5.stop();  
                                }   
                            });
                            delay5.start();
                
                            //Delays the enemy's attack
                            delay6 = new Timer(3000, new ActionListener(){
           
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    CPUEnemy();
                                    delay6.stop();  
                                }
                            });
                            delay6.start();
                
                            //Delays ending the enemy's turn
                            delay7 = new Timer(5000, new ActionListener(){
           
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    if (playerHP() > 0){
                                        endEnemyTurn(); 
                                    }
                                    delay7.stop();  
                                }
                            });
                            delay7.start();
                        }
                        delay4.stop();  
                    }
                });
                delay4.start();
            }
        }        
    }//GEN-LAST:event_btnAtkUpActionPerformed

    private void btnSkillsMsgQuitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSkillsMsgQuitActionPerformed
        //When the button is pressed
        lblPlayerSkillMsg.setText("");
        skillsMsgPanel.setVisible(false);
        skillsMenuPanel.setVisible(true);
    }//GEN-LAST:event_btnSkillsMsgQuitActionPerformed

    private void btnDefUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDefUpActionPerformed
        //When player hits the button
        if(playerFortify == true && playerTurnCounterFortify == 1){
            skillsMenuPanel.setVisible(false);
            skillsMsgPanel.setVisible(true);
            lblPlayerSkillMsg.setText("Fortify is already active."); 
        }else if(playerFortify == false || playerTurnCounterFortify == 2){
            //When player has not enough SP
            if(subtract(playerSP(), 22 ) < 0){
                skillsMenuPanel.setVisible(false);
                skillsMsgPanel.setVisible(true);
                lblPlayerSkillMsg.setText("You don't have enough SP.");
                btnDefUp.setEnabled(false);    
            }else{
                //Keeping track of player buffs
                playerTurnCounterFortify = 0;
                if(playerTurnCounterFocus >= 1){
                    playerTurnMonitorFocus();
                }
                playerTurnMonitorFortify();
                playerTurn = false;
                enemyTurn = true;
                critHit = false;
                playerPowerCharge = false;
                playerFortify = true;
                playerGuard = false;
                enemyGuard = false;
                playerUsed(5);
      
                //Delays showing the attack buff
                delay1 = new Timer(1000, new ActionListener(){
            
                    @Override
                    public void actionPerformed(ActionEvent e){
                        lblPlayerDefUp.setVisible(true);
                        delay1.stop();
                    }
                });
                delay1.start();
            
                //Delays hiding the enemy indicators for the player
                delay2 = new Timer(2000, new ActionListener(){
            
                    @Override
                    public void actionPerformed(ActionEvent e){
                        //Hides label when player uses fortify in their additional turn
                        if(playerOneMoreTurn == true){
                            lblEnemyDowned.setVisible(false);
                            playerOneMoreTurn = false;
                        }
                        endPlayerTurn();
                        delay2.stop();
                    }
                });
                delay2.start();
            
                //Delays enemy's turn
                delay3 = new Timer(4000, new ActionListener(){
            
                    @Override
                    public void actionPerformed(ActionEvent e){
                        CPUEnemy();
                        delay3.stop();
                    }
                });
                delay3.start();
        
                //Delays the set up for the player's next turn
                delay4 = new Timer(6000, new ActionListener(){   
                    @Override
                    public void actionPerformed(ActionEvent e){
                        endEnemyTurn();
                        
                        //When enemy gets an another turn 
                        if(playerTurn == false){
                            playerTurnMonitorFortify();
                            disableMoves();
                            
                            //Delays hiding the one more turn message when the enemy's turn loops
                            delay5 = new Timer(1000, new ActionListener(){
           
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    oneMoreTurnPanel.setVisible(false); 
                                    delay5.stop();  
                                }   
                            });
                            delay5.start();
                
                            //Delays the enemy's attack
                            delay6 = new Timer(3000, new ActionListener(){
           
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    CPUEnemy();
                                    delay6.stop();  
                                }
                            });
                            delay6.start();
                
                            //Delays ending the enemy's turn
                            delay7 = new Timer(5000, new ActionListener(){
           
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    if (playerHP() > 0){
                                    endEnemyTurn(); 
                                    }
                                    delay7.stop();  
                                }
                            });
                            delay7.start();
                        }
                        delay4.stop();  
                    }
                });
                delay4.start();
            
            }    
        }

    }//GEN-LAST:event_btnDefUpActionPerformed

    private void btnHitUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHitUpActionPerformed
        //When the button is pressed
        if(playerFocus == true && playerTurnCounterFocus == 1){
            skillsMenuPanel.setVisible(false);
            skillsMsgPanel.setVisible(true);
            lblPlayerSkillMsg.setText("Focus is already active."); 
        }else if(playerFocus == false || playerTurnCounterFocus == 2){
            //When player has not enough SP
            if(subtract(playerSP(), 22 ) < 0){
                skillsMenuPanel.setVisible(false);
                skillsMsgPanel.setVisible(true);
                lblPlayerSkillMsg.setText("You don't have enough SP.");
                btnHitUp.setEnabled(false);    
            }else{
                //Keeping track of player buffs
                playerTurnCounterFocus = 0;
                if(playerTurnCounterFortify >= 1){
                    playerTurnMonitorFortify();
                }
                
                playerTurnMonitorFocus();
                skillsMenuPanel.setVisible(false);
                playerTurn = false;
                enemyTurn = true;
                critHit = false;
                playerPowerCharge = false;
                playerGuard = false;
                playerFocus = true;
                enemyGuard = false;
                playerUsed(6);
      
                //Delays showing the attack buff
                delay1 = new Timer(1000, new ActionListener(){
            
                    @Override
                    public void actionPerformed(ActionEvent e){
                        lblPlayerHitEvadeUp.setVisible(true);
                        delay1.stop();
                    }
                });
                delay1.start();
            
                //Delays hiding the enemy indicators for the player
                delay2 = new Timer(2000, new ActionListener(){
            
                    @Override
                    public void actionPerformed(ActionEvent e){
                        //Hides label when player uses fortify in their additional turn
                        if(playerOneMoreTurn == true){
                            lblEnemyDowned.setVisible(false);
                            playerOneMoreTurn = false;
                        }
                        endPlayerTurn();
                        delay2.stop();
                    }
                });
                delay2.start();
            
                //Delays enemy's turn
                delay3 = new Timer(4000, new ActionListener(){
            
                    @Override
                    public void actionPerformed(ActionEvent e){
                        CPUEnemy();
                        delay3.stop();
                    }
                });
                delay3.start();
        
                //Delays the set up for the player's next turn
                delay4 = new Timer(6000, new ActionListener(){   
                    @Override
                    public void actionPerformed(ActionEvent e){
                        endEnemyTurn();
                        
                        //When enemy gets another turn
                        if(playerTurn == false){
                            playerTurnMonitorFocus();
                            disableMoves();
                            
                            //Delays hiding the one more turn message when the enemy's turn loops
                            delay5 = new Timer(1000, new ActionListener(){
           
                                @Override
                                public void actionPerformed(ActionEvent e){
                                    oneMoreTurnPanel.setVisible(false); 
                                    delay5.stop();  
                                }   
                            });
                            delay5.start();
                
                        //Delays the enemy's attack
                        delay6 = new Timer(3000, new ActionListener(){
           
                            @Override
                            public void actionPerformed(ActionEvent e){
                                CPUEnemy();
                                delay6.stop();  
                            }
                        });
                        delay6.start();
                
                        //Delays ending the enemy's turn
                        delay7 = new Timer(5000, new ActionListener(){
           
                            @Override
                            public void actionPerformed(ActionEvent e){
                                if (playerHP() > 0){
                                endEnemyTurn(); 
                                } 
                                delay7.stop();  
                            }
                        });
                        delay7.start();
                        }
                        delay4.stop();  
                    }
            });
            delay4.start();
            
            }    
        }
    }//GEN-LAST:event_btnHitUpActionPerformed

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
            java.util.logging.Logger.getLogger(BattleLevel2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BattleLevel2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BattleLevel2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BattleLevel2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BattleLevel2().setVisible(true);
            }
        });
    }
    
    //Declaring variables that every method has access to
    Timer delay1, delay2, delay3, delay4, delay5, delay6, delay7, delay8, delay9, delay10, delay11, delay12, delay13, delay14;
    boolean playerTurn, enemyTurn, critHit, playerGuard, enemyCritHit, enemyGuard, iceImmune;
    Clip clip;
    double critRate = 0.45;
    double playerHitRate = 0.5;
    double enemyHitRate = 1;
    double enemyElectricAtkRate = 0.5;
    double enemyGuardRate = 0.2;
    double enemyPowerChargeRate = 0.05;
    double enemyFortifyRate = 0.05;
    double enemyFocusRate = 0.05;
    double enemyIceWallRate = 0.05;
    boolean playerOneMoreTurn = false;
    boolean enemyOneMoreTurn = false;
    int amountOfStrawberry = 3;
    int amountOfWater = 3;
    boolean playerPowerCharge = false;
    boolean playerFortify = false;
    boolean playerFocus = false;
    boolean playerSriracha = false;
    boolean enemyPowerCharge = false;
    boolean enemyFortify = false;
    boolean enemyFocus = false;
    int playerTurnCounterFortify = 0;
    int playerTurnCounterFocus = 0;
    int enemyTurnCounterIce = 0;
    int enemyTurnCounterFortify = 0;
    int enemyTurnCounterFocus = 0;
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backWarningHPPanel;
    private javax.swing.JPanel backWarningSPPanel;
    private javax.swing.JPanel battleBackPanel;
    private javax.swing.JButton btnAtkUp;
    private javax.swing.JButton btnAttack;
    private javax.swing.JButton btnDefUp;
    private javax.swing.JButton btnElectricAtk;
    private javax.swing.JButton btnEscape;
    private javax.swing.JButton btnFireAtk;
    private javax.swing.JButton btnGuard;
    private javax.swing.JButton btnHitUp;
    private javax.swing.JButton btnIceAtk;
    private javax.swing.JButton btnItems;
    private javax.swing.JButton btnItemsMenuQuit;
    private javax.swing.JButton btnItemsMsgQuit;
    private javax.swing.JButton btnSkill;
    private javax.swing.JButton btnSkillsMenuQuit;
    private javax.swing.JButton btnSkillsMsgQuit;
    private javax.swing.JButton btnStrawberrySmoothie;
    private javax.swing.JButton btnWater;
    private javax.swing.JPanel itemsMenuPanel;
    private javax.swing.JPanel itemsMsgPanel;
    private javax.swing.JLabel lbRagnaroklEnemy;
    private javax.swing.JLabel lblAmountOfStrawberrySmoothie;
    private javax.swing.JLabel lblAmountOfWater;
    private javax.swing.JLabel lblAtkDesc;
    private javax.swing.JLabel lblAtkSPCost;
    private javax.swing.JLabel lblDefDesc;
    private javax.swing.JLabel lblDefSPCost;
    private javax.swing.JLabel lblDungeonBackground;
    private javax.swing.JLabel lblElectricDesc;
    private javax.swing.JLabel lblElectricSPCost;
    private javax.swing.JLabel lblEnemyAtkUp;
    private javax.swing.JLabel lblEnemyCriticalHit;
    private javax.swing.JLabel lblEnemyDefUp;
    private javax.swing.JLabel lblEnemyDmgIndicator;
    private javax.swing.JLabel lblEnemyDowned;
    private javax.swing.JLabel lblEnemyHP;
    private javax.swing.JLabel lblEnemyHPNum;
    private javax.swing.JLabel lblEnemyHitEvadeUp;
    private javax.swing.JLabel lblEnemyIceImmune;
    private javax.swing.JLabel lblEnemyMoreTurnPanel;
    private javax.swing.JLabel lblEnemyName;
    private javax.swing.JLabel lblEnemySP;
    private javax.swing.JLabel lblEnemySPNum;
    private javax.swing.JLabel lblEnemySkillName;
    private javax.swing.JLabel lblEnemyWeak;
    private javax.swing.JLabel lblFireDesc;
    private javax.swing.JLabel lblFireSPCost;
    private javax.swing.JLabel lblHitDesc1;
    private javax.swing.JLabel lblHitDesc2;
    private javax.swing.JLabel lblHitSPCost;
    private javax.swing.JLabel lblIceDesc;
    private javax.swing.JLabel lblIceSPCost;
    private javax.swing.JLabel lblItemMsg;
    private javax.swing.JLabel lblItemsList;
    private javax.swing.JLabel lblPlayerAtkUp;
    private javax.swing.JLabel lblPlayerCriticalHit;
    private javax.swing.JLabel lblPlayerDefUp;
    private javax.swing.JLabel lblPlayerDmgIndicator;
    private javax.swing.JLabel lblPlayerDowned;
    private javax.swing.JLabel lblPlayerHP;
    private javax.swing.JLabel lblPlayerHPLow;
    private javax.swing.JLabel lblPlayerHPNum;
    private javax.swing.JLabel lblPlayerHitEvadeUp;
    private javax.swing.JLabel lblPlayerItemMsg;
    private javax.swing.JLabel lblPlayerMissed;
    private javax.swing.JLabel lblPlayerMoreTurnPanel;
    private javax.swing.JLabel lblPlayerSP;
    private javax.swing.JLabel lblPlayerSPLow;
    private javax.swing.JLabel lblPlayerSPNum;
    private javax.swing.JLabel lblPlayerSkillMsg;
    private javax.swing.JLabel lblPlayerSkillName;
    private javax.swing.JLabel lblPlayerWeak;
    private javax.swing.JLabel lblSkillMsg;
    private javax.swing.JLabel lblSkillsList;
    private javax.swing.JLabel lblStrawberrySmoothieDesc;
    private javax.swing.JLabel lblWarningHP;
    private javax.swing.JLabel lblWarningSP;
    private javax.swing.JLabel lblWaterDesc;
    private javax.swing.JPanel movesMenuPanel;
    private javax.swing.JPanel oneMoreTurnPanel;
    private javax.swing.JProgressBar progressBarEnemyHP;
    private javax.swing.JProgressBar progressBarEnemySP;
    private javax.swing.JProgressBar progressBarPlayerHP;
    private javax.swing.JProgressBar progressBarPlayerSP;
    private javax.swing.JPanel skillsMenuPanel;
    private javax.swing.JPanel skillsMsgPanel;
    private javax.swing.JPanel usedSkillPanel;
    // End of variables declaration//GEN-END:variables
}//end class
