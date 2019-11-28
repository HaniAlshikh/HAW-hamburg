require_relative '../classes/part'

module Partlist

  module_function

  def generate(hash)
    partlist = Part.new(hash.keys[0])
    hash.each do |_, parts|
      parts.each { |sub_hash| partlist.add(generate(sub_hash)) } if parts.is_a?(Array)
      partlist.mass = parts if parts.is_a?(Numeric)
    end
    partlist
  end

  # wont count the whole
  def count(partlist)
    count = 0
    partlist.each do |part|
      count += part.parts.empty? ? 1 : partlist_parts_counter(part)
    end
    count
  end

# wer kümmert sich um die eingerückte Ausgabe? das Objekt oder der Nutzer?
  def output(partlist)
    puts partlist.to_s
    sub_part(partlist)
  end

  def sub_part(partlist)
    partlist.each do |part|
      puts("\t"*(nesting(part) + 1) + part.to_s)
      sub_part(part) unless part.parts.empty?
    end
  end

  def nesting(part)
    nesting = 0
    until part.whole == part.top
      part = part.whole
      nesting += 1
    end
    nesting
  end

end