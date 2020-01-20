package Classes;

import java.sql.Timestamp;

public class Reservation
{
    private int numReservation;
    private Timestamp dateReservation;

    public Reservation(int numReservation, Timestamp dateReservation)
    {
        this.numReservation = numReservation;
        this.dateReservation = dateReservation;
    }   

    public Reservation(int aInt) {
        this.numReservation=aInt;
    }
}
