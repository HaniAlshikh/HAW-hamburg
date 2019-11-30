class Part

  attr_reader :label, :mass, :whole, :sub_parts
  protected :sub_parts
  include Enumerable

  def initialize(label, mass = 0, *sub_parts)
    @label = label
    @sub_parts = []
    @whole = self
    add(sub_parts)
    self.mass = mass
  end

  def add(parts)
    # [*parts] -> [], buy why?
    [parts].flatten.each { |part| add_part(part) if check_part?(part) }
    self.parts
  end

  def top
    top = self
    top = top.whole until top.whole == top
    top
  end

  def remove(part)
    check_part?(part) && has_part?(part)
    delete(part)
    part.update_whole(part)
  end

  def replace(part, new_part)
    has_part?(part) && check_part?(new_part)
    part.whole.sub_parts[part.whole.sub_parts.index(part)] = new_part
    new_part.update_whole(part.whole)
    new_part.whole.generate_reader(new_part)
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
    @mass = @sub_parts.empty? ? mass : @sub_parts.map(&:mass).sum
  end

  def parts=(parts)
    [parts].flatten.any? { |part| check_part?(part) }
    @sub_parts = []
    add(parts)
  end

  # whole is used same as move
  # if the element to be move already exist in the new whole
  # it will be simply be deleted.
  # for duplicates elements
  # add should be used with remove new_whole.add(old_whole.remove(part))
  # otherwise duplicates will be created when changin
  def whole=(part)
    check_part?(part)
    @whole.delete(self)
    part.add_part(self) unless part.parts.include?(self)
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
    part.whole.update_mass
    part.whole.top.update_mass
    part
  end

  def update_whole(whole)
    @whole.update_mass
    @whole = whole
    @whole.update_mass
    @whole.top.update_mass
    @whole
  end

  def add_part(part)
    @sub_parts << part
    generate_reader(part)
    part.update_whole(self)
  end

  def update_mass
    @sub_parts.empty? ? @mass : self.mass = 0
  end

  def generate_reader(part)
    self.define_singleton_method(:"#{part.label.downcase.gsub(/\s+/, "_")}") { part }
  end
end