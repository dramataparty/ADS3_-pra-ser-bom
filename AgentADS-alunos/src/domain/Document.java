package domain;

public class Document implements Cloneable {
    private int myValue;

    public Document(int myValue) {
        this.myValue = myValue;
    }

    @Override
    public String toString() {
        return "Document [myValue=" + myValue + "]";
    }

    @Override
    public Document clone() {
        try {
            return (Document) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
