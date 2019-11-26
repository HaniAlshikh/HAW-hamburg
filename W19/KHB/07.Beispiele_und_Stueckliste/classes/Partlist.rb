class Partlist

  def initialize(*parts)
    @list = parts
  end

  def top
    @list.first
  end

  def add(part)
    sub_parts << part if check_part?(part)
    self.mass = 0
    sub_parts
  end

  def remove(part)
    sub_parts.remove(part)
  end

  def replace(part, new_part)
    sub_parts[sub_parts.index(part)] = new_part if has_part?(part) && check_part?(part)
  end


  private

  def check_part?(part)
    part.is_a?(Part) ? true : raise(ArgumentError, "#{part} is not a part")
  end

  def has_part?(part)
    whole.sub_parts.include?(part) ? true : raise(ArgumentError, "#{part} is not a part of #{whole.label}")
  end

end
