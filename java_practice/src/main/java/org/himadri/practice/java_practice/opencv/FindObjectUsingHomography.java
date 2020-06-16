package org.himadri.practice.java_practice.opencv;

import java.util.LinkedList;
import java.util.List;

import org.opencv.calib3d.Calib3d;
import org.opencv.core.Core;
import org.opencv.core.DMatch;
import org.opencv.core.KeyPoint;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfDMatch;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

class FindObjectUsingHomography {
	public static void main(String[] args) {
		System.out.println("started..");
		findObjectFromHomography();
		System.out.println("terminated..");
		
	}
	
	public static void findObjectFromHomography() {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println("\nRunning FindObject");
		String inputPathObject = "//Users//abhishek//Documents//transbit//passport_mrz_detection//input"+"//object1.png";
		String inputPathScene = "//Users//abhishek//Documents//transbit//passport_mrz_detection//input"+"//scene1.png";
		String outputPathObject = "//Users//abhishek//Documents//transbit//passport_mrz_detection//output"+"//output.jpg";

		Mat img_object = Imgcodecs.imread(inputPathObject); // 0 = CV_LOAD_IMAGE_GRAYSCALE
		Mat img_scene = Imgcodecs.imread(inputPathScene);

		Imgproc.cvtColor(img_object, img_object, Imgproc.COLOR_BGR2GRAY);
		Imgproc.cvtColor(img_scene, img_scene, Imgproc.COLOR_BGR2GRAY);

		FeatureDetector detector = FeatureDetector.create(4); // 4 = SURF

		MatOfKeyPoint keypoints_object = new MatOfKeyPoint();
		MatOfKeyPoint keypoints_scene = new MatOfKeyPoint();

		detector.detect(img_object, keypoints_object);
		detector.detect(img_scene, keypoints_scene);

		DescriptorExtractor extractor = DescriptorExtractor.create(2); // 2 = SURF;

		Mat descriptor_object = new Mat();
		Mat descriptor_scene = new Mat();

		extractor.compute(img_object, keypoints_object, descriptor_object);
		extractor.compute(img_scene, keypoints_scene, descriptor_scene);

		DescriptorMatcher matcher = DescriptorMatcher.create(1); // 1 = FLANNBASED
		MatOfDMatch matches = new MatOfDMatch();

		matcher.match(descriptor_object, descriptor_scene, matches);
		List<DMatch> matchesList = matches.toList();

		Double max_dist = 0.0;
		Double min_dist = 100.0;

		for (int i = 0; i < descriptor_object.rows(); i++) {
			Double dist = (double) matchesList.get(i).distance;
			if (dist < min_dist)
				min_dist = dist;
			if (dist > max_dist)
				max_dist = dist;
		}

		System.out.println("-- Max dist : " + max_dist);
		System.out.println("-- Min dist : " + min_dist);

		LinkedList<DMatch> good_matches = new LinkedList<DMatch>();
		MatOfDMatch gm = new MatOfDMatch();

		for (int i = 0; i < descriptor_object.rows(); i++) {
			if (matchesList.get(i).distance < 3 * min_dist) {
				good_matches.addLast(matchesList.get(i));
			}
		}

		gm.fromList(good_matches);

		Mat img_matches = new Mat();
		Features2d.drawMatches(img_object, keypoints_object, img_scene, keypoints_scene, gm, img_matches,
				new Scalar(255, 0, 0), new Scalar(0, 0, 255), new MatOfByte(), 2);

		LinkedList<Point> objList = new LinkedList<Point>();
		LinkedList<Point> sceneList = new LinkedList<Point>();

		List<KeyPoint> keypoints_objectList = keypoints_object.toList();
		List<KeyPoint> keypoints_sceneList = keypoints_scene.toList();

		for (int i = 0; i < good_matches.size(); i++) {
			objList.addLast(keypoints_objectList.get(good_matches.get(i).queryIdx).pt);
			sceneList.addLast(keypoints_sceneList.get(good_matches.get(i).trainIdx).pt);
		}

		MatOfPoint2f obj = new MatOfPoint2f();
		obj.fromList(objList);

		MatOfPoint2f scene = new MatOfPoint2f();
		scene.fromList(sceneList);

		Mat H = Calib3d.findHomography(obj, scene);

		LinkedList<Point> cornerList = new LinkedList<Point>();
		cornerList.add(new Point(0, 0));
		cornerList.add(new Point(img_object.cols(), 0));
		cornerList.add(new Point(img_object.cols(), img_object.rows()));
		cornerList.add(new Point(0, img_object.rows()));

		MatOfPoint obj_corners = new MatOfPoint();
		obj_corners.fromList(cornerList);

		MatOfPoint scene_corners = new MatOfPoint();

//ERROR HERE :
//OpenCV Error: Assertion failed (scn + 1 == m.cols && (depth == CV_32F || depth == CV_64F)) in unknown function, file ..\..\..\src\opencv\modules\core\src\matmul.cpp, line 1926
		Core.perspectiveTransform(obj_corners, scene_corners, H);
		OpenCvUtility.saveImageToDirectory(outputPathObject, H);

//Draw the lines... later, when the homography will work

	}
}
