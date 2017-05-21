package videostore

abstract class Movie constructor(val title: String) {
    abstract fun determineAmount(daysRented: Int): Double
    abstract fun determineFrequentRenterPoints(daysRented: Int): Int
}
