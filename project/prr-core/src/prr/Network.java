package prr;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import prr.clients.Client;

import prr.exceptions.UnrecognizedEntryException;
import prr.exceptions.DuplicateClientKeyException;
import prr.exceptions.DuplicateTerminalKeyException;
import prr.exceptions.InvalidTerminalKeyException;
import prr.exceptions.UnknownClientKeyException;
import prr.exceptions.UnknownTerminalKeyException;

import prr.terminals.BasicTerminal;
import prr.terminals.FancyTerminal;
import prr.terminals.StateIdle;
import prr.terminals.StateOff;
import prr.terminals.StateSilence;
import prr.terminals.Terminal;
import prr.terminals.TerminalState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Class Store implements a store.
 */
public class Network implements Serializable {

	/** Serial number for serialization. */
	private static final long serialVersionUID = 202208091753L;

	// FIXME define attributes
	// FIXME define contructor(s)
	// FIXME define methods

	/**
	 * Stores the network's clients.
	 */
	private final Map<String, Client> clients = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

	/**
	 * Stores the network's terminals.
	 */
	private final Map<String, Terminal> terminals = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

	/**
	 * Stores the network's communications.
	 */
	// private final Map<String, Client> clients = new TreeMap<>();

	/**
	 * Read text input file and create corresponding domain entities.
	 * 
	 * @param filename name of the text input file
	 * @throws UnrecognizedEntryException if some entry is not correct
	 * @throws IOException                if there is an IO erro while processing
	 *                                    the text file
	 */
	void importFile(String filename)
			throws UnrecognizedEntryException, IOException, DuplicateClientKeyException, InvalidTerminalKeyException,
			DuplicateTerminalKeyException, UnknownClientKeyException /* FIXME maybe other exceptions */ {
		// FIXME implement method
		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = in.readLine()) != null) {
				String[] fields = line.split("\\|");
				switch (fields[0]) {
					case "CLIENT" -> this.registerClient(fields[1], fields[2], Integer.parseInt(fields[3]));
					case "BASIC", "FANCY" -> this.importTerminal(fields[0], fields[1], fields[2], fields[3]);
					case "FRIENDS" -> this.registerFriends(fields);
					default -> throw new UnrecognizedEntryException(String.join("|", fields));
				}
			}
		}
	}

	/**
	 * Parse and import a client entry from a plain text file.
	 * 
	 * A correct client entry has the following format: {@code CLIENT|id|nome|taxId}
	 *
	 * @param fields The fields of the client to import, that were split by the
	 *               separator
	 * @throws DuplicateClientKeyException if already exists a client with the given
	 *                                     key.
	 */
	public void registerClient(String key, String name, int taxId) throws DuplicateClientKeyException {
		if (clients.containsKey(key))
			throw new DuplicateClientKeyException(key);

		Client newClient = new Client(key, name, taxId);
		clients.put(key, newClient);

	}

	/**
	 * Parse and import a partner entry from a plain text file.
	 * <p>
	 * A correct partner entry has the following format:
	 * {@code PARTNER|id|name|address}
	 *
	 * @param fields The fields of the partner to import, that were split by the
	 *               separator
	 * @throws IllegalEntryException if the entry does not have the correct fields
	 *                               for its type
	 */
	private void importTerminal(String type, String key, String keyClient, String state)
			throws UnrecognizedEntryException, InvalidTerminalKeyException, DuplicateTerminalKeyException,
			UnknownClientKeyException, UnrecognizedEntryException {
		TerminalState terminalState = getTerminalState(state);
		Terminal terminal = registerTerminal(type, key, keyClient);
		terminal.setState(terminalState);
	}

	/*
	 * 
	 * 
	 */
	private TerminalState getTerminalState(String state) throws UnrecognizedEntryException {
		return switch (state) {
			case "ON" -> new StateIdle();
			case "OFF" -> new StateOff();
			case "SILENCE" -> new StateSilence();
			default -> throw new UnrecognizedEntryException(state);
		};
	}

	/**
	 * Parse and import a terminal entry from a plain text file.
	 * 
	 * A correct terminal entry has the following format:
	 * {@code terminal-type|idTerminal|idClient|state}
	 *
	 * @param fields The fields of the terminal to import, that were split by the
	 *               separator
	 * @throws UnrecognizedEntryException if the entry does not have the correct
	 *                                    fields for its type
	 */
	public Terminal registerTerminal(String type, String key, String keyClient)
			throws InvalidTerminalKeyException, DuplicateTerminalKeyException,
			UnknownClientKeyException, UnrecognizedEntryException {
		if (!key.matches("\\d{6}"))
			throw new InvalidTerminalKeyException(key);
		if (terminals.get(key) != null)
			throw new DuplicateTerminalKeyException(key);

		Client terminalsClient = getClientByKey(keyClient);

		Terminal terminal = switch (type) {
			case "BASIC" -> new BasicTerminal(key, terminalsClient);
			case "FANCY" -> new FancyTerminal(key, terminalsClient);
			default -> throw new UnrecognizedEntryException(type);
		};
		terminals.put(key, terminal);
		terminalsClient.addTerminal(terminal);
		return terminal;
	}

	/**
	 * Parse and import a friends entry from a plain text file.
	 * 
	 * A correct friends entry has the following format:
	 * {@code FRIENDS|idTerminal|idTerminal1,...,idTerminalN}
	 *
	 * @param fields The fields of the friends connection to import, that were split
	 *               by the separator
	 * @throws UnrecognizedEntryException if the entry does not have the correct
	 *                                    fields
	 *                                    for its type
	 */
	public void registerFriends(String[] fields) throws UnrecognizedEntryException {

	}

	public String showClient(String key) throws UnknownClientKeyException {
		Client client = getClientByKey(key);
		return client.toString();
	}

	public String showAllClients() {
		List<String> clientStrings = new ArrayList<String>();
		for (Client client : clients.values()) {
			clientStrings.add(client.toString());
		}
		return String.join("\n", clientStrings);

	}

	public Client getClientByKey(String key) throws UnknownClientKeyException {
		Client client = clients.get(key);
		if (client == null)
			throw new UnknownClientKeyException(key);
		return client;
	}

	public String showTerminal(String key) throws UnknownTerminalKeyException {
		Terminal terminal = getTerminalByKey(key);
		return terminal.toString();
	}

	public String showAllTerminals() {
		List<String> terminalStrings = new ArrayList<String>();
		for (Terminal terminal : terminals.values()) {
			terminalStrings.add(terminal.toString());
		}
		return String.join("\n", terminalStrings);
	}

	public Terminal getTerminalByKey(String key) throws UnknownTerminalKeyException {
		Terminal terminal = terminals.get(key);
		if (terminal == null)
			throw new UnknownTerminalKeyException(key);
		return terminal;
	}

	public String ShowUnusedTerminals() {
		List<String> terminalStrings = new ArrayList<String>();
		for (Terminal terminal : terminals.values()) {
			if (terminal.isUnused())
				terminalStrings.add(terminal.toString());
		}
		return String.join("\n", terminalStrings);
	}

}
