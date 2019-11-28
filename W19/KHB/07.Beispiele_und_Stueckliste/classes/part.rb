class Part

  attr_reader :label, :mass, :whole
  include Enumerable

  def initialize(label, mass = 0, *parts)
    @label = label
    @parts = []
    @whole = self
    self.parts = parts
    self.mass = mass
  end

  def top
    top = self
    top = top.whole until top.whole == top
    top
  end

  def remove(part)
    delete(part)
    part.whole = part
  end

  def replace(part, new_part)
    @parts[@parts.index(part)] = new_part if has_part?(part) && check_part?(new_part)
    new_part.update_whole(self)
    part
  end

  def flatten
    flat_list = []
    self.each do |part|
      flat_list << part unless part.parts.empty?
      flat_list << (part.parts.empty? ? part : part.flatten)
    end
    flat_list.flatten
  end

  def parts
    @parts.dup
  end

  def mass=(mass)
    raise(ArgumentError, "mass musst be a Number") unless mass.is_a?(Numeric)
    @mass = parts.empty? ? mass : @parts.map(&:mass).inject(0, :+)
  end

  def parts=(parts)
    parts.empty? ? @parts : [*parts].each { |part| add(part) }
  end

  def add(part)
    part.whole = self if check_part?(part)
    self.mass = 0 # was mache ich mit der Masse, wenn ein Teil schon Teile hat?
    @parts
  end

  def whole=(part)
    unless @whole == part
      part << self if check_part?(part)
      @whole.delete(self)
      update_whole(part)
    end
    update_whole(part)
  end

  def label=(label)
    @label = label.to_s
  end

  alias_method :eql?, :==
  def ==(other)
    return false unless other.is_a?(Part)
    return true if self.equal?(other)
    [@label, @mass, @parts, @whole] == [other.label, other.mass, other.parts, other.whole]
  end

  def each(&block)
     block_given? ? @parts.each(&block) : @parts.each
  end

  def to_s
    "#{@label}(#{@mass}kg)"
  end

  protected

  def check_part?(part)
    part.is_a?(Part) ? true : raise(ArgumentError, "#{part} is not a part")
  end

  def delete(part)
    check_part?(part)
    return @parts.delete(part) if @parts.include?(part)
    each { |sub_part| sub_part.delete(part) unless sub_part.parts.empty? }
    part
  end

  def has_part?(part)
    flatten.include?(part) ? true : raise(ArgumentError, "#{part} is not a part of #{whole.label}")
  end

  def update_whole(whole)
    @whole = whole
    @whole.mass = 0
    @whole
  end

  def <<(part)
    @parts << part
    self.define_singleton_method(:"#{part.label.downcase.gsub(/\s+/, "_")}") do
      part
    end
  end
end