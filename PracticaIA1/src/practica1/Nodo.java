package practica1;

public class Nodo
{
	private Pos posicion;

	private Nodo padre;
	private Nodo[] hijo;
	private int distancia;
	private int tipo;

	public Nodo(final Pos pos)
	{
		padre = null;
		// sensor = new Pos[3];
		hijo = new Nodo[3];
		distancia = 1000;
		tipo = -1;

		for (int i = 0; i < 3; i++)
		{
			hijo[i] = null;
			// sensor[i] = new Pos(-1, -1);
		}

		posicion = pos;
	}

	public void pushHijo(final Nodo n)
	{
		n.setPadre(this);
		for (int i = 0; i < 3; i++)
			if (hijo[i] == null)
			{
				hijo[i] = n;
				return;
			}
		// assert (padre == null);
		// this.setPadre(n);
	}

	public Nodo getHijo(final int n)
	{
		assert (n < 3);
		return hijo[n];
	}

	public Nodo getPadre()
	{
		return padre;
	}

	public void setPadre(final Nodo n)
	{
		this.padre = n;
	}

	public void setDist(final int d)
	{
		distancia = d;
	}

	public void setTipo(final int t)
	{
		tipo = t;
	}

	public int getDist()
	{
		return distancia;
	}

	public int getTipo()
	{
		return tipo;
	}

	public Pos calcularPos(final int d)
	{
		assert (d < 4 && d >= 0);

		Pos paux = new Pos(posicion);

		switch (d)
		{
		case 0:
			paux.y = paux.y - 1;
			break;
		case 1:
			paux.x = paux.x + 1;
			break;
		case 2:
			paux.y = paux.y + 1;
			break;
		case 3:
			paux.x = paux.x - 1;
			break;
		}

		return paux;
	}

	public Nodo getNextNodo(final int dir)
	{
		for (int i = 0; i < 3; i++)
		{
			if (hijo[i].getPosicion().comparar(calcularPos(dir)))
			{
				return hijo[i];
			}
		}
		return padre;
	}

	public Pos getPosicion()
	{
		return posicion;
	}

	public int calcularDir(final int h)
	{
		if (h < 3)
			for (int j = 0; j < 4; j++)
			{
				Pos aux = new Pos(getNextNodo(j).getPosicion());
				// System.out.println("El pos aux es: (" + aux.x + ", " + aux.y + ")");
				// System.out.println(
				// "Y el pos del hijo: (" + hijo[h].getPosicion().x + ", " + hijo[h].getPosicion().y + ")");
				if (hijo[h].getPosicion().comparar(aux))
				{
					return j;
				}
			}
		else if (h == 4)
		{
			for (int j = 0; j < 4; j++)
			{
				Pos aux = new Pos(getNextNodo(j).getPosicion());
				if (padre.getPosicion().comparar(aux))
				{
					return j;
				}
			}
		}

		return 4;
	}

	public int[] getMejorHijo()
	{
		int[] ordenados = new int[3];
		for (int i = 0; i < 3; i++)
		{
			ordenados[i] = getHijo(i).getDist();
		}
		quicksort3(ordenados);

		return ordenados;
	}

	private void quicksort3(int[] vector)
	{
		int[] array = vector.clone();
		int min = 1000, minp = 3;
		int max = 0, maxp = 3;

		for (int i = 0; i < array.length; i++)
		{
			if (array[i] < min)
			{
				min = array[i];
				minp = i;
			}
		}
		for (int i = 0; i < array.length; i++)
		{
			if (array[i] > max && i != minp)
			{
				max = array[i];
				maxp = i;
			}
		}
		for (int i = 0; i < array.length; i++)
		{
			if (i != minp && i != maxp)
			{
				vector[1] = i;
			}
		}
		vector[0] = minp;
		vector[2] = maxp;
	}

	// for (int i = 0; i < 3; i++)
	// {
	// if (n.getPosicion() == hijo[i].getPosicion(
	// {
	// for (int j = 0; j < 4; j++)
	// {
	// if (hijo[i].getPosicion() == getNextNodo(j).getPosicion())
	// {
	// System.out.println("aqui no se entra.");
	// return j;
	// }
	// }
	// }
	// }
	// if (n.getPosicion() == padre.getPosicion())
	// {
	// for (int j = 0; j < 4; j++)
	// {
	// if (padre.getPosicion() == this.getNextNodo(j).getPosicion())
	// return j;
	// }
	// }
	// return 4;
}
