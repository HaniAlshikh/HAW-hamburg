#####################################################################
# Assigment sheet A08: Natural Order and Christmas Tree Pattern.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

# generate a christmas tree and visualize multi level arrays
module Tree
  module_function

  # print every array level to a line and center it according to the last level
  # every sub array of the main array is considered a level
  def visualize(tree)
    raise ArgumentError, "object musst be an Array" unless tree.is_a?(Array)
    tree.each { |level| puts((level * ' ').center((tree[-1]. * ' ').length, " ")) }
  end

  # generate christmas tree pattern
  def christmas(order)
    raise ArgumentError, "order musst be integer >= 1" unless order.is_a?(Integer) && order >= 1
    order == 1 ? [["0", "1"]] : tree(christmas(order - 1))
  end

  # process a pattern according to the assigment rules
  # @param [Array] starting_pattern
  # @return [Array] array of sub arrays (levels, lines)
  def tree(starting_pattern)
    pattern = []
    # each sub array is a line [[first_line], [second_line]]
    starting_pattern.each do |sequences|
      # add two lines to the pattern for each line of sequences
      pattern.push([],[])
      # a line consist of multipel sequences [[S1, S2...Sr]]
      sequences.each_with_index do |sequence, i|
        if i == 0
          pattern[-1].push(sequence + '0')
          pattern[-1].push(sequence + '1')
        else
          pattern[-2].push(sequence + '0') # empty array if only 1 sequence
          pattern[-1].push(sequence + '1')
        end
      end
    end
    pattern.reject(&:empty?)
  end

  private_class_method :tree
end