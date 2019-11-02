require_relative 'code_maker'

class HumanCodeMaker < CodeMaker

  def generate
    secret_code = yield
    self.secret_code = @mastermind.to_code(secret_code)
  rescue ArgumentError => e
    puts e.message.red if mastermind.verbose
    raise ArgumentError, "#{secret_code} is not a valid code"
  end

  def score(valid)
    score = yield
    score = mastermind.to_code(score)
    puts "computer score: #{valid}".green if mastermind.verbose
    raise ArgumentError, 'Please give an honest score' unless score.sort == valid.sort
    score.sort
  end

  def to_s
    "You"
  end

  def interact
    if mastermind.winner.to_s =~ /computer.*/i
      if @mastermind.knuth
        puts "Well it's Knuth algorithm, what did you expect :D"
      else
        puts "Oh yea, looks like i'm really smart after all :)"
      end
    else
      puts "Wow your code is hard to guess"
    end
  end

end