#####################################################################
# Assigment sheet A03: Unit Converter in Ruby.
#
# tiny library to extend classes functionality
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

# extend the string class to support basic output manipulation
class String
  # Output green colored string
  def green; "\e[32m#{self}\e[0m" end
  # Output red colored string
  def red; "\e[31m#{self}\e[0m" end
  # Output bold string
  def bold; "\e[1m#{self}\e[22m" end
end

# extend the Float class to support basic output manipulation
class Float
  # convert to int if it's a whole number.
  def prettify
    to_i == self ? to_i : self
  end
end