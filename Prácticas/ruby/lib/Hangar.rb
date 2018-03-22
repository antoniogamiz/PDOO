require_relative "HangarToUI"


module Deepspace
  class Hangar
    attr_reader :maxElements, :weapons, :shieldBoosters
    def initialize(capacity)
      @maxElements= capacity
      @weapons= Array.new
      @shieldBoosters= Array.new
    end
    
    def self.newCopy(h)
      @maxElements= h.maxElements
      @weapons= h.weapons
      @shieldBoosters= h.shieldBoosters
    end
    
    def getUIversion
      HangarToUI.new(self)
    end
    
    def addWeapon(w)
      if spaceAvailable
        @weapons.push(w)
        true
      else
        false
      end
    end
    
    def removeWeapon(w)
      @weapons.delete_at(w)
    end
    
    def addShieldBooster(s)
      if spaceAvailable
        @shieldBoosters << s
        true
      else
        false
      end
    end
    
    def removeShieldBooster(s)
      @shieldBoosters.delete_at(s)
    end
    
    def to_s
      "maxElements #{@maxElements}, weapons #{@weapons.join(", ")}, shieldBoosters #{@shieldBoosters.join(", ")}"
    end
    
    private
    def spaceAvailable
      (@weapons.length + @shieldBoosters.length) < @maxElements
    end
  end
end
