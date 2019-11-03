#####################################################################
# Assigment sheet A04: Mastermind in Ruby.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative '../Modules/toolbox'

# the hub point for the game configurations
class Config
  include Toolbox

  # default configs
  CONFIGS = {
      pegs: 4,
      letters: 6,
      attempts: 12,
      verbose: false,
      knuth: false
  }.freeze

  def initialize(mastermind)
    @mastermind = mastermind
    # lambdas and procs for getting configuration value from the user
    @configs = {
      # check if num has value, otherwise ask for it
      letters: ->(num) {
        @mastermind.n_letters = num ? num : prompt("number of letters (current: #{@mastermind.n_letters}): ") },
      pegs: ->(num) {
        @mastermind.pegs = num ? num : prompt("number of pegs (current: #{@mastermind.pegs}): ") },
      attempts: ->(num) {
        @mastermind.attempts = num ? num : prompt("number of attempts (current: #{@mastermind.attempts}): ") },
      # procs don't care about arguments (num is passed but we don't needed here)
      verbose: Proc.new { @mastermind.verbose = !@mastermind.verbose },
      knuth: Proc.new { @mastermind.knuth = !@mastermind.knuth  }
      }
  end

  # ask for the main configuration if user typed config or c
  def config
    letters
    pegs
    attempts
    puts "All configurations are set"
  end

  # this method is less readable but saves 16 line of code and repetition
  # generate a method for every config
  CONFIGS.keys.each do |config|
    # with an argument num = nil (e.g. letter(num = nil))
    define_method "#{config}" do |num = nil|
      repeat_on(ArgumentError) do
        # call the value getting lambda/proc for every config
        @val = @configs[config].call(num)
      ensure # that user input is used one time only (e.g. c p 10 will cease infinite loop)
        num = nil
      end
      puts "Changed #{config} value to: #{@val}".green
    end
  end
end