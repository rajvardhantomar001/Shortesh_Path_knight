import java.util.Scanner;

public class knightsolution {
    class Coordinate {
        int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    Coordinate a[][] = new Coordinate[7][262145];
    int b[][] = new int[8][8];
    boolean isVisited[][] = new boolean[8][8];

    public void knight(int x1, int y1, int x2, int y2) {
        int dx[] = { -1, -2, -1, -2, 1, 2, 1, 2 };
        int dy[] = { -2, -1, 2, 1, -2, -1, 2, 1 };
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 262144; j++) {
                a[i][j] = new Coordinate(-1, -1);
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                b[i][j] = -1;
            }
        }
        a[0][0] = new Coordinate(x1, y1);
        isVisited[0][0] = true;
        for (int i = 1; i < 7; i++) {
            int n = (int) Math.pow(8, i);
            for (int k = 0; k < n; k++) {
                if ((a[i - 1][(int) (k / 8)].x) > -1 && (a[i - 1][(int) (k / 8)].y) > -1) {
                    int x = a[i - 1][(int) (k / 8)].x + dx[k % 8];
                    int y = a[i - 1][(int) (k / 8)].y + dy[k % 8];
                    a[i][k] = new Coordinate(x, y);
                    if (!(isValid(a[i][k].x, a[i][k].y))) {
                        a[i][k].x = -1;
                        a[i][k].y = -1;
                    } else if (x < 8 && y < 8) {
                        if (isVisited[x][y] == true) {
                            a[i][k].x = -1;
                            a[i][k].y = -1;
                        }
                    } else if (x < 8 && y < 8) {
                        isVisited[x][y] = true;
                    }
                } else {
                    a[i][k].x = -1;
                    a[i][k].y = -1;
                }
                if (a[i][k].x == x2 && a[i][k].y == y2) {
                    System.out.println();
                    System.out.println("Number of Steps to reach target is: " + i);
                    int m = i;
                    int o = k;
                    while (i > -1) {
                        b[a[i][k].x][a[i][k].y] = i;
                        i--;
                        k = (int) k / 8;
                    }
                    Print(m, o);
                    return;
                }
            }
        }
        System.out.println("Not possible to reach at the target ");
    }

    void Print(int k, int l) {
        System.out.println();
        System.out.println("Board Matrix: ");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (b[i][j] != -1) {
                    System.out.print("[" + b[i][j] + "]    ");
                } else {
                    System.out.print(b[i][j] + "     ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("BFS Graph Matrix: ");
        for (int i = 0; i <= k; i++) {
            int n = (int) Math.pow(8, i);
            for (int j = 0; j < n && j <= l; j++) {
                if (a[i][j].x != -1) {
                    System.out.print("( " + a[i][j].x + "," + a[i][j].y + " )    ");
                } else {
                    System.out.print("(" + a[i][j].x + "," + a[i][j].y + ")    ");
                }
            }
            System.out.println();
        }
    }

    boolean isValid(int x, int y) {
        if (x < 0 || y < 0 || x > 8 || y > 8) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        knightsolution ob = new knightsolution();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the initial position of knight: ");
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        System.out.println("Enter the target position: ");
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        ob.knight(x1, y1, x2, y2);
        sc.close();
    }
}