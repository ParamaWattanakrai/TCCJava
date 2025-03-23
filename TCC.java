import java.io.FileWriter;
import java.io.IOException;

public class TCC {
    public static String split(String text) {
        text += "     ";
        StringBuilder splitText = new StringBuilder();
        StringBuilder log = new StringBuilder();
        FSM fsm = new FSM();
        for (int i = 0; i < text.length(); i++) {
            fsm.transition(text.charAt(i));

            log.append("considering: ").append(text.charAt(i))
            .append(", transition to: ").append(fsm.getCurrentState()).append("\n");
            // System.out.print("consider: " + text.charAt(i));
            // System.out.print(", transition to: " + fsm.getCurrentState() + "\n");

            int rollback = switch (fsm.getCurrentState()) {
                case END0 -> 0;
                case END1 -> 1;
                case END2 -> 2;
                case END3 -> 3;
                case END4 -> 4;
                default -> -1;
            };
    
            if (rollback >= 0) {
                splitText.setLength(Math.max(0, splitText.length() - rollback));
                splitText.append(" ");
                fsm = new FSM();
                i -= rollback;
            }
        }
        try (FileWriter writer = new FileWriter("log.txt")) {
            writer.write(log.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return splitText.toString().trim();
    }
}
