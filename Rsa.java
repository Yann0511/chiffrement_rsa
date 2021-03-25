import java.math.*;

public class Rsa
{
    private long p, q, d;
    private long phi_de_n;
    long n, e;

    public Rsa(long p, long q)
    {
	 n = p * q;
	 phi_de_n= (p-1) * (q-1);

	 do
	 {
	      d = (long)(Math.random() * phi_de_n - 3) + 2;
	      e = invmod(d, phi_de_n);
	 }while (e==0);

	 if (e<0)
	      e = e + phi_de_n;
    }

    public static long invmod(long x, long n)
    {
	 long u=1, v=0 ,y, q;
	 
	 if (x == 0)
	      return(0);

	 while (n != 0)
	 {
	      y = n;
	      q = x / n;
	      n = x % n;
	      x = y;
	      y = v;
	      v = u - q*v;
	      u = y;
	 }

	 if (x == 1)
	      return(u);

	 else
	     return(0);
    }

    public static void main(String[] args)
    {
	 long p, q;

	 if(args.length != 2)
	 {
	      System.out.println("Utilisation :");
	      System.out.println(" p(nombre premier) q(nombre premier)");
	      System.exit(-1);
	 }

	 p = Long.parseLong(args[0]);
	 q = Long.parseLong(args[1]);

        Rsa rsa = new Rsa(p, q);

        System.out.println("Module publique:            N = " + rsa.n);
        System.out.println("Clef publique:              e = " + rsa.e);
        System.out.println("Indicatrice d'Euler:   phi(N) = " + rsa.phi_de_n);
        System.out.println("Clef privée:                d = " + rsa.d);
        System.out.println("             e * d mod phi(N) = " + (rsa.e * rsa.d % rsa.phi_de_n));
        System.out.println();

    }
}
