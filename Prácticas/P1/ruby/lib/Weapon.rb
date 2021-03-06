=begin
Representa a las armas de las que puede disponer una estación espacial para potenciar su
energía al disparar
=end

module Deepspace
  class Weapon
    attr_reader :name, :type, :uses
    
    def initialize(name, type, uses)
      @name= name
      @type= type
      @uses= uses
    end

    def newCopy(w)
      Weapon.new(w.name, w.type, w.uses)
    end
    
    def power
      @type.power
    end

    def useIt
      if @uses > 0
        @uses -= 1
        power
      else
        1.0
      end
    end
    
    def to_s
      "Name #{@name}, type #{@type.power}, uses #{@uses}"
    end
    
  end
end