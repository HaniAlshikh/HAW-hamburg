#####################################################################
# Assigment sheet A03: Unit Converter in Ruby.
#
# this script starts the unit converter system, which can interact
# with one user and convert between common Units
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'classes/user_helper'
# require_relative 'classes/uni_converter'

# uni_converter.strat()


puts '###########################################################'
puts '####### Assigment sheet A03: Unit converter in Ruby #######'
puts '###########################################################'

puts '###########################################################'
puts "############ Welecome to your basic converter #############".green
puts

UserHelper.new().start()