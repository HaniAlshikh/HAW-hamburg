require_relative 'code_maker'

class HumanCodeMaker < CodeMaker
  def initialize(mastermind)
    # @mastermind = mastermind
    super(mastermind)
  end

  def generate
    secret_code = yield
    self.secret_code = mastermind.to_code(secret_code)
  rescue ArgumentError => e
    puts e.message.red if mastermind.verbose
    raise ArgumentError, "#{secret_code} is not a valid code"
  end

  def give_feedback(valid)
    feedback = yield
    feedback = mastermind.to_code(feedback)
    puts "computer feedback: #{valid}".green if mastermind.verbose
    raise ArgumentError, 'Please give an honest feedback' unless feedback.sort == valid.sort
    feedback
  end

  def to_s
    "You"
  end

end