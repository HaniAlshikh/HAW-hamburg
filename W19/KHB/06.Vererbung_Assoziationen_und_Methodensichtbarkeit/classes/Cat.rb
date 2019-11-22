require_relative 'Pet'
require 'set'

class Cat < Pet
  attr_reader :staff

  def initialize(name, employee)
    @staff = Set[employee]
    super(name, employee)
    @lifes = 9
  end

  def feed_me(employee)
    employee.feed(self) if pet_condition(employee)
  end

  def pet_me(employee)
    employee.pet(self) if pet_condition(employee)
  end

  def add_employee(employee)
    raise ArgumentError, "#{employee} is not a person" unless employee.is_a?(Person)
    employee.adopt(self)
    @staff << employee
  end

  def to_s
    super + "#{"\n  Staff: #{@staff.map(&:name)*', '}" unless @staff.empty?}"
  end

  def owner=(owner)
    @staff << owner if check_person?(owner)
    super(owner)
  end

  protected

  def die(killer)
    super(killer)
  end

  def pet_condition(employee)
    @staff.include?(employee) ? true : (raise ArgumentError, "employee required")
  end
end