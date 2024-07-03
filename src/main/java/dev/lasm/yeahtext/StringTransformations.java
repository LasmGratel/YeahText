package dev.lasm.yeahtext;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class StringTransformations {
    private StringTransformations() {
    }

    public static String normalize(CharSequence str, Normalizer.Form form) {
        return Normalizer.normalize(str, form);
    }

    public static String append(String str, Supplier<IntStream> chars, IntPredicate except) {
        return str.codePoints()
            .flatMap(x -> except.test(x) ? IntStream.of(x) : IntStream.concat(IntStream.of(x), chars.get()))
            .mapToObj(Character::toString)
            .collect(Collectors.joining());
    }

    public static String append(String str, int ch) {
        return str.codePoints()
            .flatMap(x -> IntStream.of(x, ch))
            .mapToObj(Character::toString)
            .collect(Collectors.joining());
    }

    public static String shiftCodePoint(String str, IntPredicate range, int add) {
        return str.codePoints()
            .map(x -> range.test(x) ? x + add : x)
            .mapToObj(Character::toString)
            .collect(Collectors.joining());
    }

    public static String mapCodePoint(String str, IntFunction<int[]> map) {
        return str.codePoints()
            .flatMap(x -> {
                var arr = map.apply(x);
                return arr.length == 0 ? IntStream.of(x) : IntStream.of(arr);
            })
            .mapToObj(Character::toString)
            .collect(Collectors.joining());
    }

    public static String mapCodePoint(String str, IntFunction<int[]> map, boolean prepend) {
        if (!prepend) return mapCodePoint(str, map);

        return str.codePoints()
            .flatMap(x -> {
                var arr = map.apply(x);
                return arr.length == 0 ? IntStream.of(x) : IntStream.concat(IntStream.of(arr), IntStream.of(x));
            })
            .mapToObj(Character::toString)
            .collect(Collectors.joining());
    }

    public static String mapCodePoint(String str, IntPredicate filter, IntUnaryOperator map) {
        return str.codePoints()
            .map(i -> filter.test(i) ? map.applyAsInt(i) : i)
            .mapToObj(Character::toString)
            .collect(Collectors.joining());
    }

    public static String mapCodePoint(String str, String map) {
        var nmap = NMaps.NMAPS.get(map);
        if (nmap != null) {
            return str.codePoints()
                .flatMap(x -> {
                    var arr = nmap.get(x);
                    if (arr != null) return IntStream.of(arr);
                    else return IntStream.of(x);
                })
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static String mapCodePoint(String str, String map, int append) {
        var nmap = NMaps.NMAPS.get(map);
        if (nmap != null) {
            return str.codePoints()
                .map(x -> {
                    var arr = nmap.get(x);
                    if (arr != null) return arr[0] + append;
                    else return x;
                })
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static String mapCodePoint(String str, String map, boolean append) {
        if (!append) return mapCodePoint(str, map);
        var nmap = NMaps.NMAPS.get(map);
        if (nmap != null) {
            return str.codePoints()
                .flatMap(x -> {
                    var arr = nmap.get(x);
                    if (arr != null) return IntStream.of(x, arr[0]);
                    else return IntStream.of(x);
                })
                .mapToObj(Character::toString)
                .collect(Collectors.joining());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static String wrap(String str, String splitChar, String wrapChar) {
        return Arrays.stream(str.split(splitChar))
            .map(s -> wrapChar + s + wrapChar)
            .collect(Collectors.joining(splitChar));
    }

    public static String stack(String str, String dmap, String smap) {
        throw new RuntimeException("Not implemented yet");
    }

    public static String reverse(String str) {
        return new StringBuilder(str).reverse().toString();
    }
}
