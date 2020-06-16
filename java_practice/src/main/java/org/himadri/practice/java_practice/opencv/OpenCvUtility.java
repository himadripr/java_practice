package org.himadri.practice.java_practice.opencv;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class OpenCvUtility {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("started..");
		detectPassportMRZLine();
		System.out.println("terminated..");
	}
	
	public static void detectPassportMRZLine() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		String inputDirPath = "//Users//abhishek//Documents//transbit//passport_mrz_detection//input";
		String outputDirPath = "//Users//abhishek//Documents//transbit//passport_mrz_detection//output";
		File dir = new File(inputDirPath);
		for (File file: dir.listFiles()) {
			if (file.isFile()) {
				try {
					String fileName = file.getName();
					String outputFilePath = outputDirPath+"/"+fileName;
					doContourDetectionAndDrawSave(file.getAbsolutePath(), true, 600, outputFilePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}
	
	public static List<MatOfPoint> doContourDetectionAndDrawSave(String inputPath, boolean isCompressionRequired,
			double compressionDimension, String outputFilePath) {
		Mat originalMat = Imgcodecs.imread(inputPath);
		Mat image = originalMat.clone();
		double f = getSizeFactorAfterCompression(image, compressionDimension);
		Mat resizedImage = new Mat();
		double w = (double) image.width();
		double h = (double) image.height();
		Imgproc.resize(image, resizedImage, new Size((int) (w * f), (int) (h * f)));
		if (!isCompressionRequired) {
			resizedImage = image;
			f = 1;
		}
		List<MatOfPoint> contours = doContourDetectionUtil(resizedImage, isCompressionRequired, compressionDimension, 4);
		Imgproc.drawContours(resizedImage, contours, -1, new Scalar(0, 0, 255), 2);
		//looping through contours to get the bounding rectangle of each contours
		for (MatOfPoint contour: contours) {
			Rect boundingRect = getBoundingRectOfContour(contour, f);
			drawRect(image, new Point(boundingRect.x, boundingRect.y), new Point(boundingRect.x+boundingRect.width, boundingRect.y+boundingRect.height), new Scalar(0,0,255), 2);
		}
		saveImageToDirectory(outputFilePath, image);
		return contours;

	}
	
	private static void drawRect(Mat matrix, Point p1, Point p2, Scalar scalar, int thickness) {
		// Drawing a rect
		Imgproc.rectangle(matrix, // Matrix obj of the image
				p1, // p1
				p2, // p2
				scalar, // Scalar object for color
				thickness // Thickness of the line
		);
	}

	private static void drawLine(Mat matrix, Point p1, Point p2, Scalar scalar, int thickness) {
		// Drawing a rect
		Imgproc.line(matrix, // Matrix obj of the image
				p1, // p1
				p2, // p2
				scalar, // Scalar object for color
				thickness // Thickness of the line
		);
	}
	
	public static Rect getBoundingRectOfContour(MatOfPoint contour, double divFactor) {
		Rect rect = null;
		Point[] points = contour.toArray();
		List<Point> listOfPoints = new ArrayList<>();
		for (Point point: points){
    		listOfPoints.add(point);
    	}
		double leftMostCoordinates = Double.MAX_VALUE;
		double RightMostCoordinates = Double.MIN_VALUE;
		double topMostCoordinates = Double.MAX_VALUE;
		double BottomMostCoordinates = Double.MIN_VALUE;
		for (Point point: listOfPoints) {
        	if (leftMostCoordinates>point.x) {
        		leftMostCoordinates = point.x;
			}
        	if (topMostCoordinates>point.y) {
        		topMostCoordinates = point.y;
			}
        	if (RightMostCoordinates<point.x) {
        		RightMostCoordinates = point.x;
			}
        	if (BottomMostCoordinates<point.y) {
        		BottomMostCoordinates = point.y;
			}
        	
        }
        
        Point topLeftPoint = new Point(leftMostCoordinates/divFactor, topMostCoordinates/divFactor);
        Point bottomLeftPoint = new Point(leftMostCoordinates/divFactor, BottomMostCoordinates/divFactor);
        Point bottomRightPoint = new Point(RightMostCoordinates/divFactor, BottomMostCoordinates/divFactor);
        Point topRightPoint = new Point(RightMostCoordinates/divFactor, topMostCoordinates/divFactor);
        rect = new Rect((int)topLeftPoint.x, (int)topLeftPoint.y, (int)topRightPoint.x-(int)topLeftPoint.x, (int)bottomRightPoint.y-(int)topRightPoint.y);
		return rect;
	}
	
	public static List<MatOfPoint> doContourDetectionUtil(Mat originalMat, boolean isCompressionRequired,
			double compressionDimension, int numberOfDilationsRequired) {
		

		Mat gray = new Mat();

		// converting to gray image

		Imgproc.cvtColor(originalMat, gray, Imgproc.COLOR_BGR2GRAY);
		// blurring image

		Imgproc.GaussianBlur(gray, gray, new Size(3, 3), 0);

		// getting canny edges
		Mat edges = new Mat();
		Imgproc.Canny(gray, edges, 20, 120, 3, true);
		// dialating the edges for better contour detection
		Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(2, 2));
		kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3), new Point(2, 2));
		while (numberOfDilationsRequired>0) {
			Imgproc.dilate(edges, edges, kernel);
			numberOfDilationsRequired--;
		}
		
//		Imgproc.dilate(edges, edges, kernel);
//		Imgproc.dilate(edges, edges, kernel);

		// getting hough lines
		Mat edgesClone = edges.clone();

		Mat lines = new Mat();
		Imgproc.HoughLinesP(edges, lines, 1, Math.PI / 180, 200);
//		for (int i = 0; i < lines.cols(); i++) {
//			double[] val = lines.get(0, i);
//			Imgproc.line(edgesClone, new Point(val[0], val[1]), new Point(val[2], val[3]), new Scalar(255, 0, 0), 2, 8,
//					0);
//			// Imgproc.line(edgesClone, new Point(val[0], val[1]), new
//			// Point(val[2], val[3]), new Scalar(255,0, 0), 1);
//
//		}

		Mat hierarcy = new Mat();
		List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
		Imgproc.findContours(edgesClone, contours, hierarcy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_TC89_KCOS);// chain_approx_simple
		return contours;
	}
	
	private static double getSizeFactorAfterCompression(Mat mat, double compressionDimension) {
		Mat image = mat;
		double w = (double) image.width();
		double h = (double) image.height();
		double f;
		f= compressionDimension / h; 
		if (w>h) {
			f= compressionDimension / h; 
		} else {
			f = compressionDimension / w; 
		}
		return f;
	}
	
	public static boolean saveImageToDirectory(String outputFilePath, Mat mat) {
		MatOfByte matOfByte = new MatOfByte();
		Imgcodecs.imencode(".jpg", mat, matOfByte);

		// Storing the encoded Mat in a byte array
		byte[] byteArray = matOfByte.toArray();

		// Displaying the image
		InputStream in = new ByteArrayInputStream(byteArray);
		BufferedImage bufImage = null;
		try {
			bufImage = ImageIO.read(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return false;
		}

		File outputfile = new File(outputFilePath);
		try {
			ImageIO.write(bufImage, "jpg", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
