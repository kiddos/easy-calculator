package calc;

import java.util.ArrayList;

public class Lexer {
    private ArrayList<Pair> pairs;
    private String cache;

    public enum Tokens {
        ADD, SUB, MUL, DIV, NUMBER
    }

    public static class Pair {
        private Tokens token;
        private double value;
        public Pair(Tokens token, double value) {
            this.token = token;
            this.value = value;
        }

        public Tokens getToken() {
            return token;
        }

        public double getValue() {
            return value;
        }
    }

    public Pair[] process(String entry) {
        pairs = new ArrayList<>();
        cache = "";
        for (int i = 0 ; i < entry.length(); ++i) {
            char c = entry.charAt(i);
            if (c == '+') {
                pairs.add(new Pair(Tokens.ADD, 0));
                addNumber();
            } else if (c == '-') {
                pairs.add(new Pair(Tokens.SUB, 0));
                addNumber();
            } else if (c == '*') {
                pairs.add(new Pair(Tokens.MUL, 0));
                addNumber();
            } else if (c == '/') {
                pairs.add(new Pair(Tokens.DIV, 0));
                addNumber();
            } else if (c == ' ') {
                addNumber();
            } else {
                cache += c;
            }
        }

        addNumber();
        return pairs.toArray(new Pair[0]);
    }

    private void addNumber() {
        cache = cache.trim();
        if (cache.length() != 0) {
            pairs.add(new Pair(Tokens.NUMBER, Double.parseDouble(cache)));
            cache = "";
        }
    }
}
