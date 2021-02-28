public class GreedyAlgorithm extends Algorithm {
    public GreedyAlgorithm(Problem problem) {
        this.problem = problem;
    }

    public int computeCost() {
        int[] demand = new int[]{20, 25, 25};
        int[] supply = new int[]{10, 35, 25};
        int total_cost = 0;
        int contor = 0;

        for (Source source : problem.getSource()) {
            for (Destination dest : source.getDestinations()) {
                System.out.println(source.getName() + "->" + dest.getName() + ": ");

                if (supply[contor] == 0)
                    continue;

                total_cost += supply[contor] * dest.getCost();
                if (supply[contor] < demand[contor]) {
                    supply[contor] = 0;
                } else {
                    supply[contor] -= demand[contor];
                }

                System.out.print(supply[contor] + " * " + dest.getCost() + " = " + supply[contor] * dest.getCost() + "\n");
            }
            contor++;
        }

        return total_cost;
    }

    @Override
    public Solution solve() {
        // Solution solution = new Solution();

        System.out.println("Costul este = " + computeCost());

        return null;
    }
}
