#####################################################################
# Assigment sheet A03: Unit Converter in Ruby.
#
# Helper class to deal with user interaction
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'unit_converter'
require_relative '../lib/prettify'

# helper class to deal with user interaction
class UserHelper
  attr_reader :unit_converter

  def initialize
    @unit_converter = UnitConverter.new
  end

  # execute
  def start
    loop do
      # 01. Input
      input_values, input_strings = ask
      assign(input_values, input_strings)
      # 02. Process
      unit_converter.conversion = unit_converter.convert
      # -- in between output
      puts "#{unit_converter} \n\n"
      # -- save and reset for the next round
      unit_converter.save_conversion
      unit_converter.reset
    end
  # 03. Output
  ensure
    output(unit_converter.conversions) unless unit_converter.conversions.empty?
  end

  # ask user for input
  # @return [[Array],[Array]]
  def ask
    # get input and check to exit
    input = prompt('what would you to do: ')
    exit(0) if input =~ /exit|done/
    # separate input to arrays of strings and values
    input_values = input.scan(/\d*\.?\d+/).map(&:to_f)
    input_strings = input.gsub(/\d+/, '').scan(/\w+/).map(&:downcase).map(&:to_sym)
    [input_values, input_strings.select { |sym| Units.measure?(sym) }]
  end

  # handel assigment
  def assign(input_values, input_strings)
    # try input if missing ask for it
    handle_assigment(ArgumentError) do
      unit_converter.factor = !!input_values[0] ? input_values[0] : prompt('Please specify a factor: ')
    end
    unit_converter.digits = input_values[1] if !!input_values[1]
    handle_assigment(ArgumentError) do
      unit_converter.from_measure = !!input_strings[0] ? input_strings.shift : prompt('convert from: ')
    end
    handle_assigment(ArgumentError) do
      unit_converter.to_measure = !!input_strings[0] ? input_strings.shift : prompt('convert to: ')
    end
  end

  # custom tableized output
  # @param [Array] conversions Array of output arrays
  # @return [NilClass]
  def output(conversions)
    # dynamically calculate column width
    # calculate the longest word of <i> elements in a two dimensional array [[..,<i>,..],[..,<i>,..]]
    max_length = ->(ind, m_arr) { m_arr.collect { |arr| arr[ind] }.map(&:to_s).map(&:length).max }
    l_spaces = max_length.call(0, conversions) + 2
    m_spaces = max_length.call(1, conversions) + 2
    # formatting and outputting
    format = "%-14s %-#{l_spaces}s %-#{m_spaces}s %s"
    puts "\n###################### Your Conversions ######################\n".green
    puts format % ['', 'From', 'To', 'Result'], ""
    conversions.each do |conversion|
      puts format % [Units.get_unit(conversion[1].to_sym), conversion[0], conversion[1], conversion[2]]
    end
    puts
  end

  # handel errors and repeat till correct value
  # @param [Exception] error Exceptions to be handled
  # @yield assigment block
  # @return [TrueClass] when handling is done
  def handle_assigment(error)
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
end