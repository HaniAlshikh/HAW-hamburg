require_relative 'code_maker'

class ComputerCodeMaker < CodeMaker

  def generate
    self.secret_code = (mastermind.letters * 2).shuffle[1..mastermind.pegs]
  rescue ArgumentError
    puts e.message.red if mastermind.verbose
    raise ArgumentError, "Computer is generating a bad code".red
  end

  def score(guess)
    mastermind.score(guess, @secret_code)
  end

  def to_s
    "Computer"
  end

  def interact
    if mastermind.winner.to_s =~ /computer.*/i
      puts "You lost, maybe next time :)"
    else
      puts "Congrats you won!"
    end
  end

end