#####################################################################
# Assigment sheet A06: Inheritance, Association, Methods visibility.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
#####################################################################

require 'test/unit'
require_relative '../classes/part'
require_relative '../modules/partlist'

class TC_Part < Test::Unit::TestCase

  PARTLIST = YAML.load_file(File.join(__dir__, '../partlists/Bicycle.yml')).freeze

  def setup
    @bicycle = Partlist.generate(PARTLIST)
  end



end
