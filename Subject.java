import java.util.Date;
import java.util.Map;

public class Subject
{
    private String subject;
    private Map<Integer, Date> grades;

    Subject(String subjectName)
    {
        subject = subjectName;
    }

    void addGrade(int grade)
    {
        grades.put(grade,new Date());
    }

}
