package dev.lasm.yeahtext;

import java.text.Normalizer;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static dev.lasm.yeahtext.StringTransformations.*;

/**
 * YeahText: Unicode text styling library
 */
public class YeahText {
    private static final Map<String, Transform> TRANSFORMS = new HashMap<>();

    /**
     * Register a string transform globally
     * @param id A unique identifier for this transform function
     * @param name Descriptive name for this text style
     * @param category Text styling category
     * @param action Actual transform action on a text
     */
    public static void registerTransform(String id, String name, String category, Function<String, String> action) {
        if (TRANSFORMS.containsKey(id)) {
            throw new IllegalArgumentException("Duplicate id: " + id);
        }
        TRANSFORMS.put(id, new Transform(id, name, category, action));
    }

    /**
     * Get a transform instance for given id
     * @param id Transform id
     * @return Registered transform function instance, or null
     */
    public static Transform getTransform(String id) {
        return TRANSFORMS.get(id);
    }

    /**
     * Transform a text by a function
     * @param str Text to be transformed
     * @param id transform function id
     * @return Transformed text, or as-is if transform is not registered in map
     */
    public static String transform(String str, String id) {
        var transform = TRANSFORMS.get(id);
        if (transform == null) { return str; }
        else return transform.apply(str);
    }

    @SafeVarargs
    public static Function<String, String> reduceActions(Function<String, String>... actions) {
        return Arrays.stream(actions).reduce(UnaryOperator.identity(), Function::andThen);
    }

    /**
     * Cycle action through the String
     * @param actions Actions to be cycled
     * @return Function instance, with loop tracker kept inside
     */
    @SafeVarargs
    public static UnaryOperator<String> cycleActions(Function<String, String>... actions) {
        return new UnaryOperator<>() {
            private int i = actions.length - 1;

            @Override
            public String apply(String s) {
                return s.codePoints().mapToObj(Character::toString).map(x -> {
                    if (i == actions.length) i = 0;
                    return actions[i++].apply(x);
                }).collect(Collectors.joining());
            }
        };
    }

    @SuppressWarnings("unchecked")
    public static AleaRandomTransform randomActions(int seed, String... actions) {
        return randomActions(seed, Arrays.stream(actions)
            .map(YeahText.TRANSFORMS::get)
            .toArray(Function[]::new));
    }

    @SafeVarargs
    public static AleaRandomTransform randomActions(int seed, Function<String, String>... actions) {
        return new AleaRandomTransform("", "", "", String.valueOf(seed), actions);
    }

    @SafeVarargs
    public static void registerTransform(String id, String name, String category, Function<String, String>... actions) {
        var op = reduceActions(actions);
        TRANSFORMS.put(id, new Transform(id, name, category, op));
    }

    @SafeVarargs
    public static UnaryOperator<String> charEcho(Function<String, String>... transforms) {
        return s -> {
            var newC = new StringBuilder();
            AtomicInteger i = new AtomicInteger();
            s.codePoints().forEach(x -> {
                for (var transform : transforms) {
                    if (transform instanceof Transform) {
                        if (((Transform) transform).action instanceof AleaRandomTransform) {
                            // TODO This sucks
                            ((AleaRandomTransform) ((Transform) transform).action).seed = "1" + i + s;
                        }
                    }
                    newC.append(transform.apply(Character.toString(x)));
                }
                i.addAndGet(1);
            });
            return newC.toString();
        };
    }

    public static final IntPredicate IS_ASCII_UPPERCASE = i -> 65 <= i && i <= 90;

    public static final IntPredicate IS_ASCII_LOWERCASE = i -> 97 <= i && i <= 122;

    public static final IntPredicate IS_ASCII_NUMBER = i -> 48 <= i && i <= 57;

    public static boolean hasTransform(Object key) {
        return TRANSFORMS.containsKey(key);
    }

    public static Set<Map.Entry<String, Transform>> entrySet() {
        return TRANSFORMS.entrySet();
    }

    public static Set<String> getTransformIds() {
        return TRANSFORMS.keySet();
    }

    public static Collection<Transform> getTransforms() {
        return TRANSFORMS.values();
    }

    public static int getTransformsSize() {
        return TRANSFORMS.size();
    }

    static {
        registerTransform("bold", "Bold (serif)", "bold-italic",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 119743),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 119737),
            s -> shiftCodePoint(s, IS_ASCII_NUMBER, 120734)
        );

        registerTransform("italic", "Italic (serif)", "bold-italic",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> mapCodePoint(s, i -> i == 104 ? new int[]{8462} : new int[0]),
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 119795),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 119789)
        );

        registerTransform("bold-italic", "Bold / Italic (serif)", "bold-italic",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 119847),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 119841));

        registerTransform("bold-sans", "Bold (sans)", "bold-italic",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 120211),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 120205),
            s -> shiftCodePoint(s, IS_ASCII_NUMBER, 120764)
        );

        registerTransform("italic-sans", "Italic (sans)", "bold-italic",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 120263),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 120257));

        registerTransform("bold-italic-sans", "Bold / Italic (sans)", "bold-italic",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 120315),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 120309)
        );

        registerTransform("alternating-italic", "Alternating Italic", "bold-italic",
            cycleActions(
                TRANSFORMS.get("italic"),
                Function.identity()
            )
        );

        registerTransform("bold-alternating-italic", "Bold w/ Alternating Italic", "bold-italic",
            cycleActions(
                TRANSFORMS.get("bold-italic"),
                TRANSFORMS.get("bold-italic-sans")
            )
        );

        registerTransform("italic-switch-serifs", "Italic Switch Serifs", "bold-italic",
            cycleActions(
                TRANSFORMS.get("italic"),
                TRANSFORMS.get("italic-sans")
            )
        );

        registerTransform("alternating-bold", "Alternating Bold", "bold-italic",
            cycleActions(
                TRANSFORMS.get("bold"),
                UnaryOperator.identity()
            )
        );

        registerTransform("double-struck", "Double Struck", "double-struck",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> mapCodePoint(s, i -> {
                //noinspection EnhancedSwitchMigration
                switch (i) {
                    case 67: return new int[]{8450};
                    case 72: return new int[]{8461};
                    case 78: return new int[]{8469};
                    case 80: return new int[]{8473};
                    case 81: return new int[]{8474};
                    case 82: return new int[]{8477};
                    case 90: return new int[]{8484};
                    default: return new int[0];
                }
            }),
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 120055),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 120049),
            s -> shiftCodePoint(s, IS_ASCII_NUMBER, 120744)
        );

        registerTransform("weird-double-struck", "Weird Double Struck", "double-struck",
            s -> mapCodePoint(s, "wdblup"),
            s -> mapCodePoint(s, "wdbllo")
        );

        registerTransform("alternating-weird-double-struck", "Alternating Weirdness Double-Struck", "double-struck",
            cycleActions(
                TRANSFORMS.get("double-struck"),
                TRANSFORMS.get("weird-double-struck")
            )
        );

        registerTransform("monospace", "Monospace", "monospace",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 120367),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 120361),
            s -> shiftCodePoint(s, i -> 49 <= i && i <= 57, 120774)
        );

        registerTransform("sans-serif", "Sans Serif", "sans-serif",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 120159),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 120153),
            s -> shiftCodePoint(s, IS_ASCII_NUMBER, 120754)
        );

        registerTransform("script", "Cursive Script", "script",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> mapCodePoint(s, "cu"),
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 119899),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 119893)
        );

        registerTransform("bold-script", "Bold Cursive Script", "script",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 119951),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 119945)
        );

        registerTransform("alt-cursive", "Cursive script w/ alternating bold", "script",
            cycleActions(
                TRANSFORMS.get("script"),
                TRANSFORMS.get("bold-script")
            )
        );

        registerTransform("fraktur", "Fraktur", "fraktur",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> mapCodePoint(s, i -> {
                //noinspection EnhancedSwitchMigration
                switch (i) {
                case 67: return new int[]{8493};
                case 72: return new int[]{8460};
                case 73: return new int[]{8465};
                case 82: return new int[]{8476};
                case 90: return new int[]{8488};
                default: return new int[0];
            }}),
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 120003),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 119997)
        );

        registerTransform("bold-fraktur", "Bold Fraktur", "fraktur",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 120107),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 120101)
        );

        registerTransform("alt-fraktur", "Alternating Fraktur", "fraktur",
            cycleActions(
                TRANSFORMS.get("fraktur"),
                TRANSFORMS.get("bold-fraktur")
            )
        );

        registerTransform("squiggles-top", "Upper Squiggles and Hooks", "squiggles-hooks",
            s -> mapCodePoint(s, "squpto"),
            s -> mapCodePoint(s, "sqloto")
        );

        registerTransform("squiggles-bottom", "Lower Squiggles and Hooks", "squiggles-hooks",
            s -> mapCodePoint(s, "squpbo"),
            s -> mapCodePoint(s, "sqlobo")
        );

        registerTransform("alt-squiggles", "Alternating Squiggles and Hooks", "squiggles-hools",
            cycleActions(
                TRANSFORMS.get("squiggles-top"),
                TRANSFORMS.get("squiggles-bottom")
            )
        );

        registerTransform("short-strikethrough", "Short Strikethrough", "strike",
            s -> append(s, 821)
        );

        registerTransform("long-strikethrough", "Long Strikethrough", "strike",
            s -> append(s, 822)
        );

        registerTransform("underline", "Underline (double macron)", "underline",
            s -> append(s, 863)
        );

        registerTransform("underline-alt", "Underline (low line)", "underline",
            s -> append(s, 818)
        );

        registerTransform("double-underline", "Double underline", "underline",
            s -> append(s, 819)
        );

        registerTransform("short-slash", "Short slash", "slash",
            s -> append(s, 823)
        );

        registerTransform("long-slash", "Long slash", "slash",
            s -> append(s, 824)
        );

        registerTransform("tilde-strikethrough", "Tilde strikethrough", "strike",
            s -> append(s, 820)
        );

        registerTransform("upside-down", "Upside down", "upside-down",
            StringTransformations::reverse,
            s -> mapCodePoint(s, "freg"),
            s -> mapCodePoint(s, "fspec")
        );

        registerTransform("cherokee-large", "Large Cherokee Letterlike", "cherokee-letterlike",
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, -32),
            s -> mapCodePoint(s, "che1")
        );

        registerTransform("cherokee-small", "Small Cherokee Letterlike", "cherokee-letterlike",
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 32),
            s -> mapCodePoint(s, "chesm1")
        );

        registerTransform("cherokee-titlecase", "Cherokee Letterlike Title Case", "cherokee-letterlike",
            s -> mapCodePoint(s, "che1"),
            s -> mapCodePoint(s, "chesm1")
        );

        registerTransform("fullwidth", "Fullwidth", "full-width-vaporwave",
            s -> shiftCodePoint(s, i -> 33 <= i && i <= 126, 65248)
        );

        registerTransform("vaporwave-ae", "Vaporwave (Λ & Ξ replacement)", "full-width-vaporwave",
            s -> mapCodePoint(s, i -> i == 65, i -> 923),
            s -> mapCodePoint(s, i -> i == 69, i -> 926),
            s -> shiftCodePoint(s, i -> 33 <= i && i <= 126, 65248)
        );

        registerTransform("vaporwave-av", "Vaporwave (▲ & ▼ replacement)", "full-width-vaporwave",
            s -> mapCodePoint(s, i -> i == 65, i -> 9650),
            s -> mapCodePoint(s, i -> i == 86, i -> 9660),
            s -> shiftCodePoint(s, i -> 33 <= i && i <= 126, 65248)
        );

        registerTransform("vaporwave-eo", "Vaporwave (Σ & ♢ replacement)", "full-width-vaporwave",
            s -> mapCodePoint(s, i -> i == 69, i -> 931),
            s -> mapCodePoint(s, i -> i == 79, i -> 9826),
            s -> shiftCodePoint(s, i -> 33 <= i && i <= 126, 65248)
        );

        registerTransform("small-caps-comp-f", "Small caps (compatible 'F')", "small-caps",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> mapCodePoint(s, "scf")
        );

        registerTransform("small-caps", "Small caps (pretty 'F')", "small-caps",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> mapCodePoint(s, "sc")
        );

        registerTransform("superscript", "Superscript", "tiny-text",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> mapCodePoint(s, "s")
        );

        registerTransform("subscript", "Subscript", "tiny-text",
            s -> normalize(s, Normalizer.Form.NFD),
            s -> mapCodePoint(s, "sub")
        );

        registerTransform("mini-me", "Mini-me", "mini-me",
            s -> mapCodePoint(s, "d", true)
        );

        registerTransform("stack", "Mini stacked", "mini-me",
            s -> stack(s, "d", "s")
        );

        registerTransform("mini-superscript", "Mini superscript", "mini-me",
            s -> mapCodePoint(s, "d", true)
        );

        registerTransform("supersub-1", "Supersub Combo 1", "tiny-text",
            cycleActions(
                reduceActions(
                    s -> normalize(s, Normalizer.Form.NFD),
                    s -> mapCodePoint(s, "s")
                ),
                reduceActions(
                    s -> normalize(s, Normalizer.Form.NFD),
                    s -> mapCodePoint(s, "sub")
                )
            )
        );

        registerTransform("supersub-2", "Supersub Combo 2", "tiny-text",
            cycleActions(
                reduceActions(
                    s -> normalize(s, Normalizer.Form.NFD),
                    s -> mapCodePoint(s, "s")
                ),
                Function.identity(),
                reduceActions(
                    s -> normalize(s, Normalizer.Form.NFD),
                    s -> mapCodePoint(s, "sub")
                ),
                Function.identity()
            )
        );

        registerTransform("bubble", "Bubble text", "bubble-text",
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 9333),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 9327),
            s -> shiftCodePoint(s, i -> 49 <= i && i <= 57, 9263),
            s -> mapCodePoint(s, i -> i == 48, i -> 9450)
        );

        registerTransform("black-bubble", "Black bubble text", "bubble-text",
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 127247),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 127215),
            s -> shiftCodePoint(s, i -> 49 <= i && i <= 57, 10073),
            s -> mapCodePoint(s, i -> i == 48, i -> 9471)
        );

        registerTransform("square", "Square", "square-text",
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 127215),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 127183)
        );

        registerTransform("black-square", "Black square", "square-text",
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 127279),
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 127247)
        );

        registerTransform("alt-bubble", "Alternating Bubble", "bubble-text",
            cycleActions(
                TRANSFORMS.get("bubble"), TRANSFORMS.get("black-bubble")
            )
        );

        registerTransform("alt-square", "Alternating Square", "square-text",
            cycleActions(
                TRANSFORMS.get("square"), TRANSFORMS.get("black-square")
            )
        );

        registerTransform("white-shapes", "White Shapes", "ransom-note-text",
            cycleActions(
                TRANSFORMS.get("square"), TRANSFORMS.get("bubble")
            )
        );

        registerTransform("black-shapes", "Black Shapes", "ransom-note-text",
            cycleActions(
                TRANSFORMS.get("black-square"), TRANSFORMS.get("black-bubble")
            )
        );

        registerTransform("rand-bubble-square", "Ransom Bubble & Squares", "ransom-note-text",
            randomActions(1,
                TRANSFORMS.get("bubble"), TRANSFORMS.get("black-bubble"),
                TRANSFORMS.get("square"), TRANSFORMS.get("black-square")
            )
        );

        registerTransform("ransom-subtle", "Ransom Subtle", "ransom-note-text",
            randomActions(1,
                TRANSFORMS.get("bold"), TRANSFORMS.get("italic"),
                TRANSFORMS.get("bold-italic"), TRANSFORMS.get("bold-sans"),
                TRANSFORMS.get("italic-sans"), TRANSFORMS.get("bold-italic-sans")
            )
        );

        registerTransform("ransom-soup-1", "Ransom Kitchen Soup 1", "ransom-note-text",
            randomActions(1, "bold,italic,bold-italic,script,bold-script,double-struck,fraktur,bold-fraktur,bold-sans,italic-sans,bold-italic-sans,monospace,bubble,black-bubble,square,black-square".split(","))
        );

        registerTransform("ransom-soup-2", "Ransom Kitchen Soup 2", "ransom-note-text",
            randomActions(2, "bold,italic,bold-italic,script,bold-script,double-struck,fraktur,bold-fraktur,bold-sans,italic-sans,bold-italic-sans,monospace,bubble,black-bubble,square,black-square".split(","))
        );

        registerTransform("fontmash-1", "FontMash 1", "ransom-note-text",
            randomActions(1, "bold,italic,bold-italic,script,bold-script,double-struck,fraktur,bold-fraktur,bold-sans,italic-sans,bold-italic-sans,monospace".split(","))
        );

        registerTransform("fontmash-2", "FontMash 2", "ransom-note-text",
            randomActions(2, "bold,italic,bold-italic,script,bold-script,double-struck,fraktur,bold-fraktur,bold-sans,italic-sans,bold-italic-sans,monospace".split(","))
        );

        registerTransform("cuniform", "Cuniform", "cuniform",
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, -32),
            s -> mapCodePoint(s, "cun1")
        );

        registerTransform("vai", "Vai Letterlike", "vai",
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, -32),
            s -> mapCodePoint(s, "vai1")
        );

        registerTransform("bamum", "Bamum Letterlike", "bamum",
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, -32),
            s -> mapCodePoint(s, "bam1")
        );

        registerTransform("canadian-aboriginal-1", "Canadian Aboriginal Letterlike 1", "canadian-aboriginal",
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, -32),
            s -> mapCodePoint(s, "canab1")
        );

        registerTransform("canadian-aboriginal-2", "Canadian Aboriginal Letterlike 2", "canadian-aboriginal",
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, -32),
            s -> mapCodePoint(s, "canab2")
        );

        registerTransform("canadian-aboriginal-3", "Canadian Aboriginal Letterlike 3", "canadian-aboriginal",
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, -32),
            s -> mapCodePoint(s, "canab3")
        );

        registerTransform("canadian-aboriginal-sm", "Small Canadian Aboriginal Letterlike", "canadian-aboriginal",
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 32),
            s -> mapCodePoint(s, "canabsm1")
        );

        registerTransform("canadian-aboriginal-titlecase", "Canadian Aboriginal Letterlike Tile Case ", "canadian-aboriginal",
            randomActions(1, s -> mapCodePoint(s, "canab1"), s -> mapCodePoint(s, "canab2")),
            s -> mapCodePoint(s, "canabsm1")
        );

        registerTransform("parenthesis", "Parenthesis", "bubble-text",
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, 9275),
            s -> shiftCodePoint(s, IS_ASCII_UPPERCASE, 127183),
            s -> shiftCodePoint(s, i -> 49 <= i && i <= 57, 9283)
        );

        registerTransform("big-bubble", "Big bubbles", "bubble-text",
            s -> append(s, () -> IntStream.of(8413), i -> i == ' ')
        );

        registerTransform("keycap-bubble", "Keycap bubbles", "bubble-text",
            s -> append(s, () -> IntStream.of(8419), i -> i == ' ')
        );

        registerTransform("cjk-1", "CJK Letterlike 1", "east-asian-text",
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, -32),
            s -> mapCodePoint(s, "cjn"),
            s -> mapCodePoint(s, "cj1")
        );

        registerTransform("cjk-2", "CJK Letterlike 2", "east-asian-text",
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, -32),
            s -> mapCodePoint(s, "cjn"),
            s -> mapCodePoint(s, "cj2")
        );

        registerTransform("cjk-3", "CJK Letterlike 3", "east-asian-text",
            s -> shiftCodePoint(s, IS_ASCII_LOWERCASE, -32),
            s -> mapCodePoint(s, "cjn"),
            s -> mapCodePoint(s, "cj3")
        );

        registerTransform("cjk-combo", "CJK Letterlike Combo", "east-asian-text",
            randomActions(1, "cjk-1", "cjk-2", "cjk-3")
        );

        registerTransform("cjk-fw", "CJK Letterlike and Fullwidth", "east-asian-text",
            charEcho(TRANSFORMS.get("cjk-combo"), s -> shiftCodePoint(s, i -> 33 <= i && i <= 126, 65248))
        );

        registerTransform("under-arrow", "Under-arrow", "underline",
            s -> append(s, 866)
        );

        registerTransform("under-seagull", "Under-seagull", "underline",
            s -> append(s, 828)
        );

        registerTransform("under-asterisk", "Under-asterisk", "underline",
            s -> append(s, 857)
        );

        registerTransform("lightening", "Lightning above", "lightning",
            s -> append(s, 859)
        );

        registerTransform("more-lightning", "More lightning", "lightning",
            s -> append(s, () -> IntStream.of(859, 859, 859), i -> false)
        );

        registerTransform("smiley-above", "Smiley above", "faces",
            s -> append(s, () -> IntStream.of(784, 776), i -> false)
        );

        registerTransform("frown-above", "Frown above", "faces",
            s -> append(s, () -> IntStream.of(785, 775, 776), i -> false)
        );

        registerTransform("diamond", "Diamonds", "diamonds",
            s -> append(s, () -> IntStream.of(8415), i -> i == ' ')
        );

        registerTransform("do-not-enter", "Do not enter", "do-not-enter",
            s -> append(s, () -> IntStream.of(8416), i -> i == ' ')
        );

        registerTransform("clapback", "Clapback", "clapback",
            s -> mapCodePoint(s, i -> i == 32 ? new int[]{55357, 56399} : new int[0], true)
        );

        registerTransform("clapback-light", "Clapback (light)", "clapback",
            s -> mapCodePoint(s, i -> i == 32 ? new int[]{55357, 56399, 55356, 57339} : new int[0], true)
        );

        registerTransform("clapback-medium-light", "Clapback (medium light)", "clapback",
            s -> mapCodePoint(s, i -> i == 32 ? new int[]{55357, 56399, 55356, 57340} : new int[0], true)
        );

        registerTransform("clapback-medium", "Clapback (medium)", "clapback",
            s -> mapCodePoint(s, i -> i == 32 ? new int[]{55357, 56399, 55356, 57341} : new int[0], true)
        );

        registerTransform("clapback-medium-dark", "Clapback (medium dark)", "clapback",
            s -> mapCodePoint(s, i -> i == 32 ? new int[]{55357, 56399, 55356, 57342} : new int[0], true)
        );

        registerTransform("clapback-dark", "Clapback (dark)", "clapback",
            s -> mapCodePoint(s, i -> i == 32 ? new int[]{55357, 56399, 55356, 57343} : new int[0], true)
        );

        // TODO This definitely requires a state machine which is a very bad design
//        registerTransform("clapback-rainbow", "Clapback (rainbow)", "clapback",
//            s -> mapCodePoint(s, i -> i == 32 ? new int[]{55357, 56399, 55356, 57344} : new int[0], true)
//        );

//        registerTransform("rainbow-hearts", "Rainbow hearts", "hearts"
//
//        );
//
//        registerTransform("rainbow-hearts-no-red", "Rainbow hearts (no red)", "hearts"
//
//        );

        registerTransform("red-hearts", "Red hearts", "hearts",
            s -> mapCodePoint(s, i -> i == 32 ? new int[]{10084, 65039} : new int[0])
        );

        registerTransform("orange-hearts", "Orange hearts", "hearts",
            s -> mapCodePoint(s, i -> i == 32 ? new int[]{55358, 56801} : new int[0])
        );

        registerTransform("yellow-hearts", "Yellow hearts", "hearts",
            s -> mapCodePoint(s, i -> i == 32 ? new int[]{55357, 56475} : new int[0])
        );

        registerTransform("green-hearts", "Green hearts", "hearts",
            s -> mapCodePoint(s, i -> i == 32 ? new int[]{55357, 56474} : new int[0])
        );

        registerTransform("blue-hearts", "Blue hearts", "hearts",
            s -> mapCodePoint(s, i -> i == 32 ? new int[]{55357, 56473} : new int[0])
        );

        registerTransform("purple-hearts", "Purple hearts", "hearts",
            s -> mapCodePoint(s, i -> i == 32 ? new int[]{55357, 56476} : new int[0])
        );

        registerTransform("air-quotes", "Air quotes", "air-quotes",
            s -> wrap(s, " ", Character.toString(9996) + Character.toString(65039))
        );

        registerTransform("classified", "Classified", "classified",
            s -> s.replaceAll("\\S", "█")
        );
    }


}
