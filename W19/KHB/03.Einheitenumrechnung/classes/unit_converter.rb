require_relative '../modules/units'

class UnitConverter

  include Units
  attr_accessor :epsilon, :conversion
  attr_reader :factor, :from_measure, :to_measure, :conversions

  def initialize()
    @factor, @from_measure, @to_measure, @conversion = [*nil]
    @epsilon = Float::EPSILON
    @conversions = []
  end

  def convert()
    if Units::get_unit(from_measure) == "OLD UNITS"
      old_convert
    elsif Units::get_unit(from_measure) == "TEMPERATURE"
      temp_convert
    else
      base_convert
    end
  end


  def base_convert()
    (factor * Units::dig_value(from_measure) / Units::dig_value(to_measure))
  end

  def old_convert()
    conv_result = []
    measure_val = factor * Units::dig_value(from_measure)
    to_in_from = base_convert.to_i

    conv_result << "#{to_in_from} #{to_measure}"
    measure_val -= Units::dig_value(to_measure) * to_in_from

    unless measure_val == 0
      Units::UNITS['OLD UNITS'].sort_by(&:last).reverse.to_h.each do |measure, value|
        if value <= measure_val
          conv_result << "#{(measure_val / value).to_i} #{measure}"
          measure_val -= value * (measure_val / value).to_i
        end
      end

      conv_result << measure_val if measure_val != 0
    end
    conv_result
  end

  def temp_convert()
    in_celsius = factor
    unless from_measure == Units::get_key('celsius')
      in_celsius = Units::TEMP_FORMULA['from'][Units::get_key(from_measure)].(factor)
    end
    Units::TEMP_FORMULA['to'][Units::get_key(to_measure)].(in_celsius)
  end

  def save_conversion()
    @conversions << ["#{factor}#{from_measure}","#{to_measure}", "#{conversion}"]
  end

  def reset()
    @factor, @from_measure, @to_measure = [*nil]
  end

  def each()
    @conversions.each
  end

  def factor=(factor)
    @factor = validate_factor?(factor) ? factor : (raise ArgumentError, "'#{factor}' not a valid value")
  end

  def from_measure=(from_measure)
    @from_measure = if validate_from_measure?(from_measure)
                      Units::get_key(from_measure)
                    else
                     raise ArgumentError, "'#{from_measure}' is not a valid measurement"
                    end
  end

  def to_measure=(to_measure)
    @to_measure = if validate_to_measure?(to_measure)
                    Units::get_key(to_measure)
                  else
                   raise ArgumentError, "can't convert between #{Units::get_unit(from_measure)} and "\
                                        "#{Units::get_unit(to_measure)}"
                  end
  end



  def validate_factor?(factor)
    factor.is_a?(Float)
  end

  def validate_from_measure?(from_measure)
    Units::is_measure?(from_measure)
  end

  def validate_to_measure?(to_measure)
    Units::same_unit?(from_measure, to_measure)
  end

end