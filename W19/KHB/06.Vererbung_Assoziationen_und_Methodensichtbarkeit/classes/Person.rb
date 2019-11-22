require_relative '../classes/nature'
require 'set'

class Person < Nature

  attr_reader :pets

  def initialize(name)
    super(name)
    @pets = Set.new
  end

  def adopt(pet)
    @pets << pet if check_pet?(pet)
    pet.owner = self
  end

  def feed(pet)
    pet.sate(self) if check_pet?(pet)
  end

  def pet(pet)
    pet.relax(self) if check_pet?(pet)
  end

  def to_s
    "#{self.class}: #{@name}" +
    "#{"\n  Pets: #{@pets.map(&:name)*', '}" unless @pets.empty?}"
  end
end