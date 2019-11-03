#####################################################################
# Assigment sheet A04: Mastermind in Ruby.
#
# tiny library to extend classes functionality
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

# extend the string class to support basic output manipulation
class String
  # Output red colored string
  def red; "\e[31m#{self}\e[0m" end
  # Output green colored string
  def green; "\e[32m#{self}\e[0m" end
  # Output yellow colored string
  def yellow; "\e[33m#{self}\e[0m" end
  # Output bold string
  def bold; "\e[1m#{self}\e[22m" end
end

# extend the Float class to support basic output manipulation
class Float
  # convert to int if it's a whole number.
  def prettify
    to_i == self ? to_i : self
  end
end

class Array
  # Subtract each passed value once:
  #   %w(1 2 3 1).subtract_once %w(1 1 2) # => ["3"]
  #   [ 1, 1, 2, 2, 3, 3, 4, 5 ].subtract_once([ 1, 2, 4 ]) => [1, 2, 3, 3, 5]
  def subtract_once(values)
    counts = values.inject(Hash.new(0)) { |h, v| h[v] += 1; h }
    reject { |e| counts[e] -= 1 unless counts[e].zero? }
  end
end