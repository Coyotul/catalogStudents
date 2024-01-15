import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Student implements Comparable {
    private String firstName;
    private String lastName;
    private List<Subject> subjects;

    public Float media=-1f;

    Student(String fName, String lName)
    {
        firstName = fName;
        lastName = lName;
        subjects = new ArrayList<>();
    }

    void addSubject(String subjectName)
    {
       Subject addSubject =  new Subject(subjectName);
       subjects.add(addSubject);
    }
    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public List<Subject> getSubjects()
    {
        return subjects;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
