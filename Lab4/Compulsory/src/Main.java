import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        //creez obiectele de tipul student
        Student student0 = new Student("S0");
        Student student1 = new Student("S1");
        Student student2 = new Student("S2");
        Student student3 = new Student("S3");
//        var students = IntStream.rangeClosed(0, 3)
//                .mapToObj(i -> new Student("S" + i) )
//                .toArray(Student[]::new);

        //creez lista si adaug studentii
        List<Student> studentList = new LinkedList<>();
        studentList.add(student0);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);

        //sortez lista folosind comparator
        studentList.sort(Comparator.comparing(Student::getName));

        //creez obiectele de tipul School
        School school0 = new School("H0", 1);
        School school1 = new School("H1", 2);
        School school2 = new School("H2", 2);
//        int[] Capacities = new int[]{1, 2, 2};
//        var school = IntStream.rangeClosed(0, 2)
//                .mapToObj(i -> new School("H" + i, Capacities[i]) )
//                .toArray(School[]::new);

        //creez obiectele de tipul TreeSet unde adaug obiecte de tipul School
        TreeSet<School> schoolTreeSet = new TreeSet<>();
        schoolTreeSet.add(school0);
        schoolTreeSet.add(school1);
        schoolTreeSet.add(school2);

        //creez obiectele de tipul Map pentru studenti
        Map<Student, List<School>> studentsPreferences = new HashMap<>();
        studentsPreferences.put(student0, Arrays.asList(school0, school1, school2));
        studentsPreferences.put(student1, Arrays.asList(school0, school1, school2));
        studentsPreferences.put(student2, Arrays.asList(school0, school1));
        studentsPreferences.put(student3, Arrays.asList(school0, school2));

        //creez obiecte de tipul Map pentru scoli
        Map<School, List<Student>> schoolsPreferences = new HashMap<>();
        schoolsPreferences.put(school0, Arrays.asList(student3, student0, student1, student2));
        schoolsPreferences.put(school1, Arrays.asList(student0, student2, student1));
        schoolsPreferences.put(school2, Arrays.asList(student0, student1, student3));

        //afisez lista folosind stream
        System.out.println("Studentii care au aplicat la school0: ");
        studentList.stream()
                .filter(std -> studentsPreferences.get(std).contains(school0))
                .forEach(std -> System.out.print(std.getName() + " "));
        System.out.println();

        //afisez studentii a caror preferinte sunt school0 si school2
        System.out.println("Studentii a caror preferinte sunt school0 si school2: ");
        List<School> target = Arrays.asList(school0, school2);
        List<Student> result = studentList.stream()
                .filter(std -> studentsPreferences.get(std).containsAll(target))
                .collect(Collectors.toList());
        result.forEach(student -> System.out.print(student.getName() + " "));
    }
}
