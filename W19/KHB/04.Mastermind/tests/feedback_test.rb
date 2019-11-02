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
require_relative '../classes/code_maker'
require_relative '../classes/computer_code_maker'
require_relative '../classes/human_code_maker'
require_relative '../classes/config'

class AlgorithmTest < Test::Unit::TestCase

  attr_reader :mastermind, :code_maker

  def setup
    @mastermind = MasterMind.new(Config::CONFIGS)
    @code_maker = ComputerCodeMaker.new(mastermind)
  end

  def test_computer

    tests = [
        [%w[A B C D], %w[A A A A], %w[B], %w[B B B B]],
        [%w[A B C D], %w[F F F F], %w[], %w[B W W B]],
        [%w[C B A A], %w[C B A B], %w[B B B], %w[B B B W]],
        [%w[A A B C], %w[A B B C], %w[B B B], %w[B W B B]],
        [%w[A B A C], %w[A D C F], %w[B W], %w[B B]],
        [%w[A C F E], %w[A B C B], %w[B W], %w[B B]],
        [%w[A A B C], %w[C A B A], %w[W B B W], %w[B B W]],
        [%w[A B C D], %w[A A B B], %w[B W], %w[B W W]]

    ]

    tests.each do |test|
      code_maker.secret_code = test[0]
      guess = test[1]
      expected = test[2]
      expected_wrong = test[3]

      assert_equal(expected.sort, code_maker.score(guess).sort, 'feedback is not correct')
      assert_not_equal(expected_wrong.sort, code_maker.score(guess).sort.empty?, 'feedback is not correct')
    end

  end
end