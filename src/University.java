import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class University implements Serializable {
    private List<Student> students = new ArrayList<>();
    private String name;

    public University() {
    }

    public University(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }


    public List getStudents() {
        return students;
    }

    public void setStudents(List students) {
        this.students = students;
    }

    public Student getStudent(String name, String surname) {
        for (Student student : students) {
            if (student.getName().equals(name) && student.getSurname().equals(surname)) {
                return student;
            }
        }
        return null;
    }

    public boolean removeStudent(String name, String surname) {
        Student student = getStudent(name, surname);
        if (student != null) {
            students.remove(student);
            return true;
        }
        return false;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("University: \nname: %s, \nstudents: \n%s", name, students.toString());
    }

}
