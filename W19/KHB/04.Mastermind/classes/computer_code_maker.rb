require_relative 'code_maker'

class ComputerCodeMaker < CodeMaker

  # attr_reader :mastermind

  # @param [MasterMind] mastermind
  def initialize(mastermind)
    super(mastermind)
  end

  def self.give_feedback(guess, code)
    black_pegs = code.zip(guess).map { |c, g| c if c == g }.compact
    feedback = ['B'] * black_pegs.size
    guess.subtract_once(black_pegs).each do |letter|
      next unless code.subtract_once(black_pegs).include?(letter)
      feedback << 'W'
      code = code.subtract_once([*letter])
    end
    feedback.shuffle
  end

  def generate
    self.secret_code = (mastermind.letters * 2).shuffle[1..mastermind.pegs]
  rescue ArgumentError
    puts e.message.red if mastermind.verbose
    raise ArgumentError, "Computer is generating a bad code".red
  end

  def give_feedback(guess)
    self.class.give_feedback(guess, @secret_code)
  end

end