public class Triangle {
    int s1;
    int s2;
    int s3;
    public Triangle(int side1, int side2, int side3) {
        this.s1 = side1;
        this.s2 = side2;
        this.s3 = side3;
    }
    public int getLongestSide() {
        int greatest = this.s1;
        if (this.s2 > greatest) {
            greatest = this.s2;
        }
        if (this.s3 > greatest) {
            greatest = this.s3;
        }
        return greatest;
    }

    public String toString() {
        return "This is a Triangle with sides of length" + this.s1 + this.s2 + this.s3;

    }
}