public class Main {
    private static int total_sources = 3;
    private static int total_destinations = 3;
    int[] demand = new int[]{20, 25, 25};
    int[] suply = new int[]{10, 35, 25};

    public static void main(String[] args) {
        /*
         * Fiecare sursa contine o lista de destinatii
         * pentru fiecare destinatie a unei surse, precizez costul
         */
        Source[] source = new Source[total_sources];
        Destination[] destination = new Destination[total_destinations];

        destination[0] = new Destination("D1", 2);
        destination[1] = new Destination("D2", 3);
        destination[2] = new Destination("D3", 1);
        source[0] = new Source("S1", SourceType.FACTORY, destination);

        destination[0] = new Destination("D1", 5);
        destination[1] = new Destination("D2", 4);
        destination[2] = new Destination("D3", 8);
        source[1] = new Source("S2", SourceType.WAREHOUSE, destination);

        destination[0] = new Destination("D1", 5);
        destination[1] = new Destination("D2", 6);
        destination[2] = new Destination("D3", 8);
        source[2] = new Source("S3", SourceType.WAREHOUSE, destination);

        /*
         * o instanta a problemei contine o lista de surse si o lista de destinatii
         */
        Problem problem = new Problem(source, destination);
        System.out.println(problem);
    }
}
