package dev.lasm.yeahtext;

import java.util.function.Function;
import java.util.stream.Collectors;

import dev.lasm.yeahtext.alea.AleaRandom;

public class AleaRandomTransform extends Transform {
    public String seed;
    private final Function<String, String>[] actions;

    public AleaRandomTransform(String id, String label, String category, String seed, Function<String, String>... actions) {
        super(id, label, category, Function.identity());
        this.seed = seed;
        this.actions = actions;
    }

    @Override
    public String apply(String s) {
        AleaRandom random = new AleaRandom(seed + s);

        return s.codePoints().mapToObj(Character::toString).map(x -> actions[(int) (actions.length * random.next())].apply(x))
            .collect(Collectors.joining());
    }
}
