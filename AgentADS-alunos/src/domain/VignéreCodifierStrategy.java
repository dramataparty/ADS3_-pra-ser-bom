package domain;

public class VignéreCodifierStrategy {
    private static final int ALPHABET_SIZE = 26;

    public static String encode(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        plaintext = plaintext.toUpperCase();
        key = key.toUpperCase();

        int keyIndex = 0;
        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);
            if (Character.isLetter(plainChar)) {
                char keyChar = key.charAt(keyIndex % key.length());
                char encodedChar = encodeChar(plainChar, keyChar);
                ciphertext.append(encodedChar);
                keyIndex++;
            } else {
                ciphertext.append(plainChar);
            }
        }

        return ciphertext.toString();
    }

    public static String decode(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        ciphertext = ciphertext.toUpperCase();
        key = key.toUpperCase();

        int keyIndex = 0;
        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);
            if (Character.isLetter(cipherChar)) {
                char keyChar = key.charAt(keyIndex % key.length());
                char decodedChar = decodeChar(cipherChar, keyChar);
                plaintext.append(decodedChar);
                keyIndex++;
            } else {
                plaintext.append(cipherChar);
            }
        }

        return plaintext.toString();
    }

    private static char encodeChar(char plainChar, char keyChar) {
        int plainIndex = plainChar - 'A';
        int keyIndex = keyChar - 'A';
        int encodedIndex = (plainIndex + keyIndex) % ALPHABET_SIZE;
        return (char) ('A' + encodedIndex);
    }

    private static char decodeChar(char cipherChar, char keyChar) {
        int cipherIndex = cipherChar - 'A';
        int keyIndex = keyChar - 'A';
        int decodedIndex = (cipherIndex - keyIndex + ALPHABET_SIZE) % ALPHABET_SIZE;
        return (char) ('A' + decodedIndex);
    }

}
