public class Body {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		double xDistance = this.xxPos - b.xxPos;
		double yDistance = this.yyPos - b.yyPos;
		double distance = Math.pow((Math.pow(xDistance,2) + Math.pow(yDistance,2)), 0.5);
		return distance;
	}

	public double calcForceExertedBy(Body b){
		if (this.equals(b)){
			return 0;
		}
		double gravConstant = 6.67 * Math.pow(10, -11);
		double exerctedForce = gravConstant * (this.mass * b.mass) / Math.pow(this.calcDistance(b),2);
		return exerctedForce;
	}


	public double calcForceExertedByX(Body b){
		double totalForce = this.calcForceExertedBy(b);
		double distance = this.calcDistance(b);
		double xDistance = b.xxPos - this.xxPos;
		double xForce = totalForce * xDistance / distance;
		return xForce;
	}

	public double calcForceExertedByY(Body b){
		double totalForce = this.calcForceExertedBy(b);
		double distance = this.calcDistance(b);
		double yDistance = b.yyPos - this.yyPos;
		double yForce = totalForce * yDistance / distance;
		return yForce;
	}

	public double calcNetForceExertedByX(Body[] b){
		double netForceX = 0;
		for (Body body: b){
			if (!this.equals(body)){
				netForceX += this.calcForceExertedByX(body);
			}
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Body[] b){
		double netForceY = 0;
		for (Body body: b){
			if (!this.equals(body)){
				netForceY += this.calcForceExertedByY(body);
			}
		}
		return netForceY;
	}

	public void update(double dt, double xForce, double yForce){
		double acelrtX = xForce / this.mass;
		double acelrtY = yForce / this.mass;
		this.xxVel = this.xxVel + dt * acelrtX;
		this.yyVel = this.yyVel + dt * acelrtY;
		this.xxPos = this.xxPos + dt * xxVel;
		this.yyPos = this.yyPos + dt * yyVel;
	}

	public void draw(){
		String filepath = "./images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, filepath);
	}
	public static void main(String[] args){

	}
}