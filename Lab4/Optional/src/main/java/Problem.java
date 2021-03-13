import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem {
    private final Matching matching;

    public Problem(Matching matching) {
        this.matching = matching;
    }

    public Map<Student, School> matchingPairFromStudents() {
        List<List<School>> SchoolList = new ArrayList<>();
        List<Student> StudentList = new ArrayList<>();

        for (Map.Entry<Student, List<School>> entry : matching.getStudentPrefMap().entrySet()) {
            SchoolList.add(entry.getValue());
            StudentList.add(entry.getKey());
        }

        Map<Student, School> pair = new HashMap<>();

        int counter = 0;
        for (Student Student : StudentList) {
            for (School h : SchoolList.get(counter)) {
                if (h.getCapacity() > 0) {
                    pair.put(Student, h);
                    h.setCapacity(h.getCapacity() - 1);
                }
            }
            counter++;
            if (counter == SchoolList.size())
                break;
        }
        return pair;
    }


    public Map<School, Student> matchingPairFromSchools() {
        List<List<Student>> StudentList = new ArrayList<>();
        List<School> SchoolList = new ArrayList<>();

        for (Map.Entry<School, List<Student>> entry : matching.getSchoolPrefMap().entrySet()) {
            SchoolList.add(entry.getKey());
            StudentList.add(entry.getValue());
        }

        Map<School, Student> pair = new HashMap<>();

        int counter = 0;
        for (School School : SchoolList) {
            for (Student r : StudentList.get(counter)) {
                if (School.getCapacity() > 0) {
                    pair.put(School, r);
                    School.setCapacity(School.getCapacity() - 1);
                }
            }
            counter++;
            if (counter == StudentList.size())
                break;
        }
        return pair;
    }
}

