import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public final class Student {
    private final String id;
    private final String name;
    private final String course;
    private final List<String> modules;


    public Student(String id, String name, String course, List<String> modules) {
        this.id = id;
        this.name = name;
        this.course = course;
        this.modules = Collections.unmodifiableList(new ArrayList<>(modules));
    }

    public String getName() {
        return name;
    }
    public String getCourse() {
        return course;
    }
    public String getId() {
        return id;
    }
    public List<String> getModules() {
        return modules;
    }
}


