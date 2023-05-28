package domain;

public class UniqueIdentifierFactory {

    private static UniqueIdentifierFactory instance;

    private UniqueIdentifierFactory() {

    }

    public static UniqueIdentifierFactory getInstance() {
        if (instance == null) {
            synchronized (UniqueIdentifierFactory.class) {
                if (instance == null) {
                    instance = new UniqueIdentifierFactory();
                }
            }
        }
        return instance;
    }

    public String getIdentifier(DocNature dn) {
        String prefix = "";
        String uniqueIdentifier = "";

        switch (dn) {
            case ToAg3:

        }

    }
}
