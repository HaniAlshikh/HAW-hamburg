####################################################
# Assigment sheet A01: Dealing with Strings in Ruby.
# Part 02 of the assigment.
#
# this script reads a file, process it's content and write it
# into a new file.
#
# Author:: Nick Marvin Rattay
# Author:: Hani Alshikh
#
####################################################

# extend the string class to support colored output
class String
  def green; "\e[32m#{self}\e[0m" end
end


puts
puts '####### Assigment sheet A01: Dealing with Strings in Ruby #######'
puts '#######            Part 02 of the assigment               #######'
puts

# 2.1. read with open and use a block
read_file = './data/picasso.txt'
puts "> 2.1. Reading #{read_file}".green
content_string = ''
File.open(read_file, 'r:UTF-8') do |contant|
  # 2.1. test with printing to console
  puts '##################### content start #####################'
  contant.each do |line|
    content_string += line and puts line.to_s
  end
  puts '##################### content end   #####################'
end
puts

# 2.2. split into one downcase word without logogramm per line
# 2.3. and reverse every word
words_final = []
word_frequency = Hash.new(0)
content_string.split.each do |word|
  word_modified = word.downcase.gsub(/[^[:alpha:]]/, '')
  # skip in cases like "-" (string with no letters) the resulted string is empty
  next if word_modified.empty?

  # 2.3. prep for writing into a file
  words_final << word_modified.reverse
  # 2.4. prep to print word frequency
  word_frequency[word_modified] += 1
end

# 2.3. write the modified words into a new file
write_file = './data/modified.txt'
puts '> 2.3. reversing and writing the modified words'.green
File.open(write_file, 'w') { |file| file.puts(words_final) }
puts "written to #{write_file}"
puts

# 2.4. print word frequency to console
puts '> 2.4. words frequency'.green
word_frequency.each { |word, frequency| puts "#{word} used #{frequency} times." }