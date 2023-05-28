package domain;

/**
 * Uma estratégia de codificação e decodificação baseada na identidade, onde o texto codificado/descodificado
 * permanece igual ao texto fornecido como parâmetro.
 */
public class IdentityCodifierStrategy implements CodifierStrategy {

    /**
     * Codifica o texto fornecido e o retorna sem alterações.
     *
     * @param text o texto a ser codificado
     * @return o texto codificado (que é igual ao texto de entrada)
     */
    @Override
    public String code(String text) {
        return text;
    }

    /**
     * Decodifica o texto fornecido e o retorna sem alterações.
     *
     * @param text o texto a ser decodificado
     * @return o texto decodificado (que é igual ao texto de entrada)
     */
    @Override
    public String decode(String text) {
        return text;
    }
}
