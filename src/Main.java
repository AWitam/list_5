import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // zad 9

        Menu menu = new Menu();
        University university = menu.createUniversity("dsw");

        // 1. add students and starosts, update their data

        university.addStudent(menu.createStudent("Ewa", "Kowalska"));
        university.addStudent(menu.createStudent("Adam", "Kowalski"));
        university.addStudent(menu.createStarost("Kamil", "Nowak"));

        menu.updateStudent(university.getStudent("Ewa", "Kowalska"), LocalDate.of(1990, 1, 1), 82, 68);
        menu.updateStudent(university.getStudent("Adam", "Kowalski"), LocalDate.of(1990, 1, 1), 34, 34);
        menu.updateStudent(university.getStudent("Kamil", "Nowak"), LocalDate.of(1994, 4, 30), 50, 43);


        // 2. Find and serialize student
        Student ewa = university.getStudent("Ewa", "Kowalska");
        menu.serializeStudent(ewa);
        Student ewaDeserialized = menu.deserializeStudent("Ewa_Kowalska.xml");
        System.out.println("Deserialized " + ewaDeserialized);

        // example: using student methods on deserialized student
        menu.updatePartyPints(ewaDeserialized, 10);
        menu.updateSciencePoints(ewaDeserialized, 10);

        // 3. Disallow serialization of starost
        Starost kamil = (Starost) university.getStudent("Kamil", "Nowak");
        menu.serializeStudent(kamil);


        // 4. serialize and deserialize university
        menu.serializeUniversity(university);
        University universityDeserialized = menu.deserializeUniversity(String.format("%s_student_list.xml", university.getName()));
        System.out.println("\nDeserialized " + universityDeserialized);

    }
}