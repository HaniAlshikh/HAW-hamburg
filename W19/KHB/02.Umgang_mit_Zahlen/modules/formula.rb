#####################################################################
# Assigment sheet A02: Dealing with numbers in Ruby.
#
# this module contains the various Methods and formulas required
# for the second assigment
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

module Formula
  # Sum all numeric elements of an array.
  # nested arrays and other types are ignored
  # @param [Array, Numeric, Range] numbers Numbers array
  # @return [Integer]
  def self.sum(numbers)
    # make sure we have an array and get only the numeric elements
    [*numbers].grep(Numeric).inject(0, :+)
  end


  # Multiply all numeric elements of an array
  # nested arrays and other types are ignored
  # @param [Array, Numeric, Range] numbers Numbers array
  # @return [Integer]
  def self.pro(numbers)
    # make sure we have an array and get only the numeric elements
    [*numbers].grep(Numeric).inject(1, :*)
  end


  # Test collatz conjecture
  # @param [Integer] base_int Base integer
  # @return [[Integer], [Integer]] base integer, cycle starting index
  def self.collatz(base_int)
    # rase an error if argument did't pass the requirements
    raise ArgumentError, "please pass an integer >= 0" unless base_int.is_a?(Integer) && base_int >= 0

    cycle, sequence = [1,4,2,1], [base_int]

    # break if subtracting the cycle array from the sequence
    # array resulted in an empty array => cycle started
    until (cycle - sequence).empty?
      sequence << if base_int.even?
                    base_int /= 2
                  else
                    base_int = 3*base_int + 1
                  end
    end

    [sequence[0], sequence.index(sequence[-1])]
  end


  # approximate pi value using leibniz formula with an accuracy of Epsilon
  # @param [Float] epsilon
  # @yield [Float]
  # @return [[Float], [Rational]]
  def self.approx_pi(epsilon = Float::EPSILON)
    # rase an error if argument did't pass the requirements
    raise ArgumentError, "please pass a float > 0" unless epsilon.is_a?(Float) && epsilon > 0

    k = approx = 0.0
    begin
      last_approx = approx
      # calculate the approximation according to the formula
      approx +=  ((-1)**k / ((2 * k) + 1)) and k += 1
      # yield the approximation for stdout manipulation
      yield approx
      # exit the loop if we reached the desired approximation
    end while (approx - last_approx).abs > epsilon

    [approx, approx.to_r]
  end


  # approximate one with an accuracy of Epsilon
  # @param [Float] epsilon
  # @return [[Float], [Array]]
  def self.approx_one(epsilon = Float::EPSILON)
    # rase an error if argument did't pass the requirements
    raise ArgumentError, "please pass a float > 0" unless epsilon.is_a?(Float) && epsilon > 0

    values, approx, k = [], 0.0, 2.0
    begin
      last_approx = approx
      # calculate the approx. according to the formula and save them to be returned
      values << approx += (k - 1) / (pro(1..k)) and k += 1
      # exit the loop if we reached the desired approximation
    end while (approx - last_approx).abs > epsilon

    [approx, values]
  end


  # multiply two numbers ancient egyptian way
  # @param [Integer] x First Number
  # @param [Integer] y Second Number
  # @return [[Float], [Array]]
  def self.egyptian_multiplication(x, y)
    # rase an error if arguments did't pass the requirements
    raise ArgumentError, "please pass two integers" unless x.is_a?(Integer) && y.is_a?(Integer)

    # check if the result should be positiv or negativ
    sign = (x.negative? || y.negative?) && ! (x.negative? && y.negative?) ? -1 :  1
    # create a two dimensional array [[l_column, r_column]]
    values = [[x.abs,y.abs]]

    # calculate tell the l_column of the last array = 1
    loop.with_index do |_, i|
      break if values[-1][0] == 1
      values << [values[i][0]/2, values[i][1]*2]
    end

    # check if the l_column value is odd and create an array of the r_column value
    # send the resulting array to our sum method and multiply the total with +1/-1
    sum(values.select { |num| num[0].odd? }.map { | num | num[1] }) * sign
  end
end