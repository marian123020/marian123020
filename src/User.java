
public abstract class User {
    String name;
    int kodas;
    String password;

    public User(){

    }

    public User(String name, int kodas) {
        this.name = name;
        this.kodas = kodas;
    }

    public int getKodas() {
        return kodas;
    }

    public void setKodas(int kodas) {
        this.kodas = kodas;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name, int kodas, String password)
    {
        this.name = name;
        this.kodas = kodas;
        this.password = password;
    }

    public void greetUser() {
        System.out.println("Sveikas vartotojas.");
    }

}
