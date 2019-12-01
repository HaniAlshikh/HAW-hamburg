#####################################################################
# Assigment sheet A07: Examples and Partlist.
#
# Example 03: defining a symbol with string interpolation
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

test = "test"
# not possible as #{} will call to_s on what ever in it
puts "#{:test}".class
# we could how ever interpolate than convert to symbol
puts :"#{test}".class