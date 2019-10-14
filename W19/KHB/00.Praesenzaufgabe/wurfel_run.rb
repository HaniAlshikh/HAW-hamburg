# Losungsvorschlag fur die Wurfel Aufgabe
# Author:: Hani Alshikh
# Author:: Nick Rattay


require_relative 'wurfel'


def haufigkeit_rechnen(wurfel, wurf_anzahl)
  zahlen_haufigkeit = Hash.new(0)
  wurf_anzahl.times { zahlen_haufigkeit[wurfel.werfen] += 1 }
  zahlen_haufigkeit
end

def haufigkeit_print(haufigkeit_hash)
  haufigkeit_hash.each { |number, haufigkeit| puts "#{number} gewurfelt #{haufigkeit} Mal." }
end


# Eingabe
begin
  print "\nwie viele Wurfe: "
  wurf_anzahl = gets.chomp.to_i
end until wurf_anzahl > 0

begin
  print "\nbitte die Wahrscheinlichkeit der Zahl 6 eingeben (soll zwischen 1/6 und 1 sein): "
  wahscheinlichkeit = gets.chomp.to_f
end until wahscheinlichkeit >= 1/6 && wahscheinlichkeit <= 1


# Logik
puts "\nNormal"
haufigkeit_print(haufigkeit_rechnen(Wurfel.new, wurf_anzahl))

puts "\nGezinkert"
haufigkeit_print(haufigkeit_rechnen(Wurfel.new(wahscheinlichkeit), wurf_anzahl))



