import java.util.List;

public class TCC {
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
        'ั', '็',
        '่', '้', '๊', '๋'
    );
    private static final List<Character> ENDING_CHARS = List.of(
        'ะ', '์', ' ', 'ๆ', 'ฯ'
    );

    private States currentState;
    
    public TCC() {
        currentState = States.START;
    }

    public States getCurrentState() {
        return currentState;
    }

    public void transition(char input) {
        switch (currentState) {
            case START:
                if (CONSONANTS.contains(input)) currentState = States.CONSONANT;
                break;
            case CONSONANT:
                if (AFTER_REQUIRED.contains(input)) currentState = States.START;
                if (FRONT_VOWELS.contains(input) || CONSONANTS.contains(input)) currentState = States.ENDBEFORE;
                if (ENDING_CHARS.contains(input)) currentState = States.END;
            default:
                break;
        }
    }
}