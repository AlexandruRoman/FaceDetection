import org.opencv.core.*;
import org.opencv.highgui.Highgui;

import java.util.Random;

class Chip {
    public Rect fata;
    public Rect ochiStanga;
    public Rect ochiDreapta;
    public Rect nas;
    public Rect gura;

    public Chip(Rect fata) {this.fata = fata;}

    public static Vector2 calculDistanta(Rect elem, Rect f)
    {
        double x1, x2, y1, y2;
        x1 = elem.x + elem.width/2;
        x2 = f.x + f.width/2;
        y1 = elem.y + elem.height/2;
        y2 = f.y + f.height/2;
        Vector2 v = new Vector2((x1-x2)/f.width,(y1-y2)/f.height);
        return v;
    }

    public static boolean checkOchiStanga(Rect elem)
    {
        double x_min, x_max, y_min, y_max, dimX_min, dimX_max;
        x_min = -0.3;
        x_max = 0;
        y_min = -0.2;
        y_max = 0;
        for(Chip c : Detect.chipuri)
        {
            Rect f = c.fata;
            if (calculDistanta(elem, f).x > x_min && calculDistanta(elem, f).x < x_max && calculDistanta(elem, f).y > y_min && calculDistanta(elem, f).y < y_max)
            {
                c.ochiStanga = elem;
                return true;
            }
        }
        return false;
    }

    public static boolean checkOchiDreapta(Rect elem)
    {
        double x_min, x_max, y_min, y_max, dimX_min, dimX_max;
        x_min = 0;
        x_max = 0.3;
        y_min = -0.2;
        y_max = 0;
        for(Chip c : Detect.chipuri)
        {
            Rect f = c.fata;
            if (calculDistanta(elem, f).x > x_min && calculDistanta(elem, f).x < x_max && calculDistanta(elem, f).y > y_min && calculDistanta(elem, f).y < y_max)
            {
                c.ochiDreapta = elem;
                return true;
            }
        }
        return false;
    }

    public static void drawContur(Mat image, String imgName)
    {
        int i, j, k;
        for(Chip c : Detect.chipuri)
        {
            Random r = new Random();
            i = r.nextInt(250);
            j = r.nextInt(250);
            k = r.nextInt(250);
            Core.rectangle(image, new Point(c.fata.x, c.fata.y), new Point(c.fata.x + c.fata.width, c.fata.y + c.fata.height), new Scalar(i, j, k));
            if(c.ochiStanga != null)
                Core.rectangle(image, new Point(c.ochiStanga.x, c.ochiStanga.y), new Point(c.ochiStanga.x + c.ochiStanga.width, c.ochiStanga.y + c.ochiStanga.height), new Scalar(i, j, k));
            if(c.ochiDreapta != null)
                Core.rectangle(image, new Point(c.ochiDreapta.x, c.ochiDreapta.y), new Point(c.ochiDreapta.x + c.ochiDreapta.width, c.ochiDreapta.y + c.ochiDreapta.height), new Scalar(i, j, k));
        }

        String filename = "out_" + imgName;
        System.out.println(String.format("Writing %s", filename));
        Highgui.imwrite(filename, image);
    }
    
}
