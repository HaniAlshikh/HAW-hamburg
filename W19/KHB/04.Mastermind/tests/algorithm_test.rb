#####################################################################
# Assigment sheet A04: Mastermind in Ruby.
# 
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require 'test/unit'
require_relative '../classes/mastermind'
require_relative '../classes/code_breaker'
require_relative '../classes/computer_code_maker'
require_relative '../classes/computer_code_breaker'
require_relative '../classes/config'

# test cases for Knuth and MAI
class AlgorithmTest < Test::Unit::TestCase
  attr_reader :mastermind, :code_maker, :code_breaker

  def setup
    @mastermind = Mastermind.new(Config::CONFIGS)
    @code_maker = ComputerCodeMaker.new(mastermind)
    @code_breaker = ComputerCodeBreaker.new(mastermind)
  end

  def test_algorithm

    @mastermind.attempts = 20
    @mastermind.knuth = false

    tests = [
        %w[A A B B],
        %w[B D C A],
        %w[A B C D],
        %w[E F A B],
        %W[F E D C],
        code_maker.generate
    ]

    tests.each do |test|
      @code_maker = ComputerCodeMaker.new(mastermind)
      @code_breaker = ComputerCodeBreaker.new(mastermind)
      code_maker.secret_code = test
      puts "-"*40
      puts "\n       #{code_maker.secret_code}"

      mastermind.attempts.times do
        @code_breaker.guess
        puts "computer guess: #{@code_breaker.guesses.last}"
        lost = @code_maker.lost?(@code_breaker.guesses.last)
        @code_breaker.scores << @code_maker.score(@code_breaker.guesses.last)
        puts "score         : #{@code_breaker.scores.last}"
        if lost
          puts "Code guessed successfully"
          @mastermind.log(@code_maker, @code_breaker)
          break
        end
      end
    end

    output

  end

  def output
    # formatting and outputting
    format = "%-13s %-10s %-10s %-10s %s"
    puts "\n############################ Game log ############################\n".green
    puts format % ['', 'Maker', 'Breaker', 'Guesses', 'Winner'], ""
    @mastermind.logs.each do |log|
      puts format % [log[:secret_code], log[:maker], log[:breaker], log[:guesses], log[:winner]]
    end
    puts
  end
end