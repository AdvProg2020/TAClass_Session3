package edu;

public class Professor extends Person {
    private String rank;

    public Professor(String firstName, String lastName, String rank, String nationalCode) {
        super(firstName, lastName, nationalCode);
        this.rank = rank;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public void receiveLoan() {
        loanReceived += Person.professorLoanAmount;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "rank='" + rank + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nationalCode='" + nationalCode + '\'' +
                '}';
    }
}
