#####################################################################
# Assigment sheet A06: Inheritance, Association, Methods visibility.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'tc'

# cat testcase
class TC_Cat < TC
  def setup
    super()
    @employee = Person.new('Employee')
  end

  def test_owner
    assert_raise(ArgumentError) { @cat.owner = '' }
    assert_equal(@owner, @cat.owner)
    assert_nothing_raised { @cat.owner = @person }
    assert_equal(@person, @cat.owner)
    assert_equal(Set[@owner, @person], @cat.staff)
    2.times { assert_nothing_raised { @cat.add_employee(@employee) } }
    assert_equal(Set[@owner, @person, @employee], @cat.staff)
  end

  def test_lifes_killer
    assert_nothing_raised { @cat.attack(@cat) }
    assert_false(@cat.dead?)
    assert_raise(NoMethodError) { @pet.attack(@cat) }
    assert_nothing_raised { Cat.new('Killer', @owner).attack(@cat) until @cat.dead? }
    assert_true(@cat.dead?)
    assert_match(/dead/, @owner.feed(@cat), 'Cat should be dead')
  end

  def test_feeding_petting
    assert_nothing_raised { @cat.feed_me(@owner) }
    assert_nothing_raised { @cat.pet_me(@owner) }
    assert_nothing_raised { @owner.feed(@cat) }
    assert_nothing_raised { @owner.pet(@cat) }
    assert_raise(ArgumentError) { @person.feed(@cat) }
    assert_raise(ArgumentError) { @person.pet(@cat) }
    assert_raise(ArgumentError) { @cat.feed_me(@person) }
    assert_raise(ArgumentError) { @cat.pet_me(@person) }
  end
end