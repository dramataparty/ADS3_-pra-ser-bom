package domain;

/**
 * Classe que implementa a estratégia de codificação/descodificação da cifra Vigenère.
 */
public class VigenereCodifierStrategy {
    private String key;

    /**
     * Construtor que inicializa a estratégia com a chave fornecida.
     *
     * @param key A chave utilizada para codificar e descodificar o texto.
     */
    public VigenereCodifierStrategy(String key) {
        this.key = key;
    }

    /**
     * Codifica o texto utilizando a cifra de Vigenère.
     *
     * @param text O texto a ser codificado.
     * @return O texto codificado.
     */
    @Override
    public String code(String text) {
        StringBuilder codedText = new StringBuilder();
        int keyLength = key.length();
        int textLength = text.length();

        for (int i = 0; i < textLength; i++) {
            char currentChar = text.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            if (Character.isLetter(currentChar)) {
                boolean isUpperCase = Character.isUpperCase(currentChar);
                char base = isUpperCase ? 'A' : 'a';

                int offset = keyChar - base;
                char codedChar = (char) ((currentChar - base + offset) % 26 + base);

                codedText.append(codedChar);
            } else {
                codedText.append(currentChar);
            }
        }

        return codedText.toString();
    }

    /**
     * Descodifica o texto utilizando a cifra de Vigenère.
     *
     * @param text O texto a ser descodificado.
     * @return O texto descodificado.
     */
    @Override
    public String decode(String text) {
        StringBuilder decodedText = new StringBuilder();
        int keyLength = key.length();
        int textLength = text.length();

        for (int i = 0; i < textLength; i++) {
            char currentChar = text.charAt(i);
            char keyChar = key.charAt(i % keyLength);

            if (Character.isLetter(currentChar)) {
                boolean isUpperCase = Character.isUpperCase(currentChar);
                char base = isUpperCase ? 'A' : 'a';

                int offset = keyChar - base;
                char decodedChar = (char) ((currentChar - base - offset + 26) % 26 + base);

                decodedText.append(decodedChar);
            } else {
                decodedText.append(currentChar);
            }
        }

        return decodedText.toString();
    }
}
