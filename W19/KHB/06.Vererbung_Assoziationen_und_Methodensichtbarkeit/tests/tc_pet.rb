
require 'test/unit'
require_relative '../classes/Cat'
require_relative '../classes/Dog'
require_relative '../classes/Person'

class DogTest < Test::Unit::TestCase

  def setup
    @owner = Person.new('Owner')
    @cat = Cat.new('Cat', @owner)
    @killer = Dog.new('Killer', @owner)
  end

  def test_owner
    dog = Dog.new('Dog', @owner)
    adopter = Person.new('adopter')

    assert_raise(ArgumentError) { dog.owner = '' }
    assert_not_equal(adopter, dog.owner, "Dog owner should not be #{adopter.name}")
    assert_nothing_raised { dog.owner = adopter }
    assert_not_equal(@owner, dog.owner, "Dog owner should not be #{@owner.name}")
  end

  def test_lifes_killer
    dog = Dog.new('Dog', @owner)

    assert_false(dog.dead?)
    assert_nothing_raised { dog.attack(dog) }
    assert_false(dog.dead?)
    assert_nothing_raised { @cat.attack(dog) }
    assert_true(dog.dead?)

    dog = Dog.new('Dog', @owner)

    assert_nothing_raised { @killer.attack(dog) }
    assert_true(dog.dead?)
    assert_match(/dead/, @owner.feed(dog), 'Dog should be dead')
  end

  def test_feeding_petting
    dog = Dog.new('Dog', @owner)
    not_owner = Person.new('Not Owner')

    assert_nothing_raised { @owner.feed(dog) }
    assert_nothing_raised { @owner.pet(dog) }
    assert_nothing_raised { not_owner.feed(dog) }
    assert_nothing_raised { not_owner.pet(dog) }
  end
end