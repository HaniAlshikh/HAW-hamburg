
require 'test/unit'
require_relative '../classes/Cat'
require_relative '../classes/Dog'
require_relative '../classes/Person'

class TC_Cat < Test::Unit::TestCase

  def setup
    @owner = Person.new('Owner')
    @killer = Cat.new('Killer', @owner)
    @dog = Dog.new('Dog', @owner)

  end

  def test_owner
    cat = Cat.new('Cat', @owner)
    adopter = Person.new('Adopter')
    employee = Person.new('Employee')

    assert_raise(ArgumentError) { cat.owner = '' }
    assert_equal(@owner, cat.owner, "Cat owner should be #{@owner.name}")
    assert_nothing_raised { cat.owner = adopter }
    assert_equal(adopter, cat.owner, "Cat owner should be #{adopter.name}")
    assert_equal(Set[@owner, adopter], cat.staff, "Cat staff should be #{@owner.name}, #{adopter.name}")
    assert_nothing_raised { cat.add_employee(employee) }
    assert_nothing_raised { cat.add_employee(employee) }
    assert_equal(Set[@owner, adopter, employee], cat.staff, "Cat staff should be #{@owner.name}, #{adopter.name}, #{employee.name}")
  end

  def test_lifes_killer
    cat = Cat.new('Cat', @owner)

    assert_nothing_raised { cat.attack(cat) }
    assert_equal(9, cat.lifes, "#{cat.name} should have 9 lifes")
    assert_false(cat.dead?)
    assert_raise(NoMethodError) { @dog.attack(cat) }
    assert_nothing_raised { @killer.attack(cat) until cat.dead? }
    assert_true(cat.dead?)
    assert_match(/dead/, @owner.feed(cat), 'Cat should be dead')
  end

  def test_feeding_petting
    cat = Cat.new('Cat', @owner)
    not_owner = Person.new('Not Owner')

    assert_nothing_raised { cat.feed_me(@owner) }
    assert_nothing_raised { cat.pet_me(@owner) }
    assert_nothing_raised { @owner.feed(cat) }
    assert_nothing_raised { @owner.pet(cat) }
    assert_raise(ArgumentError) { not_owner.feed(cat) }
    assert_raise(ArgumentError) { not_owner.pet(cat) }
    assert_raise(ArgumentError) { cat.feed_me(not_owner) }
    assert_raise(ArgumentError) { cat.pet_me(not_owner) }
  end
end