package dev.lasm.yeahtext;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Text transforming action
 */
public class Transform implements UnaryOperator<String> {

    public final String id;

    public final String label;

    public final String category;

    final Function<String, String> action;

    public Transform(String id, String label, String category, Function<String, String> action) {
        this.id = id;
        this.label = label;
        this.category = category;
        this.action = action;
    }

    @Override
    public String apply(String s) {
        return action.apply(s);
    }
}
