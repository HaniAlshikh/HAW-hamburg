require_relative 'Pet'

class Cat < Pet

  def initialize(name, employee)
    super(name, employee)
    @lifes = 9
    @staff = [employee]
  end

  def feed_me
    @staff.sample.feed(self)
  end

  def pet_me
    @staff.sample.pet(self)
  end

  def add_employee(employee)
    raise ArgumentError, "#{employee} is not a person" unless employee.is_a?(Person)
    employee.pets << self
    @staff << employee
  end

  def die(killer)
    dead? ? super(killer) : killer.instance_of?(Cat) ? super(killer) : 'only cats can kill cats'
  end

  def eat(employee)
    dead? ? super(employee) : @staff.include?(employee) ? super(employee) : "employee required"
  end

  def relax(employee)
    dead? ? super(employee) : @staff.include?(employee) ? super(employee) : "employee required"
  end

  # alias_method :eql?, :==
  def ==(other)
    other.instance_of?(Cat) ? super(other) : false
  end
end