package practica1;

public class NodosVisitados
{
	private Pos[] visitados;

	public NodosVisitados(final Pos padre)
	{
		visitados = new Pos[1];
		visitados[0] = padre;
	}

	public void push(final Pos posicion)
	{
		Pos[] aux = new Pos[visitados.length];
		for (int i = 0; i < aux.length; i++)
		{
			aux[i] = visitados[i];
		}
		visitados = new Pos[aux.length + 1];
		for (int i = 0; i < aux.length; i++)
		{
			visitados[i] = aux[i];
		}
		visitados[aux.length] = posicion;

		// System.out.print("Visitados: {");
		// for (int i = 0; i < visitados.length; i++)
		// {
		// System.out.print(" (" + visitados[i].x + ", " + visitados[i].y + "),");
		// }
		// System.out.println("};");
	}

	public boolean visitado(final Pos posicion)
	{
		for (int i = 0; i < visitados.length; i++)
		{
			if (visitados[i].x == posicion.x && visitados[i].y == posicion.y)
			{
				return true;
			}
		}
		return false;
	}

	public void clear()
	{
		visitados = new Pos[0];
	}

}
