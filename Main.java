import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String text = "เครื่องรางของขลังวัดโบสถ์ดอนพรหม";
        // String text = "หอมรดกไทย";
        // String text = "เรือน้อยลอยอยู่";
        // String text = "ประกันภัยสัมพันธ์";
        // String text = "ตากลม";
        String splitText = "";
        TCC fsm = new TCC();
        for (int i = 0; i < text.length(); i++) {
            fsm.transition(text.charAt(i));
            if (fsm.getCurrentState() == States.END) {
                splitText += text.charAt(i);
                splitText += " ";
                fsm = new TCC();
                continue;
            }
            if (fsm.getCurrentState() == States.ENDBEFORE) {
                splitText += " ";
                fsm = new TCC();
                i--;
                continue;
            }
            splitText += text.charAt(i);
        }

        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write("Split Text: " + splitText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
