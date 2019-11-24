#####################################################################
# Assigment sheet A06: Inheritance, Association, Methods visibility.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'Pet'
require 'set'

# simulate a Cat in a basic level
class Cat < Pet
  attr_reader :staff

  def initialize(name, employee)
    @staff = Set[employee]
    super(name, employee)
    @lifes = 9
  end

  # @param [Person] employee
  # @raise [ArgumentError] if not employee
  # @return [String]
  def feed_me(employee)
    employee.feed(self) if pet_condition(employee)
  end

  # @param [Person] employee
  # @raise [ArgumentError] if not employee
  # @return [String]
  def pet_me(employee)
    employee.pet(self) if pet_condition(employee)
  end

  # @param [Person] employee
  # @raise [ArgumentError] if not a Person
  # @return [Set] staff
  def add_employee(employee)
    check_person?(employee)
    employee.adopt(self)
    @staff << employee
  end

  def to_s
    super + "#{"\n  Staff: #{@staff.map(&:name) * ', '}" unless @staff.empty?}"
  end

  def owner=(owner)
    @staff << owner if check_person?(owner)
    super(owner)
  end

  protected

  # only cats can kill cats
  def die(killer)
    super(killer)
  end

  # @raise [ArgumentError] if not employee
  def pet_condition(employee)
    @staff.include?(employee) ? true : (raise ArgumentError, "employee required")
  end
end