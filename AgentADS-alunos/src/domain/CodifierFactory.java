package domain;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

public class CodifierFactory {
    private static final CodifierFactory INSTANCE = new CodifierFactory();

    private Map<String, ICodifier> codifiers;

    private CodifierFactory() {
        codifiers = new HashMap<>();
        loadCodifiers();
    }

    public static CodifierFactory getInstance() {
        return INSTANCE;
    }

    private void loadCodifiers() {
        String separator = System.getProperty("file.separator");
        File codifiersFolder = new File(System.getProperty("user.dir") + separator + "bin" +
                separator + "domain" + separator + "codifiers");

        // Ensure that the codifiers folder exists and is a directory
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
                // Print or log the exception for debugging purposes
                e.printStackTrace();
            }
        }
    }

    public ICodifier getCodifier(String name) {
        return codifiers.getOrDefault(name, new DummyCodifier());
    }

    public Iterable<String> getCodifierNames() {
        return codifiers.keySet();
    }
}
