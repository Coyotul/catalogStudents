import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Source
{
    public static void main(String[] args)
    {
        List<Professor> profesors = new ArrayList<>();
        List<Student> students = new ArrayList<>();

        Professor professor1 = new Professor("Mihai", "Ritan", List.of(new String[]{"AG", "MIP"}));
        profesors.add(professor1);
        Professor professor2 = new Professor("Andrei","Circei",List.of(new String[]{"Sport", "Chimie"}));
        profesors.add((professor2));
        Student student1 = new Student("George","Constantin");

    }
}
