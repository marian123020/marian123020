


public class AsmuoAtsakingasUzSistema extends User {

    public AsmuoAtsakingasUzSistema() {

    }


    public AsmuoAtsakingasUzSistema(String name, int kodas, String password) {
        super(name, kodas, password);
    }

    public AsmuoAtsakingasUzSistema(String name, int kodas) {
        super(name, kodas);
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getKodas() {
        return this.kodas;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public void setName(String vardas) {
        this.name = vardas;
    }

    @Override
    public void setKodas(int kodas) {
        this.kodas = kodas;
    }

    @Override
    public void setPassword(String pass) {
        this.password = pass;
    }

    public String toString()
    {
        return "Vardas: " + this.name + ", Kodas:" + this.kodas + ", Slaptazodis" + this.password;
    }
}
