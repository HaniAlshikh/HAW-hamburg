#####################################################################
# Assigment sheet A04: Mastermind in Ruby.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'code_maker'

# creates secret codes and score them
class ComputerCodeMaker < CodeMaker
  # generates a secret code
  def generate
    self.secret_code = (@mastermind.letters * 2).shuffle[1..@mastermind.pegs]
  end

  def score(guess)
    @mastermind.score(guess, @secret_code)
  end

  def to_s
    'Computer'
  end

  # gives feedback about the winner
  def interact
    if @mastermind.winner.to_s =~ /computer.*/i
      puts 'You lost, maybe next time :)'
    else
      puts 'Congrats you won!'
    end
  end
end