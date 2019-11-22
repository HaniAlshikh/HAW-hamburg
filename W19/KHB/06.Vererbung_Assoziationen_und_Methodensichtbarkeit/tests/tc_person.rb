require 'test/unit'
require_relative '../classes/Cat'
require_relative '../classes/Dog'
require_relative '../classes/Person'

class TC_Person < Test::Unit::TestCase

  def setup
    @person = Person.new('person')
    @cat = Cat.new('cat', @person)
    @dog = Dog.new('dog', @person)
  end

  def test_adopt
    adopter = Person.new('adopter')

    assert_raise(ArgumentError) { @person.adopt('') }
    assert_equal(Set[@dog, @cat], @person.pets, "#{@person.name} should own #{@cat.name}, #{@dog.name}")
    assert_nothing_raised { adopter.adopt(@dog) }
    assert_nothing_raised { adopter.adopt(@dog) }
    assert_equal(Set[@dog], adopter.pets, "#{adopter.name} should own #{@dog.name}")
  end

  def test_feed
    feeder = Person.new('Feeder')

    assert_nothing_raised { feeder.feed(@dog) }
    assert_raise(ArgumentError) { feeder.feed(@cat) }
    assert_nothing_raised { feeder.adopt(@cat) }
    assert_nothing_raised { feeder.feed(@cat) }
  end

  def test_pet
    petter = Person.new('{Petter')

    assert_nothing_raised { petter.pet(@dog) }
    assert_raise(ArgumentError) { petter.pet(@cat) }
    assert_nothing_raised { petter.adopt(@cat) }
    assert_nothing_raised { petter.pet(@cat) }
  end
end