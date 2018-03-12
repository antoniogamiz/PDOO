# Programa de prueba de la práctica 1
# Author: Elena Merelo Molina

#encoding: utf-8

require_relative "CombatResult"
require_relative "Dice"
require_relative "Weapon"
require_relative "ShieldBooster"
require_relative "SuppliesPackage"
require_relative "Loot"
require_relative "WeaponType"
require_relative "ShotResult"
require_relative "GameCharacter"

module Deepspace
  class TestP1
    def main
      #Weapon 
      puts "Weapon: "
      w1= Weapon.new("arma peligrosa", WeaponType::LASER, 5) 
      w2= Weapon.newCopy(w1)
      print "\nName: #{w1.name}, type: #{w1.type.power}, uses: #{w1.uses}"
      print "\nName: #{w2.name}, type: #{w2.type.power}, uses: #{w2.uses}"
    end
  end
  test= TestP1.new 
  test.main
end
