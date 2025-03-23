import java.util.List;

public class FSM {
    private static final List<Character> CONSONANTS = List.of(
        'ก', 'ข', 'ฃ', 'ค', 'ฅ', 'ฆ', 'ง',
        'จ', 'ฉ', 'ช', 'ซ', 'ฌ', 'ญ',
        'ฎ', 'ฏ', 'ฐ', 'ฑ', 'ฒ', 'ณ',
        'ด', 'ต', 'ถ', 'ท', 'ธ', 'น',
        'บ', 'ป', 'ผ', 'ฝ', 'พ', 'ฟ', 'ภ', 'ม',
        'ย', 'ร', 'ล', 'ว', 'ศ', 'ษ', 'ส', 'ห', 'ฬ', 'อ', 'ฮ'
    );
    private static final List<Character> UPPER_LOWER_VOWELS = List.of(
        'ิ', 'ี', 'ุ', 'ู'
    );
    private static final List<Character> TONE_MARKERS = List.of(
        '่', '้', '๊', '๋'
    );
    private static final List<Character> FRONT_VOWELS = List.of(
        'เ', 'แ', 'โ', 'ไ', 'ใ'
    );

    private States currentState;
    
    public FSM() {
        currentState = States.START;
    }

    public States getCurrentState() {
        return currentState;
    }

    public void transition(char input) {
        switch (currentState) {
            case START:
                if (input == 'ไ' || input == 'ใ') { currentState = States.SARA_AI1; return; }
                if (input == 'โ') { currentState = States.SARA_OH1; return; }
                if (CONSONANTS.contains(input)) { currentState = States.CONSONANT; return; }
                if (input == 'แ') { currentState = States.SARA_AE1; return; }
                if (input == 'เ') { currentState = States.MAINA1; return; }
                System.out.println("WHAT START");
                break;
            
            case X1:
                if (CONSONANTS.contains(input)) { currentState = States.XC; return; }
                if (input == 'ะ') { currentState = States.X2; return; }
                System.out.println("WHAT X1");
                break;
            case X2:
                if (CONSONANTS.contains(input)) { currentState = States.XC; return; }
                if (FRONT_VOWELS.contains(input)) { currentState = States.END1; return; }
                System.out.println("WHAT X2");
                break;
            case XC:
                if (input == '์') { currentState = States.END0; return; }
                if (CONSONANTS.contains(input)) { currentState = States.XCC; return; }
                if (input == 'ิ' || input == 'ุ') { currentState = States.XCV; return; }
                currentState = States.END2;
                System.out.println("WHAT XC");
                break;
            case XCC:
                if (input == '์') { currentState = States.END0; return; }
                if (CONSONANTS.contains(input)) { currentState = States.END3; return; }
                if (input == 'ิ' || input == 'ุ') { currentState = States.XCCV; return; }
                currentState = States.END3;
                System.out.println("WHAT XCC");
                break;
            case XCV:
                if (input == '์') { currentState = States.END0; return; }
                if (CONSONANTS.contains(input)) { currentState = States.END3; return; }
                System.out.println("WHAT XCV");
                break;
            case XCCV:
                if (input == '์') { currentState = States.END0; return; }
                if (CONSONANTS.contains(input)) { currentState = States.END4; return; }
                System.out.println("WHAT XCCV");
                break;
            
            case SARA_AI1:
                if (CONSONANTS.contains(input)) { currentState = States.SARA_AI2; return; }
                System.out.println("WHAT SARA_AI1");
                break;
            case SARA_AI2:
                if (CONSONANTS.contains(input)) { currentState = States.XC; return; }
                if (TONE_MARKERS.contains(input)) { currentState = States.SARA_AI2; return; }
                if (FRONT_VOWELS.contains(input)) { currentState = States.END1; return; }
                System.out.println("WHAT SARA_AI2");
                break;
            
            case SARA_OH1:
                if (CONSONANTS.contains(input)) { currentState = States.SARA_OH2; return; }
                System.out.println("WHAT SARA_OH1");
                break;
            case SARA_OH2:
                if (CONSONANTS.contains(input)) { currentState = States.XC; return; }
                if (input == 'ะ') { currentState = States.X2; return; }
                if (TONE_MARKERS.contains(input)) { currentState = States.SARA_OH2; return; }
                if (FRONT_VOWELS.contains(input)) { currentState = States.END1; return; }
                System.out.println("WHAT SARA_OH2");
                break;

            case CONSONANT:
                if (CONSONANTS.contains(input)) { currentState = States.XC; return; }
                if (UPPER_LOWER_VOWELS.contains(input)) { currentState = States.SARA_I_U; return; }
                if (input == 'า') { currentState = States.X2; return; }
                if (TONE_MARKERS.contains(input)) { currentState = States.WANNAYUK; return;}
                if (input == 'ั') { currentState = States.SARA_A1; return; }
                if (input == '็') { currentState = States.SARA_O; return; }
                if (input == 'ื') { currentState = States.FINAL_CONS_REQUIRED; return; }
                if (FRONT_VOWELS.contains(input)) { currentState = States.END1; return; }
                System.out.println("WHAT CONSONANT");
                break;
            case SARA_I_U:
                if (CONSONANTS.contains(input)) { currentState = States.XC; return; }
                if (TONE_MARKERS.contains(input)) { currentState = States.SARA_I_U; return; }
                System.out.println("WHAT SARA_I_U");
                if (FRONT_VOWELS.contains(input)) { currentState = States.END1; return; }
                break;
            case WANNAYUK:
                if (CONSONANTS.contains(input) || input == 'า') { currentState = States.X2; return; }
                System.out.println("WHAT WANNAYUK");
                break;
            case SARA_A1:
                if (CONSONANTS.contains(input)) { currentState = States.SARA_A2; return; }
                if (TONE_MARKERS.contains(input)) { currentState = States.SARA_A1; return; }
                System.out.println("WHAT SARA_A1");
                break;
            case SARA_A2:
                if (CONSONANTS.contains(input)) { currentState = States.XC; return; }
                if (input == 'ะ') { currentState = States.X2; return; }
                if (FRONT_VOWELS.contains(input)) { currentState = States.END1; return; }
                System.out.println("WHAT SARA_A2");
                break;
            case SARA_O:
                if (input == 'อ' || TONE_MARKERS.contains(input)) { currentState = States.FINAL_CONS_REQUIRED; return; }
                System.out.println("WHAT SARA_O");
                break;
            case FINAL_CONS_REQUIRED:
                if (CONSONANTS.contains(input)) { currentState = States.X2; return; }
                if (TONE_MARKERS.contains(input)) { currentState = States.FINAL_CONS_REQUIRED; return; }
                System.out.println("WHAT FINAL_CONS_REQUIRED");
                break;

            case SARA_AE1:
                if (CONSONANTS.contains(input)) { currentState = States.SARA_AE2; return; }
                System.out.println("WHAT SARA_AE1");
                break;
            case SARA_AE2:
                if (CONSONANTS.contains(input)) { currentState = States.XC; return; }
                if (TONE_MARKERS.contains(input)) { currentState = States.SARA_AE2; return; }
                if (input == '็') { currentState = States.FINAL_CONS_REQUIRED; return; }
                System.out.println("WHAT SARA_AE2");
                if (FRONT_VOWELS.contains(input)) { currentState = States.END1; return; }
                break;

            case MAINA1:
                if (CONSONANTS.contains(input)) { currentState = States.MAINA2; return; }
                System.out.println("WHAT MAINA1");
                break;
            case MAINA2:
                if (CONSONANTS.contains(input)) { currentState = States.XC; return; }
                if (TONE_MARKERS.contains(input)) { currentState = States.MAINA2; return; }
                if (input == 'ี') { currentState = States.SARA_IA; return; }
                if (input == 'ี') { currentState = States.SARA_UEA; return; }
                if (FRONT_VOWELS.contains(input)) { currentState = States.END1; return; }
                System.out.println("WHAT MAINA2");
                break;
            case SARA_IA:
                if (TONE_MARKERS.contains(input)) { currentState = States.SARA_IA; return; }
                if (input == 'ย') { currentState = States.X1; return; }
                System.out.println("WHAT SARA_IA");
                break;
            case SARA_UEA:
                if (TONE_MARKERS.contains(input)) { currentState = States.SARA_UEA; return; }
                if (input == 'อ') { currentState = States.X1; return; }
                System.out.println("WHAT SARA_UEA");
                break;
            default:
                break;
        }
    }
}