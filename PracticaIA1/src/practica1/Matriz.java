package practica1;

import java.util.Random;

public class Matriz
{
	private static final int ladobloque = Bloque.lado;
	public static int N = 12;
	public static int M = 12;
	Bloque[] matrizdata = new Bloque[N * M];
	public int[] pixeles;
	Coche cochito;

	public static Random numrandom = new Random();

	public Matriz()
	{
		pixeles = new int[(N * ladobloque) * (M * ladobloque)];
		// System.out.println("La matriz tiene " + N + " columnas y " + M + " filas");
		llenarfondo();
		cochito = new Coche(1, M);
		insertarCoche(cochito);
		insertarMeta(N, 1);
		// mostrardatosmatriz();
	}

	public Matriz(final int m, final int n)
	{
		N = n;
		M = m;
		matrizdata = new Bloque[N * M];
		pixeles = new int[(M * ladobloque) * (N * ladobloque)];
		// System.out.println("La matriz tiene " + N + " columnas y " + M + " filas");
		llenarfondo();
		cochito = new Coche(1, M);
		insertarCoche(cochito);
		insertarMeta(N, 1);
		// mostrardatosmatriz();

		// cochito.smart.actSensores(cochito.getX(), cochito.getY(), this);
	}

	// public void cambiarMatriz(int[] datos)
	// {
	// if (datos.length == matrizdata.length)
	// {
	// for (int i = 0; i < matrizdata.length; i++)
	// {
	// if (datos[i] != matrizdata[i].getTipo())
	// {
	// insertar((i % N) + 1, (i / N) + 1, datos[i]);
	// }
	// }
	// }
	// }

	void insertar(final int x, final int y, final int tipo)
	{
		assert ((x <= N) && (y <= M) && (x > 0) && (y > 0));

		Bloque bloque = new Bloque(tipo);
		matrizdata[(x - 1) + (y - 1) * N] = bloque;
		final int ncol = (x - 1) * ladobloque;
		final int nfil = (y - 1) * ladobloque;

		for (int j = 0; j < ladobloque; j++)
		{
			for (int i = 0; i < ladobloque; i++)
			{
				pixeles[(i + ncol) + (j + nfil) * (N * ladobloque)] = bloque.pixeles[i + j * ladobloque];
			}
		}
	}

	private final void llenarfondo()
	{
		for (int j = 1; j <= M; j++)
		{
			for (int i = 1; i <= N; i++)
			{
				insertar(i, j, numrandom.nextInt(5));
			}
		}
	}

	final void insertarCoche(Coche c)
	{
		if ((c.getX() > N) || (c.getY() > M) || (c.getX() <= 0) || (c.getY() <= 0))
		{
			c.setPos(1, 1);
		}
		insertar(c.getX(), c.getY(), -1);
	}

	public final void insertarMeta(int x, int y)
	{
		borrarMeta();
		if ((x > N) || (y > M) || (x <= 0) || (y <= 0))
		{
			x = N;
			y = 1;
		}
		insertar(x, y, -2);
		// System.out.println("Se calcula la nueva meta");
		if (cochito.smart != null)
			cochito.smart.calcularMeta(this);
	}

	private final void borrarMeta()
	{
		for (int i = 0; i < matrizdata.length; i++)
		{
			if (matrizdata[i].getTipo() == -2)
			{
				insertar((i % N) + 1, (i / N) + 1, 0);
			}
		}
	}

	public final void mostrardatosmatriz()
	{
		for (int j = 0; j < M; j++)
		{
			for (int i = 0; i < N; i++)
			{
				System.out.print(matrizdata[i + j * N].getTipo() + " ");
			}
			System.out.print('\n');
		}
	}

	public int getTipo(final Pos pos)
	{
		if (pos.x > 0 && pos.y > 0 && pos.x <= N && pos.y <= M)
			return (matrizdata[(pos.x - 1) + (pos.y - 1) * N].getTipo());
		else
			return -5;
	}
}