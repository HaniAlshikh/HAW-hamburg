class Part

  attr_reader :label, :mass, :whole, :sub_parts
  protected :sub_parts
  include Enumerable

  def initialize(label, mass = 0, *sub_parts)
    @label = label
    @sub_parts = []
    @whole = self
    self.sub_parts = sub_parts
    self.mass = mass
  end

  def top
    top = self
    top = top.whole until top.whole == top
    top
  end

  def remove(part)
    check_part?(part) && has_part?(part)
    delete(part)
    part.whole = part
  end

  def replace(part, new_part)
    has_part?(part) && check_part?(new_part)
    part.whole.sub_parts[@sub_parts.index(part)] = new_part
    new_part.update_whole(part.whole)
    part
  end

  def flatten
    flat = [self] << map { |part| part.sub_parts.empty? ? part : part.flatten }
    flat.flatten
  end

  def parts
    @sub_parts.dup
  end

  def mass=(mass)
    raise(ArgumentError, "mass musst be a Number") unless mass.is_a?(Numeric)
    @mass = @sub_parts.empty? ? mass : @sub_parts.map(&:mass).inject(0, :+)
  end

  def sub_parts=(sub_parts)
    sub_parts.empty? ? @sub_parts : [*sub_parts].each { |part| add(part) }
  end

  def add(part)
    part.whole = self if check_part?(part)
    parts
  end

  def whole=(part)
    part.add_part(self) if check_part?(part) unless @whole == part
    # remove the part from it's old whole
    @whole.delete(self)
    update_whole(part)
  end

  def label=(label)
    @label = label.to_s
  end

  def each(&block)
    block_given? ? @sub_parts.each(&block) : @sub_parts.each
  end

  alias_method :eql?, :==
  def ==(other)
    return false unless other.is_a?(Part)
    return true if self.equal?(other)
    [@label, @mass, @sub_parts, @whole] == [other.label, other.mass, other.sub_parts, other.whole]
  end

  def hash
    [@label, @mass, @sub_parts, @whole].hash
  end

  def to_s
    "#{@label}(#{@mass}kg)"
  end

  protected

  def check_part?(part)
    part.is_a?(Part) ? true : raise(ArgumentError, "#{part} is not a part")
  end

  def has_part?(part)
    flatten.include?(part) ? true : raise(ArgumentError, "#{part} is not a part of #{whole.label}")
  end

  def delete(part)
    part.whole.sub_parts.delete(part)
    part.whole.update_mass unless part.whole.sub_parts.empty?
    part
  end

  def update_whole(whole)
    @whole = whole
    @whole.update_mass
    whole.update_mass
    @whole
  end

  def add_part(part)
    @sub_parts << part
    self.define_singleton_method(:"#{part.label.downcase.gsub(/\s+/, "_")}") do
      part
    end
  end

  def update_mass
    # @mass = @sub_parts.map(&:mass).inject(0, :+)
    self.mass = 0
  end
end