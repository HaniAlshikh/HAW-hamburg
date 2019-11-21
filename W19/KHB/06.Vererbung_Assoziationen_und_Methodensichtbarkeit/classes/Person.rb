class Person

  attr_accessor :pets
  attr_reader :name

  def initialize(name)
    @name = name
    @pets = []
  end

  def feed(pet)
    pet.eat(self)
  end

  def pet(pet)
    pet.relax(self)
  end

  alias_method :eql?, :==
  def ==(other)
    return false if other.nil?
    return true if self.equal?(other)
    return false unless other.is_a?(Person)
    [@name, @pets] == [other.name, other.pets]
  end

  def hash
    [@name, @pets, object_id].hash
  end
end