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
require_relative 'human_code_breaker'
require_relative 'human_code_maker'
require_relative 'computer_code_breaker'
require_relative 'computer_code_maker'
require_relative '../lib/extend'

# helper class to deal with user interaction
class UserHelper

  attr_reader :mastermind
  attr_accessor :code_breaker, :code_maker
  def initialize
    @mastermind = MasterMind.new
    @code_maker, @code_breaker = [*nil]
  end


  def run
    loop do
      ask
      # break if prompt('Again? (y/n): ') =~ /^n$|^no$|^ne$|^nein$/i
    end
  end

  at_exit { puts "Here comes the output soon" }


  def ask
    repeat_on(ArgumentError) do
      input = prompt("\nwhat would you to do: ")
      case input
      when /^play$|^p$/i then play
      when /^exit$|^e$/i then exit()
      when /^config$|^c$/i then config
      when /^config attempts.*|^c a.*/i then conf_attempts(input[/\d+/])
      when /^config pegs.*|^c p.*/i then conf_pegs(input[/\d+/])
      when /^config letters.*|^c l.*/i then conf_letters(input[/\d+/])
      when /^verbose$|^v$/i then verbose
      else raise ArgumentError, "#{input} is not a valid option"
      end
    end
  end

  def play
    repeat_on(ArgumentError) do
      input = prompt('Who are you? maker or breaker: ')
      case input
      when /^codemaker$|^maker$|^m$/i
        @code_breaker = ComputerCodeBreaker.new(mastermind)
        @code_maker = HumanCodeMaker.new(mastermind)
        if mastermind.letters.size > 6 || mastermind.pegs > 4
          puts "\nWell i'm smart but not that smart :)"
          puts "please rest to 6 letters and 4 pegs to play with me."
          return
        end
        case mastermind.attempts
        when (0..5)
          puts "Well i mean you got me :|, i can't win in less than 6 attempts yet :("
          return
        when (6..8) then puts "Only #{mastermind.attempts} attempts!! well don't expect much"
        when (12..Float::INFINITY) then puts "#{mastermind.attempts} attempts, how generous of you"
        else "Hmmmm what did you do?!"
        end
        comp_v_human
      when /^codebreaker$|^breaker$|^b$/i
        @code_breaker = HumanCodeBreaker.new(mastermind)
        @code_maker = ComputerCodeMaker.new(mastermind)
        human_v_comp
      else raise ArgumentError, "#{input} is not a valid option"
      end
    end
  end

  def human_v_comp

    code_maker.generate
    display_info

    mastermind.attempts.times do
      repeat_on(ArgumentError) do
        code_breaker.guesses << code_breaker.guess { prompt('your guess: ') }
      end
      break if code_maker.lost?(code_breaker.guesses.last)

      code_breaker.feedback << code_maker.give_feedback(code_breaker.guesses.last)
      puts "feedback  : #{code_breaker.feedback.last.join(' ')}"
    end

    mastermind.evaluate(code_maker, code_breaker)

    puts "\n  #{code_maker.secret_code}  \n\n"

    if mastermind.winner.to_s =~ /human.*/i
      puts "Congrats you won!"
    else
      puts "You lost, maybe next time :)"
    end
  end

  def comp_v_human

    display_info
    repeat_on(ArgumentError) { code_maker.generate { prompt('your secret code: ') } }

    mastermind.attempts.times do
      code_breaker.guesses << code_breaker.guess
      puts "my guess: #{code_breaker.guesses.last}"

      break if code_maker.lost?(code_breaker.guesses.last)

      repeat_on(ArgumentError) do
        code_breaker.feedback << code_maker.give_feedback(
            ComputerCodeMaker.give_feedback(
            code_breaker.guesses.last, code_maker.secret_code)) { prompt('Please give a feedback: ') }
      end
    end

    mastermind.evaluate(code_maker, code_breaker)

    puts "\n  #{code_maker.secret_code}  \n\n"

    if mastermind.winner.to_s =~ /human.*/i
      puts "Wow your code is hard to guess"
    else
      puts "Oh yea, looks like i'm really smart after all :)"
    end

  end

  def display_info
    puts "\nLetters to chose from:\n\n    - #{mastermind.letters.join(' ')} -\n\n"
  end

  def config
    conf_letters
    conf_pegs
    conf_attempts
    puts "All configurations are set"
  end

  def conf_letters(num = nil)
    repeat_on(ArgumentError) do
      mastermind.n_letters = num ? num : prompt("number of letters (current: #{mastermind.n_letters}): ")
    end
    puts "Changed letters value to: #{mastermind.n_letters}".green
  end

  def conf_pegs(num = nil)
    repeat_on(ArgumentError) do
      mastermind.pegs = num ? num : prompt("number of letters (current: #{mastermind.pegs}): ")
    end
    puts "Changed pegs value to: #{mastermind.pegs}".green
  end

  def conf_attempts(num = nil)
    repeat_on(ArgumentError) do
      mastermind.attempts = num ? num : prompt("number of attempts (current: #{mastermind.attempts}): ")
    end
    puts "Changed attempts value to: #{mastermind.attempts}".green
  end



  # handel errors and repeat till correct value
  # @param [Exception] error Exceptions to be handled
  # @yield assigment block
  # @return [TrueClass] when handling is done
  def repeat_on(error)
    yield
    true
  rescue error => e
    puts e.message.red.bold
    retry
  end

  # prompt for input
  # @param [String] message prompt message
  # @return [String] input
  def prompt(message)
    print(message)
    gets.chomp
  end

  def verbose
    mastermind.verbose = true
    puts "Verbose mode is activated".green
  end

end