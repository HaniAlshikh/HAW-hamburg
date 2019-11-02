require_relative '../lib/extend'

module Toolbox

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

end