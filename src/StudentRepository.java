import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StudentRepository {
    private static StudentRepository instance;
    private final Map<String, Student> students = new HashMap<>();

    private StudentRepository() {}

    public static synchronized StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }

    public void addStudent(Student student) {
        students.put(student.getId(), student);
    }

    public void removeStudent(String id) {
        Optional.ofNullable(students.remove(id)).ifPresentOrElse(
                s -> System.out.println("Student with ID " + id + " has been removed."),
                () -> System.out.println("No student found with ID " + id)
        );
    }

    public Optional<Student> findStudentById(String id) {
        return Optional.ofNullable(students.get(id));
    }

    public List<Student> findStudentsByName(String name) {
        return students.values().stream()
                .filter(student -> student.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public List<Student> findStudentsByCourse(String course) {
        return students.values().stream()
                .filter(student -> student.getCourse().equalsIgnoreCase(course))
                .collect(Collectors.toList());
    }

    public void listAllStudents() {
        students.values().forEach(student ->
                System.out.println("Student ID: " + student.getId() + ", Name: " + student.getName())
        );
    }

    public List<Student> findStudentsByNameStartingWithLetter(String letter) {
        return students.values().stream()
                .filter(student -> student.getName().toLowerCase().startsWith(letter.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Student> findStudentsOnCourseByNameMatch(String course, String text) {
        return students.values().stream()
                .filter(student -> student.getCourse().equalsIgnoreCase(course) && student.getName().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
    }
}
