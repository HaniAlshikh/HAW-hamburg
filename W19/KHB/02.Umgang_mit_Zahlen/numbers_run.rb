#####################################################################
# Assigment sheet A02: Dealing with numbers in Ruby.
#
# this script do
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################


# expand the string class to support green output on unix machines
class String
  def green; "\e[32m#{self}\e[0m" end
end


# sum all integer array elements
def sum(numbers)
  # make sure we have an array and get only the numeric elements
  [*numbers].grep(Numeric).inject(0, :+)
end


# multiply all integer array elements
def pro(numbers)
  # make sure we have an array and get only the numeric elements
  [*numbers].grep(Numeric).inject(1, :*)
end


# test collatz conjecture return starting integer & cycle starting point
def collatz(start_int)
  return [start_int,0] unless start_int.is_a?(Integer) && start_int > 0

  cycle = [1,4,2,1]
  sequence = []

  until (cycle - sequence).empty?
    sequence << if start_int.even?
                  start_int /= 2
                else
                  start_int = 3*start_int + 1
                end
  end

  [sequence[0]*2, sequence.index(sequence[-1])]
end


# approximate pi value based on leibniz formula
def approx_pi(accuracy)
  return [0, "0/1"] unless accuracy.is_a?(Integer) && accuracy > 0

  values = []
  accuracy.times { |k| values << ((-1)**k.to_f / ((2 * k) + 1)) }
  sum = sum(values)

  [sum, sum.rationalize]
end


# approximate one using ?
def approx_one(accuracy)
  return [0,[0]] unless accuracy.is_a?(Integer) && accuracy >= 2

  values = []
  (2..accuracy).each do |k|
    approx = ((k).to_f - 1) / (pro(1..(k)))
    values << approx unless approx == 0 && break
  end

  [sum(values), values]
end


# multiply two numbers ancient egyptian way
def egyptians_pro(a, b)
  # make sure input and order are correct
  return 0 unless a.is_a?(Integer) && b.is_a?(Integer)
  a < b ? (values = [[a,b]]) : (values = [[b,a]])

  # create an array of two arrays (l_column, r_column)
  loop.with_index do |args, i|
    break if values[-1][0] == 1
    values << [values[i][0]/2, values[i][1]*2]
  end

  sum(values.select { |num| num[0].odd? }.map { | num | num[1] })
end


puts
puts '####### Assigment sheet A02: Dealing with Strings in Ruby #######'.green
puts

puts "1. Sum Method".green
puts "Total sum: #{sum([10,20,30,"test", true, :test])}"
puts

puts "2. pro Method".green
puts "Total pro: #{pro([10,20,30,"test", true, :test])}"
puts


# 3. working with mathematical formulas
puts "3.1. collatz conjecture".green
collatz_result = collatz(1000000)
puts "you started with #{collatz_result[0]} and the sequence broke at #{collatz_result[1]}"
puts

puts "3.2. PI approximation using leibniz formula".green
pi = Math::PI
pi_a = approx_pi(1000_000)
puts "Pi Value     #{pi/4}, fraction: #{(pi/4).rationalize}"
puts "Approximated #{pi_a[0]}, fraction: #{pi_a[1]}"
puts "Pi Value     #{'%.16f' % pi}, fraction: #{pi.rationalize}"
puts "Approximated #{pi_a[0]*4}, fraction: #{pi_a[1]*4}"
puts

puts "3.3. 1 approximition".green
approx_one = approx_one(300)
puts "your approximation of 1:  #{approx_one[0]}, fraction: #{approx_one[0].rationalize}"
puts "achived at: #{approx_one[1].index(approx_one[1][-1])}"
p approx_one[1]
puts

puts "3.4. ancient egyptian multiplyication".green
a, b = 10, 20
puts "#{a} * #{b} = #{egyptians_pro(a, b)}"