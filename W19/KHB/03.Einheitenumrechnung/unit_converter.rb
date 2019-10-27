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

puts <<~EOT.green
  ##############################################################
  ######## Assigment sheet A03: Unit converter in Ruby #########
  ##############################################################

  ##############################################################
  ############# Welecome to your basic converter ###############

  Supported units: #{Units::units.join(', ')}
  Usage: 1km to cm | 1 kilometer centimeter | factor unit unit
    - regardless of the syntax:
        - the first number is the factor
        - the first valid unit is the base unit
        - the second valid unit is the target unit
        - everything else will be ignored

    - conversion are shown with 2 digits after the decimal point
        - if you would like to change this please
          add another number to your input
          1k to f 5 digits | 1k f 5

    - to exit just type exit or done

  happy converting

  ##############################################################
EOT

UserHelper.new.start