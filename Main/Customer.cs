using System;
using System.Collections;
using System.Collections.Generic;

namespace Main
{
    public class Customer
    {
        public String Name { get; }
        private List<Rental> rentals = new List<Rental>();

        public Customer(String name)
        {
            Name = name;
        }
        
        public void AddRental(Rental rental)
        {
            rentals.Add(rental);
        }

        public String Statement()
        {
            double totalAmount = 0;
            int frequentRenterPoints = 0;
            IEnumerator rentals = this.rentals.GetEnumerator();
            String result = "Rental Record for " + Name + "\n";

            while (rentals.MoveNext())
            {
                double thisAmount = 0;
                Rental each = (Rental)rentals.Current;

                switch (each.Movie.PriceCode)
                {
                    case Movie.REGULAR:
                        thisAmount += 2;
                        if (each.DaysRented > 2)
                            thisAmount += (each.DaysRented - 2) * 1.5;
                        break;
                    case Movie.NEW_RELEASE:
                        thisAmount += each.DaysRented * 3;
                        break;
                    case Movie.CHILDRENS:
                        thisAmount += 1.5;
                        if (each.DaysRented > 3)
                            thisAmount += (each.DaysRented - 3) * 1.5;
                        break;
                }

                frequentRenterPoints++;
                if ((each.Movie.PriceCode == Movie.NEW_RELEASE) && each.DaysRented > 1)
                    frequentRenterPoints++;

                result += "\t" + each.Movie.Title + "\t" + thisAmount.ToString("N1") + "\n";
                totalAmount += thisAmount;
            }

            result += "You owed " + totalAmount.ToString("N1") + "\n";
            result += "You earned " + frequentRenterPoints.ToString() + " frequent renter points\n";

            return result;
        }
    }
}
