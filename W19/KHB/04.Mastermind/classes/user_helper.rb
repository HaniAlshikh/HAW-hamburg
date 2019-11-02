#####################################################################
# Assigment sheet A03: Unit Converter in Ruby.
#
# Helper class to deal with user interaction
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'master_mind'
require_relative 'config'
require_relative '../Modules/toolbox'
require_relative 'human_code_breaker'
require_relative 'human_code_maker'
require_relative 'computer_code_breaker'
require_relative 'computer_code_maker'
require_relative '../lib/extend'

# helper class to deal with user interaction
class UserHelper

  include Toolbox

  attr_reader :mastermind, :config
  attr_accessor :code_breaker, :code_maker
  def initialize
    @mastermind = MasterMind.new(Config::CONFIGS)
    @code_maker, @code_breaker = [*nil]
    @config = Config.new(@mastermind)
  end

  def run
    loop { ask }
  ensure
    output unless @mastermind.logs.empty?
  end

  def ask
    repeat_on(ArgumentError) do
      case input = prompt("\nwhat would you to do: ")
      when /^play$|^p$/i then play
      when /^exit$|^e$/i then exit
      when /^config$|^c$/i then @config.config
      when /^config attempts.*|^c a.*/i then @config.attempts(input[/\d+/])
      when /^config pegs.*|^c p.*/i then @config.pegs(input[/\d+/])
      when /^config letters.*|^c l.*/i then @config.letters(input[/\d+/])
      when /^knuth|^k$/i then @config.knuth
      when /^verbose$|^v$/i then @config.verbose
      else raise ArgumentError, "#{input} is not a valid option"
      end
    end
  end

  def play
    repeat_on(ArgumentError) do
      case players = prompt('Who are you? maker or breaker: ')
      when /^codemaker$|^maker$|^m$/i then play_comp_v_human
      when /^codebreaker$|^breaker$|^b$/i then play_human_v_comp
      else raise ArgumentError, "#{players} is not a valid option"
      end
    end
  end

  def play_comp_v_human
    @code_breaker = ComputerCodeBreaker.new(@mastermind)
    return unless @code_breaker.check_requirement?
    @code_breaker.interact
    @code_maker = HumanCodeMaker.new(@mastermind)
    game_start { comp_v_human }
  end

  def play_human_v_comp
    @code_breaker = HumanCodeBreaker.new(@mastermind)
    @code_maker = ComputerCodeMaker.new(@mastermind)
    game_start { human_v_comp }
  end

  def game_start
    display_info
    yield if block_given?
    mastermind.evaluate(@code_maker, @code_breaker)
    puts "\n  #{code_maker.secret_code}  \n\n"
    code_maker.interact
  end

  def human_v_comp
    code_maker.generate
    mastermind.attempts.times do
      repeat_on(ArgumentError) { code_breaker.guesses << code_breaker.guess { prompt('your guess: ') } }
      break if code_maker.lost?(code_breaker.guesses.last)
      # TODO: hnadel user feedback for knuth
      code_breaker.scores << code_maker.score(code_breaker.guesses.last)
      puts "feedback  : #{code_breaker.scores.last.join(' ')}"
    end
  end

  def comp_v_human
    repeat_on(ArgumentError) { code_maker.generate { prompt('your secret code: ') } }
    mastermind.attempts.times do
      code_breaker.guess
      puts "my guess: #{code_breaker.guesses.last}"
      break if code_maker.lost?(code_breaker.guesses.last)
      repeat_on(ArgumentError) do
        code_breaker.scores << code_maker.score(mastermind.score(
            code_breaker.guesses.last, code_maker.secret_code)) { prompt('Please give a feedback: ') }
      end
    end
  end

  def display_info
    puts "\nLetters to chose from:\n\n    - #{mastermind.letters.join(' ')} -\n\n"
  end

  def output
    # formatting and outputting
    format = "%-13s %-10s %-10s %-10s %s"
    puts "\n########################## Game log ##########################\n".green
    puts format % ['', 'Maker', 'Breaker', 'Guesses', 'Winner'], ""
    @mastermind.logs.each do |log|
      puts format % [log[:secret_code], log[:maker], log[:breaker], log[:guesses], log[:winner]]
    end
    puts
  end

end