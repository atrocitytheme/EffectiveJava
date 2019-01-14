package thirdsingleton;

public class EnumTest {
    Day day;

    public EnumTest(Day day) {
        this.day = day;
    }

    public void tellItlokeItIs() {
        switch(day) {
            case MONDAY:
                System.out.println("Monday");
                break;


            case FRIDAY:
                System.out.println("Friday");
                break;

            case SATURDAY:
                System.out.println("SATURDAY");
                break;

            default:
                System.out.println("Other");
                break;
        }
    }

    public static void main(String[] args) {
        EnumTest firstDay = new EnumTest(Day.MONDAY);
    }
}
