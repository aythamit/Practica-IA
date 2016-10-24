package practica1;

public class Inteligencia
{
	private int[] sensor;
	private int[] dist;
	private int[] posmeta;
	private int dir;

	public Inteligencia()
	{
		sensor = new int[4];
		dist = new int[4];
		posmeta = new int[3];
		posmeta[0] = -1;
		dir = -1;

		for (int i = 0; i < 4; i++)
		{
			dist[i] = -1;
			sensor[i] = -1;
		}
	}

	public void actSensores(final int x, final int y, final Matriz mtx)
	{
		if (posmeta[0] == -1)
			calcularMeta(mtx);

		sensor[0] = mtx.getTipo(x, y - 1);
		dist[0] = calcularDist(x, y - 1);
		sensor[1] = mtx.getTipo(x + 1, y);
		dist[1] = calcularDist(x + 1, y);
		sensor[2] = mtx.getTipo(x, y + 1);
		dist[2] = calcularDist(x, y + 1);
		sensor[3] = mtx.getTipo(x - 1, y);
		dist[3] = calcularDist(x - 1, y);

	}

	public void calcularMeta(Matriz mtx)
	{
		for (int i = 0; i < mtx.matrizdata.length; i++)
		{
			if (mtx.matrizdata[i].getTipo() == -2)
			{
				posmeta[0] = i;
			}
		}
		posmeta[1] = (posmeta[0] % Matriz.N) + 1;
		posmeta[2] = (posmeta[0] / Matriz.N) + 1;
	}

	private int calcularDist(final int x, final int y)
	{
		int distancia = 1000;

		if (x > 0 && y > 0 && x <= Matriz.N && y <= Matriz.M)
		{
			// System.out.println("La posicion de la meta es: (" + posmeta[1] + ", " + posmeta[2] + ")");
			// System.out.println("Las posiciones de las celdas vecinas son: (" + x + ", " + y + ")");
			// System.out.println("se está sumando: " + Math.abs(posmeta[1] - x) + " con " + Math.abs(posmeta[2] - y));
			distancia = Math.abs(posmeta[1] - x) + Math.abs(posmeta[2] - y);
		}
		return distancia;
	}

	public int getSensor(final int n)
	{
		if (n < 4)
			return sensor[n];
		else
			return -3;
	}

	public int getDist(final int n)
	{
		if (n < 4)
			return dist[n];
		else
			return -3;
	}

	public int calcularMovimiento()
	{
		int direccion = 4;
		int min = Matriz.M * Matriz.N;
		int ban;
		switch (dir)
		{
		case 0:
			ban = 2;
			break;
		case 1:
			ban = 3;
			break;
		case 2:
			ban = 0;
			break;
		case 3:
			ban = 1;
			break;
		default:
			ban = dir;
			break;
		}
		for (int i = 0; i < 4; i++)
		{
			if (dist[i] <= min && i != ban && sensor[i] != 1)
			{
				if (dist[i] == min)
				{
					if (Matriz.numrandom.nextInt(2) == 1)
					{
						min = dist[i];
						direccion = i;
					}
				} else
				{
					min = dist[i];
					direccion = i;
				}
			}
		}

		dir = direccion;
		return direccion;
	}
}