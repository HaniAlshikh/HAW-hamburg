#####################################################################
# Assigment sheet A04: Mastermind in Ruby.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

# we know, that this is not how ai works but we can't call it algorithm either
# anyway this "AI" will:
# 01. collect the secret_code_letters
# 02. look for duplication and simply guess a combination
# 03. if not try to get the correct position for two secret_code_letters
# 04. once done guess the position of the other two
# 05. if not switch there position
# 06. code guessed
#
# we have an average of ~ 12.5 guesses for this "AI"
module Mai
  def setup
    @secret_code = [nil] * @mastermind.pegs
    @possible_positions = @mastermind.letters.each_with_object({}) { |l, h| h[l] = (0...@mastermind.pegs).to_a }
    @guess_index = 0
    @possible_answers = []
    @secret_code_letters = []
    @helper_guess = []
    @swap_tmp = []
    @print_helper = [*true] * 6
  end

  def guess_mai
    setup if @print_helper.nil?
    # 01. collect the secret_code_letters
    puts "\nlet's see what we got here....." if @print_helper[0]; @print_helper[0] = false
    collect_code_letters
    return collecting_guess unless @secret_code_letters.size == @mastermind.pegs

    # 02. look for duplication and simply guess a combination
    if @mastermind.duplicates?(@secret_code_letters)
      puts "\nthis should be easy to guess...." if @print_helper[1]; @print_helper[1] = false
      return lucky_guess(@secret_code_letters)
    end

    # 03. if not try to get the correct position for two secret_code_letters
    puts "\nok, ok i can handel it.." if @print_helper[2]; @print_helper[2] = false
    return helper_guess if @helper_guess.empty?
    # still by 4 or got 2?
    minimize_positions if @scores.last.uniq == ['B']
    # until we get [B B] shuffle there no tricks here yet
    return lucky_guess(@helper_guess) if @helper_guess.size > 2

    # 04. once done guess the position of the other two
    puts "\nalmost there..." if @print_helper[3]; @print_helper[3] = false
    # 2 done 2 to go
    fill_secret_code if @helper_guess.size == 2

    # 05. if not switch there position
    switch_guess if @scores.last.uniq.size > 1

    # 06. code guessed
    @secret_code
  end

  def helper_guess
    # generate a pattern of [B/W . B/W .]
    pegless_letter = (@mastermind.letters - @secret_code_letters)[0]
    @helper_guess = (@secret_code_letters[0..1].map { |l| [l, pegless_letter] }.flatten)
    # generate all possible answers of our pattern [B/W . B/W .]
    @possible_answers = @helper_guess.permutation(@helper_guess.size).to_a.uniq
    # keep track of possible positions for secret_code_letters only
    @possible_positions.select! { |l, _| @secret_code_letters.include?(l) }
    @helper_guess = @secret_code_letters.dup
    lucky_guess(@helper_guess)
  end

  def minimize_positions
    # after we get the correct position for two letters
    @guesses.last.each do |letter|
      # make sure we skip the pegless letters
      if @helper_guess.include?(letter)
        # 01. make it the only possible position for the letter
        @possible_positions[letter] = [@guesses.last.index(letter)]
        # 02. delete this position from the other ones
        @possible_positions.each do |l, p|
          p.delete(@guesses.last.index(letter)) unless l == letter
        end
        # delete this letter from our helper_guess letters
        @helper_guess.delete(letter)
      end
    end
  end

  def fill_secret_code
    # file the secret code with the possible pattern
    @possible_positions.each do |letter, positions|
      positions.each do |p|
        if @secret_code[p].nil?
          @secret_code[p] = letter
          # and save the position of letters to be swapped if needed
          @swap_tmp << p if positions.size > 1
          break
        end
      end
    end
    @helper_guess = ['skip all other cases and come to the last switch guess']
  end

  def switch_guess
    @secret_code[@swap_tmp[0]], @secret_code[@swap_tmp[1]] =
        @secret_code[@swap_tmp[1]], @secret_code[@swap_tmp[0]]
  end

  def generate_answers(code)
    # generate all possible combinations of a code
    @possible_answers = code.permutation(code.size).to_a.uniq
  end

  def collect_code_letters
    # fille the secret_code_letters with a letter * the amount of B's it got
    unless @secret_code_letters.size == @mastermind.pegs
      unless @scores.empty?
        @secret_code_letters.fill(@guesses.last.uniq.first, @secret_code_letters.size, @scores.last.size)
      end
    end
  end

  def collecting_guess
    # generate a code of every letter of the possible letters e.g. [A A A A]
    @guess_index += 1
    [*@mastermind.letters[@guess_index - 1]] * 4
  end

  def lucky_guess(from_code)
    generate_answers(from_code) if @possible_answers.empty?
    # use one answer of the possible ones and remove it
    @possible_answers.shuffle!.pop
  end
end