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
        afiseazaInFisier("file.out");
        Integer input=1;
        System.out.println("Meniu:");
        System.out.println("1:Alegeti 1 daca sunteti profesor");
        System.out.println("2:Alegeti 2 daca sunteti elev");
        System.out.println("0:Exit");

        while (input!=0) {
            input = scanner.nextInt();
            switch (input) {
                case 1:
                    isStudent = false;
                case 2:
                    isStudent = true;
            }
            if(!isStudent) {
                System.out.println("Meniu Profesor:");
                System.out.println("1:Alegeti 1 sa adaugati note");
                System.out.println("2:Alegeti 2 sa sortati lista de discipline asociate");
                System.out.println("3:Alegeti 3 sa sortati lista de elevi");
                System.out.println("0:Exit");
                Integer input2 = scanner.nextInt();
                switch (input2)
                {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }
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

    private static void afiseazaInFisier( String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("\n\nInformatii despre profesori si studenti:\n\n");

            try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            System.out.println("Datele au fost afisate in " + fileName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
