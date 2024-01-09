import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Subject
{
    private String subject;
    private List<Integer> grades;
    private List<Date> dates;

    Subject(String subjectName)
    {
        subject = subjectName;
        grades = new ArrayList<>();
        dates = new ArrayList<>();
    }

    void addGrade(Integer grade)
    {
        grades.add(grade);
        dates.add(new Date());
    }

    void addGradeWithDate(Integer grade,Date date)
    {
        grades.add(grade);
        dates.add(date);
    }
    public String getName()
    {
        return subject;
    }


    public List<Integer> getGrades()
    {
        return grades;
    }
    public List<Date> getDates()
    {
        return dates;
    }
}
