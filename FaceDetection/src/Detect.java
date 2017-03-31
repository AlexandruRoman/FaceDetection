import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

import java.io.File;
import java.util.Vector;

public class Detect {

    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
    public static Vector<Chip> chipuri = new Vector<>();

    public static void main(String[] args) {

        File folder = new File("resources/images");
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                String imgName = file.getName();
                Mat image = Highgui.imread(Detect.class.getResource("images/" + imgName).getPath().substring(1));

                FaceDetector.detect(image);
                EyeDetector.detect(image);
                Chip.drawContur(image, imgName);
                chipuri.removeAllElements();
            }
        }


    }
}
