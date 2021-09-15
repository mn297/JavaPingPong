public class Sim1 {
    public static final double HEIGHT = 100.0;
    public static final double WIDTH = 180.0;

    public static final double g = 9.8;
    public static final double k = 0.1316;
    public static final double Pi=3.1416;
    public static final double bSize = 0.02;
    public static final double bMass=0.0027;
    public static final double Xinit=0.0;
    public static final double Yinit=HEIGHT/2;

    public static final double Vdef =  3.0;
    public static final double Tdef=30.0;

    public static final int SLEEP=100;
    public static final double TICK=SLEEP/1000.0;

    public static void main(String args[]) throws InterruptedException{
        double Vo = Vdef;
        double theta=Tdef;
        double Xo=Xinit;
        double Yo=Yinit;
        double time = 0;

        double Vt=bMass*g/(4*Pi*bSize*bSize*k);
        double Vox=Vo*Math.cos(theta*Pi/180);
        double Voy=Vo*Math.sin(theta*Pi/180);

        boolean falling = true;
        System.out.println("\t\t\t Ball Position and Velocity\n");

        while (falling) {
            double X=Vox*Vt/g*(1-Math.exp(-g*time/Vt));
            double Y=Vt/g*(Voy+Vt)*(1-Math.exp(-g*time/Vt))-Vt*time;
            double Vx=Vox*Math.exp(-g*time/Vt);
            double Vy=(Voy+Vt)*Math.exp(-g*time/Vt)-Vt;


            System.out.printf("t: %.2f\t\t X: %.2f\t Y:%.2f\t Vx: %.2f\t Vy: %.2f\n",
                    time,
                    X+Xo,
                    Y+Yo,
                    Vx,
                    Vy);

            Thread.sleep(SLEEP);
            if (Y+Yo < bSize) falling = false;
            time += TICK;


        }
    }
}
