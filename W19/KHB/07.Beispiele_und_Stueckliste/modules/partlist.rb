#####################################################################
# Assigment sheet A07: Examples and Partlist.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require_relative '../classes/part'

# utility module to handel Partlist deserialization and outputting
module Partlist
  module_function

  # recursive method to convert a hash into a partlist
  # @param [Hash] hash to be converted
  # @return [Part] partlist
  def generate(hash)
    partlist = Part.new(hash.keys[0])
    hash.each do |_, values|
      values.each { |sub_hash| partlist.add(generate(sub_hash)) } if values.is_a?(Array)
      partlist.mass = values if values.is_a?(Numeric)
    end
    partlist
  end

  # wont count the wholes as these are used in abstract existence
  # @param [Part] partlist to be counted
  # @return [Integer] count
  def count_parts(partlist)
    count = 0
    partlist.each { |part| count += part.parts.empty? ? 1 : count_parts(part) }
    count
  end

  # output a partlist with indentation
  # @param [Part] partlist to be outputted
  def output(partlist)
    puts "#{partlist} Part Count: #{count_parts(partlist)}"
    sub_part(partlist)
  end

  # recursively output evey part in a partlist
  def sub_part(partlist)
    partlist.each do |part|
      puts("\t" * (nesting(part) + 1) + part.to_s)
      sub_part(part) unless part.parts.empty?
    end
  end

  # calculate nesting level for a part in a partlist
  # @note root is not added to the sum
  def nesting(part)
    nesting = 0
    until part.whole == part.top
      part = part.whole
      nesting += 1
    end
    nesting
  end

  private_class_method :sub_part, :nesting
end