#####################################################################
# Assigment sheet A06: Inheritance, Association, Methods visibility.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require 'test/unit'
require 'yaml'
require_relative '../classes/part'
require_relative '../modules/partlist'

class TestPart < Test::Unit::TestCase

  PARTLIST = YAML.load_file(File.join(__dir__, '../partlists/Bicycle.yml')).freeze

  def setup
    @bicycle = Partlist.generate(PARTLIST)
  end

  def test_parts
    test = Part.new("Test", 100)
    assert_raises(ArgumentError) { Part.new('Test',0,Object.new) }
    assert_equal(test, Part.new('Test',0, test).parts[0])
    assert_nothing_raised { test.add(Part.new('Test',0)) }
    assert_raises(ArgumentError) { test.add(Object.new) }
  end

  def test_label
    assert_nothing_raised { @bicycle.label = 10 }
    assert_equal("10", @bicycle.label)
    assert_nothing_raised { @bicycle.label = "Bike" }
    assert_equal("Bike", @bicycle.parts[0].whole.label)
    assert_equal("Bike", @bicycle.parts[0].parts[0].top.label)
  end

  def test_mass(top_mass = 15.75)
    assert_equal(top_mass, @bicycle.mass)
    assert_nothing_raised { @bicycle.parts[1].mass = 50 }
    assert_equal(@bicycle.parts[1].parts[0].mass + @bicycle.parts[1].parts[1].mass,
                 @bicycle.parts[1].mass)
    assert_raises(ArgumentError) { @bicycle.parts[1].parts[0].mass = "50" }
    assert_nothing_raised { @bicycle.parts[1].parts[1].mass = 50 }
    assert_equal(50, @bicycle.parts[1].parts[1].mass)
  end

  def test_whole
  end


end
