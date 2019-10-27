require_relative '../modules/units'
require_relative '../lib/prettify'

# convert between multiple units and stores them for output
class UnitConverter
  include Units
  attr_accessor :digits, :conversion
  attr_reader :factor, :from_measure, :to_measure, :conversions

  def initialize
    @factor, @from_measure, @to_measure, @conversion = [*nil]
    @digits = 4
    @conversions = []
  end

  # check the unit and call the corresponding conversion method
  # @return [Numeric, String]
  def convert
    if Units.get_unit(from_measure) == :MENGENEINHEIT
      count_convert
    elsif Units.get_unit(from_measure) == :TEMPERATURE
      temp_convert
    else
      base_convert
    end
  end

  # factor relationship conversion formula on a basie measurement
  # @return [Numeric]
  def base_convert
    (factor * Units.dig_value(from_measure) / Units.dig_value(to_measure)).round(digits)
  end

  # convert between counting units
  # e.g. 1 gross schock
  # 01. calculate value -> 1 * 144 = 144
  # 02. schock in 1 gross -> 144 / 60 = 2 [24 leftover]
  # 03. repeat 02 for leftover in [gross, mandel, dutzend] -> 1 mandel [9 leftover]
  # 04. repeat 03 or save -> 9 saved
  # @return [String]
  def count_convert
    # 01. calculate value
    measure_val = (factor * Units.dig_value(from_measure)).round(digits)
    conv_result = [measure_val.round(digits).prettify, 'or']
    # prep: order unit by value and favor the target unit on top
    ordered_unit = Units.order_unit(Units.get_unit(from_measure))
    ordered_unit = { to_measure => ordered_unit.delete(to_measure) }.merge(ordered_unit)
    # loop between units for 02. & 03.
    ordered_unit.each do |measure, value|
      if value <= measure_val || measure == to_measure
        conv_result << "#{(measure_val / value).to_i} #{measure}"
        measure_val -= value * (measure_val / value).to_i
      end
    end
    # 04. save any leftovers and return string result
    conv_result << measure_val.round(digits).prettify if measure_val != 0
    conv_result.join(' ')
  end

  # convert between temps based on a pre-defined formulas
  # @return [Numeric]
  def temp_convert
    in_celsius = factor
    # convert to celsius if not
    unless from_measure == Units.get_key(:celsius)
      in_celsius = Units::TEMP_FORMULA[:from][from_measure].call(in_celsius)
    end
    Units::TEMP_FORMULA[:to][to_measure].call(in_celsius).round(digits)
  end

  # save the current conversion
  # @return [Array] conversions
  def save_conversion
    @conversions << ["#{factor.prettify} #{from_measure}", to_measure.to_s,
                     conversion.is_a?(Float) ? conversion.prettify : conversion]
  end

  # reset for the next conversion
  def reset
    @digits, @factor, @from_measure, @to_measure = 2, [*nil]
  end

  def each
    @conversions.each
  end

  def to_s
    "#{@conversion} #{@to_measure unless
        Units.get_unit(@from_measure) == :MENGENEINHEIT}"
  end

  # setters and validates
  def factor=(factor)
    @factor = Float(factor)
  rescue ArgumentError => e
    raise e.class, "'#{factor}' is not a valid value"
  end

  def from_measure=(from_measure)
    from_measure = from_measure.downcase.to_sym
    unless validate_from_measure?(from_measure)
      raise ArgumentError, "'#{from_measure}' is not a valid measurement"
    end

    @from_measure = Units.get_key(from_measure)
  end

  def to_measure=(to_measure)
    to_measure = to_measure.downcase.to_sym
    unless validate_to_measure?(to_measure)
      raise ArgumentError,
            "can't convert between #{Units.get_unit(from_measure)}" \
                              " and #{Units.get_unit(to_measure)}"
    end

    @to_measure = Units.get_key(to_measure)
  end

  def validate_from_measure?(from_measure)
    Units.measure?(from_measure)
  end

  def validate_to_measure?(to_measure)
    Units.same_unit?(from_measure, to_measure)
  end
end