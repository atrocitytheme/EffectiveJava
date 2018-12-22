package reuse;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PersonCorrect {
    private Date birthDate;

    private static final Date BOOM_START;
    private static final Date BOOM_END;
    // use static constructor s.t we don't need to craete intermediate 
    // object every time in the implementation of isBabyBoomer
    static {
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946, Calendar.JANUARY, 1, 0, 0 ,0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965, Calendar.JANUARY, 1, 0, 0, 0);
        BOOM_END = gmtCal.getTime();
    }

    public boolean isBabyBoomer() {
        return birthDate.compareTo(BOOM_START) >= 0 && birthDate.compareTo(BOOM_END) < 0;
    }
}
