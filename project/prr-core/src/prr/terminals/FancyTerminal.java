package prr.terminals;

public class FancyTerminal implements Terminal {
    public FancyTerminal(String key, Client client) {
        super(key, client);
        type = "fancy";
    }

    public FancyTerminal(String key, Client client, String state) {
        super(key, client, state);
        type = "fancy";
    }
}
