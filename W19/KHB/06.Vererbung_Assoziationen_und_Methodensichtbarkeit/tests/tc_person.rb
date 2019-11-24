#####################################################################
# Assigment sheet A06: Inheritance, Association, Methods visibility.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'tc'

# person testcase
class TC_Person < TC
  def test_adopt
    assert_raise(ArgumentError) { @owner.adopt('') }
    assert_equal(Set[@pet, @cat], @owner.pets)
    2.times { assert_nothing_raised { @person.adopt(@pet) } }
    assert_equal(Set[@pet], @person.pets)
  end

  def test_feed
    assert_nothing_raised { @person.feed(@pet) }
    assert_raise(ArgumentError) { @person.feed(@cat) }
  end

  def test_pet
    assert_nothing_raised { @person.pet(@pet) }
    assert_raise(ArgumentError) { @person.pet(@cat) }
  end
end