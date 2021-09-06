/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseballElimination {
    private int teamnumber = 0;
    private List<String> allteams = new ArrayList<String>();
    private Map<Integer, Integer> team_win = new HashMap<Integer, Integer>();
    private Map<Integer, Integer> team_lose = new HashMap<Integer, Integer>();
    private Map<Integer, Integer> team_left = new HashMap<Integer, Integer>();
    private Map<String, Integer> team_order = new HashMap<String, Integer>();
    private Map<Integer, String> team_index = new HashMap<Integer, String>();
    private Bag<String> subset;
    private int[][] against;

    public BaseballElimination(String filename) {
        In in = new In(filename);
        int n = 0;
        this.teamnumber = in.readInt();
        against = new int[this.teamnumber][this.teamnumber];
        in.readLine();
        while (!in.isEmpty()) {
            String s = in.readLine();
            while (s.charAt(0) == ' ') {
                s = s.substring(1);
            }
            String[] splited = s.split("\\s+");
            this.allteams.add(splited[0]);
            this.team_win.put(n, Integer.parseInt(splited[1]));
            this.team_lose.put(n, Integer.parseInt(splited[2]));
            this.team_left.put(n, Integer.parseInt(splited[3]));
            this.team_order.put(splited[0], n);
            this.team_index.put(n, splited[0]);
            for (int i = 0; i < this.teamnumber; i++) {
                against[n][i] = Integer.parseInt(splited[4 + i]);
            }

            n++;


        }

    }

    public int numberOfTeams() {
        return teamnumber;
    }                        // number of teams

    public Iterable<String> teams() {
        return this.allteams;
    }                                // all teams

    public int wins(String team) {
        if (!allteams.contains(team)) {
            throw new IllegalArgumentException();
        }
        int order = team_order.get(team);
        return team_win.get(order);
    }                      // number of wins for given team

    public int losses(String team) {
        if (!allteams.contains(team)) {
            throw new IllegalArgumentException();
        }
        int order = team_order.get(team);
        return team_lose.get(order);

    }                    // number of losses for given team

    //
    public int remaining(String team) {
        if (!allteams.contains(team)) {
            throw new IllegalArgumentException();
        }
        int order = team_order.get(team);
        return team_left.get(order);

    }                 // number of remaining games for given team

    //
    public int against(String team1,
                       String team2) {
        if (!allteams.contains(team1) || !allteams.contains(team2)) {
            throw new IllegalArgumentException();
        }
        int order1 = team_order.get(team1);
        int order2 = team_order.get(team2);
        return against[order1][order2];


    }    // number of remaining games between team1 and team2

    //
    public boolean isEliminated(String team) {
        if (!allteams.contains(team)) {
            throw new IllegalArgumentException();
        }
        subset = new Bag<String>();
        for (int i = 0; i < this.teamnumber; i++) {
            if (wins(team) + remaining(team) < team_win.get(i)) {
                subset.add(team_index.get(i));
                return true;
            }
        }

        return flowcheck(team);

    }              // is given team eliminated?

    private boolean flowcheck(String team) {  //
        if (!allteams.contains(team)) {
            throw new IllegalArgumentException();
        }
        // calculate the total vertice
        int index = team_order.get(team);
        int combination = ((this.teamnumber - 1) * (this.teamnumber - 2)) / 2;
        int total_vertice = (this.teamnumber - 1) + combination + 2;
        int total = 0;
        // first is s and is t
        int n = 1;
        int z = 1;
        Map<Integer, Integer> new_order = new HashMap<Integer, Integer>(); // order without team
        Map<Integer, Integer> new_order_reverse
                = new HashMap<Integer, Integer>(); // order without team
        int order = 0; // for order
        for (int i = 0; i < this.teamnumber; i++) {
            if (i == index) {
                continue;
            }
            new_order.put(i, order);
            new_order_reverse.put(order, i);
            order++;
        }
        FlowNetwork network = new FlowNetwork(total_vertice);
        for (int i = 0; i < this.teamnumber; i++) {
            if (i == index) {
                continue;
            }
            for (int j = i + 1; j < this.teamnumber; j++) {
                if (j == index) {
                    continue;
                }
                total += against[i][j];
                network.addEdge(new FlowEdge(0, n, against[i][j]));
                network.addEdge(new FlowEdge(n, (1 + combination + new_order.get(i)),
                                             Double.POSITIVE_INFINITY));
                network.addEdge(new FlowEdge(n, (1 + combination + new_order.get(j)),
                                             Double.POSITIVE_INFINITY));

                n++;
            }


            network.addEdge(new FlowEdge(z + combination, total_vertice - 1,
                                         team_win.get(index) + team_left.get(index) - team_win
                                                 .get(i)));
            z++;
        }


        // calculate maxflow with ff
        FordFulkerson find = new FordFulkerson(network, 0, total_vertice - 1);
        if (find.value() == total) {
            return false;
        }
        else {
            Bag<String> subset1 = new Bag<String>();
            n = 0;

            for (int i = combination + 1; i < total_vertice - 1; i++) {

                if (find.inCut(i)) {

                    int real_order = new_order_reverse.get(i - combination - 1);
                    subset1.add(team_index.get(real_order));
                    n++;

                }
            }

            this.subset = subset1;
            return true;
        }

    } //

    //
    public Iterable<String> certificateOfElimination(
            String team) {
        if (!allteams.contains(team)) {
            throw new IllegalArgumentException();
        }
        if (isEliminated(team)) {
            return this.subset;
        }
        else {
            return null;
        }

    }  // subset R of teams that eliminates given team; null if not eliminated

    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination("teams5c.txt");
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            }
            else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
