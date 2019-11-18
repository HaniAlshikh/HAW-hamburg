#####################################################################
# Assigment sheet A05: Data Structures.
#
# test queue data structure functionality
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require 'test/unit'
require_relative '../classes/custom_queue'

class CustomQueueTest < Test::Unit::TestCase
  def test_enqueue
    enq_test = CustomQueue.new

    assert_nothing_raised { enq_test.enqueue(5) }
    assert_nothing_raised { enq_test.enqueue('test') }
    assert_nothing_raised { enq_test.enqueue(CustomQueue.new) }
    assert_equal(6, enq_test.enqueue(6).last, 'element should be at the end of the queue')
    assert_raise(EnqueueError) { enq_test.enqueue(nil) }
  end

  def test_dequeue
    deq_test = CustomQueue.new
    deq_test.enqueue(5)
    deq_test.enqueue(6)

    assert_equal(5, deq_test.dequeue, 'first element should be removed')
    assert_nothing_raised { deq_test.dequeue }
    assert_raise(DequeueError) { deq_test.dequeue }
  end

  def test_empty?
    empty_test = CustomQueue.new

    assert_true(empty_test.empty?)
    empty_test.enqueue(5)
    assert_false(empty_test.empty?)
  end

  def test_equal
    # test equality for CustomQueue objects
    tests = [
        # first queue, second queue, expected by == and eql?
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
      equal_test = CustomQueue.new
      equal_test_other = CustomQueue.new

      test[0].each { |e| equal_test.enqueue(e) }
      test[1].each { |e| equal_test_other.enqueue(e) }

      assert_true(equal_test == equal_test_other) if test[2]
      assert_true(equal_test.eql?(equal_test_other)) if test[2]
      assert_false(equal_test == equal_test_other) unless test[2]
      assert_false(equal_test.eql?(equal_test_other)) unless test[2]
    end

    assert_false(CustomQueue.new == Array.new)
    assert_false(CustomQueue.new == nil)

    # test object equality from parent and child class
    special = SpecialQueue.new('special')
    special.enqueue(5)
    queue = CustomQueue.new
    queue.enqueue(5)

    assert_true(queue == special)
    assert_false(queue.eql?(special))

    # test hashing
    same_queue = CustomQueue.new
    same_queue.enqueue(5)

    hash_test = { queue => 'Normal queue with elemet 5'}
    assert_equal('Normal queue with elemet 5', hash_test[same_queue])
    assert_not_equal('Normal queue with elemet 5', hash_test[special])
  end
end

# dummy class to demonstrate the equality decisions we made
class SpecialQueue < CustomQueue
  attr_reader :special
  def initialize(special = '')
    super()
    @special = special
  end
end