import java.io.FileWriter;
import java.io.IOException;

public class TCC {
    public static String split(String text) {
        String splitText = "";
        String log = "";
        FSM fsm = new FSM();
        for (int i = 0; i < text.length(); i++) {
            log += "consider: " + text.charAt(i);
            System.out.print("consider: " + text.charAt(i));
            fsm.transition(text.charAt(i));
            log += ", transition to: " + fsm.getCurrentState() + "\n";
            System.out.print(", transition to: " + fsm.getCurrentState() + "\n");
            splitText += text.charAt(i);
            try (FileWriter writer = new FileWriter("log.txt")) {
                writer.write(log);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (fsm.getCurrentState() == States.END0) {
                splitText += " ";
                fsm = new FSM();
                continue;
            }
            if (fsm.getCurrentState() == States.END1) {
                splitText = splitText.substring(0, splitText.length() - 1);
                splitText += " ";
                fsm = new FSM();
                i -= 1;
                continue;
            }
            if (fsm.getCurrentState() == States.END2) {
                splitText = splitText.substring(0, splitText.length() - 2);
                splitText += " ";
                fsm = new FSM();
                i -= 2;
                continue;
            }
            if (fsm.getCurrentState() == States.END3) {
                splitText = splitText.substring(0, splitText.length() - 3);
                splitText += " ";
                fsm = new FSM();
                i -= 3;
                continue;
            }
            if (fsm.getCurrentState() == States.END4) {
                splitText = splitText.substring(0, splitText.length() - 4);
                splitText += " ";
                fsm = new FSM();
                i -= 4;
                continue;
            }
        }
        try (FileWriter writer = new FileWriter("log.txt")) {
            writer.write(log);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return splitText;
    }
}
