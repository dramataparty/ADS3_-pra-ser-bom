package domain;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe que representa uma fábrica de codificadores.
 */
public class CodifierFactory {
    private static final CodifierFactory INSTANCE = new CodifierFactory();

    private Map<String, ICodifier> codifiers;

    /**
     * Construtor privado da classe CodifierFactory.
     * Inicializa o mapa de codificadores e carrega os codificadores disponíveis.
     */
    private CodifierFactory() {
        codifiers = new HashMap<>();
        loadCodifiers();
    }

    /**
     * Obtém a instância singleton da fábrica de codificadores.
     *
     * @return A instância singleton da fábrica de codificadores.
     */
    public static CodifierFactory getInstance() {
        return INSTANCE;
    }

    /**
     * Carrega os codificadores disponíveis a partir dos arquivos de classe no diretório "codifiers".
     * Os codificadores são adicionados ao mapa de codificadores.
     * O diretório "codifiers" é relativo ao diretório de trabalho atual.
     * Somente as classes que implementam a interface ICodifier e não são "DummyCodifier" são carregadas.
     */
    private void loadCodifiers() {
        String separator = System.getProperty("file.separator");
        File codifiersFolder = new File(System.getProperty("user.dir") + separator + "bin" +
                separator + "domain" + separator + "codifiers");

        // Verifica se a pasta "codifiers" existe e é um diretório
        if (!codifiersFolder.exists() || !codifiersFolder.isDirectory()) {
            throw new IllegalStateException("Codifiers folder does not exist or is not a directory.");
        }

        File[] classes = codifiersFolder.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".class");
            }
        });

        for (File className : classes) {
            try {
                String fileName = className.getName();
                String codifierName = fileName.substring(0, fileName.lastIndexOf('.'));
                Class<?> codifierClass = Class.forName("domain.codifiers." + codifierName);

                if (ICodifier.class.isAssignableFrom(codifierClass) && !codifierName.equals("DummyCodifier")) {
                    ICodifier codifier = (ICodifier) codifierClass.getDeclaredConstructor().newInstance();
                    codifiers.put(codifier.getName(), codifier);
                }
            } catch (Exception e) {
                // Imprime ou registra a exceção para fins de depuração
                e.printStackTrace();
            }
        }
    }

    /**
     * Obtém o codificador com o nome especificado.
     *
     * @param name O nome do codificador.
     * @return O codificador correspondente ao nome especificado, ou um DummyCodifier se não for encontrado.
     */
    public ICodifier getCodifier(String name) {
        return codifiers.getOrDefault(name, new DummyCodifier());
    }

    /**
     * Obtém os nomes dos codificadores disponíveis.
     *
     * @return Os nomes dos codificadores disponíveis.
     */
    public Iterable<String> getCodifierNames() {
        return codifiers.keySet();
    }
}
