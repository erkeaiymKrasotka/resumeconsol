package resume;

public class Resume extends Person {
    private String experience;

    public Resume(String name, String email, String experience) {
        super(name, email);
        this.experience = experience;
    }

    public String getExperience() { return experience; }

    @Override
    public String toString() {
        return "Имя: " + getName() + " | Email: " + getEmail() + " | Опыт: " + experience;
    }
}