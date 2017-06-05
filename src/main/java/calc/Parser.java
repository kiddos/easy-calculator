package calc;

import calc.exception.*;

import java.util.ArrayList;
import java.util.Arrays;


public class Parser {
    ArrayList<Lexer.Pair> expression;

    public double parse(Lexer.Pair[] pairs) throws CalculatorException {
        expression = new ArrayList<>(Arrays.asList(pairs));

        while (expression.size() > 1) {
            boolean proirity = false;
            // evaluate multiplication and division first in order
            for (int i = 0; i < expression.size(); ++i) {
                Lexer.Pair p = expression.get(i);
                if (p.getToken() == Lexer.Tokens.MUL ||
                        p.getToken() == Lexer.Tokens.DIV) {
                    if (i <= 0 || i >= expression.size() - 1)
                        throw new ParseException("Invalid Expression: index out of bound");
                    Lexer.Pair prev = expression.get(i - 1);
                    Lexer.Pair next = expression.get(i + 1);
                    if (prev.getToken() != Lexer.Tokens.NUMBER ||
                            next.getToken() != Lexer.Tokens.NUMBER)
                        throw new ParseException("Invalid Expression: incorrect type");
                    proirity = true;
                    Lexer.Pair result;
                    if (p.getToken() == Lexer.Tokens.MUL) {
                        result = new Lexer.Pair(Lexer.Tokens.NUMBER,
                                prev.getValue() * next.getValue());
                    } else {
                        try {
                            result = new Lexer.Pair(Lexer.Tokens.NUMBER,
                                    prev.getValue() / next.getValue());
                        } catch (ArithmeticException e) {
                            throw new ComputeException("Division by zero");
                        }
                    }
                    for (int j = 0; j < 3; ++j) {
                        expression.remove(expression.get(i - 1));
                    }
                    expression.add(i - 1, result);
                }
            }
            // and then evaluate addition and subtraction in order
            if (!proirity) {
                for (int i = 0; i < expression.size(); ++i) {
                    Lexer.Pair p = expression.get(i);
                    if (p.getToken() == Lexer.Tokens.ADD ||
                            p.getToken() == Lexer.Tokens.SUB) {
                        if (i <= 0 || i >= expression.size() - 1)
                            throw new ParseException("Invalid Expression: index out of bound");
                        Lexer.Pair prev = expression.get(i - 1);
                        Lexer.Pair next = expression.get(i + 1);
                        if (prev.getToken() != Lexer.Tokens.NUMBER ||
                                next.getToken() != Lexer.Tokens.NUMBER)
                            throw new ParseException("Invalid Expression: incorrect type");
                        // remove old token and replace with result
                        Lexer.Pair result;
                        if (p.getToken() == Lexer.Tokens.ADD) {
                            result = new Lexer.Pair(Lexer.Tokens.NUMBER,
                                    prev.getValue() + next.getValue());
                        } else {
                            result = new Lexer.Pair(Lexer.Tokens.NUMBER,
                                    prev.getValue() - next.getValue());
                        }
                        for (int j = 0; j < 3; ++j) {
                            expression.remove(expression.get(i - 1));
                        }
                        expression.add(i - 1, result);
                    }
                }
            }
        }
        return expression.get(0).getValue();
    }
}
