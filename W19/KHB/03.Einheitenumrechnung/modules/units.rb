require 'yaml'

module Units

  # UNITS = JSON.parse(File.read("config/units.json")).freeze
  # SYNONYM = JSON.parse(File.read("config/synonyms.json")).freeze

  UNITS = YAML.load_file(File.join(__dir__, '../config/units.yml')).freeze
  SYNONYM = YAML.load_file(File.join(__dir__, '../config/synonyms.yml')).freeze

  TEMP_FORMULA = { from: {
                      fahrenheit: ->(factor) { (factor - 32) / 1.8},
                      kelvin: ->(factor) { factor - 273.15 },
                      smurdley: ->(factor) { (factor + 273) / 3 }
                  },
                   to: {
                      celsius: ->(factor) { factor * 1 },
                      fahrenheit: ->(factor) { factor * 1.8 + 32 },
                      kelvin: ->(factor) { factor + 273.15 },
                      smurdley: ->(factor) { 3 * factor - 273 }
                  }
                 }.freeze

  # return all supported units
  def self.get_units()
    UNITS.keys
  end

  def self.get_measurements()
    SYNONYM.keys()
  end

  def self.get_unit(measurement)
    if is_measure?(measurement)
      UNITS.find { |unit, measurements| measurements.key?(get_key(measurement)) }.first
    else
      'Unknown'
    end
  end

  def self.get_key(measurement)
    SYNONYM.find { |_measure, synonyms| synonyms.include?(measurement) }.first
  end

  def self.dig_value(measurement)
    UNITS[get_unit(measurement)][measurement]
  end


  def self.is_measure?(measure)
    SYNONYM.values.flatten.include?(measure)
  end

  def self.same_unit?(from_measure, to_measure)
    get_unit(from_measure) == get_unit(to_measure)
  end

end

