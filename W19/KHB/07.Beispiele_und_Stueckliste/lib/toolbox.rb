#####################################################################
# small module containing our commonly used methods
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative 'extend'

# basic generic methods
module Toolbox
  # handel errors and repeat till correct value
  # @param [Exception] error Exceptions to be handled
  # @yield the block to be ran
  # @return whatever the block returns
  def repeat_on(error)
    yield
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

  # try running a block
  # @yield for the block to be ran
  # @return whatever the block returns or Exception object
  def self.try(err)
    yield
  rescue err => e
    e
  end
end