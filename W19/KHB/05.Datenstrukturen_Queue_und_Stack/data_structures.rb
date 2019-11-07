require_relative 'classes/custom_queue'

queue = CustomQueue.new
queue.enqueue(5)
puts queue