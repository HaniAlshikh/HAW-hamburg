#####################################################################
# Assigment sheet A09: Structs und OpenStructs.
#
# experimenting with struct and openstruct.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require 'json'
require 'ostruct'
require_relative 'lib/extend'

# reading the data from a json file
people = JSON.parse(File.open(File.join(__dir__, 'data/people.json')).read, symbolize_names: true)

# experimenting with openstruct
puts "\n> 1.1. Openstruct".green
mike = OpenStruct.new(people[:Mike])
puts mike.name, mike[:age], mike.website, mike.fax, mike.phone
mike[:address] = "address"
puts mike.address

# experimenting with struct
puts "\n> 1.2. Struct".green
Person = Struct.new(:name, :age, :website, :fax, :phone) do
  def to_s
    "name: #{name}, age: #{age}"
  end
end

lara = Person.new(*people[:Lara].values)
puts lara.name, lara[:age], lara.website, lara.fax, lara.phone

puts
mike = Person.new(*people[:Mike].values)
puts mike.name, mike[:age], mike.website, mike.fax, mike.phone

puts
def mike.address
  "adress"
end
puts mike.address
puts mike == lara
puts [mike, lara].max_by(&:age)