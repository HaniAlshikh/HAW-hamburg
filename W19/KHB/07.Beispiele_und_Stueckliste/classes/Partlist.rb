class Partlist

  attr_reader :parts

  def initialize(*parts)
    @parts = parts
    @mass = 0
  end

end
