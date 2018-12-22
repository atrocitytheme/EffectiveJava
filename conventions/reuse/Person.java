package reuse;

/*
* You can often avoid creating unnecessary objects by using static
* factory meth- ods (Item 1) in preference to constructors on immutable
* classes that provide both
* reuse of classes that are mutable or immutable
* */


import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Person {
    private Date birthDate;

    // Other fields, methods, and constructor omitted

    //Don't do this:
    public boolean isBabyBoomer() {
        //Unnecessary allocation of expensive objects

        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        Date boomStart = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0 ,0);

        Date boomEnd = gmtCal.getTime();
        return birthDate.compareTo(boomStart) >= 0 &&
                birthDate.compareTo(boomEnd)   <  0;

        //The isBabyBoomer method unnecessarily creates a new Calendar, TimeZone, and two Date instances each time it is invoked.
    }
}
