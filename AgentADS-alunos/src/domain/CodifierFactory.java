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
        File[] classes = codifiersFolder.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".class");
            }
        });

        for (File className : classes) {
            try {
                String s = className.getName();
                Class<?> codifierClass = Class.forName("domain.codifiers." + s.substring(0, s.lastIndexOf('.')));
                if (ICodifier.class.isAssignableFrom(codifierClass) && !className.equals("Dummy")) {
                    ICodifier codifier = (ICodifier) codifierClass.getDeclaredConstructor().newInstance();
                    codifiers.put(codifier.getName(), codifier);
                }
            } catch (Exception e) {
                // Do nothing! Just ignore the class;
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
