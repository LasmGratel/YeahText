package dev.lasm.yeahtext.alea;


import java.util.stream.DoubleStream;

public class Mash {
    private double n = 0xefc8249dL;

    public double mash(String seed) {
        return mash(seed.codePoints().mapToDouble(i -> (long)i));
    }

    public double mash(DoubleStream data) {
        n = data.reduce(n, (n, d) -> {
            n += d;
            var h = 0.02519603282416938 * n;
            n = (long)h;
            h -= n;
            h *= n;
            n = (long)h;
            h -= n;
            n += h * 0x100000000L; // 2^32
            return n;
        }); // 2^-32
        return Math.floor(n) * 2.3283064365386963e-10;
    }
}
