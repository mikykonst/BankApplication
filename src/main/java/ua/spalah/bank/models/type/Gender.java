package ua.spalah.bank.models.type;

public enum Gender {
    MALE("Mr."),
    FEMALE("Mrs.");

    private final String salutation;

    Gender(String salutation) {
        this.salutation = salutation;
    }

    public String getSalutation() {
        return salutation;
    }
}