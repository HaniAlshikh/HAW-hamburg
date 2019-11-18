#####################################################################
# Assigment sheet A05: Data Structures.
#
# test stack data structure functionality
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require 'test/unit'
require_relative '../classes/custom_stack'

class CustomStackTest < Test::Unit::TestCase
  def test_push
    push_test = CustomStack.new

    assert_nothing_raised { push_test.push(5) }
    assert_nothing_raised { push_test.push('test') }
    assert_nothing_raised { push_test.push(CustomStack.new) }
    assert_equal(6, push_test.push(6).last, 'element should be at the end of the stack')
    assert_raise(PushError) { push_test.push(nil) }
  end

  def test_pop
    pop_test = CustomStack.new
    pop_test.push(5)
    pop_test.push(6)

    assert_equal(6, pop_test.pop, 'last element should be removed')
    assert_nothing_raised { pop_test.pop }
    assert_raise(PopError) { pop_test.pop }
  end

  def test_empty?
    empty_test = CustomStack.new

    assert_true(empty_test.empty?)
    empty_test.push(5)
    assert_false(empty_test.empty?)
  end

  def test_equal
    # test equality for Customstack objects
    tests = [
        # first stack, second stack, expected by == and eql?
        [ %w[A B C D], %w[D C B A], false ],
        [ %w[A B C D], %w[A B C D], true ],
        [ %w[A B C D E], %w[D C B A], false ],
        [ %w[A B C D E], %w[A B C D], false ],
        [ %w[A B C D E], %w[A B C D E], true ],
        [ [1, "B", "C", 2], [1, "B", "C", 2], true ],
        [ [1, 2, 3, 4], [4, 3, 2, 1], false ],
        [ ["", [], {}, 4], [[], {}, 2, ""], false ],
        [ ["", [], {}, 4], ["", [], {}, 4], true ]
    ]

    tests.each do |test|
      equal_test = CustomStack.new
      equal_test_other = CustomStack.new

      test[0].each { |e| equal_test.push(e) }
      test[1].each { |e| equal_test_other.push(e) }

      assert_true(equal_test == equal_test_other) if test[2]
      assert_true(equal_test.eql?(equal_test_other)) if test[2]
      assert_false(equal_test == equal_test_other) unless test[2]
      assert_false(equal_test.eql?(equal_test_other)) unless test[2]
    end

    assert_false(CustomStack.new == Array.new)
    assert_false(CustomStack.new == nil)

    # test object equality from parent and child class
    special = SpecialStack.new('special')
    special.push(5)
    stack = CustomStack.new
    stack.push(5)

    assert_true(stack == special)
    assert_false(stack.eql?(special))

    # test hashing
    same_stack = CustomStack.new
    same_stack.push(5)

    hash_test = { stack => 'Normal stack with elemet 5'}
    assert_equal('Normal stack with elemet 5', hash_test[same_stack])
    assert_not_equal('Normal stack with elemet 5', hash_test[special])
  end
end

# dummy class to demonstrate the equality decisions we made
class SpecialStack < CustomStack
  attr_reader :special
  def initialize(special = '')
    super()
    @special = special
  end
end