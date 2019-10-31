require_relative 'code_breaker'

class ComputerCodeBreaker < CodeBreaker

  attr_accessor :guess_index, :code_letters, :possible_positions, :swap_tmp, :letters_possibility

  def initialize(mastermind)
    # @mastermind = mastermind
    super(mastermind)
    @guess_index = 0
    @possible_positions = mastermind.letters.each_with_object({}) { |l, h| h[l] = (0...mastermind.pegs).to_a }
    @possible_answers = []
    @code_letters = []
    @helper_guess = []
    @secret_code = [nil] * mastermind.pegs
    @swap_tmp = []
  end

  def guess()

    collect_code_letters unless @code_letters.size == mastermind.pegs
    return collecting_guess unless @code_letters.size == mastermind.pegs

    if mastermind.has_duplicates?(@code_letters)
      generate_answers(@code_letters) if @possible_answers.empty?
      return feeling_lucky
    end

    return generate_helper_guess if @helper_guess.empty?

    # still by 4 or got 2?
    if @helper_guess.size > 2
      # until we get [B B] shuffle there no tricks here yet
      @feedback.last.uniq == ['B'] ? minimize_positions : (return feeling_lucky)
    end

    # 2 done 2 to go
    if @helper_guess.size == 2
      fill_secret_code
      @helper_guess = ['skip all other cases and come to the last switch downstairs']
      return @secret_code
    end

    # oh common a switch should do it now :)
    if @feedback.last.uniq.size > 1
      @secret_code[@swap_tmp[0]], @secret_code[@swap_tmp[1]] =
          @secret_code[@swap_tmp[1]], @secret_code[@swap_tmp[0]]
    end

    @secret_code

  end

  def generate_helper_guess
    # generate a pattern of [B/W . B/W .]
    pegless_letter = (mastermind.letters - @code_letters)[0]
    @helper_guess = (@code_letters[0..1].map { |l| [l, pegless_letter] }.flatten)
    @possible_answers = @helper_guess.permutation(@helper_guess.size).to_a.uniq
    # keep track of possible positions for possible letters only
    @possible_positions.select! { |l, _| @code_letters.include?(l) }
    @helper_guess = @code_letters.dup
    feeling_lucky
  end

  def minimize_positions
    @guesses.last.each do |letter|
      if @helper_guess.include?(letter)
        @possible_positions[letter] = [@guesses.last.index(letter)]
        @possible_positions.each do |l, p|
          p.delete(@guesses.last.index(letter)) unless l == letter
        end
        @helper_guess.delete(letter)
      end
    end
  end

  def fill_secret_code
    @possible_positions.each do |letter, positions|
      positions.each do |p|
        if @secret_code[p].nil?
          @secret_code[p] = letter
          @swap_tmp << p if positions.size > 1
          break
        end
      end
    end
  end

  def generate_answers(code)
    @possible_answers = code.permutation(code.size).to_a.uniq
  end

  def collect_code_letters
    unless @feedback.empty?
      @code_letters.fill(@guesses.last.uniq.first, code_letters.size, feedback.last.size)
    end
  end

  def collecting_guess
    @guess_index += 1
    [*mastermind.letters[guess_index - 1]] * 4
  end

  def feeling_lucky
    @possible_answers.shuffle!.pop
  end

end