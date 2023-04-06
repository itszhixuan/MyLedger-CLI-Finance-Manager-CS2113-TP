package seedu.expenditure;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class TuitionExpenditure extends Expenditure {

    public static final String EXPENDITURE_TYPE = "Tu";
    public static String iconPaid = "[X]";
    public static String iconUnpaid = "[ ]";
    private boolean isPaid;
    private LocalDate nextRepeatDate;

    public TuitionExpenditure(String description, double value, LocalDate date) {
        super(description, value, date);
        isPaid = false;
        nextRepeatDate = date;
        //nextRepeatDate = getNextRepeatDate();
    }

    public void setPaid() {
        isPaid = true;
    }

    public void resetPaid() {
        isPaid = false;
    }

    public String getPaidIcon() {
        return (isPaid) ? iconPaid : iconUnpaid;
    }

    public void checkMark() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.equals(nextRepeatDate) || currentDate.isAfter(nextRepeatDate)) {
            isPaid = false;
            nextRepeatDate = getNextRepeatDate();
        }
    }

    public LocalDate getNextRepeatDate() {
        String stringNextYear = fetchNextYear();
        String stringNextMonth = fetchNextMonth();
        String stringNextDay = fetchNextDay();
        String newDate = String.format("%s-%s-%s", stringNextYear, stringNextMonth, stringNextDay);
        return LocalDate.parse(newDate);
    }

    public String fetchNextYear() {
        // Repeats annually
        int nextYear = nextRepeatDate.getYear() + 1;
        return Integer.toString(nextYear);
    }

    public String fetchNextMonth() {
        int nextMonth = nextRepeatDate.getMonthValue();
        if (nextMonth < 10) {
            return  "0" + nextMonth;
        } else {
            return Integer.toString(nextMonth);
        }
    }

    public String fetchNextDay() {
        int nextDay = nextRepeatDate.getDayOfMonth();
        if (nextDay < 10) {
            return "0" + nextDay;
        }
        return Integer.toString(nextDay);
    }

    @Override
    public String toString() {
        return String.format("[Tuition] || %s || %s", getPaidIcon(), super.toString());
    }

    @Override
    public String expenditureString(String currency) {
        return String.format("[Tuition] || %s || %s", getPaidIcon(), super.expenditureString(currency));
    }

    public String getExpenditureType() {
        return EXPENDITURE_TYPE;
    }

    @Override
    public String saveInfo() {
        return getExpenditureType() +
                "d/" + getDescription() +
                "v/" + getValue() +
                "t/" + getDate() +
                "p/" + getPaidIcon() +
                "n/" + "None" +
                "o/" + "None" + "\n";
    }
}
