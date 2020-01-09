#####################################################################
# Assigment sheet A07: Examples and Partlist.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

# partlist data structure implementation
class Part
  attr_reader :label, :mass, :whole, :sub_parts
  protected :sub_parts
  include Enumerable

  def initialize(label, *sub_parts, mass: 0)
    @label = label
    @sub_parts = []
    @whole = nil
    self.mass = mass
    add(sub_parts)
  end

  def parts
    @sub_parts.dup
  end

  # @return [Part] the root Part
  def top
    top = self
    top = top.whole until top.whole.nil?
    top
  end

  # @note if the part to be added has a whole a duplicate will
  #       be made to avoid editing the same part in multiple places
  # @param [Part, Array] parts array or one part
  # @raise [ArgumentError] if not a Part
  # @return [Array] parts array
  def add(parts)
    [parts].flatten.each do |part|
      check_part?(part)
      # make sure the same reference is not used twice
      part = Marshal.load(Marshal.dump(part)) unless part.whole.nil? && part.parts.empty?
      @sub_parts << part
      part.update_whole(self)
    end
    self.parts
  end

  # @note removes all items from self that are == to part even if nested
  #       to remove once use remove_at
  # @param [Part] part to be removed
  # @raise [ArgumentError] if not a Part or partlist don't have it
  # @return [Part] part removed part
  def remove(part)
    check_part?(part) && has_part?(part)
    delete(part)
    part.reset_whole
    part
  end

  # @param [Integer] index of the part to be removed
  # @return [Part] part removed part
  def remove_at(index)
    part = @sub_parts.delete_at(index)
    update_mass
    top.update_mass
    part
  end

  # replaces a part with another once at first match
  # @param [Part] part part to be replaced
  # @param [Part] new_part replacement part
  # @raise [ArgumentError] if partlist doesn't have the part
  # @raise [ArgumentError] if new_part is not a Part object
  # @return [Part] part replaced part
  def replace(part, new_part)
    has_part?(part) && check_part?(new_part)
    part.whole.sub_parts[part.whole.sub_parts.index(part)] = new_part
    new_part.update_whole(part.whole)
    part
  end

  # @note mass is ignored if part has parts
  # @param [Numeric] mass part mass
  # @raise [ArgumentError] if argument is not a Numeric object
  # @return [Numeric] mass of the Part object
  def mass=(mass)
    raise(ArgumentError, 'mass musst be a Number') unless mass.is_a?(Numeric)
    @mass = @sub_parts.empty? ? mass : @sub_parts.map(&:mass).sum
  end

  # @param [Part, Array] parts array or one part
  # @raise [ArgumentError] if any of the argument is not Part object
  # @return [Array] parts added to the Part object
  def parts=(*parts)
    parts.flatten.all? { |part| check_part?(part) }
    @mass = 0
    @sub_parts = []
    add(parts)
  end

  # whole is used same as move
  def whole=(part)
    check_part?(part)
    raise ArgumentError, "Parts can't have them self as a whole" if equal?(part)
    @whole&.delete(self)
    part.add(self)
    @whole = part
  end

  def label=(label)
    @label = label.to_s
  end

  def each(&block)
    if block_given?
      yield self
      @sub_parts.each{ |part| part.each(&block) }
      self
    else
      enum_for(__method__)
    end
  end

  # @note @whole was not added as this can cause an endless loop
  # when compering a partlist with it self or when trying to remove parts
  # with another parts see test_identity in tests/part_test.rb
  alias eql? ==
  def ==(other)
    return false unless other.is_a?(Part)
    return true if equal?(other)
    [@label, @mass, @sub_parts] == [other.label, other.mass, other.sub_parts]
  end

  def hash
    [@label, @mass, @sub_parts].hash
  end

  def to_s
    "#{@label}(#{@mass}kg)"
  end

  protected

  def check_part?(part)
    part.is_a?(Part) ? true : raise(ArgumentError, "#{part} is not a part")
  end

  def has_part?(part)
    member?(part) ? true : raise(ArgumentError, "#{part} is not a part of #{self.label}")
  end

  def delete(part)
    update_mass unless @sub_parts.delete(part).nil?
    @sub_parts.each { |sub_part| sub_part.delete(part) }
    top.update_mass
  end

  def update_whole(whole)
    @whole = whole
    @whole.update_mass
    @whole.top.update_mass
    @whole
  end

  def reset_whole
    @whole = nil
  end

  def update_mass
    @sub_parts.empty? ? @mass : self.mass = 0
  end

  def method_missing(label, *args, &block)
    # TODO: deal with duplicates
    part = @sub_parts.select{ |part| part.label.downcase.gsub(/\s+/, '_') == label.to_s }.first
    super unless part
    part
  end

  def respond_to_missing?(method_name, include_private = false)
    @sub_parts.any?{ |part| part.label.downcase.gsub(/\s+/, '_') == method_name.to_s } || super
  end
end