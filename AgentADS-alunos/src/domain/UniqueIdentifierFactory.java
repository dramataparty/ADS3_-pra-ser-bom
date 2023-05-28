package domain;

public class UniqueIdentifierFactory {
    private static UniqueIdentifierFactory instance;
    private DocNature lastDocNature;
    private int sequenceNumber;

    private UniqueIdentifierFactory() {
        lastDocNature = null;
        sequenceNumber = 1;
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
        String prefix = dn.toString();
        if (lastDocNature != null && lastDocNature != dn) {
            sequenceNumber = 1; // Reset sequence number if a different DocNature is requested
        }
        String uniqueIdentifier = prefix + sequenceNumber;
        lastDocNature = dn;
        sequenceNumber++;
        return uniqueIdentifier;
    }
}

