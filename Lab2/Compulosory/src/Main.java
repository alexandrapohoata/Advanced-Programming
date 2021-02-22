public class Main {
    private static int TOTAL_SOURCES = 3;
    private static int TOTAL_DESTINATIONS = 3;
    int[] demand = new int[]{20, 25, 25};
    int[] supply = new int[]{10, 35, 25};

    public static void main(String[] args) {
        /*
         * Fiecare sursa contine o lista de destinatii
         * pentru fiecare destinatie a unei surse, precizez costul
         */
        Source[] sources = new Source[TOTAL_SOURCES];
        Destination[] destinations = new Destination[TOTAL_DESTINATIONS];

        destinations[0] = new Destination("D1", 2);
        destinations[1] = new Destination("D2", 3);
        destinations[2] = new Destination("D3", 1);
        sources[0] = new Source("S1", SourceType.FACTORY, destinations);

        destinations[0] = new Destination("D1", 5);
        destinations[1] = new Destination("D2", 4);
        destinations[2] = new Destination("D3", 8);
        sources[1] = new Source("S2", SourceType.WAREHOUSE, destinations);

        destinations[0] = new Destination("D1", 5);
        destinations[1] = new Destination("D2", 6);
        destinations[2] = new Destination("D3", 8);
        sources[2] = new Source("S3", SourceType.WAREHOUSE, destinations);

        /*
         * o instanta a problemei contine o lista de surse si o lista de destinatii
         */
        Problem problem = new Problem(sources, destinations);
        System.out.println(problem);
    }
}
