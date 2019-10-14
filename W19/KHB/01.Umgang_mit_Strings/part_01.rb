#####################################################################
# Assigment sheet A01: Dealing with Strings in Ruby.
# Part 01 of the assigment.
#
# this script takes advantage of the deferent methods in ruby to
# handel and process strings.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

puts
puts '####### Assigment sheet A01: Dealing with Strings in Ruby #######'
puts '#######            Part 01 of the assigment               #######'
puts

print('Please enter a string: ')
string = gets.chomp

puts "#{string} reversed: #{string.reverse}"
puts "#{string} upcase: #{string.upcase}"
puts "#{string} downcase: #{string.downcase}"
puts "#{string} Swapped: #{string.swapcase}"

puts "#{string} downcase vocals swapped with a '*': \
#{string.gsub(/[aeiou]/, '*')}"

puts "#{string} consonant swapped with 'e.g. coc': \
#{string.gsub(/[[:alpha:]&&[^aAeEiIoOuU]]/, '\0o\0')}"
