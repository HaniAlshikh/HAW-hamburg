class NaturalOrder

  include Comparable
  attr_reader :factor, :exponent
  protected :factor, :exponent

  def initialize(factor = 1, exponent = 0)
    raise ArgumentError, "#{exponent} musst be an Integer and >= 0" unless exponent.is_a?(Integer) && exponent >= 0
    raise ArgumentError, "#{factor} musst be odd" unless factor.is_a?(Integer) && factor > 0 && factor.odd?
    @exponent = exponent
    @factor = factor
  end

  def parent
    NaturalOrder.new((@factor - 2).abs, @exponent + 1)
  end

  def children
    return [self, self] if @exponent == 0
    j = value
    l_factor = (j - ((j& -j) >> 1)) / (2**(@exponent - 1))
    r_factor = (j + ((j& -j) >> 1)) / (2**(@exponent - 1))
    [NaturalOrder.new(l_factor, @exponent - 1),
     NaturalOrder.new(r_factor, @exponent - 1)]
  end

  def family
    result = []
    result << (members = children)
    begin
      grandchildren = []
      members.each { |child| grandchildren += child.children }
      members = grandchildren
      result << grandchildren
    end until grandchildren[0].exponent.zero?
    result
  end

  def value
    @factor * 2**@exponent
  end

  def succ
    NaturalOrder.new(@factor + 2, @exponent)
  end

  alias eql? <=>
  def <=>(other)
    return value <=> other if other.is_a?(Integer)
    value <=> other.value
  end

  def hash
    value.hash
  end

  def to_s
    value.to_s
  end

  def inspect
    value
  end
end