require_relative 'master_mind'

class CodeBreaker

  attr_reader :mastermind
  attr_accessor :guesses, :feedback

  # @param [MasterMind] mastermind
  def initialize(mastermind)
    @mastermind = mastermind
    @guesses = []
    @feedback = []
  end

end