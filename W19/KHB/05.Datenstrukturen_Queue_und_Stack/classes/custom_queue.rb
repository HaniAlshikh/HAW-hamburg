#####################################################################
# Assigment sheet A05: Data Structures.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative '../Errors/queue_error'
require_relative '../Errors/enqueue_error'
require_relative '../Errors/dequeue_error'

# Basic implementation of the data structure: Queue
class CustomQueue
  attr_reader :queue

  def initialize
    @queue = []
  end

  # add a new element at the end
  # @param [Object] element objects to be added to the end
  # @raise [EnqueueError] if the element is nil
  # @return [Array] queue elements as an array
  def enqueue(element)
    raise EnqueueError, "element can't be nil" if element.nil?
    queue.push(element)
  end

  # removes to first element of the queue
  # @raise [dequeueError] if the queue is empty
  # @return [Object] the removed element
  def dequeue
    raise DequeueError, 'dequeing out an empty queue' if queue.empty?
    queue.shift
  end

  def empty?
    queue.empty?
  end

  # @return [Boolean] true if the the elements are the same
  # and have the same sequence
  def ==(other)
    return false if other.nil?
    return true if self.equal?(other)
    return false unless other.is_a?(CustomQueue)
    # this result of making CustomQueue objects
    # == to objects of a "Special" CustomQueue class if it was to exists
    # but as there is nothing specified about this
    # we decided to go with the convention and make eql? the one
    # responsible for more strict comparision
    return false unless other.respond_to?(:queue)
    other = other.queue
    # empty means all elements are the same
    queue.size == other.size && queue.zip(other).map { |a,b| a unless a == b }.compact.empty?
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
    queue.hash
  end
end