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
    private static final List<Character> FRONT_VOWELS = List.of(
        'เ', 'แ', 'โ', 'ไ', 'ใ'
    );
    private static final List<Character> AFTER_REQUIRED = List.of(
        'ั', 'ื', '็'
    );
    private static final List<Character> TONES = List.of(
        '่', '้', '๊', '๋'
    );
    private static final List<Character> ENDING_CHARS = List.of(
        'ะ', '์', ' ', 'ๆ', 'ฯ'
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
                if (CONSONANTS.contains(input)) currentState = States.CONSONANT;
                else if (input == 'เ') currentState = States.E1;
                break;
            case REST:
                if (FRONT_VOWELS.contains(input) || CONSONANTS.contains(input)) currentState = States.ENDBEFORE;
                else if (ENDING_CHARS.contains(input)) currentState = States.END;
                break;
            case E1:
                if (TONES.contains(input)) currentState = States.MIDDLE;
                else if (AFTER_REQUIRED.contains(input)) currentState = States.REST;
                else if (FRONT_VOWELS.contains(input)) currentState = States.ENDBEFORE;
                else if (ENDING_CHARS.contains(input)) currentState = States.END;
                else currentState = States.E2;
                break;
            case E2:
                if (input == 'ี') currentState = States.IA;
                else if (input == 'ื') currentState = States.UEA;
                else currentState = States.ENDBEFORE;
                break;
            case IA:
                if (input == 'ย') currentState = States.MIDDLE;
                else if (TONES.contains(input)) currentState = States.IA;
                else currentState = States.ENDBEFORE;
                break;
            case UEA:
                if (input == 'อ') currentState = States.MIDDLE;
                else if (TONES.contains(input)) currentState = States.UEA;
                else currentState = States.ENDBEFORE;
                break;
            case CONSONANT:
                if (TONES.contains(input)) currentState = States.REST;
                else if (AFTER_REQUIRED.contains(input)) currentState = States.REST;
                else if (FRONT_VOWELS.contains(input) || CONSONANTS.contains(input)) currentState = States.ENDBEFORE;
                else if (ENDING_CHARS.contains(input)) currentState = States.END;
                break;
            case MIDDLE:
                if (AFTER_REQUIRED.contains(input)) currentState = States.REST;
                else if (FRONT_VOWELS.contains(input) || CONSONANTS.contains(input)) currentState = States.ENDBEFORE;
                else if (ENDING_CHARS.contains(input)) currentState = States.END;
            default:
                break;
        }
    }
}