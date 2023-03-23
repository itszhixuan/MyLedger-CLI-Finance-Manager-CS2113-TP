package seedu.expenditure;

import seedu.exceptions.NoPaidFieldException;
import seedu.txtdata.TxtFileStatus;
import java.io.IOException;
import java.util.ArrayList;

public class ExpenditureList {
    public static final int LIST_OFFSET = 1;
    private static ArrayList<Expenditure> expenditures;

    public ExpenditureList() {
        expenditures = new ArrayList<>();
    }

    public void addExpenditure(Expenditure expenditure) {
        expenditures.add(expenditure);
    }

    public void deleteExpenditure(int index) {
        expenditures.remove(index);
        expenditures.trimToSize();
    }

    public void markExpenditure(int index) throws NoPaidFieldException {
        Expenditure expenditure = expenditures.get(index);
        if (expenditure instanceof TuitionExpenditure) {
            ((TuitionExpenditure) expenditure).setPaid();
        } else if (expenditure instanceof AccommodationExpenditure) {
            ((AccommodationExpenditure) expenditure).setPaid();
        } else {
            throw new NoPaidFieldException();
        }
    }

    public void unmarkExpenditure(int index) throws NoPaidFieldException {
        Expenditure expenditure = expenditures.get(index);
        if (expenditure instanceof TuitionExpenditure) {
            ((TuitionExpenditure) expenditure).resetPaid();
        } else if (expenditure instanceof AccommodationExpenditure) {
            ((AccommodationExpenditure) expenditure).resetPaid();
        } else {
            throw new NoPaidFieldException();
        }
    }

    public int getSize() {
        return expenditures.size();
    }

    public Expenditure getExpenditure(int index) {
        return expenditures.get(index);
    }

    @Override
    public String toString() {
        StringBuilder stringOfExpenditures = new StringBuilder();
        for (int i = 0; i < expenditures.size(); i++) {
            final int expenditureNumber = i + 1;
            stringOfExpenditures.append(String.format("%d. %s\n", expenditureNumber, expenditures.get(i)));
        }
        return stringOfExpenditures.toString().stripTrailing();
    }

    public static void saveList() {
        try {
            TxtFileStatus.saveExpenditureList(expenditures);
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }
}