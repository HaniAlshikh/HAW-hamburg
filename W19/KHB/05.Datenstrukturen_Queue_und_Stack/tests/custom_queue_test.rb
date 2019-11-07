require 'test/unit'
require_relative '../classes/custom_queue'

class CustomQueueTest < Test::Unit::TestCase

  attr_accessor :stack

  def setup
  end

  def test_enqueue

    enq_test = CustomQueue.new

    assert_nothing_raised { enq_test.enqueue(5) }
    assert_equal(6, enq_test.enqueue(6).last, "element should be at the end of the queue")
    assert_raise(EnqueueError) { enq_test.enqueue(nil) }
  end

  def test_dequeue

    deq_test = CustomQueue.new
    deq_test.enqueue(5)
    deq_test.enqueue(6)

    assert_nothing_raised { deq_test.dequeue }
    assert_equal(6, deq_test.dequeue, "first element should be removed")
    assert_raise(DequeueError) { deq_test.dequeue }

  end

  def test_empty?

    empty_test = CustomQueue.new

    assert_true(empty_test.empty?)
    empty_test.enqueue(5)
    assert_false(empty_test.empty?)
  end

  def test_equal

    tests = [
        [ %w[A B C D], %w[D C B A], false ],
        [ %w[A B C D], %w[A B C D], true ],
        [ %w[A B C D E], %w[D C B A], false ],
        [ %w[A B C D E], %w[A B C D], false ],
        [ %w[A B C D E], %w[A B C D E], true ]
    ]

    tests.each do |test|

      equal_test = CustomQueue.new
      equal_test_other = CustomQueue.new

      test[0].each { |e| equal_test.enqueue(e) }
      test[1].each { |e| equal_test_other.enqueue(e) }

      assert_true(equal_test == equal_test_other) if test[2]
      assert_false(equal_test == equal_test_other) unless test[2]

      # rescue
    end
  end


end