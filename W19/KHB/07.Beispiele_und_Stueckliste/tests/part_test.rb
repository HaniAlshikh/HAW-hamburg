#####################################################################
# Assigment sheet A07: Examples and Partlist.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative '../modules/partlist'
require_relative '../classes/part'
require 'test/unit'
require 'yaml'

class PartTest < Test::Unit::TestCase

  PARTLIST = YAML.load_file(File.join(__dir__, '../partlists/Bicycle.yml')).freeze

  def setup
    @bicycle = Partlist.generate(PARTLIST)
    @mass = @bicycle.mass
    @whole = @bicycle.front_set
    @whole_mass = @whole.mass
    @part = @whole.handlebar_grip
    @new_whole = @bicycle.saddle_area
    @new_part = Part.new("Test", mass: 100)
  end

  def test_equality
    test = Part.new("Test", mass: 100)
    assert_equal(@part, @part)
    assert_equal(@new_part, test)
    assert_nothing_raised { @new_part.add(@part) }
    assert_not_equal(@new_part, test)
    assert_nothing_raised { test.add(@part) }
    assert_equal(@new_part, test)
  end

  def test_parts
    assert_raises(ArgumentError) { Part.new('Test', Object.new) }
    assert_equal(@new_part, Part.new('Test', @new_part).parts[0])
    assert_nothing_raised { @new_part.add(Part.new('Test',mass: 100)) }
    assert_raises(ArgumentError) { @new_part.add(Object.new) }
    assert_nothing_raised { @bicycle.parts = [] }
    assert_equal([], @bicycle.parts)
  end

  def test_label
    assert_nothing_raised { @bicycle.label = 10 }
    assert_equal("10", @bicycle.label)
    assert_nothing_raised { @bicycle.label = "Bike" }
    assert_equal("Bike", @whole.whole.label)
    assert_equal("Bike", @part.top.label)
  end

  def test_mass(top_mass = 15.9)
    assert_equal(top_mass, @bicycle.mass)
    assert_nothing_raised { @bicycle.parts[1].mass = 50 }
    assert_equal(@bicycle.parts[1].parts[0].mass + @bicycle.parts[1].parts[1].mass,
                 @bicycle.parts[1].mass)
    assert_raises(ArgumentError) { @bicycle.parts[1].parts[0].mass = "50" }
    assert_nothing_raised { @bicycle.parts[1].parts[1].mass = 50 }
    assert_equal(50, @bicycle.parts[1].parts[1].mass)
  end

  def test_whole
    assert_raises(ArgumentError) { @bicycle.whole = @bicycle }
    assert_equal(@whole, @part.whole)
    assert_nothing_raised { @part.whole = @new_whole }
    assert_equal(@new_whole, @part.whole)
    assert_not_equal(@old_mass, @whole.mass)
    assert_equal(@whole_mass - @part.mass, @whole.mass )
  end

  def test_remove
    assert_nothing_raised { @bicycle.remove(@part) }
    assert_equal(@whole_mass - @part.mass, @whole.mass)
    assert_false(@whole.parts.include?(@part))
    assert_not_equal(@mass, @bicycle.mass)
  end

  def test_replace
    new_part = Part.new("New Part", mass: 100)
    assert_nothing_raised { @bicycle.replace(@part, new_part) }
    assert_false(@whole.parts.include?(@part))
    assert_equal(@whole_mass - @part.mass + new_part.mass, @whole.mass)
    assert_equal(@whole, @whole.new_part.whole)
  end

  def test_flatten
    @part.add(Part.new('Test', mass: 100))
    @bicycle.parts = @part
    assert_equal(3, @bicycle.flatten.size)
  end

  def test_identity
    old_part_id = @part.object_id
    old_part_whole_id = @part.whole.object_id
    car = Part.new('Car', @new_part, @part)
    # test reference change when adding to partlist
    assert_nothing_raised { @bicycle.add(car) }
    assert_not_equal(old_part_id, @bicycle.car.handlebar_grip.object_id)
    assert_not_equal(old_part_whole_id, @bicycle.car.handlebar_grip.whole.object_id)
    # parts still found
    assert_nothing_raised { @bicycle.remove(@new_part.dup) }
    assert_false(@bicycle.car.parts.include?(@new_part))
    assert_true(car.parts.include?(@new_part))
  end
end
