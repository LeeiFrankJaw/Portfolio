class Complex {
    double a, b;
    
    Complex(double a, double b) {
        this.a = a;
        this.b = b;
    }
    
    double getRe() {
        return a;
    }
    
    double getIm() {
        return b;
    }
    
    void display() {
        System.out.println(a + " + " + b + " i");
    }
    
    Complex add(Complex c) {
        return new Complex(a + c.getRe(), b + c.getIm());
    }
    
    Complex add(double r) {
        return add(new Complex(r, 0));
    }
    
    Complex subtract(Complex c) {
        return new Complex(a - c.getRe(), b - c.getIm());
    }
    
    Complex subtract(double r) {
        return subtract(new Complex(r, 0));
    }
    
    Complex multiply(Complex c) {
        return new Complex(a*c.getRe() - b*c.getIm(),
                           b*c.getRe() + a*c.getIm());
    }
    
    Complex multiply(double r) {
        return multiply(new Complex(r, 0));
    }
    
    Complex divide(Complex c) {
        double d = Math.pow(c.getRe(), 2) + Math.pow(c.getIm(), 2);
        return new Complex((a*c.getRe() + b*c.getIm()) / d,
                           (b*c.getRe() - a*c.getIm()) / d);
    }
    
    Complex divide(double r) {
        return divide(new Complex(r, 0));
    }
}

public class P13 {
    public static void main(String[] args) {
        Complex a = new Complex(0, 1), b;
        System.out.println(a.getRe());
        System.out.println(a.getIm());
        a.display();
        a.add(7).display();
        a.add(10).display();
        a.add(-5).display();
        a.add(-11).display();
        a.subtract(4).display();
        a.subtract(9).display();
        a.subtract(-4).display();
        a.subtract(-9).display();
        a.multiply(a).display();
        a = new Complex(1, 1);
        a.multiply(4).display();
        a.multiply(-4).display();
        a.multiply(0.5).display();
        a.multiply(-0.5).display();
        a.divide(4).display();
        a.divide(-4).display();
        a.divide(0.5).display();
        a.divide(-0.5).display();        
        a = new Complex(5, 2);
        b = new Complex(7, 11);
        a.add(b).display();
        a.subtract(b).display();
        a.multiply(b).display();
        a.divide(b).display();
    }
}
