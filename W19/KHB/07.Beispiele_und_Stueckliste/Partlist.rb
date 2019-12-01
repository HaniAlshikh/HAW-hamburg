#####################################################################
# Assigment sheet A07: Examples and Partlist.
#
# this script displays the data structure "Partlist" functionality
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'classes/part'
require_relative 'modules/partlist'
require_relative 'lib/extend'
require 'yaml'

bicycle_blueprint = YAML.load_file(File.join(__dir__, 'partlists/Bicycle.yml')).freeze
bicycle = Partlist.generate(bicycle_blueprint)

puts "\n#### Partlist data structure functionality overview ####\n\n".bold.green

puts '> 6.1. generating a Partlist'.green
Partlist.output(bicycle)
puts

puts '> 6.2. the top part and the total wight'.green
puts bicycle.back_set.wheel.spokes.top
puts

puts '> 6.3. total count'.green
puts "#{bicycle} total count: #{Partlist.count_parts(bicycle)} parts"

puts "\n############ Bonus ############\n\n".bold.green

puts '> 6.4. removing elements'.green
puts bicycle.remove(bicycle.back_set)
puts bicycle.remove(bicycle.front_set.wheel)
puts bicycle.remove(bicycle.frame.top_tube)
puts
Partlist.output(bicycle)
puts

puts '> 6.5. changing whole (moving) '.green
part = bicycle.front_set.handlebar_grip
puts "changing #{part} whole to saddle_area (moving to)"
puts part.whole = bicycle.saddle_area
puts
Partlist.output(bicycle)
puts

puts '> 6.6. replacing'.green
puts 'create a new partlist car'
puts car = Part.new('Car')
puts "\nadding some parts to car"
car.add(Part.new('engine', mass: 400))
car.add(Part.new('frame', mass: 400))
Partlist.output(car)
puts
puts 'replace Front Set with the car'
puts bicycle.replace(bicycle.front_set, car)
puts
Partlist.output(bicycle)