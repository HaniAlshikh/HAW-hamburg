require_relative 'code_breaker'
require_relative '../Modules/knuth_algorithm'
require_relative '../Modules/mai'

class ComputerCodeBreaker < CodeBreaker

  include KnuthAlgorithm
  include Mai

  def guess
    @guesses << (@mastermind.knuth ? guess_knuth : guess_mai)
  end

  def check_requirement?
    unless @mastermind.n_letters == 6 && @mastermind.pegs == 4
      return puts "\nWell i'm smart but not that smart :)\n" \
                  "please rest to 6 letters and 4 pegs to play with me.".yellow
    end
    if (0..5).include?(@mastermind.attempts)
      return puts "Well i mean you got me :|, i can't win in less than 6 attempts yet :(".yellow
    end
    true
  end

  def interact
    case @mastermind.attempts
    when (6..8) then puts "Only #{mastermind.attempts} attempts!! well don't expect much"
    when (14..Float::INFINITY) then puts "#{mastermind.attempts} attempts, how generous of you"
    else "Hmmmm what did you do?!"
    end
  end

  def to_s
    "Computer"
  end
end