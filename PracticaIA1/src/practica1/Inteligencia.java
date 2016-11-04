package practica1;

import javax.swing.JOptionPane;

public class Inteligencia
{

	public NodosVisitados visitados;
	public Nodo nodoactual;

	public boolean regresando = false;

	private int[] posmeta;

	public Inteligencia(final Pos pos)
	{
		visitados = new NodosVisitados(pos);
		nodoactual = new Nodo(pos);

		posmeta = new int[3];
		posmeta[0] = -1;
	}

	public void actSensores(final Matriz mtx)
	{
		if (posmeta[0] == -1)
		{
			calcularMeta(mtx);
			nodoactual.setDist(calcularDist(nodoactual.getPosicion()));
		}

		if (nodoactual.getHijo(0) == null)
		{
			for (int i = 0; i < 4; i++)
			{
				Pos pos = nodoactual.calcularPos(i);
				if (nodoactual.getPadre() == null || !nodoactual.getPadre().getPosicion().comparar(pos))
				{
					Nodo aux = new Nodo(pos);
					aux.setTipo(mtx.getTipo(pos));
					aux.setDist(calcularDist(pos));
					nodoactual.pushHijo(aux);

				}
			}
		}
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

	private int calcularDist(final Pos pos)
	{
		int distancia = 1000;

		if (pos.x > 0 && pos.y > 0 && pos.x <= Matriz.N && pos.y <= Matriz.M)
		{
			// System.out.println("La posicion de la meta es: (" + posmeta[1] + ", " + posmeta[2] + ")");
			// System.out.println("Las posiciones de las celdas vecinas son: (" + x + ", " + y + ")");
			// System.out.println("se está sumando: " + Math.abs(posmeta[1] - x) + " con " + Math.abs(posmeta[2] - y));
			distancia = Math.abs(posmeta[1] - pos.x) + Math.abs(posmeta[2] - pos.y);
		}
		return distancia;
	}

	public int calcularMovimiento()
	{
		int direccion = 4;

		// Systems OUT aclaratorios
		// System.out.println("Se esta moviendo de manera inteligente");

		System.out.println(
				"El nodo actual es: (" + nodoactual.getPosicion().x + ", " + nodoactual.getPosicion().y + ");");
		// System.out.println("Y los hijos son: (0){" + nodoactual.getHijo(0).getPosicion().x + ", "
		// + nodoactual.getHijo(0).getPosicion().y + " (d=" + nodoactual.getHijo(0).getDist() + ")}, (1){"
		// + nodoactual.getHijo(1).getPosicion().x + ", " + nodoactual.getHijo(1).getPosicion().y + " (d="
		// + nodoactual.getHijo(1).getDist() + ")}, (2){" + nodoactual.getHijo(2).getPosicion().x + ", "
		// + nodoactual.getHijo(2).getPosicion().y + " (d=" + nodoactual.getHijo(2).getDist() + ")};");
		// System.out.println("Y la del padre: (p){" + nodoactual.getPadre().getPosicion().x + ", "
		// + nodoactual.getPadre().getPosicion().y + " (d=" + nodoactual.getPadre().getDist() + ")};");
		// if (!nodoactual.getPadre().getPosicion().comparar(nodoactual.getPadre(false).getPosicion()))
		// {
		// System.out.println("El padre impostor de este nodo es: {" + nodoactual.getPadre(false).getPosicion().x
		// + ", " + nodoactual.getPadre(false).getPosicion().y + "};");
		// }
		// Fin de las aclaraciones

		// Calcular mejor nodo hijo
		// nodoactual.getHijo(0).getDist();

		int[] posib = nodoactual.getMejorHijo();
		int hijoelegido = -1, cont = 0;
		while (hijoelegido == -1 && cont < 3)
		{
			if (visitados.visitado(nodoactual.getHijo(posib[cont]).getPosicion())
					|| nodoactual.getHijo(posib[cont]).getTipo() == 1
					|| nodoactual.getHijo(posib[cont]).getDist() == 1000)
				cont++;
			else
				hijoelegido = posib[cont];
		}

		if (hijoelegido == -1)
		{
			// regresar();

			if (nodoactual.getPadre() == null)
			{
				JOptionPane.showMessageDialog(null, "¡El coche no puede llegar a la meta!");
				System.exit(0);
			}
			System.out.println("Se está regresando");
			direccion = nodoactual.calcularDir(4);
			nodoactual = nodoactual.getPadre();
		} else
		{

			direccion = nodoactual.calcularDir(hijoelegido);
			nodoactual = nodoactual.getHijo(hijoelegido);
			visitados.push(nodoactual.getPosicion());

			// System.out.println("El hijo elegido es: " + hijoelegido);
		}

		return direccion;
	}

}