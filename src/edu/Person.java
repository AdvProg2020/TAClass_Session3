package edu;

public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String nationalCode;
    protected long loanReceived;
    public static final long studentLoanAmount = 5000000;
    public static final long professorLoanAmount = 10000000;

    public Person(String firstName, String lastName, String nationalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public abstract void receiveLoan();

}
