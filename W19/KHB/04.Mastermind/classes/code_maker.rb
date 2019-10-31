require_relative 'master_mind'

class CodeMaker

  attr_reader :secret_code, :mastermind

  def initialize(mastermind)
    @mastermind = mastermind
    @secret_code = []
  end

  def secret_code=(code)
    raise ArgumentError, "#{code} is not valid" unless mastermind.validate_code?(code)
    @secret_code = code
  end

  def lost?(guess)
    @secret_code == guess
  end

end