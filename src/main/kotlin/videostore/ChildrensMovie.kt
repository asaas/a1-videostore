package videostore

class ChildrensMovie(title: String) : Movie(title) {
    override fun determineAmount(daysRented: Int): Double {
        var rentalAmount = 1.5
        if (daysRented > 3) rentalAmount += (daysRented - 3) * 1.5
        return rentalAmount
    }

    override fun determineFrequentRenterPoints(daysRented: Int) = 1
}
