#####################################################################
# Assigment sheet A06: Inheritance, Association, Methods visibility.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative '../classes/nature'
require 'set'

# simulate a Person/Human in a basic level
class Person < Nature
  attr_reader :pets

  def initialize(name)
    super(name)
    @pets = Set.new
  end

  # @param [Pet] pet to adopt
  # @raise [ArgumentError] if not Pet
  # @return [Person] adopter object
  def adopt(pet)
    @pets << pet if check_pet?(pet)
    pet.owner = self
  end

  # @param [Pet] pet to feed
  # @raise [ArgumentError] if not Pet
  # @return [String] pet reaction
  def feed(pet)
    pet.sate(self) if check_pet?(pet)
  end

  # @param [Pet] pet to pet
  # @raise [ArgumentError] if not Pet
  # @return [String] pet reaction
  def pet(pet)
    pet.relax(self) if check_pet?(pet)
  end

  def to_s
    "#{self.class}: #{@name}" \
    "#{"\n  Pets: #{@pets.map(&:name) * ', '}" unless @pets.empty?}"
  end
end