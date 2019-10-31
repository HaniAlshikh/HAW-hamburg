require_relative 'code_breaker'

class HumanCodeBreaker < CodeBreaker

  def initialize(mastermind)
    # @mastermind = mastermind
    super(mastermind)
  end

  def guess
    guess = yield
    guess_ = mastermind.to_code(guess)
    raise ArgumentError, "#{guess} is Not a valid guess" unless mastermind.validate_code?(guess_)
    guess_
  end

end