require_relative 'unit_converter'

class String
  def green; "\e[32m#{self}\e[0m" end
  def red; "\e[31m#{self}\e[0m" end
end

# a helper class to deal with user input
class UserHelper

  def start()
    unit_converter = UnitConverter.new

    loop do
      ask(unit_converter)
      unit_converter.conversion = unit_converter.convert
      puts "#{unit_converter.conversion}#{unit_converter.to_measure}"
      unit_converter.save_conversion
      puts
      case prompt "Another one? (y/n) > ".downcase
      when 'n','nein','nope','ne'
        break
      else
        unit_converter.reset
      end
    end
    puts
    p unit_converter.conversions

  end



  # ask the user for input
  def ask(unit_converter)

    input = prompt("what would you like to convert: ")
    input_values = input.scan(/\d+/).map(&:to_f)
    input_strings = input.gsub(/\d+/, '').scan(/\w+/).map(&:strip).map(&:downcase)

    handle_assigment(ArgumentError) do
      unit_converter.factor = !!input_values[0] ? input_values[0] : get_value()
    end

    unit_converter.epsilon = input_values[1] if !!input_values[1]

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
    prompt(message) { |measur| measur.downcase }
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
  # @return [String, BlockType]
  def prompt(message)
    print(message)
    input = gets.chomp
    input = yield input if block_given?
    input
  end
end
