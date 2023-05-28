package domain;

/**
 * A classe Round13CodifierStrategy representa uma estratégia de codificação utilizando o algoritmo de cifra Round13.
 * Essa estratégia codifica e decodifica mensagens utilizando um deslocamento de 13 posições no alfabeto inglês.
 */
public class Round13CodifierStrategy {
    private static final int SHIFT = 13;

    /**
     * Codifica uma mensagem utilizando o algoritmo Round13.
     *
     * @param message a mensagem a ser codificada
     * @return a versão codificada da mensagem
     */
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

    /**
     * Decodifica uma mensagem codificada utilizando o algoritmo Round13.
     *
     * @param encodedMessage a mensagem codificada a ser decodificada
     * @return a versão decodificada da mensagem
     */
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
