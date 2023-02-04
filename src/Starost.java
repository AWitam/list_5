import java.time.LocalDate;

public class Starost extends Student{
    private String groupName;

    public Starost(String name, String surname) {
        super(name, surname);
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    // postpone colloqium day by one day




}
