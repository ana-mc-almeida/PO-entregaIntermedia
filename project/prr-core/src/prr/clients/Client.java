package prr.clients;

import java.io.Serializable;

import javax.swing.plaf.basic.BasicTreeUI.TreeCancelEditingAction;

import prr.Network;
import prr.terminals.Terminal;

public class Client implements Serializable {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202208091753L;

    private String key;
    private String name;
    private int taxId;

    /* atributos que ainda não estão implementados */
    // private boolean allowNotifications = true;
    // private Terminal[] terminals;
    // private ClientLevel level;
    // private Notification[] notifications;

    public Client(String key, String name, int taxId) throws DuplicateClientKeyException {
        this.key = key;
        this.name = name;
        this.taxId = taxId;
    }

    // public String getKey() {
    // return this.key;
    // }

    // public String getName() {
    // return this.name;
    // }

    // public String getTaxId() {
    // return this.taxId;
    // }

    /*
     * Return client in the correct format.
     * The correct format
     * {@code CLIENT|key|name|taxId|type|notifications|terminals|payments|debts}
     */
    @Override
    public String toString() {
        return "CLIENT|" + key + "|" + name + "|" + taxId + "|...";
    }

}
