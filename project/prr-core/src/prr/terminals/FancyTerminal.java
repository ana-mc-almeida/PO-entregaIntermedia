package prr.terminals;

import prr.clients.Client;

public class FancyTerminal extends Terminal {
    public FancyTerminal(String key, Client client) {
        super(key, client);
        // type = "fancy";
        setType("fancy");
    }

    public FancyTerminal(String key, Client client, String state) {
        super(key, client, state);
        // type = "fancy";
        setType("fancy");
    }
}
