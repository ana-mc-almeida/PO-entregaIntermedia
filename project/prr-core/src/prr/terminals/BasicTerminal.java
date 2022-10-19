package prr.terminals;

import prr.clients.Client;

public class BasicTerminal extends Terminal {

    public BasicTerminal(String key, Client client) {
        super(key, client);
        // this.type = "basic";
        setType("BASIC");
    }

    public BasicTerminal(String key, Client client, String state) {
        super(key, client, state);
        // type = "basic";
        setType("BASIC");
    }
}
