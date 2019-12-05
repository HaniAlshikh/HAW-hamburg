

require_relative 'modules/tree'

christmas_tree = Tree.christmas(6)
center = christmas_tree[-1].join(" ").length

puts
puts "\\ /".center(center, " ")
puts "-->*<--".center(center, " ")
puts "/ \\".center(center, " ")
Tree.visualize(christmas_tree)
(christmas_tree.size / 3).times { puts christmas_tree.last.first.center(christmas_tree[-1].join(" ").length, " ") }
puts;puts
puts "Frohe Weihnachten".center(center, " ")