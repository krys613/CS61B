public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    /**
     * Constructor
     */
    public Body(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }
    public Body(Body b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    /**
     * Calculate the distance between two Bodys
     */
    public double calcDistance(Body b) {
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double r = Math.hypot(dx, dy);
        return r;
    }

    /**
     * Calculate the force exerted on this body by the given body
     */
    public double calcForceExertedBy(Body b) {
        double G = 6.67e-11;
        double F = (G * this.mass * b.mass) / Math.pow(this.calcDistance(b), 2);
        return F;
    }

    /**
     * Calculate the force in x direction and y direction
     */
    public double calcForceExertedByX(Body b) {
        double Fx = this.calcForceExertedBy(b) * (b.xxPos - this.xxPos) / this.calcDistance(b);
        return Fx;
    }

    public double calcForceExertedByY(Body b) {
        double Fy = this.calcForceExertedBy(b) * (b.yyPos - this.yyPos) / this.calcDistance(b);
        return Fy;
    }

    /**
     * Calcualte the net force in x direction and y direction
     */
    public double calcNetForceExertedByX(Body[] bs) {
        double FxNet = 0;
        for (Body b : bs) {
            if (!this.equals(b)) {
                FxNet += this.calcForceExertedByX(b);
            }
        }
        return FxNet;
    }

    public double calcNetForceExertedByY(Body[] bs) {
        double FyNet = 0;
        for (Body b : bs) {
            if (!this.equals(b)) {
                FyNet += this.calcForceExertedByY(b);
            }
        }
        return FyNet;
    }

    /**
     * Update the velocity and position of the Body under the effect of force
     */
    public void update(double dt, double fX, double fY) {
        double ax = fX / this.mass;
        double ay = fY / this.mass;
        this.xxVel += ax * dt;
        this.yyVel += ay * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    /**
     * Draw the picture of the Body according to its position
     */
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
