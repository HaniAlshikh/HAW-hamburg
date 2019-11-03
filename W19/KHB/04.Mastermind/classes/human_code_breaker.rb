#####################################################################
# Assigment sheet A04: Mastermind in Ruby.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'code_breaker'

# user breaker class
class HumanCodeBreaker < CodeBreaker
  # @yield for user guess
  # @raise [ArgumentError] if guess is not valid code
  # @return [Array] guesses array
  def guess
    guess = yield
    guess_ = @mastermind.to_code(guess)
    raise ArgumentError, "#{guess} is Not a valid guess" unless @mastermind.validate_code?(guess_)

    @guesses << guess_
  end

  def to_s
    'You'
  end
end