#####################################################################
# Assigment sheet A06: Inheritance, Association, Methods visibility.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

# simulate nature in a basic level
class Nature
  attr_accessor :name
  attr_reader :born_date, :lifes

  def initialize(name)
    @name = name
    @lifes = 1
    @born_date = Time.now
  end

  def dead?
    @lifes.zero?
  end

  # @param [Object] pet
  # @raise [ArgumentError] if Object not a Pet
  # @return [TrueClass] if Object is a Pet
  def check_pet?(pet)
    pet.is_a?(Pet) ? true : (raise ArgumentError, "#{pet} musst be a pet")
  end

  # @param [Object] person
  # @raise [ArgumentError] if Object not a Person
  # @return [TrueClass] if Object is a Person
  def check_person?(person)
    person.is_a?(Person) ? true : (raise ArgumentError, "#{person} musst be a person")
  end

  protected

  # substrat 1 life out of the lifes total
  # @param [Nature]
  # @return [String]
  def die(killer)
    return dead if dead?
    return "#{@name} can't kill itself" if killer == self
    @lifes -= 1
    dead? ? "#{@name} died" : "#{@name} survived (#{@lifes} lifes left)"
  end

  # @return [String]
  def sate(person)
    pet_condition(person) if defined?(pet_condition)
    dead? ? dead : "i'm full"
  end

  # @return [String]
  def relax(person)
    pet_condition(person) if defined?(pet_condition)
    dead? ? dead : "i'm relaxed"
  end

  private

  def dead
    "#{@name} is dead"
  end
end