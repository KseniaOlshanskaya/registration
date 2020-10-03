public class Person {
    private String personInitials;
    private String birthdayDate;

    public Person(String initials, String date) {
        this.personInitials = initials;
        this.birthdayDate = date;
    }
    public String getInitials() {
        return this.personInitials;
    }

    public String getBirthdayDate() {
        return this.birthdayDate;
    }
}
