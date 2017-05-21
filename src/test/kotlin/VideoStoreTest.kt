import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import videostore.*

class VideoStoreTest {
    private val DELTA = .001
    lateinit private var statement: Statement
    lateinit private var newReleaseMovie1: Movie
    lateinit private var newReleaseMovie2: Movie
    lateinit private var childrensMovie: Movie
    lateinit private var regular1: Movie
    lateinit private var regular2: Movie
    lateinit private var regular3: Movie

    @Before fun setUp() {
        statement = Statement("Customer")
        newReleaseMovie1 = NewReleaseMovie("New Release 1")
        newReleaseMovie2 = NewReleaseMovie("New Release 2")
        childrensMovie = ChildrensMovie("Childrens")
        regular1 = RegularMovie("Regular 1")
        regular2 = RegularMovie("Regular 2")
        regular3 = RegularMovie("Regular 3")
    }

    @Test fun testSingleNewReleaseStatementTotals() {
        statement.addRental(Rental(newReleaseMovie1, 3))
        statement.generate()
        assertEquals(9.0, statement.total, DELTA)
        assertEquals(2, statement.frequentRenterPoints.toLong())
    }

    @Test fun testDualNewReleaseStatementTotals() {
        statement.addRental(Rental(newReleaseMovie1, 3))
        statement.addRental(Rental(newReleaseMovie2, 3))
        statement.generate()
        assertEquals(18.0, statement.total, DELTA)
        assertEquals(4, statement.frequentRenterPoints.toLong())
    }

    @Test fun testSingleChildrensStatementTotals() {
        statement.addRental(Rental(childrensMovie, 4))
        statement.generate()
        assertEquals(3.0, statement.total, DELTA)
        assertEquals(1, statement.frequentRenterPoints.toLong())
    }

    @Test fun testMultipleRegularStatementTotals() {
        statement.addRental(Rental(regular1, 1))
        statement.addRental(Rental(regular2, 2))
        statement.addRental(Rental(regular3, 3))
        statement.generate()
        assertEquals(7.5, statement.total, DELTA)
        assertEquals(3, statement.frequentRenterPoints.toLong())
    }

    @Test fun testMultipleRegularStatementFormat() {
        statement.addRental(Rental(regular1, 1))
        statement.addRental(Rental(regular2, 2))
        statement.addRental(Rental(regular3, 3))
        assertEquals(
                "Rental Record for Customer\n" +
                        "\tRegular 1\t2.0\n" +
                        "\tRegular 2\t2.0\n" +
                        "\tRegular 3\t3.5\n" +
                        "You owed 7.5\n" +
                        "You earned 3 frequent renter points\n",
                statement.generate())
    }
}
