require 'test/unit'
require 'set'
require_relative '../classes/natural_order'

class NaturalOrderTest < Test::Unit::TestCase
  def setup
    @range = NaturalOrder.new..NaturalOrder.new(5)
  end

  def test_argument
    assert_nothing_raised { NaturalOrder.new(1,1) }
    assert_raises(ArgumentError) { NaturalOrder.new(1,-1) }
    assert_raises(ArgumentError) { NaturalOrder.new(1,1.1) }
    assert_raises(ArgumentError) { NaturalOrder.new(1,Object.new) }
    assert_raises(ArgumentError) { NaturalOrder.new(2) }
    assert_raises(ArgumentError) { NaturalOrder.new(-1) }
    assert_raises(ArgumentError) { NaturalOrder.new(3.3) }
  end

  def test_equality
    assert_equal(NaturalOrder.new, NaturalOrder.new)
    assert_equal([1, 3, 5], @range.to_a)
    assert_equal([NaturalOrder.new, NaturalOrder.new(3), NaturalOrder.new(5)], @range.to_a)
    assert_true(NaturalOrder.new(9) > 5)
    assert_false(NaturalOrder.new(1, 1) == NaturalOrder.new)
    assert_true(@range === NaturalOrder.new(3))
    assert_false(@range === NaturalOrder.new(9))
    assert_equal(1, Set[NaturalOrder.new, NaturalOrder.new].size)
  end

  def test_parent
    assert_equal(4, NaturalOrder.new(1,1).parent)
    assert_equal(8, NaturalOrder.new(3,2).parent)
    assert_equal(2, NaturalOrder.new.parent)
    assert_not_equal(4, NaturalOrder.new.parent)
  end

  def test_children
    assert_equal([1, 3], NaturalOrder.new(1,1).children)
    assert_equal([10, 14], NaturalOrder.new(3,2).children)
    assert_equal([1, 1], NaturalOrder.new.children)
    assert_not_equal([1, 3], NaturalOrder.new.children)
  end

  def test_family
    assert_equal([[2, 6],[1, 3, 5, 7]], NaturalOrder.new(1,2).family)
    assert_not_equal([[2, 6],[1, 3, 5, 7]], NaturalOrder.new(3,2).family)
  end

  def test_range
    assert_kind_of(Range, @range)
    assert_equal([1, 3, 5], @range.to_a)
  end
end