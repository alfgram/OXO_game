public class Main {
    public static void main(String[] args) {
        Triangle testTriangle = new Triangle(5, 7, 9);
        int longestSide = testTriangle.getLongestSide();
        System.out.println("The longest side of the triangle is " + longestSide);

        System.out.println(testTriangle.toString());
    }
}
