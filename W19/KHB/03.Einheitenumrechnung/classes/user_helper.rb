require_relative 'unit_converter'
class String
  def green; "\e[32m#{self}\e[0m" end
  def red; "\e[31m#{self}\e[0m" end
end


# a helper class to deal with user input
class UserHelper

  def initialize()

  end

  def start()
    unit_converter = UnitConverter.new
    loop do
      ask(unit_converter)
      unit_converter.convert
      puts "#{unit_converter.conversion}#{unit_converter.to_measur}"
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
    input_strings = input.gsub(/\d+/, '').scan(/\w+/).map(&:strip).map(&:to_sym)

    begin
      unit_converter.value = !!input_values[0] ? input_values[0] : get_value()
    rescue ArgumentError => e
      puts e.message.red
      retry
    end

    unit_converter.epsilon = input_values[1] if !!input_values[1]

    begin
      # try to assign
      print_e = input_strings.empty?
      unit_converter.from_measur = !!input_strings[0] ? input_strings.shift() : get_from_measur()
    # input is not a measurement?
    rescue ArgumentError => e
      puts e.message.red if print_e
      retry
    end

    begin
      # try to assign
      print_e = input_strings.empty?
      unit_converter.to_measur = !!input_strings[0] ? input_strings.shift() : get_to_measur()
        # input is not a measurement?
    rescue ArgumentError => e
      puts e.message.red if print_e
      retry
    end
  end

  def get_value()
    prompt("Please enter a value: ") { |num| Float(num) rescue return num}
  end

  def get_from_measur()
    prompt("convert from: ") { |measur| measur.to_sym }
  end

  def get_to_measur()
    prompt("convert to: ") { |measur| measur.to_sym }
  end

  #prompt for input
  # @param [String] message prompt message
  # @return [String, BlockType]
  def prompt(message)
    print(message)
    input = gets.chomp
    input = yield input if block_given?
    input
  end
end
