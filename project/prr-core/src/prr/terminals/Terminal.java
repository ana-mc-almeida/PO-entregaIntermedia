package prr.terminals;

import java.io.Serializable;

import prr.clients.Client;

// FIXME add more import if needed (cannot import from pt.tecnico or prr.app)

/**
 * Abstract terminal.
 */
abstract public class Terminal implements Serializable /* FIXME maybe addd more interfaces */ {

        /** Serial number for serialization. */
        private static final long serialVersionUID = 202208091753L;

        // FIXME define attributes
        // FIXME define contructor(s)
        // FIXME define methods

        private String key;
        private String type;
        // private Double debts = 0;
        // private Double payments = 0;
        private Client client;
        // private Terminal[] terminalsFriends;
        // private Communication[];
        // private String state;

        public Terminal(String key, Client client) {
                this.key = key;
                this.client = client;
        }

        public Terminal(String key, Client client, String state) {
                this.key = key;
                this.client = client;
                /* FIXME tratar dos states */
        }

        public void setType(String type) {
                this.type = type;
        }

        /**
         * Checks if this terminal can end the current interactive communication.
         *
         * @return true if this terminal is busy (i.e., it has an active interactive
         *         communication) and
         *         it was the originator of this communication.
         **/
        public boolean canEndCurrentCommunication() {
                // FIXME add implementation code
                return false;
        }

        /**
         * Checks if this terminal can start a new communication.
         *
         * @return true if this terminal is neither off neither busy, false otherwise.
         **/
        public boolean canStartCommunication() {
                // FIXME add implementation code
                return false;
        }

        @Override
        public String toString() {
                return "key:" + key + ", type:" + type + ", Client Key" + client.getKey();
        }
}
