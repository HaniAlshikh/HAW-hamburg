#####################################################################
# Assigment sheet A04: Unit Converter in Ruby.
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
   __  __            _                     _           _
  |  \\/  | __ _  ___| |_  ___  _ _  _ __  (_) _ _   __| |
  | |\\/| |/ _` |(_-<|  _|/ -_)| '_|| '  \\ | || ' \\ / _` |
  |_|  |_|\\__,_|/__/ \\__|\\___||_|  |_|_|_||_||_||_|\\__,_|

  B Black peg
  W White peg

  ##############################################################
EOT

UserHelper.new.run