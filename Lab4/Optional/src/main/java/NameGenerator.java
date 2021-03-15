
import com.github.javafaker.Faker;

import java.util.*;

public class NameGenerator
{
    private List<Student> studentList()
    {
        List<Student> listWithFakeNames = new ArrayList<>();
        int randomNumberOfStudents = (int)((Math.random() * 10) + 10)/2;
        int i;
        for(i = 0; i < randomNumberOfStudents; i++)
        {
            Faker faker = new Faker();
            Student student = new Student(faker.name().fullName());
            listWithFakeNames.add(student);
        }

        return listWithFakeNames;
    }

    private List<School> schoolList()
    {
        List<School> listWithFakeNames = new ArrayList<>();
        int randomNumberOfSchools = (int)(Math.random() * 10) + 1;
        int i;

        // capacitatea spitalului este un nr random intre 1 si 11
        for(i = 0; i < randomNumberOfSchools; i++)
        {
            Faker faker = new Faker();
            School school = new School(faker.name().fullName(), (int)(Math.random() * 10) + 1);
            listWithFakeNames.add(school);
        }

        return listWithFakeNames;
    }

    public Map<Student, List<School>> generateFakeName()
    {
        Map<Student, List<School>> studsPrefMap = new HashMap<>();

        // nr random intre (5 si 10)
        int randomNumberOfSchools;
        int i;

        List<School> tempList = schoolList();
        for(i = 0; i < schoolList().size(); i++)
        {
            randomNumberOfSchools = (int)(Math.random() * 10 + 1);

            // sortam lista mereu pt a amesteca scolile intre ele (ordinea lor in lista)
            tempList.sort(Comparator.comparing(School::getName));

            // copiem un anumit numar de scoli
            List<School> tempList2 = new ArrayList<>();
            for(int j = 0; j < randomNumberOfSchools && j < tempList.size(); j++)
                tempList2.add(tempList.get(j));

            // adaugam in map numele Studentului si lista scolilor
            studsPrefMap.put(studentList().get(i), tempList2);
        }
        return studsPrefMap;
    }
}