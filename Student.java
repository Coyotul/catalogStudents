import java.util.ArrayList;
import java.util.List;
public class Student {
    private String firstName;
    private String lastName;
    private List<Subject> grades;

    Student(String fName, String lName)
    {
        firstName = fName;
        lastName = lName;
        grades = new ArrayList<>();
    }

    void addSubject(String subjectName)
    {
       Subject addSubject =  new Subject(subjectName);
    }
}
