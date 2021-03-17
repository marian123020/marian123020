

import java.util.ArrayList;

public class Vadovas extends User {
    ArrayList<AsmuoAtsakingasUzSistema> list = new ArrayList<>();

    public Vadovas()
    {

    }
    public Vadovas(String name, int kodas, String password) {
        super(name, kodas, password);;
    }

    /*public void greetUser() {
        System.out.println("Sveikas vadovas su vardu:" + this.name + " su kodu:" + this.kodas);
    }*/

    public void setList(AsmuoAtsakingasUzSistema s) {
        list.add(s);
    }

    public ArrayList<AsmuoAtsakingasUzSistema> getList(){
        return list;
    }


    public ArrayList<AsmuoAtsakingasUzSistema> getSize() {
        return this.list;
    }
}
