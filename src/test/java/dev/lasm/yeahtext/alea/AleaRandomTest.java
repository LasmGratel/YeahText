package dev.lasm.yeahtext.alea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AleaRandomTest {
    @Test
    void test() {
        var prng1 = new AleaRandom("1");
        var prng2 = new AleaRandom("3");
        var prng3 = new AleaRandom("1");

        var a = prng1.next();
        var b = prng2.next();
        var c = prng3.next();

        assertEquals(a, c, "return values of the same seed");
        assertNotEquals(a, b, "return values of the same seed");

        assertEquals(prng1.next(), prng3.next(), "same seed called again");

        assertNotEquals(prng1.next(), prng2.next(), "different seed called again");
        assertNotEquals(prng1.next(), prng3.next(), "prng1 called more times than prng3");
        assertNotEquals(prng2.next(), prng3.next(), "prng3 called again");

        assertEquals(prng1.next(), prng3.next(), "call counts equal again");
    }

    @Test
    void testExportState() {
        assertArrayEquals(new double[]{0.45692888274788857, 0.25068293139338493, 0.4848942293319851, 1}, new AleaRandom("12345").exportState());
    }

    @Test
    void testMash() {
        var mash = new Mash();
        assertEquals(0.8633289230056107, mash.mash(" "));
        assertEquals(0.15019597788341343, mash.mash(" "));
        assertEquals(0.9176952994894236, mash.mash(" "));
    }

    @Test
    void knownValuesTest() {
        var prng1 = new AleaRandom("12345");

        var values = new double[]{
            0.27138191112317145,
            0.19615925149992108,
            0.6810678059700876
        };

        assertEquals(prng1.next(), values[0], "check value 1");
        assertEquals(prng1.next(), values[1], "check value 2");
        assertEquals(prng1.next(), values[2], "check value 3");
    }

    @Test
    void testUInt32() {
        var prng1 = new AleaRandom("12345");
        var values = new long[] {1165576433,
            842497570,
            2925163953L};

        assertEquals(values[0], prng1.nextUInt32());
        assertEquals(values[1], prng1.nextUInt32());
        assertEquals(values[2], prng1.nextUInt32());
    }

    @Test
    void testFract53() {
        var prng1 = new AleaRandom("12345");
        var values = new double[] {
            0.27138191116884325,
            0.6810678062004586,
            0.3407802057882554
        };

        assertEquals(values[0], prng1.nextFract53());
        assertEquals(values[1], prng1.nextFract53());
        assertEquals(values[2], prng1.nextFract53());
    }

    @Test
    void testImport() {
        var prng1 = new AleaRandom("200");

        // generate a few numbers
        prng1.next();
        prng1.next();
        prng1.next();

        var e = prng1.exportState();

        var prng4 = new AleaRandom(e);

        assertEquals(prng1.next(), prng4.next(), "synced prngs, call 1");
        assertEquals(prng1.next(), prng4.next(), "synced prngs, call 2");
        assertEquals(prng1.next(), prng4.next(), "synced prngs, call 3");
    }

    @Test
    void testSync() {
        var prng1 = new AleaRandom("200000");
        var prng2 = new AleaRandom("9000");

        // generate a few numbers

        assertNotEquals(prng1.next(), prng2.next(), "just generating randomness, call 1");
        assertNotEquals(prng1.next(), prng2.next(), "just generating randomness, call 2");
        assertNotEquals(prng1.next(), prng2.next(), "just generating randomness, call 3");

        // sync prng2 to prng1
        prng2.importState(prng1.exportState());

        assertEquals(prng1.next(), prng2.next(), "imported prng, call 1");
        assertEquals(prng1.next(), prng2.next(), "imported prng, call 2");
        assertEquals(prng1.next(), prng2.next(), "imported prng, call 3");

        // let's test they still sync up if called non-sequentially

        prng1.next();
        prng1.next();

        var a1 = prng1.next();
        var b1 = prng1.next();
        var c1 = prng1.next();

        prng2.next();
        prng2.next();

        var a2 = prng2.next();
        var b2 = prng2.next();
        var c2 = prng2.next();

        assertEquals(a1, a2, "return values should sync based on number of calls, call 1");
        assertEquals(b1, b2, "return values should sync based on number of calls, call 2");
        assertEquals(c1, c2, "return values should sync based on number of calls, call 3");
    };
}