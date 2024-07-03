package dev.lasm.yeahtext;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Stacker {
    private Map<Integer, int[]> dmap = NMaps.NMAPS.get("d");
    private Map<Integer, int[]> smap = NMaps.NMAPS.get("s");
    private Map<Integer, int[]> idmap = NMaps.NMAPS.get("id");
    private Map<Integer, int[]> ismap = NMaps.NMAPS.get("is");

    public Stacker() {

    }

    public static String[] makePairParts(String str) {
        var split = str.split("\n");
        if (split.length < 2) {
            split = str.split(" ");
        }
        return split;
    }

    public static ArrayList<AbstractMap.SimpleEntry<String, String>> makePairs(String str) {
        var parts = makePairParts(str);
        var pairs = new ArrayList<AbstractMap.SimpleEntry<String, String>>();
        for (int i = 0; i < parts.length; i+=2) {
            pairs.add(new AbstractMap.SimpleEntry<>(parts[i], i+1 < parts.length ? parts[i+1] : ""));
        }
        return pairs;
    }

    public void shufflePair(AbstractMap.SimpleEntry<String, String> pair) {
        if (dmap.containsKey(pair.getKey().codePointAt(0))) {
            pair.setValue(" " + pair.getValue());
        }
        var maxLength = Math.max(pair.getKey().length(), pair.getValue().length());
        for (int i = 0; i < maxLength; i++) {

        }
    }
}
