require 'test/unit'
require_relative '../classes/unit_converter'

# test UnitConverter for falls conversion
class ConversionTest < Test::Unit::TestCase
  attr_reader :unit_converter
  def setup
    @unit_converter = UnitConverter.new
  end

  def test_general
    unit_converter.from_measure = :km
    unit_converter.to_measure = :m
    unit_converter.factor = 1
    unit_converter.digits = 4
    expected = 1000

    test(expected)
  end

  # we could test all units in one testing method
  # but that's not how testing works???
  def test_length
    # work around same_unit? validation
    unit_converter.from_measure = :m
    unit_converter.to_measure = :m
    unit_converter.factor = 1

    Units::UNITS[:LENGTH].each do |measure, expected|
      unit_converter.from_measure = measure
      test((unit_converter.factor * expected).round(unit_converter.digits))
    end
  end

  def test_mass
    # work around same_unit? validation
    unit_converter.from_measure = :kg
    unit_converter.to_measure = :kg
    unit_converter.factor = 10

    Units::UNITS[:MASS].each do |measure, expected|
      unit_converter.from_measure = measure
      test((unit_converter.factor * expected).round(unit_converter.digits))
    end
  end

  def test_temp
    # work around same_unit? validation
    unit_converter.from_measure = :celsius
    unit_converter.to_measure = :celsius
    unit_converter.factor = 1

    Units::UNITS[:TEMPERATURE].each do |measure, expected|
      unit_converter.from_measure = measure
      test((unit_converter.factor * expected).round(unit_converter.digits))
    end
  end

  def test_count
    # work around same_unit? validation
    unit_converter.from_measure = :dutzend
    unit_converter.to_measure = :dutzend
    unit_converter.factor = 1

    expected = {dutzend: '12 or 1 dutzend',
                mandel: '15 or 1 dutzend 3',
                schock: '60 or 5 dutzend',
                gross: '144 or 12 dutzend'}

    Units::UNITS[:MENGENEINHEIT].each do |measure, _expected|
      unit_converter.from_measure = measure
      test(expected[measure])
    end
  end

  private

  def test(expected)
    assert_equal(expected, unit_converter.convert,
         "#{unit_converter.factor} #{unit_converter.from_measure} should"\
                 " equal #{expected} #{unit_converter.to_measure}")
  end
end