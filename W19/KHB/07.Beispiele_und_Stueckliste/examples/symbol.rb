



test = "test"
# not possible as #{} will call to_s on what ever in it
puts "#{"#{:test}".class}"
# we could how ever interpolate than convert to symbol
puts :"test_#{test}".class