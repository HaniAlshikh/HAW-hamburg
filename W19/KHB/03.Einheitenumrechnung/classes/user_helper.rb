require_relative 'unit_converter'

# extend the string class to support basic output manipulation
class String
  # Output green colored string
  def green; "\e[32m#{self}\e[0m" end
  # Output red colored string
  def red; "\e[31m#{self}\e[0m" end
  # Output bold string
  def bold; "\e[1m#{self}\e[22m" end
end

# helper class to deal with user interaction
class UserHelper
  # execute
  def start
    # 00. Prep: create a new converter object
    unit_converter = UnitConverter.new
    # 01. Input
    loop do
      # get input
      ask(unit_converter)
    # 02. Process
      unit_converter.conversion = unit_converter.convert
      # in between output
      puts "#{unit_converter} \n\n"
      # save and reset for the next round
      unit_converter.save_conversion
      unit_converter.reset
    end
    # 03. Output
  ensure
    output(unit_converter.conversions) unless unit_converter.conversions.empty?
  end

  # process user for input
  # @param [Object] unit_converter
  # @return [NilClass]
  def ask(unit_converter)
    # get input and check to exit
    input = prompt("what would you to do: ")
    exit(0) if input =~ /exit|done/
    # separate input to arrays of strings and values
    input_values = input.scan(/\d+/).map(&:to_f)
    input_strings = input.gsub(/\d+/, '').scan(/\w+/).map(&:downcase).map(&:to_sym)
    # handel assigment
    # try input if missing ask
    handle_assigment(ArgumentError) do
      unit_converter.factor = !!input_values[0] ? input_values[0] : prompt('Please specify a factor: ')
    end
    unit_converter.digits = input_values[1] if !!input_values[1]
    handle_assigment(ArgumentError, input_strings) do
      unit_converter.from_measure = !!input_strings[0] ? input_strings.shift : prompt('convert from: ')
    end
    handle_assigment(ArgumentError, input_strings) do
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
      puts format % [Units::get_unit(conversion[1].to_sym), conversion[0], conversion[1], conversion[2]]
    end
    puts
  end

  # handel errors and repeat for correct value
  # @note error message won't be printed if the array has elements
  # @param [Array] arr array of inputs
  # @param [Exception] *error Exceptions to be handled
  # @yield assigment block
  # @return [NilClass]
  def handle_assigment(error, arr=[])
    print_e = arr.empty?
    yield
  rescue error => e
    puts e.message.red.bold if print_e
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