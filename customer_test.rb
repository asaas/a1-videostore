require 'test/unit'
require_relative 'customer'
require_relative 'rental'
require_relative 'movie'

class CustomerTest < Test::Unit::TestCase
  setup do
    @customer = Customer.new("Fred")
  end

  test "Single new release statement" do
    @customer.add_rental(Rental.new(Movie.new("The Cell", Movie::NEW_RELEASE), 3))
    assert_equal(<<~EOS, @customer.statement)
      Rental Record for Fred
      \tThe Cell\t9
      Amount owed is 9
      You earned 2 frequent renter points
    EOS
  end

  test "Dual new release statement" do
    @customer.add_rental(Rental.new(Movie.new("The Cell", Movie::NEW_RELEASE), 3))
    @customer.add_rental(Rental.new(Movie.new("The Tigger Movie", Movie::NEW_RELEASE), 3))
    assert_equal(<<~EOS, @customer.statement)
      Rental Record for Fred
      \tThe Cell\t9
      \tThe Tigger Movie\t9
      Amount owed is 18
      You earned 4 frequent renter points
    EOS
  end

  test "Single children's statement" do
    @customer.add_rental(Rental.new(Movie.new("The Tigger Movie", Movie::CHILDRENS), 3))
    assert_equal(<<~EOS, @customer.statement)
      Rental Record for Fred
      \tThe Tigger Movie\t1.5
      Amount owed is 1.5
      You earned 1 frequent renter points
    EOS
  end

  test "Multiple regular statement" do
    @customer.add_rental(Rental.new(Movie.new("Plan 9 from Outer Space", Movie::REGULAR), 1))
    @customer.add_rental(Rental.new(Movie.new("8 1/2", Movie::REGULAR), 2))
    @customer.add_rental(Rental.new(Movie.new("Eraserhead", Movie::REGULAR), 3))
    assert_equal(<<~EOS, @customer.statement)
      Rental Record for Fred
      \tPlan 9 from Outer Space\t2
      \t8 1/2\t2
      \tEraserhead\t3.5
      Amount owed is 7.5
      You earned 3 frequent renter points
    EOS
  end
end
