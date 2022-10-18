package prr.terminals;

import prr.clients.Client;

public class BasicTerminal implements Terminal {

    public BasicTerminal(String key, Client client) {
        super(key, client);
        type = "basic";
    }

    public BasicTerminal(String key, Client client, String state) {
        super(key, client, state);
        type = "basic";
    }
}
