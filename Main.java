import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // String text = "ไวกิ้ง";
        String text = "แจ๊กพ็อตเดชานุภาพแคปทัวริสต์ตู้เซฟ เชฟฉลุย สลัม วิดีโอดั๊มพ์ อ่อนด้อยแรงดูด เมาท์ปิกอัพคอลัมนิสต์ วาซาบิลีเมอร์แชเชือน เมเปิลออร์แกนิกไคลแมกซ์โปรเจกต์ โกะแมมโบ้ แพ็คโมเดิร์น อพาร์ตเมนต์ คอลัมน์โปรโมเตอร์ สโตนกรรมาชนสี่แยกรีสอร์ตดยุค พันธกิจไวกิ้งแช่แข็งแหวว ดีลเลอร์ ไฮเอนด์เวสต์ดีไซน์เนอร์";
        // String text = "สิทธิ์ลุ้นเครื่องรางของขลังวัดโบสถ์พันธุ์แท้ชี้ทางเรืองเพี้ยะลกล้อเลียนเกรียนโละใหม่";
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
