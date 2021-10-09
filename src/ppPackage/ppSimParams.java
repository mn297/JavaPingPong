package ppPackage;

public class ppSimParams {
    public static final boolean DEBUG = false;
    public static final boolean TEST = true;

    //screen
    public static final int HEIGHT = 600; //600 default
    public static final int WIDTH = 1280;// 1280 default
    public static final int OFFSET = 200;

    //coordinates transformation

    public static final double Xmin = 0;
    public static final double Xmax = 2.74; //2.74 default
    public static final double Ymin = 0.0;
    public static final double Ymax = 1.52; //1.52 default
    public static final int xmin = 0;
    public static final int xmax = WIDTH;
    public static final int ymin = 0;
    public static final int ymax = HEIGHT;
    public static final double Xs = (xmax - xmin) / (Xmax - Xmin);
    public static final double Ys = (ymax - ymin) / (Ymax - Ymin);
    public static final double PD = 1;

    public static final double wallThickness = 3; //useless
    public static final double XwallL = 0.05;
    public static final double XwallR = 2.69;


    //simulation parameters
    public static final double g = 9.8;
    public static final double k = 0.1316;
    public static final double Pi = 3.1416;
    public static final double bSize = 0.02; //0.02 default
    public static final double bMass = 0.0027;
    public static final double Xinit = XwallL; //0 looks better than XwallL
    public static final double Yinit = Ymax / 2; // or HEIGHT/2

    //energy
    public static final double ETHR = 0.001;

    public static final double Vdef = 3.0;
    public static final double Tdef = 10.0;

    public static final double SLEEP = 10;
    public static final double TICK = SLEEP / 1000.0;


}
