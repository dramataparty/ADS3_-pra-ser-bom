package domain;

/**
 * Classe abstrata que define uma estratégia de codificação.
 */
public abstract class AbstractCodifierStrategy {

    /**
     * Retorna o nome da estratégia de codificação.
     *
     * @return o nome da estratégia de codificação
     */
    public String getName() {
        return "My Codifier Strategy";
    }

}
