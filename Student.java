import java.util.ArrayList;
import java.util.List;
public class Student {
    private String firstName;
    private String lastName;
    private List<Integer> grades;

    Student(String fName, String lName)
    {
        firstName = fName;
        lastName = lName;
        grades = new ArrayList<>();
    }
}
