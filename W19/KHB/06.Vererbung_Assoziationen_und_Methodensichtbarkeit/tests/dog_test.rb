
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

  def test_lifes
    dog = Dog.new('Dog', @owner)

    @killer.kill(dog)
    assert_true(dog.dead?)

    assert_equal("Dog is dead", @killer.kill(dog), 'Cat should be dead')
  end

  def test_killer
    dog = Dog.new('Dog', @owner)
    @cat.kill(dog)
    assert_true(dog.dead?)
    dog = Dog.new('Dog', @owner)
    dog.kill(dog)
    assert_false(dog.dead?)
    @killer.kill(dog)
    assert_true(dog.dead?)
  end

  def test_feeding_petting

    dog = Dog.new('Dog', @owner)
    not_owner = Person.new('Not Owner')

    assert_match(/eating/, @owner.feed(dog))
    assert_match(/letting/, @owner.pet(dog))
    assert_match(/eating/, not_owner.feed(dog))
    assert_match(/letting/, not_owner.pet(dog))
  end
end