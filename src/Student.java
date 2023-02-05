import java.io.Serializable;
import java.time.LocalDate;

public class Student implements Serializable {
    private String name;
    private String surname;
    transient private LocalDate birthdate;
    private int sciencePoints;
    private int lifePoints;

    public Student() {
    }

    Student(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public int getSciencePoints() {
        return sciencePoints;
    }

    public int getLifePoints() {
        return lifePoints;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setSciencePoints(int sciencePoints) {
        this.sciencePoints = sciencePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public void learn(int points) {
        this.sciencePoints += points;
    }

    public void party(int points) {
        this.lifePoints -= points;
    }

    public boolean isGoodStudent() {
        return this.sciencePoints > 75;
    }

    @Override
    public String toString() {
        return String.format("Student: %s %s, \nbirthdate: %s,\nscience points: %d, \nlife points: %d \n \n", name, surname, birthdate, sciencePoints, lifePoints);
    }
}
