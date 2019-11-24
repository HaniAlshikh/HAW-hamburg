#####################################################################
# Assigment sheet A06: Inheritance, Association, Methods visibility.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative '../classes/nature'

# simulate a Pet in a basic level
class Pet < Nature
  attr_reader :owner

  def initialize(name, owner)
    super(name)
    self.owner = owner
  end

  # @param [Pet] pet to be attacked
  def attack(pet)
    pet.die(self)
  end

  def to_s
    "#{self.class}: #{@name}"\
    ", Born Date: #{@born_date}"
  end

  # @return [Person] owner
  def owner=(owner)
    @owner = owner if check_person?(owner)
    @owner.adopt(self) unless @owner.pets.include?(self)
  end
end