#####################################################################
# Assigment sheet A06: Inheritance, Association, Methods visibility.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require 'test/unit'
require_relative '../classes/Cat'
require_relative '../classes/Dog'
require_relative '../classes/Person'

# helper class to distribute #setup and requires across the testcases
class TC < Test::Unit::TestCase
  def setup
    @owner = Person.new('Owner')
    @person = Person.new('Person')
    @pet = Pet.new('Pet', @owner)
    @cat = Cat.new('Cat', @owner)
  end
end