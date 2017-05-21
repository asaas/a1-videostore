package videostore

class Rental(private val movie: Movie, private val daysRented: Int) {
    val title: String
        get() = movie.title

    fun determineAmount() = movie.determineAmount(daysRented)
    fun determineFrequentRenterPoints() = movie.determineFrequentRenterPoints(daysRented)
}
