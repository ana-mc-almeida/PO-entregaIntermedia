package prr;

import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import prr.exceptions.UnrecognizedEntryException;

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
	 * Read text input file and create corresponding domain entities.
	 * 
	 * @param filename name of the text input file
	 * @throws UnrecognizedEntryException if some entry is not correct
	 * @throws IOException                if there is an IO erro while processing
	 *                                    the text file
	 */
	void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */ {
		// FIXME implement method
		try (BufferedReader in = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = in.readLine()) != null) {
				String[] fields = line.split("\\|");
				switch (fields[0]) {
				case "CLIENT" -> this.registerClient(fields);
				case "BASIC", "FANCY" -> this.registerTerminal(fields);
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
	 * @throws UnknownDataException if the entry does not have the correct fields
	 *                              for its type
	 */
	private void registerClient(String[] fields) throws UnknownDataException {

	}

	/**
	 * Parse and import a terminal entry from a plain text file.
	 * 
	 * A correct terminal entry has the following format:
	 * {@code terminal-type|idTerminal|idClient|state}
	 *
	 * @param fields The fields of the terminal to import, that were split by the
	 *               separator
	 * @throws UnknownDataException if the entry does not have the correct fields
	 *                              for its type
	 */
	private void registerTerminal(String[] fields) throws UnknownDataException {

	}

	/**
	 * Parse and import a friends entry from a plain text file.
	 * 
	 * A correct friends entry has the following format:
	 * {@code FRIENDS|idTerminal|idTerminal1,...,idTerminalN}
	 *
	 * @param fields The fields of the friends connection to import, that were split
	 *               by the separator
	 * @throws UnknownDataException if the entry does not have the correct fields
	 *                              for its type
	 */
	private void registerFriends(String[] fields) throws UnknownDataException {

	}
}
