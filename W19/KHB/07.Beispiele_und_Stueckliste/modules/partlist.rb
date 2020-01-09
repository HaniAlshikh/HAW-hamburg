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
    partlist.count { |part| part.parts.empty? }
  end

  # output a partlist with indentation
  # @param [Part] partlist to be outputted
  def output(partlist)
    partlist.each do |part|
      puts( "\t" * (nest(part)) + part.to_s \
      + "#{ " Part Count: #{count_parts(partlist)}" if part.whole.nil? }" )
    end
  end

  # calculate nesting level for a part in a partlist
  # @note root is not added to the sum
  def nest(part)
    return 0 if part.whole.nil?
    nesting = 1
    until part.whole == part.top
      part = part.whole
      nesting += 1
    end
    nesting
  end

  private_class_method :nest
end