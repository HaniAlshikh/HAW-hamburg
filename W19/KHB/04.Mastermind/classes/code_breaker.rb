require_relative 'master_mind'

class CodeBreaker

  attr_reader :mastermind
  attr_accessor :guesses, :scores

  # @param [MasterMind] mastermind
  def initialize(mastermind)
    @mastermind = mastermind
    @guesses = []
    @scores = []
  end
end