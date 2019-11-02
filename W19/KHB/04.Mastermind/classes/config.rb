require_relative '../Modules/toolbox'

class Config

  include Toolbox

  CONFIGS = {
      verbose: false,
      letters: 6,
      pegs: 4,
      attempts: 12,
      knuth: false
  }.freeze

  def initialize(mastermind)
    @mastermind = mastermind
    @configs = {
      letters: ->(num) {
        @mastermind.n_letters = num ? num : prompt("number of letters (current: #{@mastermind.n_letters}): ") },
      pegs: ->(num) {
        @mastermind.pegs = num ? num : prompt("number of pegs (current: #{@mastermind.pegs}): ") },
      attempts: ->(num) {
        @mastermind.attempts = num ? num : prompt("number of attempts (current: #{@mastermind.attempts}): ") },
      verbose: Proc.new { @mastermind.verbose = !@mastermind.verbose },
      knuth: Proc.new { @mastermind.knuth = !@mastermind.knuth  }
      }
  end

  def config
    letters
    pegs
    attempts
    puts "All configurations are set"
  end

  CONFIGS.keys.each do |config|
    define_method "#{config}" do |num = nil|
      repeat_on(ArgumentError) { num = @configs[config].call(num) }
      puts "Changed #{config} value to: #{num}".green
    end
  end
end