import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.util.Optional;
import java.util.ArrayList;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentRepository repository = StudentRepository.getInstance();



    public static void main(String[] args) {
        preloadStudents();
        while (true) {
            System.out.println("\n** University Student Register **");
            System.out.println("1. Add a new student");
            System.out.println("2. Remove an existing student");
            System.out.println("3. List all currently enrolled students");
            System.out.println("4. Query existing students");
            System.out.println("5. Get Students by name starting with Letter");
            System.out.println("6. Get students on a course by matching name");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); //


            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    listAllStudents();
                    break;
                case 4:
                    queryStudent();
                    break;
                case 5:
                    getStudentsByNameStartingWithLetter();
                    break;
                case 6:
                    getStudentsOnCourseByNameMatch();
                    break;
                case 7:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    private static void preloadStudents() {
        repository.addStudent(new Student("1", "Alicia Johnson", "Computer Science", Arrays.asList("CS101", "CS102")));
        repository.addStudent(new Student("2", "Bobby Boucher", "Maths", Arrays.asList("Math201", "Math202")));
        repository.addStudent(new Student("3", "Carol White", "Physics", Arrays.asList("Phys201", "Phys202")));
        repository.addStudent(new Student("4", "David Brown", "Engineering", Arrays.asList("Eng301", "Eng302")));
        repository.addStudent(new Student("5", "Eva Green", "Biology", Arrays.asList("Bio101", "Bio102")));
        repository.addStudent(new Student("6", "Frank Watkins", "Chemistry", Arrays.asList("Chem101", "Chem102")));
        repository.addStudent(new Student("7", "Grace Gu", "Art", Arrays.asList("Art101", "Art102")));
        repository.addStudent(new Student("8", "Henry Chan", "Physics", Arrays.asList("Phy201", "Phys202")));
        repository.addStudent(new Student("9", "Ivy Iverson", "Law", Arrays.asList("Law101", "Law102")));
        repository.addStudent(new Student("10", "Jack Turner", "Chemistry", Arrays.asList("Chem101", "Chem102")));
        repository.addStudent(new Student("11", "Karen Karen", "Law", Arrays.asList("Law101", "Law102")));
        repository.addStudent(new Student("12", "Joe Johnson", "Medicine", Arrays.asList("Med201", "Med202")));
        repository.addStudent(new Student("13", "Nora Norbert", "Medicine", Arrays.asList("Med201", "Med202")));
        repository.addStudent(new Student("14", "Noah Lee", "Biology", Arrays.asList("Bio101", "Bio102")));
        repository.addStudent(new Student("15", "Marcelo Rodriquez", "Engineering", Arrays.asList("Eng401", "Eng402")));
        repository.addStudent(new Student("16", "Vlad Impaler", "Physics", Arrays.asList("Phy201", "Phy202")));
        repository.addStudent(new Student("17", "Quinn Lee", "Engineering", Arrays.asList("Eng301", "Eng302")));
        repository.addStudent(new Student("18", "Anna Lovely", "Art", Arrays.asList("Art101", "Art102")));
        repository.addStudent(new Student("19", "Shavkat Rhakmanov", "Computer Science", Arrays.asList("CS101", "CS102")));
        repository.addStudent(new Student("20", "Lee Shing", "Maths", Arrays.asList("Math201", "Math202")));
        repository.listAllStudents();
    }

    private static void addStudent() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter course: ");
        String course = scanner.nextLine();

        System.out.print("Enter modules (comma-separated): ");
        String modulesInput = scanner.nextLine();
        List<String> modules = Arrays.asList(modulesInput.split(","));

        Student student = new Student(id, name, course, modules);
        repository.addStudent(student);
        System.out.println("Student added successfully.");
    }

    private static void removeStudent() {
        System.out.print("Enter student ID to remove: ");
        String id = scanner.nextLine().trim();
        repository.removeStudent(id);
    }

    private static void listAllStudents() {
        repository.listAllStudents();
    }

    private static void queryStudent() {
        System.out.println("Query by:");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Course");


        int queryChoice = scanner.nextInt();
        scanner.nextLine();


        switch (queryChoice) {
            case 1:
                queryById();
                break;
            case 2:
                queryByName();
                break;
            case 3:
                queryByCourse();
                break;
            default:
                System.out.println("Invalid query option.");
        }
    }

    private static void queryById() {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        Optional<Student> student = repository.findStudentById(id);

        List<Student> studentsList = new ArrayList<>();
        student.ifPresent(studentsList::add);

        printQueryResults(studentsList);
    }

    private static void queryByName() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        List<Student> students = repository.findStudentsByName(name);
        printQueryResults(students);

    }

    private static void queryByCourse() {
        System.out.print("Enter course name: ");
        String course = scanner.nextLine();
        List<Student> students = repository.findStudentsByCourse(course);
        printQueryResults(students);
    }

    private static void getStudentsByNameStartingWithLetter() {
        System.out.print("Enter the starting letter of the student's name: ");
        String startingLetter = scanner.nextLine();

        List<Student> studentsStartingWithLetter = repository.findStudentsByNameStartingWithLetter(startingLetter);
        printQueryResults(studentsStartingWithLetter);
    }

    private static void getStudentsOnCourseByNameMatch() {
        System.out.print("Enter course name: ");
        String course = scanner.nextLine();

        System.out.print("Enter text to match in names: ");
        String textToMatch = scanner.nextLine();

        List<Student> studentsOnCourseByNameMatch = repository.findStudentsOnCourseByNameMatch(course, textToMatch);
        printQueryResults(studentsOnCourseByNameMatch);
    }

    private static void printQueryResults(List<Student> students) {
        Optional.of(students)
                .filter(s -> !s.isEmpty())
                .ifPresentOrElse(
                        s -> {
                            System.out.println("Students found:");
                            s.forEach(student -> System.out.println("ID: " + student.getId() + ", Name: " + student.getName() +
                                    ", Course: " + student.getCourse() + ", Modules: " + String.join(", ", student.getModules())));
                        },
                        () -> System.out.println("No students found.")
                );
    }

}


