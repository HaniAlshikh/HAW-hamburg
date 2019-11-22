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

  def check_pet?(pet)
    pet.is_a?(Pet) ? true : (raise ArgumentError, "#{pet} musst be a pet")
  end

  def check_person?(person)
    person.is_a?(Person) ? true : (raise ArgumentError, "#{person} musst be a person")
  end

  protected

  def die(killer)
    return dead if dead?
    return "#{@name} can't kill itself" if killer == self
    @lifes -= 1
    dead? ? "#{@name} died" : "#{@name} survived (#{@lifes} lifes left)"
  end

  def sate(person)
    pet_condition(person) if defined?(self.pet_condition)
    dead? ? dead : "i'm full"
  end

  def relax(person)
    pet_condition(person) if defined?(self.pet_condition)
    dead? ? dead : "i'm relaxed"
  end

  private

  def dead
    "#{@name} is dead"
  end
end