import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Source
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        List<Professor> profesors = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        List<String> passwords = new ArrayList<>();
        boolean isStudent=false;
        ReadData(profesors,students,passwords);

        for(int i=0;i<students.size();i++)
        {
            for(int j=0;j<profesors.size();j++)
            {
                for(int k=0;k<profesors.get(j).getSubjects().size();k++)
                    students.get(i).addSubject(profesors.get(j).getSubjects().get(k));
            }
        }

        Integer input=1,id=-1;
        System.out.println("Introduceti Parola:");
        String password = scanner.nextLine();
        for(int i=0;i<profesors.size();i++)
        {
            if(passwords.get(i).equals(password))
            {
                id = i;
                break;
            }
        }
        for(int i=0;i<students.size();i++)
        {
            if(passwords.get(i+profesors.size()-1).equals(password))
            {
                id = i+profesors.size();
                break;
            }
        }
        if(id == -1)
        {
            System.out.println("Parola gresita");
            System.out.println(passwords.size());
            input=0;
        }
        else {
            System.out.print("Bun venit ");

            if (id >= profesors.size()) {
                System.out.print(students.get(id-profesors.size()-1).getLastName());
                System.out.print(" ");
                System.out.print(students.get(id-profesors.size()-1).getFirstName());
                System.out.println();
                input = 2;
            } else {
                input = 1;
                System.out.print(profesors.get(id).getLastName());
                System.out.print(" ");
                System.out.print(profesors.get(id).getFirstName());
                System.out.println();
            }
        }
        while (input!=0) {
            if (input == 2)
            {
                isStudent = true;
            }
            if(!isStudent) {
                System.out.println("Meniu Profesor:");
                System.out.println("1:Alegeti 1 sa adaugati note");
                System.out.println("2:Alegeti 2 sa sortati lista de discipline asociate");
                System.out.println("3:Alegeti 3 sa sortati lista de elevi");
                System.out.println("4:Alegeti 4 sa salvati toate datele in fisier");
                System.out.println("5:Alegeti 5 sa afisati notele elevilor");
                System.out.println("6:Alegeti 6 sa calculati media unui elev");
                System.out.println("7:Citire backup");
                System.out.println("0:Exit");
                int input2 = scanner.nextInt();
                scanner.nextLine();
                switch (input2)
                {
                    case 1:
                        boolean canAddGrade = false;
                        System.out.println("Introduceti Materia:");
                        String stringInput = scanner.nextLine();
                        for(int i=0;i<profesors.get(id).getSubjects().size();i++)
                        {
                            if(profesors.get(id).getSubjects().get(i).equals(stringInput))
                            {
                                canAddGrade = true;
                            }
                        }
                        if(!canAddGrade)
                        {
                            System.out.println("Nu aveti permisiunea de a adauga nota la aceasta materie");
                            break;
                        }
                        System.out.println("Introduceti Numele si Prenumele Elevului:");
                        String lnameInput = scanner.nextLine();
                        String fnameInput = scanner.nextLine();
                        System.out.println("Introduceti Nota");
                        Integer nota = scanner.nextInt();
                        scanner.nextLine();
                        int index1=-1,index2=-1;
                        for(int i=0;i<students.size();i++)
                        {
                            if(students.get(i).getFirstName().equals(fnameInput) && students.get(i).getLastName().equals(lnameInput))
                                index1 = i;
                        }
                        if(index1!=-1)
                        {
                            for(int i=0;i<students.get(index1).getSubjects().size();i++)
                            {
                                if(students.get(index1).getSubjects().get(i).getName().equals(stringInput))
                                    index2 = i;
                            }
                        }
                        else break;
                        if(index2!=-1)
                        {
                            students.get(index1).getSubjects().get(index2).addGrade(nota);
                        }
                        else break;
                        System.out.println("Nota adaugata cu succes!");
                        continue;
                    case 2:
                        Collections.sort(profesors.get(id).getSubjects(), Comparator.naturalOrder());
                        System.out.println("Sortare efectuata cu succes");
                        continue;
                    case 3:
                        students.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
                        System.out.println("Sortare efectuata cu succes");
                        continue;
                    case 4:
                        PrintData("file.out",profesors,students,passwords);
                        continue;
                    case 5:
                        System.out.println("Studenti:\n");
                        for (Student student : students) {
                            System.out.print(student.getFirstName());
                            System.out.print(" ");
                            System.out.print(student.getLastName());
                            System.out.println();
                            System.out.print("Materii si note:\n");
                            for (int j = 0; j < student.getSubjects().size(); j++) {
                                System.out.print(student.getSubjects().get(j).getName());
                                System.out.print(" ");
                                for (int k = 0; k < student.getSubjects().get(j).getGrades().size(); k++) {
                                    System.out.print(student.getSubjects().get(j).getGrades().get(k).toString());
                                    System.out.print(" ");
                                    System.out.print(student.getSubjects().get(j).getDates().get(k).toString());
                                }
                                System.out.println();
                            }
                            System.out.println();
                            System.out.println();
                        }
                        continue;
                    case 6:
                        System.out.println("Introduceti Materia:");
                        canAddGrade = false;
                        String string = scanner.nextLine();
                        for(int i=0;i<profesors.get(id).getSubjects().size();i++)
                        {
                            if(profesors.get(id).getSubjects().get(i).equals(string))
                            {
                                canAddGrade = true;
                            }
                        }
                        if(!canAddGrade)
                        {
                            System.out.println("Nu aveti permisiunea de a adauga nota la aceasta materie");
                            break;
                        }
                        System.out.println("Introduceti Numele si Prenumele Elevului:");
                        String lname = scanner.nextLine();
                        String fname= scanner.nextLine();
                        float media = 0f;
                        for(Student student:students)
                        {
                            if(lname.equals(student.getLastName()) && fname.equals(student.getFirstName()))
                            {
                                for(Subject subject:student.getSubjects())
                                {
                                    if(Objects.equals(subject.getName(), string)) {
                                        for (Integer it:subject.getGrades())
                                            media += it;
                                        media/=subject.getGrades().size();
                                        student.media = media;
                                    }
                                }
                            }
                        }
                        System.out.println("Media este: ");
                        System.out.println(media);
                        continue;
                    case 7:
                        ReadBackup(profesors,students,passwords);
                        continue;
                }
            }
            else
            {
                System.out.println("Meniu Elev:");
                System.out.println("1:Alegeti 1 ca sa va vedeti toate notele:");
                System.out.println("2:Alegeti 2 ca sa va vedeti mediile:");
                System.out.println("3:Exit");
                int input2 = scanner.nextInt();
                scanner.nextLine();
                if(input2 == 1)
                {
                for(int i=0;i<students.get(id-profesors.size()-1).getSubjects().size();i++)
                {
                    System.out.print(students.get(id).getSubjects().get(i).getName());
                    System.out.println();
                    for(int j=0;j<students.get(id-profesors.size()-1).getSubjects().get(i).getDates().size();j++)
                    {
                        System.out.print(students.get(id-profesors.size()-1).getSubjects().get(i).getGrades().get(j));
                        System.out.print(" ");
                        System.out.print(students.get(id-profesors.size()-1).getSubjects().get(i).getDates().get(j));
                        System.out.println();
                    }
                    System.out.println();
                }
                continue;
                }
                else if(input2==2)
                {
                    for(int i=0;i<students.get(id-profesors.size()-1).getSubjects().size();i++)
                    {
                        System.out.print(students.get(id).getSubjects().get(i).getName());
                        System.out.print(" ");
                        if(students.get(i).media!=-1)
                            System.out.print(students.get(i).media);
                        else System.out.print("Medie necalculata");
                        System.out.print("\n");
                    }
                }
            }
            id=-1;
            System.out.println("Introduceti Parola:");
            password = scanner.nextLine();
            for(int i=0;i<profesors.size();i++)
            {
                if(passwords.get(i).equals(password))
                {
                    id = i;
                    break;
                }
            }
            for(int i=0;i<students.size();i++)
            {
                if(passwords.get(i+profesors.size()-1).equals(password))
                {
                    id = i+profesors.size();
                    break;
                }
            }
            if(id == -1)
            {
                System.out.println("Parola gresita");
                System.out.println(passwords.size());
                input=0;
            }
            else {
                System.out.print("Bun venit ");

                if (id >= profesors.size()) {
                    System.out.print(students.get(id-profesors.size()-1).getLastName());
                    System.out.print(" ");
                    System.out.print(students.get(id-profesors.size()-1).getFirstName());
                    System.out.println();
                    isStudent = true;
                } else {
                    input = 1;
                    System.out.print(profesors.get(id).getLastName());
                    System.out.print(" ");
                    System.out.print(profesors.get(id).getFirstName());
                    System.out.println();
                    isStudent = false;
                }
            }
        }
    }

    private static void ReadBackup(List<Professor> professors, List<Student> students, List<String> passwords) {
        String fileName = "file.out";
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader)) {

            int size = Integer.parseInt(reader.readLine());

            professors.clear();
            students.clear();
            passwords.clear();
            List<String> stringSubjects = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                String fName = reader.readLine();
                String lName = reader.readLine();
                String password = reader.readLine();
                passwords.add(password);

                int m = Integer.parseInt(reader.readLine());
                List<String> subjects = new ArrayList<>();
                for (int j = 0; j < m; j++) {
                    String subject = reader.readLine();
                    subjects.add(subject);
                    stringSubjects.add(subject);
                }

                professors.add(new Professor(fName, lName, subjects));
            }

            int k = Integer.parseInt(reader.readLine());

            for (int i = 0; i < k; i++) {
                String fName = reader.readLine();
                String lName = reader.readLine();
                String password = reader.readLine();
                passwords.add(password);

                Student student = new Student(fName, lName);
                for(String subject: stringSubjects)
                {
                    student.addSubject(subject);
                }
                students.add(student);

                int studentSubjectsSize = Integer.parseInt(reader.readLine());
                for (int j = 0; j < studentSubjectsSize; j++) {
                    int grades = Integer.parseInt(reader.readLine());
                    for (int index = 0; index < grades; index++) {
                        int grade = Integer.parseInt(reader.readLine());
                        Date date = new Date();
                        try {
                            date = new SimpleDateFormat("yyyy-MM-dd").parse(reader.readLine());
                        } catch (ParseException e) {
                            System.out.println("Eroare la conversia datei");
                            date = new Date();
                        }
                        students.get(i).getSubjects().get(j).addGradeWithDate(grade, date);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Eroare la citirea fisierului de backup");
        } catch (IOException e) {
            System.out.println("Eroare la citirea fisierului de backup");
        }
    }


    private static void ReadData(List<Professor> professors, List<Student> students,List<String> passwords) {
        String fileName = "input.txt";
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader)) {

            int n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                String fName = reader.readLine();
                String lName = reader.readLine();
                String password = reader.readLine();
                passwords.add(password);
                int m = Integer.parseInt(reader.readLine());
                List<String> subjects = new ArrayList<>();
                for (int j = 0; j < m; j++) {
                    subjects.add((reader.readLine()));
                }
                professors.add(new Professor(fName, lName, subjects));
            }

            int k = Integer.parseInt(reader.readLine());
            for (int i = 0; i < k; i++) {
                String fName = reader.readLine();
                String lName = reader.readLine();
                String password = reader.readLine();
                passwords.add(password);
                students.add(new Student(fName, lName));
            }

        } catch (FileNotFoundException e) {
            System.out.println("error");

        } catch (IOException e) {
            System.out.println("error2");
        }
    }

    private static void PrintData( String fileName,List<Professor> professors,List<Student> students,List<String> passwords) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(String.valueOf(professors.size()));
            writer.write('\n');
            Integer index=0;
            for(int i=0;i< professors.size();i++)
            {
                writer.write(professors.get(i).getFirstName());
                writer.write("\n");
                writer.write(professors.get(i).getLastName());
                writer.newLine();
                writer.write(passwords.get(index++));
                writer.newLine();
                writer.write(String.valueOf(professors.get(i).getSubjects().size()));
                writer.newLine();
                for(int j=0;j<professors.get(i).getSubjects().size();j++)
                {
                    writer.write(professors.get(i).getSubjects().get(j));
                    writer.write("\n");
                }
            }
            writer.write(String.valueOf(students.size()));
            writer.write("\n");
            for(int i=0;i< students.size();i++) {
                writer.write(students.get(i).getFirstName());
                writer.write("\n");
                writer.write(students.get(i).getLastName());
                writer.newLine();
                writer.write(passwords.get(index++));
                writer.newLine();
                writer.write(String.valueOf(students.get(i).getSubjects().size()));
                writer.newLine();
                for (int j = 0; j < students.get(i).getSubjects().size(); j++) {
                    writer.write(String.valueOf(students.get(i).getSubjects().get(j).getGrades().size()));
                    writer.write("\n");
                    for (int k = 0; k < students.get(i).getSubjects().get(j).getGrades().size(); k++) {
                        writer.write(students.get(i).getSubjects().get(j).getGrades().get(k).toString());
                        writer.write("\n");
                        writer.write(new SimpleDateFormat("yyyy-MM-dd").format(students.get(i).getSubjects().get(j).getDates().get(i)));
                        writer.write("\n");
                    }
                }
            }
            System.out.println("Datele au fost afisate in " + fileName);

        } catch (IOException e) {
            System.out.println("error");
        }
    }

}
