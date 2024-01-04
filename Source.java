import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Source
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        List<Professor> profesors = new ArrayList<>();
        List<Student> students = new ArrayList<>();
        boolean isStudent=false;
        ReadData(profesors,students);

        for(int i=0;i<students.size();i++)
        {
            for(int j=0;j<profesors.size();j++)
            {
                for(int k=0;k<profesors.get(j).getSubjects().size();k++)
                    students.get(i).addSubject(profesors.get(j).getSubjects().get(k));
            }
        }

        Integer input=1;
        System.out.println("Meniu:");
        System.out.println("1:Alegeti 1 daca sunteti profesor");
        System.out.println("2:Alegeti 2 daca sunteti elev");
        System.out.println("0:Exit");

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
                System.out.println("0:Exit");
                Integer input2 = scanner.nextInt();
                scanner.nextLine();
                switch (input2)
                {
                    case 1:
                        System.out.println("Introduceti Materia:");
                        String stringInput = scanner.nextLine();
                        System.out.println("Introduceti Numele si Prenumele Elevului:");
                        String lnameInput = scanner.nextLine();
                        String fnameInput = scanner.nextLine();
                        System.out.println("Introduceti Nota");
                        Integer nota = scanner.nextInt();
                        scanner.nextLine();
                        Integer index1=-1,index2=-1;
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
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        PrintData("file.out",profesors,students);
                        break;
                }
            }
            else
            {

            }
            System.out.println("Meniu:");
            System.out.println("1:Alegeti 1 daca sunteti profesor");
            System.out.println("2:Alegeti 2 daca sunteti elev");
            System.out.println("0:Exit");
            input = scanner.nextInt();

        }
    }

    private static void ReadData(List<Professor> profesors, List<Student> students) {
        String fileName = "input.txt";
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader)) {

            List<String> subjects = new ArrayList<>();
            int n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                String fName = reader.readLine();
                String name = reader.readLine();
                int m = Integer.parseInt(reader.readLine());
                for (int j = 0; j < m; j++) {
                    subjects.add(reader.readLine());
                }
                Professor professor = new Professor(fName, name, new ArrayList<>(subjects));
                profesors.add(professor);
                subjects.clear();
            }

            n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                String fName = reader.readLine();
                String name = reader.readLine();
                Student student = new Student(fName, name);
                students.add(student);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void PrintData( String fileName,List<Professor> professors,List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("\n\nInformatii despre profesori si studenti:\n\n");
            writer.write("Profesori:\n");
            for(int i=0;i< professors.size();i++)
            {
                writer.write(professors.get(i).getFirstName());
                writer.write(" ");
                writer.write(professors.get(i).getLastName());
                writer.newLine();
                writer.write("Materii predate: ");
                for(int j=0;j<professors.get(i).getSubjects().size();j++)
                {
                    writer.write(professors.get(i).getSubjects().get(j));
                    writer.write(" ");
                }
                writer.newLine();
                writer.newLine();
            }
            writer.write("Studenti:\n");
            for(int i=0;i< students.size();i++)
            {
                writer.write(students.get(i).getFirstName());
                writer.write(" ");
                writer.write(students.get(i).getLastName());
                writer.newLine();
                writer.write("Materii si note: ");
                for(int j = 0; j<students.get(i).getSubjects().size(); j++)
                {
                    writer.write(students.get(i).getSubjects().get(j).getName());
                    writer.write(" ");
                    for(int k=0;k<students.get(i).getSubjects().get(j).getGrades().size();k++)
                    {
                        writer.write(students.get(i).getSubjects().get(j).getGrades().get(k).toString());
                        writer.write(" ");
                        writer.write(students.get(i).getSubjects().get(j).getDates().get(k).toString());
                    }
                    writer.newLine();
                }
                writer.newLine();
                writer.newLine();
            }
            System.out.println("Datele au fost afisate in " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
