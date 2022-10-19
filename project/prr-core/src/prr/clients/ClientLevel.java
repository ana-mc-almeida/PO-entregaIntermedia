package prr.clients;

public class ClientLevel {

    private Client client;

    public ClientLevel(Client client) {
        this.client = client;
    }

    public String status() {
        return "Normal";
    }
}
