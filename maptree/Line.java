/** a class for simple geometric calculation 
 * Yuzhen Ye@School of Informatics and Computing, IUB
 */
public class Line {
	private double x1, y1, x2, y2; //defined by start and end points
	private double m, b; //slope and intercept
	Line(double x1t, double y1t, double x2t, double y2t) {
		if(x1t < x2t) {
			x1 = x1t; y1 = y1t; x2 = x2t; y2 = y2t;
		}
		else {
			x1 = x2t; y1 = y2t; x2 = x1t; y2 = y2t;
		}
		if(x1 == x2) { m = 10000000; b = 0; } //vertical line
		else {
			m = (y1 - y2) / (x1 - x2);
			b = y1 - m * x1;
		}
	}
	public double slope() { return m; }
	public double intercept() { return b; }
	public double getX1() { return x1; }
	public double getY1() { return y1; }
	public double getX2() { return x2; }
	public double getY2() { return y2; }
	public boolean parallel(Line l2) { 
		if(Math.abs(m - l2.slope()) < 0.00001) return true;
		else return false;
	}
	public int compareTo(double x0, double y0) {
		if((x1 - x2) == 0) {
			if(x0 == x1) return 0;
			else if(x0 < x1) return -1; //the given point is at the "left" of the line
			else return 1; //"right" side of the line
		}
		else {
			double m = (y2 - y1) / (x2 - x1);
			//foot of the  perpendicular (x, y)
			double x = (1/m * x0 + m * x1 - y1 + y0) / (1/m + m);
			double y = m * (x - x1) + y1;
			if(x0 == x) return 0;
			else if(x0 < x) return -1;
			else return 1;
		}
	}
	public int cross(Line l2) {
		int dir1 = compareTo(l2.getX1(), l2.getY1());
		int dir2 = compareTo(l2.getX2(), l2.getY2());
		if(dir1 * dir2 < 0) return 0; //cross
		else {
			if(dir1 == 0) return dir2;
			else return dir1;
		}
	}
}
