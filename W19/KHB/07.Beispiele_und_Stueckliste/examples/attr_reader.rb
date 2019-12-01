#####################################################################
# Assigment sheet A07: Examples and Partlist.
#
# Example 02: why not to use attr_reader to define attributes
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

class Test
  attr_reader :bad, :good, :ugly, :protected_list

  def initialize
    @good = 'good'
    @protected_list = []
  end

  def testing
    @bad = 'bad'
  end

  def change_bad
    bad + 'test'
  end

  def update_bad
    change_bad
  end
end

test = Test.new

# attr_reader exposes the attribute reference, which in many cases
# allows for object modification.
puts test.protected_list << "exposed"

# this will make no seance as ugly is always nil
puts test.ugly.class
# while this wont cause an exception
puts test.good
# this will, as bad is not yet defined
puts test.bad

# in more complex environment it is farley possible to call the method
# in another way then intended, if by maker or user, which
# could also cause NoMethodError for nil class.
# debugging such error is really hard, as the description applies to almost
# every thing.
puts test.update_bad
