/**
 * @author elena
 * @brief Representa un paquete de suministros para una estación espacial. Puede contener
 * armamento, combustible y/o energía para los escudos.
*/
package deepspace;

class SuppliesPackage{
    private float ammoPower, fuelUnits, shieldPower;

    //Constructores
    SuppliesPackage(float ammo, float fuel, float power){
        this.ammoPower= ammo;
        this.fuelUnits= fuel;
        this.shieldPower= power;
    }
    
    SuppliesPackage(SuppliesPackage s){
        this(s.getAmmoPower(), s.getFuelUnits(), s.getShieldPower());
    }
    
    //Consultores
    public float getAmmoPower(){
        return ammoPower;
    }
    
    public float getFuelUnits(){
        return fuelUnits;
    }
    
    public float getShieldPower(){
        return shieldPower;
    }
    
}
