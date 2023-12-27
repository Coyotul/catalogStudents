import java.util.ArrayList;
import java.util.List;

public class Professor
{
    private String firstName;
    private String lastName;
    private List<String> subjects;

    public void setFirstName(String name)
    {
        firstName = name;
    }
    public void setLastName(String name)
    {
        lastName = name;
    }

    Professor(String fName,String lName)
    {
        firstName = fName;
        lastName = lName;
        subjects = new ArrayList<>();
    }
    Professor(String fName,String lName, List<String> Subjects)
    {
        firstName = fName;
        lastName = lName;
        subjects = Subjects;
    }
}
