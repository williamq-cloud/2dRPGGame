import java.util.Random;

public class NoiseGen {

    private static final int PERMUTATION_SIZE = 256;
    private final short[] perm;

    public NoiseGen(long seed) {
        perm = new short[PERMUTATION_SIZE * 2];
        short[] source = new short[PERMUTATION_SIZE];
        for (short i = 0; i < PERMUTATION_SIZE; i++) {
            source[i] = i;
        }

        Random rand = new Random(seed);
        for (int i = PERMUTATION_SIZE - 1; i >= 0; i--) {
            int r = rand.nextInt(i + 1);
            short temp = source[i];
            source[i] = source[r];
            source[r] = temp;
            perm[i] = source[i];
            perm[i + PERMUTATION_SIZE] = source[i];
        }
    }

    public double noise(double x, double y) {
        // Super simplified value noise (not full simplex, but works for terrain!)
        int xi = (int) Math.floor(x) & 255;
        int yi = (int) Math.floor(y) & 255;

        double xf = x - Math.floor(x);
        double yf = y - Math.floor(y);

        double u = fade(xf);
        double v = fade(yf);

        int aa = perm[perm[xi] + yi];
        int ab = perm[perm[xi] + yi + 1];
        int ba = perm[perm[xi + 1] + yi];
        int bb = perm[perm[xi + 1] + yi + 1];

        double x1 = lerp(u, grad(aa, xf, yf), grad(ba, xf - 1, yf));
        double x2 = lerp(u, grad(ab, xf, yf - 1), grad(bb, xf - 1, yf - 1));

        return (lerp(v, x1, x2) + 1) / 2; // return value in [0,1]
    }

    private double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }

    private double lerp(double t, double a, double b) {
        return a + t * (b - a);
    }

    private double grad(int hash, double x, double y) {
        switch (hash & 3) {
            case 0: return x + y;
            case 1: return -x + y;
            case 2: return x - y;
            case 3: return -x - y;
            default: return 0; // never reached
        }
    }

}
