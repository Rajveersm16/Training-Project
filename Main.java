interface BookingService{
    void book(Booking booking);
    void cancel(int bookingId);
    Booking findBookingById(int id);
}

class TourBooking{
    private int ID;
    private String destination;
    private double price;

    public TourBooking(int id, String dest, double rate){
        ID=id;
        destination=dest;
        price= rate;
    };
    public int getID(){
        return ID;
    }
    public String getDestination(){
        return destination;
    }
    public double getPrice(){
        return price;
    }
    public void TourDetail(){
        System.out.println("TourPackage{"+"ID="+ID+",destination="+destination+",price="+price+"}" );
      }
}

class Booking{
    private int ID;
    private String customerName;
    private TourBooking tourPackage;

    // Constructor, getters, and setters
    public Booking(int id, String cName, TourBooking tPackage) {
        ID = id;
        customerName = cName;
        tourPackage = tPackage;
    }

    public int getId() {
        return ID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public TourBooking getTourPackage() {
        return tourPackage;
    }

    public void BookingDetail(){
        System.out.println("Booking{"+"ID="+ID+", customerName='" + customerName +", tourPackage=" + tourPackage+"}" );
      }
}


class BookingManager implements BookingService {
    private Booking[] bookings;
    private int bookingCount;

    public BookingManager(int capacity) {
        bookings = new Booking[capacity];
        bookingCount = 0;
    }

    public void book(Booking booking) {
        if (bookingCount < bookings.length) {
            bookings[bookingCount] = booking;
            bookingCount++;
        } else {
            System.out.println("Booking limit reached.");
        }
    }

    public void cancel(int bookingId) {
        for (int i = 0; i < bookingCount; i++) {
            if (bookings[i].getId() == bookingId) {
                bookings[i] = bookings[bookingCount - 1];
                bookings[bookingCount - 1] = null;
                bookingCount--;
                break;
            }
        }
    }

    public Booking findBookingById(int id) {
        for (int i = 0; i < bookingCount; i++) {
            if (bookings[i].getId() == id) {
                return bookings[i];
            }
        }
        return null;
    }

    public Booking[] getBookings() {
        Booking[] currentBookings = new Booking[bookingCount];
        for (int i = 0; i < bookingCount; i++) {
            currentBookings[i] = bookings[i];
        }
        return currentBookings;
    }
}

public class Main {
    public static void main(String[] args) {
        BookingManager bookingManager = new BookingManager(10);

        TourBooking obj1 = new TourBooking(1, "Paris", 1500.0);
        TourBooking obj2 = new TourBooking(2, "New York", 2000.0);

        Booking booking1 = new Booking(1, "John Doe", obj1);
        
        bookingManager.book(booking1);

        System.out.println("Bookings:");
        for (Booking booking : bookingManager.getBookings()) {
            System.out.println(booking);
        }

        bookingManager.cancel(1);

        System.out.println("Bookings after cancellation:");
        for (Booking booking : bookingManager.getBookings()) {
            System.out.println(booking);
        }
    }
}
