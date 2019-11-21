class Pet

  attr_reader :born_date
  attr_accessor :name

  def initialize(name, owner)
    @name = name
    @born_date = Time.now
    @owner = owner
    @lifes = 1
    owner.pets << self
  end

  def kill(pet)
    pet.dead? ? "#{pet.name} is dead" : pet.die(self)
  end

  def dead?
    @lifes.zero?
  end

  alias_method :eql?, :==
  def ==(other)
    self.equal?(other)
  end

  def hash
    [@name, @born_date, @lifes, @owner].hash
  end

  def to_s
    "#{@name} was born on the #{@born_date}"
  end

  # protected

  def die(killer)
    return dead if dead?
    return "#{@name} can't kill itself" if killer == self
    @lifes -= 1
    dead? ? "#{@name} died" : "#{@name} survived (#{@lifes} lifes left)"
  end

  def eat(person)
    dead? ? dead : "#{@name} eating from #{person.name}"
  end

  def relax(person)
    dead? ? dead : "#{@name} letting #{person.name} pet it"
  end

  private

  def dead
    "#{@name} is dead"
  end
end