#####################################################################
# Assigment sheet A07: Examples and Parts List.
#
# Example 01: deference between alias and alias_method
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

class SuperClass
  def where_am_i
    "in SuperClass"
  end

  def self.alias_location
    alias location where_am_i
  end

  def self.alias_method_location
    alias_method :location, :where_am_i
  end
end

class SubClass < SuperClass
  def where_am_i
    "in SubClass"
  end

  def if_alias
    self.class.alias_location
  end

  def if_alias_method
    self.class.alias_method_location
  end
end

find_me = SubClass.new

# location alias/method not yet defined
puts find_me.location rescue nil

# Neeraj Singh: "alias treats self as the value of self at the time the source code was read"
find_me.if_alias # meaning self is wherever alias is explicitly written, in this case SuperClass
puts find_me.location

$alias = "Alias works with constants as well"
alias $flexible $alias
puts $flexible

# Neeraj Singh: "alias_method treats self as the value determined at the run time."
find_me.if_alias_method # meaning self is wherever #alias_method is called from, in this case SubClass
puts find_me.location
