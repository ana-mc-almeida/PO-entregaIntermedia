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
        private String type;
        private Double debts = 0.0;
        private Double payments = 0.0;
        private Client client;
        // private List<Terminal> terminalsFriends;
        private List<Communication> communications;
        // private TerminalState state;
        private String state = "IDLE";

        public Terminal(String key, Client client) {
                this.key = key;
                this.client = client;
                // payments = 0;
                // debts = 0;
                // state = new TerminalState(this);
                state = "IDLE";

        }

        public Terminal(String key, Client client, String state) {
                this.key = key;
                this.client = client;
                // payments = 0;
                // debts = 0;
                // this.state = new TerminalState(this);
                // this.state.setState(state);
                this.state = state;
                if (state.equals("ON"))
                        this.state = "IDLE";
                communications = new ArrayList<Communication>();
                // System.out.println("AAAAAAAAAAAAAAAAAAAAA - " + state);
                // System.out.println("BBBBBBBBBBBBBBBBBBBBB - " + this.state);
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
                return true;
        }

        public boolean isUnused() {
                return communications.size() == 0;
        }

        @Override
        public String toString() {
                // return type + "|" + key + "|" + client.getKey() + "|" + state.status() + "|"
                // + payments + "|"
                // + debts; /* faltam os friends */
                // System.out.println("CCCCCCCCCCCCCCCCCCC - " + this.state);
                return type + "|" + key + "|" + client.getKey() + "|" + this.state + "|" + Math.round(payments) + "|"
                                + Math.round(debts); /* faltam os friends */
        }
}
