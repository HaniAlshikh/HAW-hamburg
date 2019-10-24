require_relative '../modules/units'

class UnitConverter

  include Units
  attr_accessor :epsilon
  attr_reader  :value, :from_measur, :to_measur, :conversion, :conversions

  def initialize()
    @value = nil
    @from_measur = nil
    @to_measur = nil
    @conversion = nil
    @epsilon = Float::EPSILON
    @conversions = []
  end

  def convert()
    # convert from_unit to meter and then to to_unit
    @conversion = (value * Units::dig_value(from_measur) / Units::dig_value(to_measur))
  end



  def save_conversion()
    @conversions << ["#{value}#{from_measur}", "#{conversion}#{to_measur}"]
  end

  def reset()
    @value, @from_measur, @to_measur = [*nil]
  end



  def value=(value)
    @value = validate_value?(value) ? value : (raise ArgumentError, "'#{value}' not a valid value")
  end

  def from_measur=(from_measur)
    @from_measur = if validate_from_measur?(from_measur)
                     from_measur
                   else
                     raise ArgumentError, "#{from_measur} is not a valid measurement"
                   end
  end

  def to_measur=(to_measur)
    @to_measur = if validate_to_measur?(to_measur)
                   to_measur
                 else
                   raise ArgumentError, "can't convert between #{Units::get_unit(from_measur)} and "\
                                        "#{Units::get_unit(to_measur)}"
                 end
  end



  def validate_value?(value)
    value.is_a?(Float)
  end

  def validate_from_measur?(from_measur)
    Units::is_measur?(from_measur)
  end

  def validate_to_measur?(to_measur)
    Units::same_unit?(from_measur, to_measur)
  end



end