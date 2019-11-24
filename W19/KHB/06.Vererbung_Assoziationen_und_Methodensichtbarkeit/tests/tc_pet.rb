#####################################################################
# Assigment sheet A06: Inheritance, Association, Methods visibility.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'tc'

# pet testcase
class TC_Pet < TC
  def test_owner
    assert_raise(ArgumentError) { @pet.owner = '' }
    assert_not_equal(@person, @pet.owner)
    assert_nothing_raised { @pet.owner = @person }
    assert_equal(@person, @pet.owner)
  end

  def test_lifes_killer
    assert_false(@pet.dead?)
    assert_nothing_raised { @pet.attack(@pet) }
    assert_false(@pet.dead?)
    assert_nothing_raised { @cat.attack(@pet) }
    assert_true(@pet.dead?)
    assert_match(/dead/, @owner.feed(@pet), 'Pet should be dead')
  end

  def test_feeding_petting
    assert_nothing_raised { @owner.feed(@pet) }
    assert_nothing_raised { @owner.pet(@pet) }
    assert_nothing_raised { @person.feed(@pet) }
    assert_nothing_raised { @person.pet(@pet) }
  end
end