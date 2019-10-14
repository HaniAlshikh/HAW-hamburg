class Wurfel

  def initialize(wahrscheinlichkeit = 1.0/6)
    @wahrscheinlichkeit = wahrscheinlichkeit
  end

  def werfen
    @wahrscheinlichkeit >= rand ? 6 : rand(1..5)
  end

end
