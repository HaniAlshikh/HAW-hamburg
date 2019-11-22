module Custom
  def self.try(err)
    yield if block_given?
  rescue err => e
    e
  end
end