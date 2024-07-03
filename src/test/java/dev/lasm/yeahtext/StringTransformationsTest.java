package dev.lasm.yeahtext;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringTransformationsTest {

    @Test
    void normalize() {
    }

    @Test
    void append() {
        var s = StringTransformations.append("xxxxx", "͛"::codePoints, x -> false);
        assertEquals("x͛x͛x͛x͛x͛", s);

        s = StringTransformations.mapCodePoint("fdjsnfd", "wdblup");
        s = StringTransformations.mapCodePoint(s, "wdbllo");
        assertEquals("⨎ⅆⅉ\uD835\uDD64ℼ⨎ⅆ", s);

        s = StringTransformations.shiftCodePoint("Shikire", i -> 65 <= i && i <= 90, 119951);
        s = StringTransformations.shiftCodePoint(s, i -> 97 <= i && i <= 122, 119945);
        assertEquals("𝓢𝓱𝓲𝓴𝓲𝓻𝓮", s);
    }
}