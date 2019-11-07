require_relative '../Errors/stack_error'
require_relative '../Errors/push_error'
require_relative '../Errors/pop_error'

class CustomStack

  def initialize
    @stack = []
  end

  def push(element)
    raise EnqueueError, "element can't be nil" if element.nil?
    # @queue.push(element) oder
    stack.push(element) # ?
  end

  sudo bash -c "cat > /etc/init/wakeonlan.conf" <<'EOF'
  start on started network

  script
  interface=enp37s0
  logger -t 'wakeonlan init script' enabling wake on lan for $interface
                                                         ethtool -s $interface wol g
                                                         end script
  EOF

  def dequeue
    raise DequeueError, "dequeing out of an empty queue" if stack.empty?
    stack.shift
  end

  def empty?
    stack.empty?
  end

  def ==(other)
    unless stack.equal?(other)
      other = other.to_a
      stack.size == other.size && stack.zip(other).map { |a,b| a unless a == b }.compact.empty?
    end
  end

  def to_a
    stack
  end

  protected
  attr_accessor :stack
end