require "set"

module KnuthAlgorithm

  @unused_patterns = []
  @potential_patterns = []

  def guess_knuth

    if @feedback.empty?
      @possible_answers = @mastermind.letters.product(*Array.new(@mastermind.pegs - 1) { @mastermind.letters })
      @unused_patterns = @possible_answers.dup
      @potential_patterns = Set.new(@unused_patterns)
      return %w[A A B B]
    end

    @unused_patterns.reject! { |pattern| pattern == @guesses.last }

    @potential_patterns.reject! do |potential_pattern|
      ComputerCodeMaker.give_feedback(potential_pattern, @guesses.last) != @feedback.last
    end

    # generate new guess
    possible_guesses = @unused_patterns.map do |possible_guess|

      hit_counts = @potential_patterns.each_with_object(Hash.new(0)) do |potential_pattern, counts|
        counts[ComputerCodeMaker.give_feedback(possible_guess, potential_pattern)] += 1
      end

      highest_hit_count = hit_counts.values.max || 0

      membership_value = @potential_patterns.include?(possible_guess) ? 0 : 1

      [highest_hit_count, membership_value, possible_guess]
    end

    possible_guesses.min.last
  end


end