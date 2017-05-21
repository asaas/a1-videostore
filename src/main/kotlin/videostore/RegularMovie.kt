package videostore

class RegularMovie(title: String) : Movie(title) {
    override fun determineAmount(daysRented: Int): Double {
        var rentalAmount = 2.0
        if (daysRented > 2) rentalAmount += (daysRented - 2) * 1.5
        return rentalAmount
    }

    override fun determineFrequentRenterPoints(daysRented: Int) = 1
}
