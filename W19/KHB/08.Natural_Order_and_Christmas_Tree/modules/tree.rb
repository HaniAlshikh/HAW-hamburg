module Tree

  module_function

  def visualize(tree)
    tree.each { |level| puts((level * ' ').center((tree[-1]. * ' ').length, " ")) }
  end

  def christmas(order)
    order == 1 ? [["0", "1"]] : tree(christmas(order - 1))
  end

  def tree(starting_pattern)
    # each sub array is a line [[first_line] [second_line]]
    pattern = []
    new_line = 0
    # a line consist of multipel sequences [[S1, S2...Sr]]
    starting_pattern.each do |sequences|
      # add a new line to the pattern
      pattern[new_line] = []
      # line have only one sequence? (r = 1)
      if sequences.size == 1
        pattern[new_line][0] = sequences[0] + "0"
        pattern[new_line][1] = sequences[0] + "1"
        new_line += 1
      else
        # add a second line
        pattern[new_line + 1] = []
        sequences.each_with_index do |sequence, index|
          if index == 0 # skip S1 on the first line
            pattern[new_line + 1][index] = sequence + "0"
            pattern[new_line + 1][index + 1] = sequence + "1"
          else
            pattern[new_line][index - 1] = sequence + "0"
            pattern[new_line + 1][index + 1] = sequence + "1"
          end
        end
        new_line += 2
      end
    end
    pattern
  end

  private_class_method :tree
end