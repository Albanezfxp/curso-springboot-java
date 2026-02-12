package curso_spring.model;


import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Person implements Serializable {

    @Serial
    private  static final long serialVersionUID = 1L;

    private long id;

    private String firstName;
    private String lastName;
    private String Address;
    private String Gender;


    public Person() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(Address, person.Address) && Objects.equals(Gender, person.Gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, Address, Gender);
    }
}
