import org.opencv.core.*;
import org.opencv.objdetect.CascadeClassifier;

class EyeDetector {

    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
    public static void detect(Mat image) {

        System.out.println("\nRunning EyeDetector");

        CascadeClassifier eyeDetector = new CascadeClassifier(EyeDetector.class.getResource("cascades/haarcascade_eye.xml").getPath().substring(1));


        MatOfRect detections = new MatOfRect();
        eyeDetector.detectMultiScale(image, detections, 1.05, 4, 0, new Size(30, 30), image.size());

        System.out.println(String.format("Detected %s eyes", detections.toArray().length));
        for (Rect rect : detections.toArray()) {
            Chip.checkOchiStanga(rect);
            Chip.checkOchiDreapta(rect);
        }
    }
}