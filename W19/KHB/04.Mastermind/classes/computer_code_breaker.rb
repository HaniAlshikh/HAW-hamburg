#####################################################################
# Assigment sheet A04: Mastermind in Ruby.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'code_breaker'
require_relative '../Modules/knuth_algorithm'
require_relative '../Modules/mai'

# guess the secret code using Knuth's Algorithm or Mastermind AI
class ComputerCodeBreaker < CodeBreaker
  include KnuthAlgorithm
  include Mai

  # @return [Array] guesses array
  def guess
    @guesses << (@mastermind.knuth ? guess_knuth : guess_mai)
  end

  # check the requirements for knuth/ai to work as expected
  def check_requirement?
    # returning puts is same as returning false
    unless @mastermind.n_letters == 6 && @mastermind.pegs == 4
      return puts "\nWell i'm smart but not that smart :)\n" \
                  'please rest to 6 letters and 4 pegs to play with me.'.yellow
    end
    # it's almost impossible for mai to win in under 5 guesses
    if (0..5).include?(@mastermind.attempts) && !@mastermind.knuth
      return puts "Well i mean you got me :|, i can't win in less than 6 attempts yet :(".yellow
    end

    true
  end

  # simulate AI :)
  def interact
    case @mastermind.attempts
    when (6..8) then puts "Only #{@mastermind.attempts} attempts!! well don't expect much"
    when (14..Float::INFINITY) then puts "#{@mastermind.attempts} attempts, how generous of you"
    else 'Hmmmm what did you do?!'
    end
  end

  def to_s
    'Computer'
  end
end