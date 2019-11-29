#####################################################################
# Assigment sheet A05: Data Structures.
#
# small script to demonstrate queue and slack data structures functionality
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'classes/custom_queue'
require_relative 'classes/custom_stack'

queue = CustomQueue.new
stack = CustomStack.new
[1, 2, 3].each { |e| queue.enqueue(e) }
[1, 2, 3].each { |e| stack.push(e) }
puts "Queue: #{queue.peek}"
puts "Stack: #{stack.peek}"
queue.dequeue
stack.pop
puts
puts "Queue: #{queue.peek}"
puts "Stack: #{stack.peek}"
