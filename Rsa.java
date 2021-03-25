import java.math.*;

public class Rsa
{
     private long p, q, d, phi_de_n;
     long n, e;

    public Rsa(long p, long q)
    {
	 int premier;
	 n = p * q;
	 phi_de_n= (p-1) * (q-1);

	 do
	 {
	      e = (long)(Math.random() * (phi_de_n-3)) + 3;
	      premier = si_premier_entre_eux(phi_de_n, e);
	 }while (premier == 0);

	 d = invmod(e, phi_de_n);

	 if(d < 0)
	      d = d + phi_de_n;
    }

     public static int si_premier_entre_eux(long a, long b)
     {
	  long x = a, y = b, r ;

	  if(x >= y)
	  {
	       r = x%y ;

	       while(r!=0)
	       {
		    x=y ;
		    y=r ;
		    r=x%y ;
	       }

	       if(y == 1)
		    return(1);
	  }

	  else if(x <= y)
	  {
	       r = y%x ;

	       while(r!=0)
	       {
		    y=x ;
		    x=r ;
		    r=y%x ;
	       }

	       if(x == 1)
		    return(1);
	  }

	 else if(x == 0 && y == 1 )
	      return(1);
	  
	 else if(y == 0 && x == 1)
	      return(1);
	  
	 else
	      return(0);
	  
	 return(0);
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

        System.out.println("Module publique:            n = " + rsa.n);
        System.out.println("Clef publique:              e = " + rsa.e);
        System.out.println("Indicatrice d'Euler:   phi(n) = " + rsa.phi_de_n);
        System.out.println("Clef privée:                d = " + rsa.d);
        System.out.println("             e * d mod phi(n) = " + (rsa.e * rsa.d % rsa.phi_de_n));
        System.out.println();

    }
}
