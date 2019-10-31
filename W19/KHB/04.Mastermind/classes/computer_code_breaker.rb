require_relative 'code_breaker'

class ComputerCodeBreaker < CodeBreaker

  attr_accessor :guess_index, :secret_code_letters, :possible_positions,
                :swap_tmp, :letters_possibility, :print_helper

  def initialize(mastermind)
    # @mastermind = mastermind
    super(mastermind)
    @guess_index = 0
    @possible_positions = mastermind.letters.each_with_object({}) { |l, h| h[l] = (0...mastermind.pegs).to_a }
    @possible_answers = []
    @secret_code_letters = []
    @helper_guess = []
    @secret_code = [nil] * mastermind.pegs
    @swap_tmp = []
    @print_helper = [*true] * 6
  end

  def guess()

    puts "\nlet's see what we got here....." if print_helper[0]; @print_helper[0] = false
    collect_code_letters
    return collecting_guess unless @secret_code_letters.size == mastermind.pegs

    if mastermind.has_duplicates?(@secret_code_letters)
      puts "\nthis should be easy to guess...." if print_helper[1]; @print_helper[1] = false
      return lucky_guess(@secret_code_letters)
    end

    puts "\nok, ok i can handel it.." if print_helper[2]; @print_helper[2] = false
    return helper_guess if @helper_guess.empty?

    # still by 4 or got 2?
    minimize_positions if @feedback.last.uniq == ['B']
    # until we get [B B] shuffle there no tricks here yet
    return lucky_guess(@helper_guess) if @helper_guess.size > 2

    puts 'almost there...' if print_helper[3]; @print_helper[3] = false
    # 2 done 2 to go
    fill_secret_code if @helper_guess.size == 2
    # oh common a switch should do it now :)
    switch_guess if @feedback.last.uniq.size > 1

    @secret_code

  end

  def helper_guess
    # generate a pattern of [B/W . B/W .]
    pegless_letter = (mastermind.letters - @secret_code_letters)[0]
    @helper_guess = (@secret_code_letters[0..1].map { |l| [l, pegless_letter] }.flatten)
    @possible_answers = @helper_guess.permutation(@helper_guess.size).to_a.uniq
    # keep track of possible positions for possible letters only
    @possible_positions.select! { |l, _| @secret_code_letters.include?(l) }
    @helper_guess = @secret_code_letters.dup
    lucky_guess(@helper_guess)
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
    @helper_guess = ['skip all other cases and come to the last switch guess']
  end

  def switch_guess
    @secret_code[@swap_tmp[0]], @secret_code[@swap_tmp[1]] =
        @secret_code[@swap_tmp[1]], @secret_code[@swap_tmp[0]]
  end

  def generate_answers(code)
    @possible_answers = code.permutation(code.size).to_a.uniq
  end

  def collect_code_letters
    unless @secret_code_letters.size == mastermind.pegs
      unless @feedback.empty?
        @secret_code_letters.fill(@guesses.last.uniq.first, secret_code_letters.size, feedback.last.size)
      end
    end
  end

  def collecting_guess
    @guess_index += 1
    [*mastermind.letters[guess_index - 1]] * 4
  end

  def lucky_guess(from_code)
    generate_answers(from_code) if @possible_answers.empty?
    @possible_answers.shuffle!.pop
  end

end