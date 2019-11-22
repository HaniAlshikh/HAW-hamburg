class TS_Pets
  def self.suite
    suite = Test::Unit::TestSuite.new
    suite << TC_MyFirstTests.suite
    suite << TC_MoreTestsByMe.suite
    suite << TS_AnotherSetOfTests.suite
    return suite
  end
end
Test::Unit::UI::Console::TestRunner.run(TS_MyTests)