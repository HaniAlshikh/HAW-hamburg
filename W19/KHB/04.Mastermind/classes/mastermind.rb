#####################################################################
# Assigment sheet A04: Mastermind in Ruby.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative '../lib/extend'

# rules violation error
class GameError < StandardError
end

# mastermind game logic
class Mastermind
  attr_reader :letters, :n_letters, :pegs, :attempts
  attr_accessor :logs, :winner, :verbose, :knuth

  def initialize(config)
    @verbose = config[:verbose]
    @n_letters = config[:letters]
    @pegs = config[:pegs]
    @attempts = config[:attempts]
    @knuth = config[:knuth]
    @letters = generate_letters
    @logs = []
    @winner = nil
  end

  def generate_letters
    ('A'..'Z').to_a[0...n_letters]
  end

  def validate_code?(code)
    code.size == @pegs && (code - @letters).empty?
  end

  def duplicates?(code)
    code.uniq.size != code.size
  end

  # @return [Array] code
  def to_code(str)
    str.gsub(/[^[:alpha:]]/, '').chars.map(&:upcase).to_a
  end

  # logs current game date
  # @param [CodeMaker] code_maker
  # @param [CodeBreaker] code_breaker
  # @return [Array] logs
  def log(code_maker, code_breaker)
    @winner = code_maker.lost?(code_breaker.guesses.last) ? code_breaker : code_maker
    @logs << { secret_code: "[ #{code_maker.secret_code.join(' ')} ]",
               maker: code_maker, breaker: code_breaker, guesses: code_breaker.guesses.size,
               winner: @winner }
  end

  # scores a guess based on a code
  # @param [Array] guess
  # @param [Array] code
  # @return [Array] score
  def score(guess, code) # -> [ A A B D ], [ A B C B ]
    # compact removes nil elements, that map generate
    black_pegs = code.zip(guess).map { |c, g| c if c == g }.compact # -> [ A ]
    # add as many B's as we have black pegs to the score array
    score = ['B'] * black_pegs.size # -> [ B ] for A
    # iterate over the guess without the matching letters
    guess.subtract_once(black_pegs).each do |letter| # -> [ A B D ]
      # add W for every letter in the code once
      next unless code.subtract_once(black_pegs).include?(letter) # -> [ B C B ] ? [ A B D ]
      score << 'W' # -> [ W ] for B
      # in case we still have duplicates in the guess
      code = code.subtract_once([*letter]) # -> [ C B ]
    end

    score.sort
  end

  def n_letters=(num)
    safe_assign(num) { |num| @n_letters = num }
    @letters = generate_letters
  end

  def pegs=(num)
    safe_assign(num) do |num|
      raise GameError, "Pegs should be less than letters: #{@n_letters}" if num >= @n_letters
      @pegs = num
    end
  end

  def attempts=(num)
    safe_assign(num) { |num| @attempts = num }
  end

  private

  def safe_assign(num)
    num = Integer(num)
    raise GameError, 'no can do!, number musst be positive and bigger than 0' if num <= 0
    yield num if block_given?
  rescue ArgumentError
    raise ArgumentError, "#{num} is not a valid number"
  rescue GameError => e
    raise ArgumentError, e.message
  end
end