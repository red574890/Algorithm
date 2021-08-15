import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private Picture picture;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        this.picture = picture;
    }

    // current picture
    public Picture picture() {
        return this.picture;
    }

    // width of current picture
    public int width() {
        return this.picture.width();
    }

    // height of current picture
    public int height() {
        return this.picture.height();
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        // define the energy of a pixel at the border of the image to be 1000, so that it is strictly larger than the energy of any interior pixel.
        if (x == 0 || y == 0 || x == this.picture.width() - 1 || y == this.picture.height() - 1) {
            return 1000.0;
        }
        else {
            double x_range =
                    Math.pow((picture.get(x + 1, y).getRed() - picture.get(x - 1, y).getRed()), 2)
                            + Math.pow(
                            (picture.get(x + 1, y).getGreen() - picture.get(x - 1, y).getGreen()),
                            2)
                            + Math.pow((picture.get(x + 1, y).getBlue() - picture.get(x - 1, y)
                                                                                 .getBlue()), 2);
            double y_range =
                    Math.pow(picture.get(x, y + 1).getRed() - picture.get(x, y - 1).getRed(), 2)
                            + Math.pow(
                            (picture.get(x, y + 1).getGreen() - picture.get(x, y - 1).getGreen()),
                            2)
                            + Math.pow((picture.get(x, y + 1).getBlue() - picture.get(x, y - 1)
                                                                                 .getBlue()), 2);

            return Math.sqrt(x_range + y_range);

        }
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        transposePicture();
        int[] seam = findVerticalSeam();
        transposePicture();
        return seam;

    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        // cache the energy levels for each pixel
        double[][] energy = new double[picture.height()][];

        // store the lowest energy cost
        double[][] distTo = new double[picture.height()][];

        // the lowest energy path to each pixel
        int[][] edgeTo = new int[picture.height()][];

        for (int row = 0; row < picture.height(); row++) {
            energy[row] = new double[picture.width()];
            distTo[row] = new double[picture.width()];
            edgeTo[row] = new int[picture.width()];
            for (int col = 0; col < picture.width(); col++) {
                energy[row][col] = energy(col, row);
                if (row == 0) {
                    distTo[row][col] = 0;
                }
                else {
                    distTo[row][col] = Double.POSITIVE_INFINITY;
                }
                edgeTo[row][col] = -1;

            }
        }

        //traverse image in topological order
        for (int row = 0; row < picture.height() - 1; row++) {
            for (int col = 0; col < picture.width(); col++) {
                int next_row = row + 1;
                for (int next_col = col - 1; next_col <= col + 1; next_col++) {
                    if (next_col >= 0 && next_col < picture.width()) {
                        double weight = energy[next_row][next_col];
                        if (distTo[next_row][next_col] > distTo[row][col] + weight) {
                            distTo[next_row][next_col] = distTo[row][col] + weight;
                            edgeTo[next_row][next_col] = col;
                        }

                    }
                }
            }
        }


        int[] seam = new int[picture.height()];

        //find the lowest path
        double min = Double.POSITIVE_INFINITY;
        int low_col = -1;
        for (int col = 0; col < picture.width(); col++) {

            if (min > distTo[picture.height() - 1][col]) {
                low_col = col;
                min = distTo[picture.height() - 1][col];
            }
        }
        for (int row = picture.height() - 1; row >= 0; row--) {
            seam[row] = low_col;
            low_col = edgeTo[row][low_col];
        }
        return seam;

    }


    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null) {
            throw new IllegalArgumentException();
        }

        transposePicture();
        removeVerticalSeam(seam);
        transposePicture();

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null) {
            throw new IllegalArgumentException();
        }

        if (picture.width() <= 1) {
            throw new IllegalArgumentException("insufficient picture width");
        }
        validateSeam(seam);
        int n = 0;

        Picture newpic = new Picture(picture.width() - 1, picture.height());
        for (int row = 0; row < picture.height(); row++) {
            int newcol = 0;
            for (int col = 0; col < picture.width(); col++) {
                if (col == seam[n]) {
                    ;
                }
                else {
                    newpic.set(newcol, row, picture.get(col, row));
                    newcol++;
                }
            }
            n++;
        }
        picture = newpic;

    }

    private void transposePicture() {
        Picture newpicture = new Picture(picture.height(), picture.width());
        for (int row = 0; row < picture.height(); row++) {
            for (int col = 0; col < picture.width(); col++) {
                newpicture.set(row, col, picture.get(col, row));
            }
        }
        picture = newpicture;
    }

    private void validateSeam(int[] seam) {
        if (seam == null) {
            throw new IllegalArgumentException("seam is null");
        }

        if (seam.length != picture.height()) {
            throw new IllegalArgumentException(
                    "seam length does not match range of " + picture.height());
        }

        int prev = seam[0];
        if (prev < 0 || prev >= picture.width()) {
            throw new IllegalArgumentException(
                    "seam entry " + prev + " is outside prescribed range of " + picture.width());
        }
        for (int i = 1; i < seam.length; ++i) {
            int curr = seam[i];
            if (Math.abs(curr - prev) > 1) {
                throw new IllegalArgumentException("adjacent seam entries are too var apart");
            }
            if (curr < 0 || curr >= picture.width()) {
                throw new IllegalArgumentException(
                        "seam entry " + i + " is outside prescribed range of " + picture.width());
            }
            prev = curr;
        }
    }

    //  unit testing (optional)
    public static void main(String[] args) {
        Picture picture = new Picture("3x7.png");
        SeamCarver test = new SeamCarver(picture);
        int[] test2 = new int[picture.height()];
        test2 = test.findVerticalSeam();
        test.removeVerticalSeam(test2);

    }

}
