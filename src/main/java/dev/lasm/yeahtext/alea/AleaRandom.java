package dev.lasm.yeahtext.alea;

import java.util.function.Supplier;
import java.util.stream.DoubleStream;

/**
 * Alea 0.9 PRNG
 * @author Johannes Baagoe (baagoe@baagoe.com)
 */
public class AleaRandom {
    private double s0, s1, s2, c = 1;

    public AleaRandom(double[] i) {
        s0 = Math.abs(i[0]);
        s1 = Math.abs(i[1]);
        s2 = Math.abs(i[2]);
        c = Math.abs(i[3]);
    }

    public AleaRandom(String... args) {
        var masher = new Mash();
        s0 = masher.mash(" ");
        s1 = masher.mash(" ");
        s2 = masher.mash(" ");

        if (args.length == 0) {
            Supplier<DoubleStream> seed = () -> DoubleStream.of(System.currentTimeMillis());
            s0 -= masher.mash(seed.get());
            if (s0 < 0) {
                s0 += 1;
            }
            s1 -= masher.mash(seed.get());
            if (s1 < 0) {
                s1 += 1;
            }
            s2 -= masher.mash(seed.get());
            if (s2 < 0) {
                s2 += 1;
            }
        } else {
            for (String arg : args) {
                s0 -= masher.mash(arg);
                if (s0 < 0) {
                    s0 += 1;
                }
                s1 -= masher.mash(arg);
                if (s1 < 0) {
                    s1 += 1;
                }
                s2 -= masher.mash(arg);
                if (s2 < 0) {
                    s2 += 1;
                }
            }
        }
    }

    public double next() {
        var t = 2091639 * s0 + c * 2.3283064365386963e-10; // 2^-32
        s0 = s1;
        s1 = s2;
        return s2 = t - (c = (long) t);
    }

    public long nextUInt32() {
        return (long) (next() * 0x100000000L);
    }

    public int nextInt() {
        return (int) (next() * 0x100000000L);
    }

    public double nextFract53() {
        return next() +
            (long)(next() * 0x200000) * 1.1102230246251565e-16; // 2^-53
    }

    public void importState(double[] i) {
        s0 = Math.abs(i[0]);
        s1 = Math.abs(i[1]);
        s2 = Math.abs(i[2]);
        c = Math.abs(i[3]);
    }

    public double[] exportState() {
        return new double[]{s0, s1, s2, c};
    }
}
