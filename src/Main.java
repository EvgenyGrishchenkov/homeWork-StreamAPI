import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long peopleYoung = persons.stream()
                .filter(x -> x.getAge() < 18)
                .count();
        System.out.println("Несовершеннолетних лиц в городе - " + peopleYoung + " чел.");

        List<String> militaryService = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .filter(y -> y.getSex() == Sex.MAN)
                .map(Object::toString)
                .toList();
        System.out.println("Пофамильный список военнообязанных лиц:\n" + families);

        List<Person> ableBodiedHigherEducation = persons.stream()
                .filter(x -> x.getSex() == Sex.WOMAN && x.getAge() >= 18 && x.getAge() <= 60 ||
                        x.getSex() == Sex.MAN && x.getAge() >= 18 && x.getAge() <= 65)
                .filter(x -> x.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .toList();
        System.out.println("Список трудоспособного населения с высшим образованием:");
        for (Person list : ableBodiedHigherEducation) {
            System.out.println(list);
        }
    }
}