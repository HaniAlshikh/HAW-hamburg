# frozen_string_literal: true

class Test
  attr_reader :bad, :good, :ugly

  def initialize
    @good = 'good'
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

# this will make no seance as ugly is always nil
puts test.ugly.class
# while this wont cause an exception
puts test.good
# this will, as bad is not yet defined
puts test.bad

# in more complex environment it is farley possible to call the method
# in other way then intended, if by the maker or the user, which
# could also cause NoMethodError for nil class.
# debugging such error is really hard, as the description applies to almost
# every thing.
puts test.update_bad
