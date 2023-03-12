package seedu.commands;

import seedu.Expenditure.ExpenditureList;
import seedu.Expenditure.TuitionExpenditure;

import java.time.LocalDate;

public class TuitionExpenditureCommand extends Command{
    public static final String COMMAND_WORD = "tuition";
    private final String tuitionExpenditureDescription;
    private final double tuitionExpenditureValue;
    private final LocalDate tuitionExpenditureDate;

    public TuitionExpenditureCommand(String description, double value, LocalDate date) {
        this.tuitionExpenditureDescription = description;
        this.tuitionExpenditureValue = value;
        this.tuitionExpenditureDate = date;
    }

    public CommandResult execute(ExpenditureList expenditures) {
        TuitionExpenditure tuitionExpenditure = new TuitionExpenditure(tuitionExpenditureDescription,
                tuitionExpenditureValue, tuitionExpenditureDate);
        expenditures.addExpenditure(tuitionExpenditure);
        return new CommandResult(String.format("Added %s expenditure %s", COMMAND_WORD, tuitionExpenditure.toString()));
    }
}