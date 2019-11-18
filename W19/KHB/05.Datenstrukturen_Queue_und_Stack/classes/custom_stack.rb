#####################################################################
# Assigment sheet A05: Data Structures.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative '../Errors/stack_error'
require_relative '../Errors/push_error'
require_relative '../Errors/pop_error'

# Basic implementation of the data structure: Queue
class CustomStack
  attr_reader :stack

  def initialize
    @stack = []
  end

  # add a new element at the end
  # @param [Object] element objects to be added to the end
  # @raise [EnstackError] if the element is nil
  # @return [Array] stack elements as an array
  def push(element)
    raise PushError, "element can't be nil" if element.nil?
    stack.push(element)
  end

  # removes to first element of the stack
  # @raise [destackError] if the stack is empty
  # @return [Object] the removed element
  def pop
    raise PopError, "poping out an empty stack" if stack.empty?
    stack.pop
  end

  def empty?
    stack.empty?
  end

  # @return [Boolean] true if the the elements are the same
  # and have the same sequence
  def ==(other)
    return false if other.nil?
    return true if self.equal?(other)
    return false unless other.is_a?(CustomStack)
    # this result of making CustomStack objects
    # == to objects of a "Special" CustomStack class if it was to exists
    # but as there is nothing specified about this
    # we decided to go with the convention and make eql? the one
    # responsible for more strict comparision
    return false unless other.respond_to?(:stack)
    other = other.stack
    # empty means all elements are the same
    stack.size == other.size && stack.zip(other).map { |a,b| a unless a == b }.compact.empty?
  end

  # @return [Boolean] true if the objects descend from the same direct class
  def eql?(other)
    # this could be achieved by multiple solutions for example:
    return false if self.class != other.class
    # or
    # return false unless instance_variables.sort == other.instance_variables.sort
    self == other
  end

  def hash
    stack.hash
  end
end