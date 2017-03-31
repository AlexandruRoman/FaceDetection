import org.opencv.core.*;
import org.opencv.objdetect.CascadeClassifier;

class FaceDetector {

    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
    public static void detect(Mat image) {

        System.out.println("\nRunning FaceDetector");

        CascadeClassifier faceDetector = new CascadeClassifier(FaceDetector.class.getResource("cascades/haarcascade_frontalface_alt.xml").getPath().substring(1));

        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections, 1.05, 6, 0, new Size(30, 30), image.size());

        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));
        for (Rect rect : faceDetections.toArray()) {
            Detect.chipuri.add(new Chip(rect));
        }
    }
}