require_relative '../modules/units'

class UnitConverter

  include Units
  attr_accessor :digits, :conversion
  attr_reader :factor, :from_measure, :to_measure, :conversions

  def initialize()
    @factor, @from_measure, @to_measure, @conversion = [*nil]
    @digits = 2
    @conversions = []
  end

  def convert()
    if Units::get_unit(from_measure) == :MENGENEINHEIT
      old_convert
    elsif Units::get_unit(from_measure) == :TEMPERATURE
      temp_convert
    else
      base_convert
    end
  end


  def base_convert()
    (factor * Units::dig_value(from_measure) / Units::dig_value(to_measure)).round(digits)
  end

  def old_convert()
    measure_val = factor * Units::dig_value(from_measure)
    conv_result = [measure_val, "oder"]

    unit = Units::UNITS[Units::get_unit(from_measure)]
    ordered_unit = unit.sort_by(&:last).reverse.to_h
    ordered_unit = {to_measure => ordered_unit.delete(to_measure)}.merge(ordered_unit)

    ordered_unit.each do |measure, value|
      if value <= measure_val
        conv_result << "#{(measure_val / value).to_i} #{measure}"
        measure_val -= value * (measure_val / value).to_i
      end
    end

    conv_result << measure_val if measure_val != 0
    conv_result.join(' ')
  end

  def temp_convert()
    in_celsius = factor
    unless from_measure == Units::get_key(:celsius)
      in_celsius = Units::TEMP_FORMULA[:from][from_measure].(in_celsius)
    end

    Units::TEMP_FORMULA[:to][to_measure].(in_celsius).round(digits)
  end

  def save_conversion()
    @conversions << ["#{factor} #{from_measure}","#{to_measure}", conversion]
  end

  def reset()
    @digits, @factor, @from_measure, @to_measure = 2, [*nil]
  end

  def each()
    @conversions.each
  end

  def to_s()
    "#{@conversion} #{@to_measure unless
        Units::get_unit(@from_measure) == :MENGENEINHEIT}"
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