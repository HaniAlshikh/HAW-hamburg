#####################################################################
# Assigment sheet A08: Natural Order and Christmas Tree Pattern.
#
# small demonstration of the NaturalOrder class functionality
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'classes/natural_order'
require_relative 'modules/tree'
require_relative 'lib/extend'

puts "\n> Ranges".green
x = NaturalOrder.new(1)..NaturalOrder.new(7)
p "n = 0, #{x}: #{x.to_a}"
x = NaturalOrder.new(1, 1)..NaturalOrder.new(7, 1)
p "n = 1, #{x}: #{x.to_a}"
x = NaturalOrder.new(1, 2)..NaturalOrder.new(7, 2)
p "n = 2, #{x}: #{x.to_a}"
x = NaturalOrder.new(1, 1)..NaturalOrder.new(7, 2)
p "n = 1, #{x}: #{x.to_a}"

puts "\n> Tree".green
puts "Node: #{x = NaturalOrder.new(1, 3)}"
puts "Parent: #{x.parent}"
puts "Children: [#{x.children * ' '}]"
puts 'Tree:'
puts x.to_s.center(x.family[-1].join(' ').length, ' ')
Tree.visualize(x.family)