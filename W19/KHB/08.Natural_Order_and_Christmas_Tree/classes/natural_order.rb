#####################################################################
# Assigment sheet A08: Natural Order and Christmas Tree Pattern.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

# generate odd multiple by 2^n
class NaturalOrder
  include Comparable
  attr_reader :exponent, :value
  protected :exponent

  def initialize(factor = 1, exponent = 0)
    raise ArgumentError, "exponent musst be an Integer and >= 0" unless exponent.is_a?(Integer) && exponent >= 0
    raise ArgumentError, "factor musst be odd" unless factor.is_a?(Integer) && factor.positive? && factor.odd?
    @exponent = exponent
    @factor = factor
    @value = @factor * 2**@exponent
  end

  # @return [NaturalOrder] node parent
  def parent
    self.class.new((@factor - 2).abs, @exponent + 1)
  end

  # @return [Array] array of the node children
  def children
    return [self, self] if @exponent == 0
    [self.class.new((@value - ((@value& -@value) >> 1)) / (2**(@exponent - 1)), @exponent - 1),
     self.class.new((@value + ((@value& -@value) >> 1)) / (2**(@exponent - 1)), @exponent - 1)]
  end

  # @return [Array] array of arrays, where each array is a tree level
  def family
    family = [] << members = children
    begin
      grandchildren = []
      members.each { |child| grandchildren += child.children }
      members = grandchildren
      family << grandchildren
    end until grandchildren[0].exponent.zero?
    family
  end

  def succ
    self.class.new(@factor + 2, @exponent)
  end

  alias eql? ==
  def <=>(other)
    return @value <=> other if other.is_a?(Integer)
    raise ArgumentError, "#{self.class} and #{other.class} are not comparable" unless other.is_a?(NaturalOrder)
    @value <=> other.value
  end

  def hash
    @value.hash
  end

  # TODO: alias inspect to_s not working, why?
  def to_s
    @value.to_s
  end

  def inspect
    to_s
  end
end