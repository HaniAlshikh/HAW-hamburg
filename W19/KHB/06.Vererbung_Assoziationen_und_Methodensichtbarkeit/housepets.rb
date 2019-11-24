#####################################################################
# Assigment sheet A06: Inheritance, Association, Methods visibility.
#
# small story, that utilize the code functionality
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'classes/Cat'
require_relative 'classes/Dog'
require_relative 'classes/Person'
require_relative 'lib/extend'
require_relative 'lib/toolbox'

puts "\n#### Mike, Lara and the story of Cat ####".bold.green

puts "\n> Creating new Persons Mike and Lara...".bold.green
puts mike = Person.new('Mike')
puts lara = Person.new('Lara')

puts "\n> giving Mike 2 cats and a 2 dogs...".bold.green
puts dog = Dog.new('Dog', mike)
puts cat = Cat.new('Cat', mike)
puts doggy = Dog.new('Doggy', mike)
puts catty = Cat.new('Catty', mike)
puts
puts mike

puts "\n> lara try to feed Mike's pets...".bold.green
mike.pets.each do |pet|
  puts "#{pet.name}: #{Toolbox.try(ArgumentError) { lara.feed(pet) }}"
end

puts "\n> Mike cats are hungry...".upcase.bold.green
puts "#{cat.name}: #{cat.feed_me(mike)}"
puts "#{catty.name}: #{catty.feed_me(mike)}"

puts "\n> lara adopt cat...".bold.green
puts lara.adopt(cat)

puts "\n> Catty is now jealous and makes lara an employee...".bold.green
puts "New Staff: #{catty.add_employee(lara).map(&:name)*', '}"

puts "\n> cat attacks catty...".bold.green
puts cat.attack(catty)

puts "\n> dog try to revenge for catty...".bold.green
attack = Toolbox.try(NoMethodError) { dog.attack(cat) }
puts attack.is_a?(NoMethodError) ? "Only cats can attack cats" : attack

puts "\n> doggy loves cat...".bold.green
puts doggy.attack(dog)

puts "\n> catty revenge for dog...".bold.green
puts catty.attack(doggy)

puts "\n> cat revenge for doggy...".bold.green
puts cat.attack(catty)

puts "\n> cat and catty fight...".bold.green
until cat.dead? || catty.dead?
  puts catty.attack(cat)
  puts cat.attack(catty)
end

puts "\n> cat feel ashamed and try to kill itself...".bold.green
puts cat.attack(cat)

puts "\n> Mike wants to pet his pets, but...".bold.green
mike.pets.each { |pet| puts "#{pet.name}: #{mike.pet(pet)}" }