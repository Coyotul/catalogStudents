import java.io.*;
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
                System.out.println("5:Alegeti 5 sa afisati notele");
                System.out.println("0:Exit");
                Integer input2 = scanner.nextInt();
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
                        continue;
                    case 2:
                        Collections.sort(profesors.get(id).getSubjects(), Comparator.naturalOrder());
                        System.out.println("Sortare efectuata cu succes");
                        continue;
                    case 3:
                        Collections.sort(students, new Comparator<Student>() {
                            @Override
                            public int compare(Student o1, Student o2) {
                                return o1.getLastName().compareTo(o2.getLastName());
                            }
                        });
                        System.out.println("Sortare efectuata cu succes");
                        continue;
                    case 4:
                        PrintData("file.out",profesors,students);
                        continue;
                }
            }
            else
            {
                System.out.println("Meniu Elev:");
                System.out.println("1:Alegeti 1 ca sa va vedeti toate notele:");
                System.out.println("2:Exit");
                Integer input2 = scanner.nextInt();
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
                writer.write("Materii si note:\n");
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
