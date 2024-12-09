/* Импортируем необходимые библиотеки */
import java.util.Arrays;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

interface Animal //Интерфейс Animal
{
    String getBreed();
    String getName();
    Double getCost();
    String getPersonality();
    LocalDate getBirthDate();
    boolean equals(Object o);
}

abstract class AbstractAnimal implements Animal // Класс животного
{
    protected String breed;// Порода
    protected String name;// Имя
    protected Double cost;// Стоимость
    protected String personality;// Характер
    protected LocalDate birthDate;// Дата рождения

    public AbstractAnimal(String breed, String name, Double cost, String personality,LocalDate birthDate) 
    {
        this.breed = breed;
        this.name = name;
        this.cost = cost;
        this.personality = personality;
        this.birthDate = birthDate;
    }

    public String getBreed() 
    {
        return breed;
    }
    public String getName() 
    {
        return name;
    }
    public Double getCost() 
    {
        return cost;
    }
    public String getPersonality() 
    {
        return personality;
    }
    public LocalDate getBirthDate() 
    {
        return birthDate;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAnimal)) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return breed.equals(that.breed) &&
               name.equals(that.name) &&
               cost.equals(that.cost) &&
               personality.equals(that.personality) &&
               birthDate.equals(that.birthDate);
    }

}

class Predator extends AbstractAnimal // Класс хищника
{
    public Predator(String breed, String name, Double cost, String personality, LocalDate birthDate) {
        super(breed, name, cost, personality, birthDate);
    }
}
class Pet extends AbstractAnimal // Класс домашнего животного
{
    public Pet(String breed, String name, Double cost, String personality, LocalDate birthDate) {
        super(breed, name, cost, personality, birthDate);
    }
}
class Wolf extends Predator // Волк
{
    public Wolf(String name, Double cost, LocalDate birthDate) {
        super("Wolf", name, cost, "Aggressive",birthDate);
    }
}
class Shark extends Predator // Акула
{
    public Shark(String name, Double cost, LocalDate birthDate) {
        super("Shark", name, cost, "Dangerous",birthDate);
    }
}
class Dog extends Pet // Собака
{
    public Dog(String name, Double cost, LocalDate birthDate) {
        super("Dog", name, cost, "Friendly",birthDate);
    }
}
class Cat extends Pet // Кошка
{
    public Cat(String name, Double cost, LocalDate birthDate) {
        super("Cat", name, cost, "Cute", birthDate);
    }
}

interface CreateAnimalService {
    /**
     * Создает массив животных указанного размера с помощью цикла for
     * 
     * @param n количество животных
     * @return массив животных
     */
    Animal[]  createAnimalsFor(int n);
    /**
     * Создает массив животных указанного размера с помощью цикла do-while
     * 
     * @param n количество животных
     * @return массив животных
     */
    Animal[]  createAnimalsDoWhile(int n);
    /**
     * Создает и возвращает уникальное животное
     * 
     * @param id идентификатор для имени животного
     * @return животное
     */   
    public default Animal createUniqueAnimal(int id) {
            Random random = new Random();
            int type = random.nextInt(4);
            String name = "Animal_" + id;
            double cost = 1000 + random.nextInt(5000); 
            int year = 2000 + random.nextInt(25);
            int dayOfYear = 1 + random.nextInt(365);
            LocalDate birthDate = LocalDate.ofYearDay(year, dayOfYear);

            switch (type) {
                case 0: return new Wolf(name, cost, birthDate);
                case 1: return new Shark(name, cost, birthDate);
                case 2: return new Dog(name, cost, birthDate);
                case 3: return new Cat(name, cost, birthDate);
                default: return null;
            }
        }
        /**
     * Создает массив животных из 10 элементов с использованием цикла while
     * 
     * @return массив животных
     */
    public default Animal[] createAnimals() {
            Animal[] animals = new Animal[10];
            int i = 0;
            while (i < 10) {
                animals[i] = createUniqueAnimal(i);
                System.out.println("Created animal: " + animals[i].getName() + " (" + animals[i].getBreed() + ") " + "Cost: " + animals[i].getCost()
                 + " Personality: " + animals[i].getPersonality() + " Birthdate: " + animals[i].getBirthDate());
                i++;
            }
            return animals;
        }
}

class CreateAnimalServiceImpl implements CreateAnimalService{
    public Animal[] createAnimalsFor(int n) {
        Animal[] animals = new Animal[n];
        for (int i = 0; i < n; i++) {
            animals[i] = createUniqueAnimal(i);
            System.out.println("Created animal: " + animals[i].getName() + " (" + animals[i].getBreed() + ") " + "Cost: " + animals[i].getCost()
                 + " Personality: " + animals[i].getPersonality() + " Birthdate: " + animals[i].getBirthDate());
        }
        return animals;
    }
    public Animal[] createAnimalsDoWhile(int n) {
        Animal[] animals = new Animal[n];
        int i = 0;
        do {
            animals[i] = createUniqueAnimal(i);
            System.out.println("Created animal: " + animals[i].getName() + " (" + animals[i].getBreed() + ") " + "Cost: " + animals[i].getCost()
                 + " Personality: " + animals[i].getPersonality() + " Birthdate: " + animals[i].getBirthDate());
            i++;
        } while (i < n);
        return animals;
    }
}

interface SearchService {
    /**
     * Находит имена животных родившихся в високосный год.
     * @param animals массив животных
     * @return массив имен животных
     */
    String[] findLeapYearNames(Animal[] animals);

    /**
     * Находит животных возраст которых старше указанного числа лет
     * @param animals массив животных
     * @param age возрастной порог
     * @return массив животных
     */
    void findOlderAnimal(Animal[] animals, int age);

    /**
     * Выводит дубликаты животных 
     * @param animals массив животных
     */
    void findDuplicate(Animal[] animals);
}

class SearchServiceImpl implements SearchService {
    public String[] findLeapYearNames(Animal[] animals) {
        String[] names = new String[animals.length];
        int i = 0;
        for (Animal animal : animals) {
            if (animal.getBirthDate().isLeapYear()) {
                names[i++] = animal.getName();
            }
        }
        return Arrays.copyOf(names, i);
    }

    public void findOlderAnimal(Animal[] animals, int age) {
        Animal[] olderAnimals = new Animal[animals.length];
        int i = 0;
        LocalDate currentDate = LocalDate.now();
    
        for (Animal animal : animals) {
            if (currentDate.minusYears(age).isAfter(animal.getBirthDate())) {
                olderAnimals[i++] = animal;
                // Вывод информации о животном
                System.out.println("Animal older than " + age + " years: " +
                animal.getName() + " (" + animal.getBreed() + ") " + "Cost: " + animal.getCost()
                + " Personality: " + animal.getPersonality() + " Birthdate: " + animal.getBirthDate());
            }
        }

    }
    public void findDuplicate(Animal[] animals) {
        for (int i = 0; i < animals.length; i++) {
            for (int j = i + 1; j < animals.length; j++) {
                if (animals[i].equals(animals[j])) {
                    System.out.println("Duplicated animal: " + animals[i].getName() + " (" + animals[i].getBreed() + ") " + "Cost: " + animals[i].getCost()
                 + " Personality: " + animals[i].getPersonality() + " Birthdate: " + animals[i].getBirthDate());
                }
            }
        }
    }
}

class Main {
    public static void main(String[] args) 
    {

        CreateAnimalServiceImpl createService = new CreateAnimalServiceImpl(); // Создание экземпляра сервиса создания
        SearchServiceImpl searchService = new SearchServiceImpl(); // Создание экземпляра сервиса поиска

        Animal[] animals = createService.createAnimals(); // Создаём 10 животных

        String[] leapYearNames = searchService.findLeapYearNames(animals); // Поиск животных рожденных в високосный год
        System.out.println("Animals born in leap year: " + Arrays.toString(leapYearNames));

        searchService.findOlderAnimal(animals, 10);// Поиск животных, старше 5 лет
       
        animals[8] = new Dog("Bobik", 5000.0, LocalDate.of(2020, 5, 20)); // Добавление дубликатов
        animals[9] = new Dog("Bobik", 5000.0, LocalDate.of(2020, 5, 20));
        searchService.findDuplicate(animals); // Поиск и вывод дубликатов животных
    }
}