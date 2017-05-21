package videostore

class Statement(private val customerName: String) {
    private val rentals = mutableListOf<Rental>()
    var total: Double = 0.0
        private set
    var frequentRenterPoints: Int = 0
        private set

    fun addRental(rental: Rental) {
        rentals.add(rental)
    }

    fun generate(): String {
        clearTotals()
        var statementText = header()
        statementText += rentalLines()
        statementText += footer()
        return statementText
    }

    private fun clearTotals() {
        total = 0.0
        frequentRenterPoints = 0
    }

    private fun header() = "Rental Record for $customerName\n"

    private fun rentalLines(): String {
        val rentalLines = StringBuilder()
        for (rental in rentals) rentalLines.append(rentalLine(rental))
        return rentalLines.toString()
    }

    private fun rentalLine(rental: Rental): String {
        val rentalAmount = rental.determineAmount()
        frequentRenterPoints += rental.determineFrequentRenterPoints()
        total += rentalAmount
        return formatRentalLine(rental, rentalAmount)
    }

    private fun formatRentalLine(rental: Rental, rentalAmount: Double) =
            "\t%s\t%.1f\n".format(rental.title, rentalAmount)

    private fun footer() =
            ("You owed %.1f\n" +
                    "You earned %d frequent renter points\n")
                    .format(total, frequentRenterPoints)
}
