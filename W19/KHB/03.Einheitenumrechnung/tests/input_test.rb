#####################################################################
# Assigment sheet A03: Unit Converter in Ruby.
#
# Test cases for for user interaction
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require 'test/unit'
require_relative '../classes/user_helper'

class InputTest < Test::Unit::TestCase
  attr_reader :user_helper
  def setup
    @user_helper = UserHelper.new
  end

  # basic input test
  def test_ask
    test_cases = { '1k c' => [[1.0],[:k,:c]],
                   '10.2kg g' => [[10.2],[:kg, :g]],
                   '1 kilometer centimeter' => [[1.0],[:kilometer, :centimeter]],
                   'bla1.2bla km bl2a cm' => [[1.2,2.0],[:km, :cm]],
                   'convert 1km kg c m' => [[1.0],[:km, :kg, :c, :m]]}

    test_cases.each do |string, expected|
      with_stdin do |user|
        user.puts string
        assert_equal(expected, user_helper.ask, 'blabla')
      end
    end
  end


  private

  # simulate user input
  def with_stdin
    stdin = $stdin             # remember $stdin
    $stdin, write = IO.pipe    # create pipe assigning its "read end" to $stdin
    yield write                # pass pipe's "write end" to block
  ensure
    write.close                # close pipe
    $stdin = stdin             # restore $stdin
  end
end