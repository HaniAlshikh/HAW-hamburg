#####################################################################
# Assigment sheet A04: Mastermind in Ruby.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'mastermind'

# parent class of code breakers
class CodeBreaker
  attr_reader :mastermind
  attr_accessor :guesses, :scores

  # @param [Mastermind] mastermind
  def initialize(mastermind)
    @mastermind = mastermind
    @guesses = []
    @scores = []
  end
end