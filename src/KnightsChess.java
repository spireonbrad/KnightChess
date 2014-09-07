import java.util.*;

public class KnightsChess {

    public static void main(String[] args) {
        int[][] blocked = new int[4][3];
        blocked[0][0] = 0;
        blocked[0][1] = 0;
        blocked[0][2] = 0;

        blocked[1][0] = 0;
        blocked[1][1] = 0;
        blocked[1][2] = 1;

        blocked[2][0] = 1;
        blocked[2][1] = 0;
        blocked[2][2] = 0;

        blocked[3][0] = 0;
        blocked[3][1] = 0;
        blocked[3][2] = 0;

        KnightsChess knightsChess = new KnightsChess();
        System.out.println(knightsChess.solution(blocked));
    }

    public int solution(int[][] A) {
        int steps = BreadthFirstSearch(A, new ChessNode(0,0,0), new ChessNode(A.length-1, A[0].length-1));
        return steps;
    }

    private int BreadthFirstSearch(int[][] blocked, ChessNode root, ChessNode end) {


        Queue<ChessNode> q = new LinkedList<ChessNode>();
        Set<ChessNode> v = new HashSet<ChessNode>();
        v.add(root);
        q.add(root);

        while (!q.isEmpty()) {
            ChessNode t = q.remove();
            int level = t.level+1;
            if (t.equals(end)) {
                return t.level;
            }

            Set<ChessNode> validNextNodes = getValidNextKnightMoves(t, blocked, level, v);
            for (ChessNode u : validNextNodes) {
                if (!v.contains(u)) {
                    v.add(u);
                    q.add(u);
                }
            }
        }

        return -1;
    }

    private Set<ChessNode> getValidNextKnightMoves(ChessNode t, int[][] blocked, int level, Set<ChessNode> v) {
        Set<ChessNode> validNextNodes = new HashSet<ChessNode>();
        int x = t.x;
        int y = t.y;

        int xs = blocked.length;
        int ys = blocked[0].length;

        if (x-1 >= 0 && x-1 < xs && y-2 >= 0 && y-2 < ys && blocked[x-1][y-2] == 0 && !v.contains(new ChessNode(x-1,y-2))) {
            validNextNodes.add(new ChessNode(x-1,y-2,level));
        }
        if (x-2 >= 0 && x-2 < xs && y-1 >= 0 && y-1 < ys && blocked[x-2][y-1] == 0 && !v.contains(new ChessNode(x-2,y-1))) {
            validNextNodes.add(new ChessNode(x-2,y-1,level));
        }
        if (x-1 >= 0 && x-1 < xs && y+2 >= 0 && y+2 < ys && blocked[x-1][y+2] == 0 && !v.contains(new ChessNode(x-1,y+2))) {
            validNextNodes.add(new ChessNode(x-1,y+2,level));
        }
        if (x-2 >= 0 && x-2 < xs && y+1 >= 0 && y+1 < ys && blocked[x-2][y+1] == 0 && !v.contains(new ChessNode(x-2,y+1))) {
            validNextNodes.add(new ChessNode(x-2,y+1,level));
        }
        if (x+1 >= 0 && x+1 < xs && y-2 >= 0 && y-2 < ys && blocked[x+1][y-2] == 0 && !v.contains(new ChessNode(x+1,y-2))) {
            validNextNodes.add(new ChessNode(x+1,y-2,level));
        }
        if (x+2 >= 0 && x+2 < xs && y-1 >= 0 && y-1 < ys && blocked[x+2][y-1] == 0 && !v.contains(new ChessNode(x+2,y-1))) {
            validNextNodes.add(new ChessNode(x+2,y-1,level));
        }
        if (x+1 >= 0 && x+1 < xs && y+2 >= 0 && y+2 < ys && blocked[x+1][y+2] == 0 && !v.contains(new ChessNode(x+1,y+2))) {
            validNextNodes.add(new ChessNode(x+1,y+2,level));
        }
        if (x+2 >= 0 && x+2 < xs && y+1 >= 0 && y+1 < ys && blocked[x+2][y+1] == 0 && !v.contains(new ChessNode(x+2,y+1))) {
            validNextNodes.add(new ChessNode(x+2,y+1,level));
        }

        return validNextNodes;
    }

    class ChessNode {

        int x;
        int y;
        int level;

        public ChessNode(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public ChessNode(int x, int y, int level) {
            this(x,y);
            this.level = level;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ChessNode)) return false;

            ChessNode ChessNode = (ChessNode) o;

            if (x != ChessNode.x) return false;
            if (y != ChessNode.y) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }

        @Override
        public String toString() {
            return "chessNode{" +
                    "x=" + x +
                    ", y=" + y +
                    ", level=" + level +
                    '}';
        }
    }
}


