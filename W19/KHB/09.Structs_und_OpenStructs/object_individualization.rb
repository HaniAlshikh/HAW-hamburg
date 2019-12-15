#####################################################################
# Assigment sheet A09: Structs und OpenStructs.
#
# experimenting with Object Individualization.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'lib/extend'

puts "\n> 2.1. Object Individualization".green
Student = Struct.new(:first_name, :last_name)
Prof = Struct.new(:first_name, :last_name)

people = [
  Student.new('Tom', 'Batcher'),
  Student.new('Omar', 'Ballweg'),
  Prof.new('Bong', 'Schwarz'),
  Prof.new('Dewey', 'Campas')
]

people.each do |person|
  # each object is created with a singleton class that it knows about
  puts "singleton class: #{person.singleton_class}",
  # the superclass of the singleton class is the class of the object itself,
  # which is, in this case, a subclass of struct class
       "Superclass     : #{person.singleton_class.superclass} #{person.singleton_class.superclass.object_id}",
  # that is why we see the same class twice (singleton class and class)
       "Ancestors      : #{person.singleton_class.ancestors}\n\n"
end

puts "BasicObject singleton class: #{BasicObject.singleton_class}"
puts "Super of singleton class of BasicObject: #{BasicObject.singleton_class.superclass}"
puts "Super of singleton class of BasicObject: #{BasicObject.singleton_class.ancestors}"

puts "\n> 2.2. Singleton Methods".green

foo = Struct.new(:bar).new('bar')
# this method can simply define a method
def foo.first
  'first method'
end
puts foo.first

# while this method "opens up" the singleton class of foo and adds what ever we write
class << foo
  include Enumerable
  BAR = 'second method'

  def second
    BAR
  end
end
puts foo.second