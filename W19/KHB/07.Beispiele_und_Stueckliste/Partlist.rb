require_relative 'classes/part'
require 'yaml'

# ist das, was mit der Konstruktion Stücklisten gemeint (05.)?
# in diesem Fall YAML -> hash -> Teil objekte
def partlist(hash)
  partlist = Part.new(hash.keys[0])
  hash.each do |_, parts|
    parts.each { |sub_hash| partlist.add(partlist(sub_hash)) } if parts.is_a?(Array)
    partlist.mass = parts if parts.is_a?(Numeric)
  end
  partlist
end

def partlist_parts_counter(partlist)
  count = 0
  partlist.each do |part|
    count += part.parts.empty? ? 1 : partlist_parts_counter(part)
  end
  count
end

# wer kümmert sich um die eingerückte Ausgabe? das Objekt oder der Nutzer?
def nesting(part)
  nesting = 0
  until part.whole == part.top
    part = part.whole
    nesting += 1
  end
  nesting
end

def output(partlist)
  partlist.each do |part|
    puts("\t"*(nesting(part) + 1) + part.to_s)
    output(part) unless part.parts.empty?
  end
end

bicycle_blueprint = YAML.load_file(File.join(__dir__, 'partlists/Bicycle.yml')).freeze

bicycle = partlist(bicycle_blueprint)

puts "#{bicycle} Part Count: #{partlist_parts_counter(bicycle)}"
output(bicycle)
puts

# ist das was mit top gemeint (02. Lesende)?
puts bicycle.parts[3].parts[0].top
puts

puts bicycle.remove(bicycle.parts[3].parts[2])
puts
puts "#{bicycle.parts[3]} Part Count: #{partlist_parts_counter(bicycle.parts[3])}"
output(bicycle.parts[3])
puts
puts bicycle
puts
puts bicycle.front_set.handlebar_grip.whole = bicycle.saddle_area
puts
output(bicycle)
puts


puts car = Part.new('Car', 1000)
puts

car.add(Part.new('engine', 400))
car.add(Part.new('frame', 400))
puts "#{car} Part Count: #{partlist_parts_counter(car)}"
output(car)
puts

puts bicycle.replace(bicycle.parts[2], car)
puts bicycle.replace(bicycle.parts[0].parts[4], car)
puts
puts "#{bicycle} Part Count: #{partlist_parts_counter(bicycle)}"
output(bicycle)
puts


bicycle.parts[0].parts[0].whole = bicycle.parts[2]
puts "#{bicycle} Part Count: #{partlist_parts_counter(bicycle)}"
output(bicycle)
puts