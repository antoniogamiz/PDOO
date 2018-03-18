/**
 *
 * @author Elena Merelo
 */

package deepspace;

import java.util.ArrayList;

public class Hangar {
    private int maxElements;
    private ArrayList<Weapon> weapons;
    private ArrayList<ShieldBooster> shield_boosters;
    
    //Constructores 
    Hangar(int capacity, ArrayList<Weapon> w, ArrayList<ShieldBooster> s){
        maxElements= capacity;
        weapons= new ArrayList<> (w);
        shield_boosters= new ArrayList<> (s);
    }
    
    Hangar(Hangar h){
        this(h.maxElements, h.weapons, h.shield_boosters);
    }
    
    //getters
    HangarToUI getUIversion(){
        return new HangarToUI(this);
    }
    
    public int getMaxElements(){
        return maxElements;
    }
    
    public ArrayList<Weapon> getWeapons(){
        return weapons;
    }
    
    public ArrayList<ShieldBooster> getShieldBoosters(){
        return shield_boosters;
    }
    
    /*
     * @brief  Devuelve true si aún hay espacio para añadir elementos y por lo
     * tanto no se ha llegado a la capacidad máxima.
    */
    private boolean spaceAvalaible(){
        return weapons.size() + shield_boosters.size() < maxElements;
    }
    
    /*
     * @brief Añade el arma al hangar si queda espacio en el Hangar, devolviendo 
     * true en ese caso. Devuelve false en cualquier otro caso.
    */
    public boolean addWeapon(Weapon w){
        if(spaceAvalaible() == true){
            weapons.add(w);
            return true;
        }
        return false;
    }
    
    /*
     * @brief Añade el potenciador de escudo al hangar si queda espacio. Devuelve 
     * true si ha sido posible añadir el potenciador, false en otro caso.
    */
    public boolean addShieldBooster(ShieldBooster s){
        if(spaceAvalaible() == true){
            shield_boosters.add(s);
            return true;
        }
        return false;
    }
    
    /* 
     * @brief Elimina el arma número n del hangar y la devuelve, siempre que
     * ésta exista. Si el índice suministrado es incorrecto devuelve null.
    */
    public Weapon removeWeapon(int n){
        if(n > 0 && n < weapons.size()){
            Weapon removed= new Weapon(weapons.get(n));
            weapons.remove(n);
            return removed;
        }
        return null; 
    }
    
    /*
     * @brief Elimina el potenciador de escudo número s del hangar y lo devuelve, 
     * siempre que éste exista. Si el índice suministrado es incorrecto devuelve null.
    */
    ShieldBooster removeShieldBooster(int n){
        if(n > 0 && n < weapons.size()){
            ShieldBooster removed= new ShieldBooster(shield_boosters.get(n));
            shield_boosters.remove(n);
            return removed;
        }
        return null;  
    }
}




