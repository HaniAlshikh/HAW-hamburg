require_relative '../lib/extend'

class GameError < StandardError
end

class MasterMind

  attr_reader :letters, :n_letters, :pegs, :attempts
  attr_accessor :logs, :winner, :verbose

  def initialize
    @verbose = false
    @n_letters = 6
    @pegs = 4
    @attempts = 12
    @letters = ("A".."Z").to_a[0...n_letters]
    @logs = []
    @winner = nil
  end

  def validate_code?(code)
    code.size == pegs && (code - letters).empty?
  end

  def has_duplicates?(code)
    code.uniq.size != code.size
  end

  def to_code(str)
    str.gsub(/[^[:alpha:]]/, '').chars.map(&:upcase).to_a
  end

  def evaluate(code_maker, code_breaker)
    @winner = code_maker.lost?(code_breaker.guesses.last) ? code_breaker : code_maker
    @logs << { secret_code: "[ #{code_maker.secret_code.join(' ')} ]",
               maker: code_maker, breaker: code_breaker, guesses: code_breaker.guesses.size,
               winner: @winner }
    @winner
  end

  def n_letters=(num)
    safe_assign(num) { |num| @n_letters = num }
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

  def safe_assign(num)
    unless num.empty?
      num = Integer(num)
      raise GameError, 'no can do!, number musst be positive and bigger than 0' if num <= 0
      yield num if block_given?
    end
  rescue ArgumentError
    raise ArgumentError, "#{num} is not a valid number"
  rescue GameError => e
    raise ArgumentError, e.message
  end
end