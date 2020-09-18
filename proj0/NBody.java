public class NBody{
	public static double readRadius(String filename){
		In in = new In(filename);
		int numPlanets = in.readInt();
		double radiusOfUniverse = in.readDouble();
		return radiusOfUniverse;
	}

	public static Body[] readBodies(String filename){
		In in = new In(filename);
		int numPlanets = in.readInt();
		double radius = in.readDouble();
		Body[] b = new Body[numPlanets];
		int count = 0;
		while (count < numPlanets){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			b[count] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			count++;
		}
		return b;
	}

	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		
		Body[] universe = readBodies(filename);
		String filepath = "./images/starfield.jpg";
		
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius, radius);
		
		
		int time = 0;

		while (time < T){
			double[] xForces = new double[universe.length];
			double[] yForces = new double[universe.length];

			int idx = 0;
			for(Body b: universe){
				double netForceX = b.calcNetForceExertedByX(universe);
				double netForceY = b.calcNetForceExertedByY(universe);
				xForces[idx] = netForceX;
				yForces[idx] = netForceY;
				idx++;
			}
			idx = 0;
			for(Body b: universe){
				b.update(dt, xForces[idx], yForces[idx]);
				idx++;
			}
			StdDraw.picture(0, 0, filepath);
			for(Body b: universe){
				b.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time += dt;
		}

		StdOut.printf("%d\n", universe.length);
		StdOut.printf("%.2e\n", radius);
		for(int i = 0; i < universe.length; i++){
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
						   universe[i].xxPos, universe[i].yyPos, universe[i].xxVel,
						   universe[i].yyVel, universe[i].mass, universe[i].imgFileName);
		}		
	}
}