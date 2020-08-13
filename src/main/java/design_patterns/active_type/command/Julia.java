package design_patterns.active_type.command;

public class Julia {

    public static void main(String[] args) {
        KeyPad keyPad = new KeyPad();
        keyPad.sendCommand(PlayCommand.class);
    }
}
