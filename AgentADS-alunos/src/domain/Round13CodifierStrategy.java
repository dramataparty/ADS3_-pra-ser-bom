package domain;

public class Round13CodifierStrategy {
    private static final int SHIFT = 13;

    public static String encode(String message) {
        StringBuilder encodedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                int offset = (c - base + SHIFT) % 26;
                encodedMessage.append((char) (base + offset));
            } else {
                encodedMessage.append(c);
            }
        }
        return encodedMessage.toString();
    }

    public static String decode(String encodedMessage) {
        StringBuilder decodedMessage = new StringBuilder();
        for (char c : encodedMessage.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                int offset = (c - base - SHIFT + 26) % 26;
                decodedMessage.append((char) (base + offset));
            } else {
                decodedMessage.append(c);
            }
        }
        return decodedMessage.toString();
    }
}
