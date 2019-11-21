#####################################################################
# Assigment sheet A04: Mastermind in Ruby.
#
# this script prints usage info and starts the game
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative '../classes/Cat'
require_relative '../classes/Dog'
require_relative '../classes/lion'
require_relative '../classes/Person'

cat = Cat.new('Cat', @owner)
dog = Dog.new('Dog', @owner)
lion = Lion.new('Lion', @owner)
killer = Cat.new('Killer', @owner)

p cat.kill(cat)
# dog.kill(cat)
p killer.kill(cat)
p lion.kill(dog)
p lion.kill(dog)