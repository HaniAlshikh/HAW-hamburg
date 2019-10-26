require_relative 'unit_converter'

class String
  def green; "\e[32m#{self}\e[0m" end
  def red; "\e[31m#{self}\e[0m" end
  def bold; "\e[1m#{self}\e[22m" end
end

# a helper class to deal with user input
class UserHelper

  def start()
    unit_converter = UnitConverter.new

    loop do
      ask(unit_converter)

      unit_converter.conversion = unit_converter.convert

      puts unit_converter

      unit_converter.save_conversion
      unit_converter.reset
      puts
    end
  ensure
    output(unit_converter.conversions) unless unit_converter.conversions.empty?
  end

  #
  # @param [Array] conversions
  def output(conversions)
    max_lenght = ->(ind, m_arr) { m_arr.collect { |arr| arr[ind] }.map(&:to_s).map(&:length).max }
    l_spaces, m_spaces = max_lenght.(0, conversions) + 2, max_lenght.(1, conversions) + 2
    format = "%-14s %-#{l_spaces}s %-#{m_spaces}s %s"

    puts "\n###################### Your Conversions ######################\n".green

    puts format % ['', 'From', 'To', 'Result']
    puts
    conversions.each do |conversion|
      puts format % [Units::get_unit(conversion[1].to_sym),
                     conversion[0], conversion[1], conversion[2]]
    end
    puts
  end

  # ask the user for input
  def ask(unit_converter)

    input = prompt("what would you to do: ")
    exit(0) if input =~ /exit|done/

    input_values = input.scan(/\d+/).map(&:to_f)
    input_strings = input.gsub(/\d+/, '').scan(/\w+/).map(&:downcase).map(&:to_sym)

    handle_assigment(ArgumentError) do
      unit_converter.factor = !!input_values[0] ? input_values[0] : get_value
    end

    unit_converter.digits = input_values[1] if !!input_values[1]

    handle_assigment(ArgumentError, input_strings) do
      unit_converter.from_measure = !!input_strings[0] ? input_strings.shift : get_measure('convert from: ')
    end

    handle_assigment(ArgumentError, input_strings) do
      unit_converter.to_measure = !!input_strings[0] ? input_strings.shift : get_measure('convert to: ')
    end
  end

  def get_value()
    prompt("Please enter a value: ") { |num| Float(num) rescue return num}
  end

  def get_measure(message)
    prompt(message) { |measur| measur.downcase.to_sym }
  end

  def handle_assigment(error, arr=[])
    print_e = arr.empty?
    yield if block_given?
  rescue error => e
    puts e.message.red if print_e
    retry
  end

  # prompt for input
  # @param [String] message prompt message
  # @return [String] input
  # @yield [String] input
  def prompt(message)
    print(message)
    input = gets.chomp
    input = yield input if block_given?
    input
  end
end
