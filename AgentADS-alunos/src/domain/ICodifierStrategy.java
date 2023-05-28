package domain;

import java.util.ArrayList;
import java.util.List;

public interface ICodifierStrategy {
    String getName();

    Iterable<String> code(String key, Iterable<String> text);

    Iterable<String> decode(String key, Iterable<String> text);
}

public class MyCodifierStrategy implements ICodifierStrategy {
    @Override
    public String getName() {
        return "My Codifier Strategy";
    }

    @Override
    public Iterable<String> code(String key, Iterable<String> text) {
        List<String> encodedText = new ArrayList<>();
        for (String line : text) {
            encodedText.add(encodeLine(key, line));
        }
        return encodedText;
    }

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
