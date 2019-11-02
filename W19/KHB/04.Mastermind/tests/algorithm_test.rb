#####################################################################
# Assigment sheet A03: Unit Converter in Ruby.
#
# Test cases for every unit and measurement
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require 'test/unit'
require_relative '../classes/master_mind'
require_relative '../classes/code_breaker'
require_relative '../classes/computer_code_maker'
require_relative '../classes/computer_code_breaker'

class AlgorithmTest < Test::Unit::TestCase

  attr_reader :mastermind, :code_maker, :code_breaker

  def setup
    @mastermind = MasterMind.new
    @code_maker = ComputerCodeMaker.new(mastermind)
    @code_breaker = ComputerCodeBreaker.new(mastermind)
  end

  def test_algorithm

    tests = [
        %w[A A B B],
        %w[B D C A],
        %w[A B C D],
        %w[E F A B],
        code_maker.generate
    ]

    tests.each do |test|
      @code_maker = ComputerCodeMaker.new(mastermind)
      @code_breaker = ComputerCodeBreaker.new(mastermind)
      code_maker.secret_code = test
      puts "-"*40
      puts "\n       #{code_maker.secret_code}"

      mastermind.attempts.times do
        @code_breaker.guesses << @code_breaker.guess_knuth # guess or guess_knuth
        puts "computer guess: #{@code_breaker.guesses.last}"
        lost = @code_maker.lost?(@code_breaker.guesses.last)
        @code_breaker.feedback << @code_maker.give_feedback(@code_breaker.guesses.last)
        puts "feedback      : #{@code_breaker.feedback.last}"
        if lost
          puts "Code guessed successfully"
          @mastermind.evaluate(@code_maker, @code_breaker)
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