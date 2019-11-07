require_relative '../Errors/queue_error'
require_relative '../Errors/enqueue_error'
require_relative '../Errors/dequeue_error'

class CustomQueue

  def initialize
    @queue = []
  end

  def enqueue(element)
    raise EnqueueError, "element can't be nil" if element.nil?
    # @queue.push(element) oder
    queue.push(element) # ?
  end

  def dequeue
    raise DequeueError, "dequeing out of an empty queue" if queue.empty?
    queue.shift
  end

  def empty?
    queue.empty?
  end

  def ==(other)
    other = other.queue
    queue.size == other.size && queue.zip(other).map { |a,b| a unless a == b }.compact.empty?
  end

  def to_s
    queue.to_s
  end

  protected
  attr_accessor :queue
end