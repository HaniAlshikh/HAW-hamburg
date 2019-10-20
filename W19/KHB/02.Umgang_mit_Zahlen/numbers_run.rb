#####################################################################
# Assigment sheet A02: Dealing with numbers in Ruby.
#
# this script utilize various formulas located in a separate module
# to calculate/approximate various values
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'modules/formula'

# expand the string class to support green output on unix machines
class String
  def green; "\e[32m#{self}\e[0m" end
end


puts
puts '####### Assigment sheet A02: Dealing with Numbers in Ruby #######'.green
puts

# 01. & 02. basic sum & pro Methods
puts "1. Sum Method".green
puts "Total sum: #{Formula::sum([10,20,30,"test", true, :test])}"
puts

puts "2. pro Method".green
puts "Total pro: #{Formula::pro([10,20,30,"test", true, :test])}"
puts


# 3. working with mathematical formulas
puts "3.1. collatz conjecture".green
collatz_result = Formula::collatz(63_728_127)
puts "you started with #{collatz_result[0]} and the sequence broke at index: #{collatz_result[1]}"
puts


puts "3.2. PI approximation using leibniz formula".green
pi = Math::PI
puts "Pi Value     #{pi/4}, fraction: #{(pi/4).rationalize}"
Formula::approx_pi(1e-6) { |approx| print "\rApproximated #{approx}, fraction: #{approx.to_r}" }#1e-6)
puts; puts


puts "3.3. 1 approximition".green
one_approx = Formula::approx_one
puts "your approximation of 1:  #{one_approx[0]}, fraction: #{one_approx[0].rationalize}"
puts "achived at index: #{one_approx[1].index(one_approx[1][-1])} and the calculated values are:"
p one_approx[1]
puts


puts "3.4. ancient egyptian multiplyication".green
x, y = 100, -50
puts "#{x} * #{y} = #{Formula::egyptian_multiplication(x, y)}"