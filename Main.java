import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String text = "สิทธิ์ลุ้นเครื่องรางของขลังวัดโบสถ์พันธุ์แท้";
        // String text = "หอมรดกไทย";
        // String text = "เรือน้อยลอยอยู่";
        // String text = "ประกันภัยสัมพันธ์";
        // String text = "ตากลม";

        String splitText = TCC.split(text);

        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write("Original Text: " + text + "\nSplit Text: " + splitText);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
