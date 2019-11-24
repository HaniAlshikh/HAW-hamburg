#####################################################################
# Assigment sheet A06: Inheritance, Association, Methods visibility.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require 'test/unit'
require_relative 'tc_pet'
require_relative 'tc_person'
require_relative 'tc_cat'

# Pets TestSuite
class TS_Pets
  def self.suite
    suite = Test::Unit::TestSuite.new('Pets TestSuite')
    suite << TC_Pet.suite
    suite << TC_Person.suite
    suite << TC_Cat.suite
  end
end