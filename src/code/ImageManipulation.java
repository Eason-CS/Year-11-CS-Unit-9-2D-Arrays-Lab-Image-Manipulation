package code;

import image.Pixel;

public class ImageManipulation {

    /** CHALLENGE 0: Display Image
     *  Write a statement that will display the image in a window
     */
    public static void main(String[] args) {
    }

    /** CHALLENGE ONE: Grayscale
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a grayscale copy of the image
     *
     * To convert a colour image to grayscale, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * Set the red, green, and blue components to this average value. */
    public static void grayScale(String pathOfFile) {
        Pixel[][] image = loadImage(pathOfFile);
        for (int y = 0; y < image.length; y++) {
            for (int x = 0; x < image[y].length; x++) {
                int avg = getAverageColour(image[y][x]);
                image[y][x].setRed(avg);
                image[y][x].setGreen(avg);
                image[y][x].setBlue(avg);
            }
        }
        saveImage(image, "grayscale_image.jpg");
    }

    /** A helper method that can be used to assist you in each challenge.
     * This method simply calculates the average of the RGB values of a single pixel.
     * @param pixel
     * @return the average RGB value
     */
    private static int getAverageColour(Pixel pixel) {
        return (pixel.getRed() + pixel.getGreen() + pixel.getBlue());
    }

    /** CHALLENGE TWO: Black and White
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: a black and white copy of the image
     *
     * To convert a colour image to black and white, we need to visit every pixel in the image ...
     * Calculate the average of the red, green, and blue components of the pixel.
     * If the average is less than 128, set the pixel to black
     * If the average is equal to or greater than 128, set the pixel to white */
    public static void blackAndWhite(String pathOfFile) {
        Pixel[][] image = loadImage(pathOfFile);
        for (int y = 0; y < image.length; y++) {
            for (int x = 0; x < image[y].length; x++) {
                int avg = getAverageColour(image[y][x]);
                if (avg < 128) {
                    image[y][x].setRed(0);
                    image[y][x].setGreen(0);
                    image[y][x].setBlue(0);
                } else {
                    image[y][x].setRed(255);
                    image[y][x].setGreen(255);
                    image[y][x].setBlue(255);
                }
            }
        }
        saveImage(image, "bw_image.jpg");
    }


    /** CHALLENGE Three: Edge Detection
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: an outline of the image. The amount of information will correspond to the threshold.
     *
     * Edge detection is an image processing technique for finding the boundaries of objects within images.
     * It works by detecting discontinuities in brightness. Edge detection is used for image segmentation
     * and data extraction in areas such as image processing, computer vision, and machine vision.
     *
     * There are many different edge detection algorithms. We will use a basic edge detection technique
     * For each pixel, we will calculate ...
     * 1. The average colour value of the current pixel
     * 2. The average colour value of the pixel to the left of the current pixel
     * 3. The average colour value of the pixel below the current pixel
     * If the difference between 1. and 2. OR if the difference between 1. and 3. is greater than some threshold value,
     * we will set the current pixel to black. This is because an absolute difference that is greater than our threshold
     * value should indicate an edge and thus, we colour the pixel black.
     * Otherwise, we will set the current pixel to white
     * NOTE: We want to be able to apply edge detection using various thresholds
     * For example, we could apply edge detection to an image using a threshold of 20 OR we could apply
     * edge detection to an image using a threshold of 35
     *  */
    public static void edgeDetection(String pathToFile, int threshold) {
        Pixel[][] image = loadImage(pathToFile);
        Pixel[][] edgeImage = new Pixel[image.length][image[0].length];
        for (int y = 0; y < image.length; y++) {
            for (int x = 0; x < image[y].length; x++) {
                int currentAvg = getAverageColour(image[y][x]);
                int leftAvg = (x > 0) ? getAverageColour(image[y][x - 1]) : currentAvg;
                int bottomAvg = (y < image.length - 1) ? getAverageColour(image[y + 1][x]) : currentAvg;
                if (Math.abs(currentAvg - leftAvg) > threshold || Math.abs(currentAvg - bottomAvg) > threshold) {
                    edgeImage[y][x] = new Pixel(0, 0, 0);
                } else {
                    edgeImage[y][x] = new Pixel(255, 255, 255);
                }
            }
        }
        saveImage(edgeImage, "edge_image.jpg");
    }

    /** CHALLENGE Four: Reflect Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image reflected about the y-axis
     *
     */
    public static void reflectImage(String pathToFile) {
        Pixel[][] image = loadImage(pathToFile);
        Pixel[][] reflectedImage = new Pixel[image.length][image[0].length];
        for (int y = 0; y < image.length; y++) {
            for (int x = 0; x < image[y].length; x++) {
                reflectedImage[y][x] = image[y][image[y].length - 1 - x];
            }
        }
        saveImage(reflectedImage, "reflected_image.jpg");
    }

    /** CHALLENGE Five: Rotate Image
     *
     * INPUT: the complete path file name of the image
     * OUTPUT: the image rotated 90 degrees CLOCKWISE
     *
     *  */
    public static void rotateImage(String pathToFile) {
        Pixel[][] image = loadImage(pathToFile);
        Pixel[][] rotatedImage = new Pixel[image[0].length][image.length];
        for (int y = 0; y < image.length; y++) {
            for (int x = 0; x < image[y].length; x++) {
                rotatedImage[x][image.length - 1 - y] = image[y][x];
            }
        }
        saveImage(rotatedImage, "rotated_image.jpg");
    }

    private static Pixel[][] loadImage(String pathOfFile) {

        return new Pixel[100][100];
    }

    private static void saveImage(Pixel[][] image, String outputPath) {

    }
}
