module Units

  UNITS = {
    # length conversion factors based on meter
    LENGTH: { mm: 0.001, cm: 0.01, m: 1.0, km: 1000.0,
              in: 0.0254, ft: 0.3048, yd: 0.9144, mile: 1609.344,
              potrzebie: 0.0022633, mp: 2.2633e-6, Kp: 2.2633, Fp: 2263.3 },

    # mass conversion factors based on kg
    # TODO: what is ngogn's value =
    MASSE: { g: 0.001, kg: 1.0, Zentner: 100.0, Tonne: 1000,
             ounce: 0.0283495231, pound: 0.453592, ton: 907.18474,
             ngogn: 0, blintz: 0.0364253863, Kn: 101.9716005 },

    # temperature conversion factors  based on Celsius
    TEMPERATURE: { Celsius: 1.0, Kelvin: 274.15, Fahrenheit: 33.8, Smurdley: 0.27 },

    # old german units based on dutzend
    OLD_UNITS: { dutzend: 1, mandel: 1.25, schock: 5, gross: 12  }.freeze

  }.freeze

  @synonym = {
      mm: [:mm, :millimeter],
      cm: [:cm, :centimeter],
      m: [:m, :meter],
      km: [:km, :kilometer],
      in: [:in, :inch],
      ft: [:ft, :feet],
      yd: [:yd, :yard],
      mile: [:ml, :mile],
      potrzebie: [:potrzebie],
      mp: [:mp],
      Kp: [:Kp],
      Fp: [:Fp],
      g: [:g],
      kg: [:kg],
      Zentner: [:Zentner],
      Tonne: [:Tonne],
      ounce: [:ounce],
      pound: [:pound],
      ton: [:ton],
      ngogn: [:ngogn],
      blintz: [:blintz],
      Kn: [:Kn],
      Celsius: [:Celsius],
      Kelvin: [:Kelvin],
      Fahrenheit: [:Fahrenheit],
      Smurdley: [:Smurdley],
      dutzend: [:dutzend],
      mandel: [:mandel],
      schock: [:schock],
      gross: [:gross]
  }

  # return all supported units
  def self.get_units()
    UNITS.keys
  end

  def self.get_measurements()
    @synonym.keys()
  end

  def self.get_unit(measurement)
    if is_measur?(measurement)
      return UNITS.select { |unit, measurements| unit if measurements.key?(measurement) }.keys[0]
    end
    'Unknown'
  end

  def self.dig_value(measurement)
    UNITS.find { |unit, measurements| measurements[measurement] }[1][measurement]
  end

  def self.is_measur?(measur)
    @synonym.values.flatten.include?(measur)
  end

  def self.same_unit?(from_measur, to_measur)
    get_unit(from_measur) == get_unit(to_measur)
  end


end


