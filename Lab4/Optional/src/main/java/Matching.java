import java.util.List;
import java.util.Map;

public class Matching {
    private final Map<Student, List<School>> studentPrefMap;
    private final Map<School, List<Student>> schoolPrefMap;

    public Map<Student, List<School>> getStudentPrefMap() {
        return studentPrefMap;
    }

    public Map<School, List<Student>> getSchoolPrefMap() {
        return schoolPrefMap;
    }

    public Matching(Map<Student, List<School>> studentPrefMap, Map<School, List<Student>> schoolPrefMap) {
        this.studentPrefMap = studentPrefMap;
        this.schoolPrefMap = schoolPrefMap;
    }

    public void printMatchingBetweenStudentAndSchool(Map<Student, School> m) {
        m.forEach((key, value) -> System.out.println("(" + key.getName() + ", " + value.getName() + ")"));
    }

    public void printMatchingBetweenSchoolAndStudent(Map<School, Student> m) {
        m.forEach((key, value) -> System.out.println("(" + key.getName() + ", " + value.getName() + ")"));
    }
}
