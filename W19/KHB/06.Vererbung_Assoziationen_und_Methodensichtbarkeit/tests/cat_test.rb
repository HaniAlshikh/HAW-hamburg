
require 'test/unit'
require_relative '../classes/Cat'
require_relative '../classes/Dog'
require_relative '../classes/lion'
require_relative '../classes/Person'

class CatTest < Test::Unit::TestCase

  def setup
    @owner = Person.new('Owner')
  end

  def test_lifes
    cat = Cat.new('Cat', @owner)
    killer = Cat.new('Killer', @owner)

    killer.kill(cat)
    assert_false(cat.dead?)

    killer.kill(cat) until cat.dead?
    assert_true(cat.dead?)

    assert_equal("Cat is dead", killer.kill(cat), 'Cat should be dead')
  end

  def test_killer
    cat = Cat.new('Cat', @owner)
    dog = Dog.new('Dog', @owner)
    lion = Lion.new('Lion', @owner)
    killer = Cat.new('Killer', @owner)

    cat.kill(cat)
    assert_false(cat.dead?)
    dog.kill(cat)
    assert_false(cat.dead?)
    killer.kill(cat) until cat.dead?
    assert_true(cat.dead?)
    lion.kill(dog)
    assert_true(dog.dead?)
  end

  def test_feeding_petting

    cat = Cat.new('Cat', @owner)
    not_owner = Person.new('Not Owner')

    assert_match(/eating/, cat.feed_me)
    assert_match(/letting/, cat.pet_me)
    assert_match(/eating/, @owner.feed(cat))
    assert_match(/letting/, @owner.pet(cat))
    assert_match(/required/, not_owner.feed(cat))
    assert_match(/required/, not_owner.pet(cat))
  end

end