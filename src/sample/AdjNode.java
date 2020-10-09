package sample;

public class AdjNode<T extends Comparable<T>> implements Comparable<AdjNode> {
    int weight;
    Node n1;

    public AdjNode(Node n1, int weight) {
        this.n1 = n1;
        this.weight = weight;

    }

    @Override
    public int compareTo(AdjNode o) {
        if (o.weight == this.weight) {
            return 0;
        }
        if (o.weight < this.weight) {
            return 1;
        } else return -1;
    }

    @Override
    public String toString() {
        return "AdjNode{" +
                "weight=" + weight +
                ", n1=" + n1.toString() +
                '}';
    }
}
