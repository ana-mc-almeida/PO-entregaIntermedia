package prr.terminals;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import prr.clients.Client;
import prr.communications.Communication;

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
        private Double debts = 0.0;
        private Double payments = 0.0;
        private Client client;
        // private List<Terminal> terminalsFriends;
        private List<Communication> communications;
        // private TerminalState state;
        private TerminalState state;

        public Terminal(String key, Client client) {
                this.key = key;
                this.client = client;
                state = new StateIdle();
                communications = new ArrayList<Communication>();
        }

        // public Terminal(String key, Client client, TerminalState state) {
        // this.key = key;
        // this.client = client;
        // this.state = state;
        // communications = new ArrayList<Communication>();
        // }

        public abstract String getTypeName();

        public void setState(TerminalState state) {
                this.state = state;
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
                return true;
        }

        public boolean isUnused() {
                return communications.size() == 0;
        }

        @Override
        public String toString() {
                return getTypeName() + "|" + key + "|" + client.getKey() + "|" + state.getName() + "|"
                                + Math.round(payments)
                                + "|"
                                + Math.round(debts); /* faltam os friends */
        }
}
