package prr.terminals;

/**
 * Abstract terminal state. -> por enquando ainda nao está como abstrata
 */
public class TerminalState {
    private Terminal terminal;
    private String state;

    public TerminalState(Terminal terminal) {
        this.terminal = terminal;
        state = "espera";
    }

    public String status() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
