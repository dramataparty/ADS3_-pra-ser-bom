import java.util.ArrayList;
import java.util.List;

/**
 * Interface que define estratégias de codificação de documentos.
 */
public interface IdentityCodifierStrategy {
    /**
     * Retorna o nome da estratégia de codificação (ou codificador).
     *
     * @return O nome da estratégia de codificação.
     */
    String getName();

    /**
     * Codifica o texto usando a chave fornecida.
     *
     * @param key  A chave usada para codificar o texto.
     * @param text O texto a ser codificado.
     * @return O texto codificado.
     */
    Iterable<String> code(String key, Iterable<String> text);

    /**
     * Decodifica o texto usando a chave fornecida.
     *
     * @param key  A chave usada para decodificar o texto.
     * @param text O texto codificado a ser decodificado.
     * @return O texto decodificado.
     */
    Iterable<String> decode(String key, Iterable<String> text);
}

/**
 * Implementação da interface IdentityCodifierStrategy que realiza uma
 * codificação simples de substituição de caracteres.
 */
public class MyCodifierStrategy implements IdentityCodifierStrategy {
    /**
     * Retorna o nome da estratégia de codificação.
     *
     * @return O nome da estratégia de codificação.
     */
    @Override
    public String getName() {
        return "My Codifier Strategy";
    }

    /**
     * Codifica o texto usando uma chave de substituição simples.
     *
     * @param key  A chave usada para codificar o texto.
     * @param text O texto a ser codificado.
     * @return O texto codificado.
     */
    @Override
    public Iterable<String> code(String key, Iterable<String> text) {
        List<String> encodedText = new ArrayList<>();
        for (String line : text) {
            encodedText.add(encodeLine(key, line));
        }
        return encodedText;
    }

    /**
     * Decodifica o texto usando uma chave de substituição simples.
     *
     * @param key  A chave usada para decodificar o texto.
     * @param text O texto codificado a ser decodificado.
     * @return O texto decodificado.
     */
    @Override
    public Iterable<String> decode(String key, Iterable<String> text) {
        List<String> decodedText = new ArrayList<>();
        for (String line : text) {
            decodedText.add(decodeLine(key, line));
        }
        return decodedText;
    }

    private String encodeLine(String key, String line) {
        StringBuilder encodedLine = new StringBuilder();
        for (char c : line.toCharArray()) {
            int index = key.indexOf(c);
            if (index != -1) {
                encodedLine.append(key.charAt((index + 1) % key.length()));
            } else {
                encodedLine.append(c);
            }
        }
        return encodedLine.toString();
    }

    private String decodeLine(String key, String line) {
        StringBuilder decodedLine = new StringBuilder();
        for (char c : line.toCharArray()) {
            int index = key.indexOf(c);
            if (index != -1) {
                int decodedIndex = (index - 1 + key.length()) % key.length();
                decodedLine.append(key.charAt(decodedIndex));
            } else {
                decodedLine.append(c);
            }
        }
        return decodedLine.toString();
    }
}
