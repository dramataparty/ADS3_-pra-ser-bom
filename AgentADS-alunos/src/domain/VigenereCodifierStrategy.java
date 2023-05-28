package domain;

/**
 * A classe VigenereCodifierStrategy implementa a estratégia de codificação e decodificação usando o cifrador Vignére.
 * Essa estratégia utiliza uma chave para codificar ou decodificar uma mensagem.
 */
public class VigenereCodifierStrategy {
    private static final int TAMANHO_ALFABETO = 26;

    /**
     * Codifica uma mensagem utilizando o cifrador Vignére.
     *
     * @param plaintext A mensagem em texto claro a ser codificada.
     * @param key A chave utilizada para a codificação.
     * @return A mensagem codificada.
     */
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

    /**
     * Decodifica uma mensagem codificada utilizando o cifrador Vignére.
     *
     * @param ciphertext A mensagem codificada a ser decodificada.
     * @param key A chave utilizada para a decodificação.
     * @return A mensagem decodificada.
     */
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
        int encodedIndex = (plainIndex + keyIndex) % TAMANHO_ALFABETO;
        return (char) ('A' + encodedIndex);
    }

    private static char decodeChar(char cipherChar, char keyChar) {
        int cipherIndex = cipherChar - 'A';
        int keyIndex = keyChar - 'A';
        int decodedIndex = (cipherIndex - keyIndex + TAMANHO_ALFABETO) % TAMANHO_ALFABETO;
        return (char) ('A' + decodedIndex);
    }
}
