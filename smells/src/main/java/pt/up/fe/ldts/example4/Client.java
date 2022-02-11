package pt.up.fe.ldts.example4;

public class Client extends Person {
    public Client(String name, String phone) {
        super(name, phone);
    }

    @Override
    public boolean login(String username, String password) {
        return false;
    }
}
