require_relative '../classes/nature'

class Pet < Nature

  attr_reader :owner

  def initialize(name, owner)
    super(name)
    self.owner = owner
  end

  def attack(pet)
    pet.die(self)
  end

  def to_s
    "#{self.class}: #{@name}"\
    ", Born Date: #{@born_date}"
  end

  def owner=(owner)
    @owner = owner if check_person?(owner)
    # unless to break the loop
    @owner.adopt(self) unless @owner.pets.include?(self)
  end
end