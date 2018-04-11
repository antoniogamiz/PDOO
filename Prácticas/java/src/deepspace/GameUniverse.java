/**
 * @author Elena Merelo
 */
package deepspace;

import java.util.ArrayList;

public class GameUniverse {
    private static final int WIN= 10;
    private int currentStationIndex;
    private int turns;
    private EnemyStarShip currentEnemy;
    private GameStateController gameState;
    private Dice dice;
    private SpaceStation currentStation;
    private ArrayList<SpaceStation> spaceStations;
    
    //Constructor 
    public GameUniverse(){
        gameState= new GameStateController();
        turns= 0;
        dice= new Dice();
        spaceStations= new ArrayList<>();
    }
    
    //Getters
    public GameUniverseToUI getUIversion(){
        return new GameUniverseToUI(currentStation, currentEnemy);
    }

    public GameState getState() {
        return gameState.getState();
    } 
    
    public void discardHangar(){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
            currentStation.discardHangar();
    }
    
    public void discardShieldBooster(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
            currentStation.discardShieldBooster(i);
    }
    
    public void discardShieldBoosterInHangar(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
            currentStation.discardShieldBoosterInHangar(i);
    } 
    
    public void discardWeapon(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
            currentStation.discardWeapon(i);
    }
    
    public void discardWeaponInHangar(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
            currentStation.discardWeaponInHangar(i);
    }
    
    public boolean haveAWinner(){
        return currentStation.getNMedals() == WIN;
    }
    
    public void mountShieldBooster(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
            currentStation.mountShieldBooster(i);
    }
    
    public void mountWeapon(int i){
        if(gameState.getState() == GameState.INIT || gameState.getState() == GameState.AFTERCOMBAT)
            currentStation.mountWeapon(i);
    }
    
    //Siguiente práctica
    CombatResult combat(SpaceStation station, EnemyStarShip enemy){
        throw new UnsupportedOperationException();
    }

    public void init(ArrayList<String> names){
        throw new UnsupportedOperationException();
    }
    
    public CombatResult combat(){
        throw new UnsupportedOperationException();
    }
    
    public boolean nextTurn(){
        throw new UnsupportedOperationException();
    }
}


















