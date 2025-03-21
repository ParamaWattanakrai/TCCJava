public class TCC {
    public static String split(String text) {
    String splitText = "";
    FSM fsm = new FSM();
    for (int i = 0; i < text.length(); i++) {
        System.out.print("considering: " + text.charAt(i));
        fsm.transition(text.charAt(i));
        System.out.print(", " + fsm.getCurrentState() + "\n");
        if (fsm.getCurrentState() == States.END) {
            splitText += text.charAt(i);
            splitText += " ";
            fsm = new FSM();
            continue;
        }
        if (fsm.getCurrentState() == States.ENDBEFORE) {
            splitText += " ";
            fsm = new FSM();
            i--;
            continue;
        }
        splitText += text.charAt(i);
    }
    return splitText;
    }
}
