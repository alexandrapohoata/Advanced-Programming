/*
Let n be an odd integer given as a command line argument. Validate the argument!
Create a n x n matrix, representing the adjacency matrix of a random graph .
Display on the screen the generated matrix (you might want to use the geometric shapes from
the Unicode chart to create a "pretty" representation of the matrix).
Verify if the generated graph is connected and display the connected components (if it is not).
Assuming that the generated graph is connected, implement an algorithm that creates a partial
tree of the graph. Display the adjacency matrix of the tree.
For larger n display the running time of the application in nanoseconds (DO NOT display the matrices).
Try n > 30_000. You might want to adjust the JVM Heap Space using the VM options -Xms4G -Xmx4G.
Launch the application from the command line, for example: java Lab1 100.
 */

public class Main {
    private static double probabilitate = 0.13;

    public static void main(String[] args) {
        int n = 0;
        try {
            n = Integer.parseInt(args[0], 10);
        } catch (NumberFormatException exp) {
            System.out.println("Numarul nu este valid!!!");
        }

        System.out.println("Numarul dat ca argument este " + n);

        // verific daca nr este impar
        if (n % 2 == 0) {
            System.out.println("Numarul este par. Introdu un nr impar.");
            System.exit(1);
        } else {
            System.out.println("Numarul este valid.");
            int[][] matrice = new int[n][n];
            for (int i = 0; i < n; i++)  {
                for (int j = i+1; j < n; j++) {
                    double probabilitate_random = (Math.random() * 100) / 100;

                    if (probabilitate_random <= probabilitate) {
                        matrice[i][j] = 1;
                        matrice[j][i] = 1;
                    } else {
                        matrice[i][j] = 0;
                        matrice[j][i] = 0;
                    }
                }
            }

//            System.out.println("Matricea generata este:");
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.print(matrice[i][j] + " ");
//                }
//                System.out.println();
//            }
            System.out.println("Componentele conexe sunt: ");

            // verific daca este conex
            if (!connectedComponents(n, matrice))
                System.out.println("Graful nu este conex.");
            else {
                System.out.println("Graful este conex.");

                
            }
        }
    }

    // afiseaza din nou nodurile care sunt vizitate
    public static void DFS(int nod, int n, int[][] matrice, boolean[] visited, boolean[] discovered, int isConnected) {
        visited[nod] = true;
        if (!discovered[nod]) {
            System.out.print(nod + " ");
            isConnected++;
        }

        // veficam vecinii nodului nod si cautam daca are alti vecini
        // in caz adevarat, parcurgem si vecinii acestuia pana la epuizare
        for (int i = 0; i < n; i++) {
            if (matrice[nod][i] == 1 && !visited[i]) {
                DFS(i, n, matrice, visited, discovered, isConnected);
            }
        }
    }

    private static boolean connectedComponents(int n, int[][] matrice) {
        boolean[] visited = new boolean[n];
        boolean[] discovered = new boolean[n];
        int isConnected = 1;
        for (int v = 0; v < n; v++) {
            // daca nodul este deja vizitat, nu aplicam din nou dfs din acel nod
            if (visited[v])
                continue;

            DFS(v, n, matrice, visited, discovered, isConnected);
            System.out.println();
            isConnected--;
        }

        return isConnected == 0;
    }
}

