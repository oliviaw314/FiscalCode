public class Data {
    String name;
    String surname;
    String gender;
    String birthDate;

    public Data(String name, String surname, String gender, String birthDate) {
        this.name=name;
        this.surname=surname;
        this.gender=gender;
        this.birthDate=birthDate;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthDate() {
        return birthDate;
    }
}
