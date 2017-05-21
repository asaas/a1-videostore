package videostore

class NewReleaseMovie(title: String) : Movie(title) {
    override fun determineAmount(daysRented: Int) = (daysRented * 3).toDouble()
    override fun determineFrequentRenterPoints(daysRented: Int) = if (daysRented > 1) 2 else 1
}
