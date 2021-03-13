import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        // Creez obiectele de tip Student
        var students = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Student("S" + i))
                .toArray(Student[]::new);

        // Creez lista inlantuita
        List<Student> studentList = new LinkedList<>(Arrays.asList(students));

        //sortez lista folosind comparator
        studentList.sort(Comparator.comparing(Student::getName));

        // Creez obiectele de tip School
        var capacities = new int[]{1, 2, 2};
        var schools = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new School("H" + i, capacities[i]))
                .toArray(School[]::new);

        // Creez un obiect de tipul TreeSet ce contine array-ul de obiecte School creat anterior
        TreeSet<School> schoolsSet = new TreeSet<>(Arrays.asList(schools));

        //creez obiectele de tipul Map pentru studenti
        Map<Student, List<School>> studentsPreferences = new HashMap<>();
        studentsPreferences.put(students[0], Arrays.asList(schools[0], schools[1], schools[2]));
        studentsPreferences.put(students[1], Arrays.asList(schools[0], schools[1], schools[2]));
        studentsPreferences.put(students[2], Arrays.asList(schools[0], schools[1]));
        studentsPreferences.put(students[3], Arrays.asList(schools[0], schools[2]));

        //creez obiecte de tipul Map pentru scoli
        Map<School, List<Student>> schoolsPreferences = new HashMap<>();
        schoolsPreferences.put(schools[0], Arrays.asList(students[3], students[0], students[1], students[2]));
        schoolsPreferences.put(schools[1], Arrays.asList(students[0], students[2], students[1]));
        schoolsPreferences.put(schools[2], Arrays.asList(students[0], students[1], students[3]));

        //afisez lista folosind stream
        System.out.println("Studentii care au aplicat la school0: ");
        studentList.stream()
                .filter(std -> studentsPreferences.get(std).contains(schools[0]))
                .forEach(std -> System.out.print(std.getName() + " "));
        System.out.println();

        //afisez studentii a caror preferinte sunt school0 si school2
        System.out.println("Studentii a caror preferinte sunt school0 si school2: ");
        List<School> target = Arrays.asList(schools[0], schools[2]);
        List<Student> result = studentList.stream()
                .filter(std -> studentsPreferences.get(std).containsAll(target))
                .collect(Collectors.toList());
        result.forEach(student -> System.out.print(student.getName() + " "));


        System.out.println("\nMatching:");
        Map<Student, List<School>> studentsPrefCopy = studentsPreferences;
        Map<School, List<Student>> schoolPrefCopy = schoolsPreferences;

        System.out.println("Student -> School:");
        Matching studentSchoolPreference = new Matching(studentsPreferences, schoolsPreferences);
        Problem problem = new Problem(studentSchoolPreference);
        studentSchoolPreference.printMatchingBetweenStudentAndSchool(problem.matchingPairFromStudents());

        System.out.println("School -> Student:");
        Matching schoolStudentPreference = new Matching(studentsPrefCopy, schoolPrefCopy);
        Problem problem1 = new Problem(schoolStudentPreference);
        schoolStudentPreference.printMatchingBetweenSchoolAndStudent(problem1.matchingPairFromSchools());

        // generam nume random de studenti si scoli
        NameGenerator nameGenerator = new NameGenerator();
        Map<Student, List<School>> names = nameGenerator.generateFakeName();
        System.out.println("Studentii care prefera scolile: (generated by JavaFaker)");
        names.forEach((key, value) -> System.out.println(key.getName() + ": School " + value)); // afisam
    }
}
