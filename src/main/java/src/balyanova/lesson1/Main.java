package src.balyanova.lesson1;

public class Main {
    public static void main(String[] args) {
        Person myPerson = new Person.Builder()
                .withFirstName("John")
                .withLastName("Smith")
                .withMiddleName("Max")
                .withCountry("USA")
                .withPhone("+3557843")
                .withAddress("Lane Street, 12")
                .withAge(28)
                .withGender("male")
                .build();
        System.out.println(myPerson.getAddress());
    }
}
