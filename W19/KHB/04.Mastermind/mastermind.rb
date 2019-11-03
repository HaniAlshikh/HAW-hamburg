#####################################################################
# Assigment sheet A04: Mastermind in Ruby.
#
# this script prints usage info and starts the game
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

                   B Black Peg  W White Peg

  Usage:
  - play or p to start playing
  - exit or e to exit
  - config or c to config the main configuration
  - config attempts <num> or c a <num> to change the attempts value
  - config pegs <num> or c p <num> to change the pegs value
  - config letters <num> or c l <num> to change the letters value
  - knuth or k to enable knuth's algorithm

  ##############################################################
EOT

UserHelper.new.run