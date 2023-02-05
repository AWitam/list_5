import java.beans.*;
import java.io.*;
import java.time.LocalDate;
import java.util.List;


public class Menu {

    public University createUniversity(String name) {
        return new University(name);
    }

    public Student createStudent(String name, String surname) {
        return new Student(name, surname);
    }

    public void updateStudent(Student student, LocalDate birthdate, int sciencePoints, int lifePoints) {
        student.setBirthdate(birthdate);
        student.setSciencePoints(sciencePoints);
        student.setLifePoints(lifePoints);
    }

    public void updatePartyPints(Student student, int points) {
        student.party(points);
    }

    public void updateSciencePoints(Student student, int points) {
        student.learn(points);
    }

    public Starost createStarost(String name, String surname) {
        return new Starost(name, surname);
    }

    public void serializeStudent(Student student) {
        if (student instanceof Starost) {
            System.out.println("Cannot serialize starost \n");
            return;
        }

        try {
            String fileName = String.format("%s_%s.xml", student.getName(), student.getSurname());
            XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
            e.setPersistenceDelegate(LocalDate.class, new PersistenceDelegate() {
                @Override
                protected Expression instantiate(Object localDate, Encoder encdr) {
                    return new Expression(localDate, LocalDate.class, "parse", new Object[]{localDate.toString()});
                }
            });
            e.writeObject(student);
            e.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Student deserializeStudent(String fileName) throws IOException {
        XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
        d.close();
        return (Student) d.readObject();
    }

    public void serializeUniversity(University university) throws IOException {
        String fileName = String.format("%s_student_list.xml", university.getName());
        XMLEncoder e = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(fileName)));
        e.setPersistenceDelegate(LocalDate.class, new PersistenceDelegate() {
            @Override
            protected Expression instantiate(Object localDate, Encoder encdr) {
                return new Expression(localDate, LocalDate.class, "parse", new Object[]{localDate.toString()});
            }
        });

        e.setExceptionListener(err -> {
            if (err.getMessage().contains("Starost")) {
                System.out.println("Found starost on university list. Starost cannot be serialized. Skipping...");
            }
        });
        e.writeObject(university);
        e.close();
    }

    public University deserializeUniversity(String fileName) throws IOException {
        XMLDecoder d = new XMLDecoder(new BufferedInputStream(new FileInputStream(fileName)));
        University university = (University) d.readObject();
        d.close();
        return university;
    }

}
