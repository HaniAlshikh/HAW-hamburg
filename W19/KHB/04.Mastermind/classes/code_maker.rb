#####################################################################
# Assigment sheet A04: Mastermind in Ruby.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'mastermind'

# parent class of code makers
class CodeMaker
  attr_reader :secret_code, :mastermind

  # @param [Mastermind] mastermind
  def initialize(mastermind)
    @mastermind = mastermind
    @secret_code = []
  end

  # @param [Array] code
  def secret_code=(code)
    raise ArgumentError, "#{code} is not valid" unless @mastermind.validate_code?(code)
    @secret_code = code
  end

  # @param [Array] guess
  def lost?(guess)
    @secret_code == guess
  end
end