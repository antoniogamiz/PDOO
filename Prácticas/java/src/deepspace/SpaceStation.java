/** 
 * @author Elena Merelo
 */
package deepspace;

import java.util.ArrayList;

class SpaceStation {
    private static final int MAXFUEL= 100;
    private static final float SHIELDLOSSPERUNITSHOT= 0.1f;    
    private float ammoPower, fuelUnits, shieldPower;
    private String name;
    private int nMedals;
    private Damage pendingDamage;
    private ArrayList<Weapon> weapons;
    private ArrayList<ShieldBooster> shieldBoosters;
    private Hangar hangar;
    
    //Constructor
    SpaceStation(String n, SuppliesPackage supplies){
        name= n;
        ammoPower= supplies.getAmmoPower();
        fuelUnits= supplies.getFuelUnits();
        shieldPower= supplies.getShieldPower();
        nMedals= 0;
        weapons= new ArrayList<>();
        shieldBoosters= new ArrayList<>();
    }
    
    //Getters
    public float getAmmoPower(){
        return ammoPower;
    }
    
    public float getFuelUnits(){
        return fuelUnits;
    }
    
    public Hangar getHangar(){
        return hangar;
    }
    
    public String getName(){
        return name;
    }
    
    public int getNMedals(){
        return nMedals;
    }
    
    public Damage getPendingDamage(){
        return pendingDamage;
    }
    
    public ArrayList<ShieldBooster> getShieldBoosters(){
        return shieldBoosters;
    }
    
    public float getShieldPower(){
        return shieldPower;
    }
    
    public SpaceStationToUI getUIversion(){
        return new SpaceStationToUI(this);
    }
    
    public ArrayList<Weapon> getWeapons(){
        return weapons;
    }
    
    /**
     * @brief Fija la cantidad de combustible al valor pasado como parámetro sin
     * que nunca se exceda el límite.
     */
    private void assignFuelValue(float f){
        if(f < MAXFUEL)
            fuelUnits= f;
        else 
            fuelUnits= MAXFUEL;
    }
    
    /**
     * @brief Si el daño pendiente (pendingDamage) no tiene efecto fija la
     * referencia al mismo a null.
     */
    private void cleanPendingDamage(){
        if(pendingDamage.hasNoEffect())
            pendingDamage= null;
    }
    
    public boolean receiveWeapon(Weapon w){
        if(hangar != null)
           return hangar.addWeapon(w);
      
        return false;
    }
    
    public boolean receiveShieldBooster(ShieldBooster s){
        if(hangar != null)
            return hangar.addShieldBooster(s);
        return false;       
    }
    
    public void receiveHangar(Hangar h){
        if(hangar == null)
            hangar= h;
    }
    
    public void discardHangar(){
        hangar= null;
    }
    
    public void receiveSupplies(SuppliesPackage s){
        ammoPower += s.getAmmoPower();
        shieldPower += s.getShieldPower();
        assignFuelValue(fuelUnits + s.getFuelUnits());
    }
    
    public void setPendingDamage(Damage d){
        pendingDamage= d.adjust(weapons, shieldBoosters);
    }
    
    public void mountWeapon(int i){
        Weapon w= new Weapon("w", null, 0);
        if(hangar != null || !hangar.getWeapons().isEmpty())
            w= hangar.removeWeapon(i);
            if(w != null)
                weapons.add(w);
    }
    
    public void mountShieldBooster(int i){
        ShieldBooster sb= new ShieldBooster("sb", 0.0f, 0);
        if(hangar != null || !hangar.getShieldBoosters().isEmpty())
            sb= hangar.removeShieldBooster(i);
            if(sb != null)
                shieldBoosters.add(sb);
    }
    
    public void discardWeaponInHangar(int i){
        if(hangar != null)
            hangar.removeWeapon(i);  
    }
    
    public void discardShieldBoosterInHangar(int i){
        if(hangar != null)
            hangar.removeShieldBooster(i);  
    }
    
    public float getSpeed(){
        return fuelUnits/MAXFUEL;
    }
    
    public void move(){
        if(fuelUnits - fuelUnits*getSpeed() <= 0)
            fuelUnits= 0;
        else
            fuelUnits -= fuelUnits*getSpeed();
    }
    
    public boolean validState(){
        return pendingDamage == null || pendingDamage.hasNoEffect() == true;
    }
    
    public void cleanUpMountedItems(){
       weapons.removeIf(w -> w.getUses() == 0);
       shieldBoosters.removeIf(s -> s.getUses() == 0);
    }
    
    /**
     * @brief Realiza un disparo y se devuelve la energía o potencia del mismo. 
     * Para ello se multiplica la potencia de disparo por los factores potenciadores
     * proporcionados por todas las armas.
     */
    public float fire(){
        float factor= 1.0f;
        
        for(int i= 0; i< weapons.size(); i++)
          factor *= weapons.get(i).useIt();  
        
        return ammoPower*factor;
    }
    
    /**
     * @brief Se usa el escudo de protección y se devuelve la energía del mismo. 
     * Para ello se multiplica la potencia del escudo por los factores potenciadores
     * proporcionados por todos los potenciadores de escudos de los que se dispone.
     */
    public float protection(){
        float factor= 1.0f;
        
        for(int i= 0; i< shieldBoosters.size(); i++)
            factor *= shieldBoosters.get(i).useIt();
        
        return shieldPower*factor;
    }
    
    /**
     * @brief Realiza las operaciones relacionadas con la recepción del impacto 
     * de un disparo enemigo. Ello implica decrementar la potencia del escudo en 
     * función de la energía del disparo recibido como parámetro y devolver el 
     * resultado de si se ha resistido el disparo o no.
     */
    public ShotResult receiveShot(float shot){
        if(protection() >= shot){
            shieldPower -= SHIELDLOSSPERUNITSHOT*shot;
            shieldPower= Math.max(0.0f, shieldPower);
            
            return ShotResult.RESIST;
        }
        else return ShotResult.DONOTRESIST;
    }
    
    /**
     * @brief Recepción de un botín. Por cada elemento que indique el botín (pasado
     * como parámetro) se le pide a CardDealer un elemento de ese tipo y se intenta
     * almacenar con el método receive*() correspondiente. Para las medallas, 
     * simplemente se incrementa su número según lo que indique el botín.
     */
    public void setLoot(Loot loot){
        CardDealer dealer= CardDealer.getInstance();
        int h= loot.getNHangars();
        
        if(h > 0){
            hangar= dealer.nextHangar();
            receiveHangar(hangar);
        }
        
        for(int i= 1; i< loot.getNSupplies(); i++){
            SuppliesPackage sup= dealer.nextSuppliesPackage();
            receiveSupplies(sup);
        }
    }
    
    public void discardWeapon(int i){
        throw new UnsupportedOperationException();
    }
    
    public void discardShieldBooster(int i){
        throw new UnsupportedOperationException();
    }
}





