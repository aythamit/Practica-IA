package practica1;

import javax.swing.JOptionPane;

public class Coche extends Bloque
{

	private Pos pos;
	public int choques;
	int pasajeros;

	private boolean terminado;

	Inteligencia smart;

	public Coche(int x, int y)
	{
		super(-1);
		pos = new Pos(x, y);
		choques = 0;
		pasajeros = 0;
		terminado = false;

		smart = null;

		// smart.nodoactual = new Nodo(pos);
		// smart.visitados = new NodosVisitados(pos);
	}

	public int getX()
	{
		return pos.x;
	}

	public int getY()
	{
		return pos.y;
	}

	public void setPos(int x, int y)
	{
		pos = new Pos(x, y);
	}

	public void setPos(final Pos npos)
	{
		pos = npos;
	}

	public void Terminado()
	{
		if (terminado)
		{
			JOptionPane.showMessageDialog(null, "¡Ha llegado a la meta!");
			System.exit(0);
		}
	}

	public void Mover(int dir, Matriz matriz)
	{

		matriz.insertar(pos.x, pos.y, 0); // inserta fondo

		if (pos.x == Matriz.N)
		{
			if (pos.y == 1)
			{
				while (dir == 1 || dir == 0)
				{
					dir = Matriz.numrandom.nextInt(4);
				}
			}
			while (dir == 1)
			{
				dir = Matriz.numrandom.nextInt(4);
			}
		} else if (pos.x == 1)
		{
			if (pos.y == Matriz.M)
			{
				while (dir == 3 || dir == 2)
				{
					dir = Matriz.numrandom.nextInt(4);
				}
			}
			while (dir == 3)
			{
				dir = Matriz.numrandom.nextInt(4);
			}
		}
		if (pos.y == Matriz.M)
		{
			if (pos.x == Matriz.N)
			{
				while (dir == 1 || dir == 2)
				{
					dir = Matriz.numrandom.nextInt(4);
				}
			}
			while (dir == 2)
			{
				dir = Matriz.numrandom.nextInt(4);
			}
		} else if (pos.y == 1)
		{
			if (pos.x == 1)
			{
				while (dir == 3 || dir == 0)
				{
					dir = Matriz.numrandom.nextInt(4);
				}
			}
			while (dir == 0)
			{
				dir = Matriz.numrandom.nextInt(4);
			}
		}

		switch (dir)
		{
		case 0:
		{
			// if (matriz.matrizdata[(x - 1) + (y - 2) * Matriz.N].getTipo() == -2)
			if (matriz.matrizdata[(pos.x - 1) + (pos.y - 2) * Matriz.N].getTipo() != 1)
			{
				setPos(pos.x, pos.y - 1);
				matriz.insertarCoche(this);

				if (matriz.matrizdata[(pos.x - 1) + (pos.y - 1) * Matriz.N].getTipo() == -2)
					terminado = true;

			} else
			{
				// System.out.println("Me he chocaoooo");
				choques++;
				matriz.insertarCoche(this);
			}
		}
			break;
		case 1:
		{
			if (matriz.matrizdata[(pos.x) + (pos.y - 1) * Matriz.N].getTipo() != 1)
			{
				setPos(pos.x + 1, pos.y);
				matriz.insertarCoche(this);

				if (matriz.matrizdata[(pos.x - 1) + (pos.y - 1) * Matriz.N].getTipo() == -2)
					terminado = true;
			} else
			{
				// System.out.println("Me he chocaoooo");
				choques++;
				matriz.insertarCoche(this);
			}
		}
			break;
		case 2:
		{
			if (matriz.matrizdata[(pos.x - 1) + (pos.y) * Matriz.N].getTipo() != 1)
			{
				setPos(pos.x, pos.y + 1);
				matriz.insertarCoche(this);

				if (matriz.matrizdata[(pos.x - 1) + (pos.y - 1) * Matriz.N].getTipo() == -2)
					terminado = true;
			} else
			{
				// System.out.println("Me he chocaoooo");
				choques++;
				matriz.insertarCoche(this);
			}
		}
			break;
		case 3:
		{
			if (matriz.matrizdata[(pos.x - 2) + (pos.y - 1) * Matriz.N].getTipo() != 1)
			{

				setPos(pos.x - 1, pos.y);
				matriz.insertarCoche(this);

				if (matriz.matrizdata[(pos.x - 1) + (pos.y - 1) * Matriz.N].getTipo() == -2)
					terminado = true;
			} else
			{
				// System.out.println("Me he chocaoooo");
				choques++;
				matriz.insertarCoche(this);
			}
		}
			break;
		default:
			matriz.insertarCoche(this);
			break;
		}

	}

	public void moveraleatorio(Matriz m)
	{
		Mover(Matriz.numrandom.nextInt(4), m);
	}

	public void moverSmart(Matriz m)
	{
		// System.out.println("Se esta moviendo de manera inteligente.");
		// System.out.println("Los sensores marcan: (" + smart.getSensor(0) + ", " + smart.getSensor(1) + ", "
		// + smart.getSensor(2) + ", " + smart.getSensor(3) + ")");
		// System.out.println("Las distancias marcan: (" + smart.getDist(0) + ", " + smart.getDist(1) + ", "
		// + smart.getDist(2) + ", " + smart.getDist(3) + ")");

		int mov = smart.calcularMovimiento();

		// System.out.println("El movimiento elegido es " + mov);

		supermover(mov, m);

	}

	public void activarInteligencia()
	{
		if (smart == null)
		{
			smart = new Inteligencia(pos);
		}
	}

	public void supermover(final int dir, final Matriz matriz)
	{
		matriz.insertar(pos.x, pos.y, 0);

		switch (dir)
		{
		case 0:
			setPos(pos.x, pos.y - 1);
			matriz.insertarCoche(this);
			break;
		case 1:
			setPos(pos.x + 1, pos.y);
			matriz.insertarCoche(this);
			break;
		case 2:
			setPos(pos.x, pos.y + 1);
			matriz.insertarCoche(this);
			break;
		case 3:
			setPos(pos.x - 1, pos.y);
			matriz.insertarCoche(this);
			break;
		default:
			matriz.insertarCoche(this);
			break;
		}
		if (smart.nodoactual.getTipo() == -2)
		{
			terminado = true;
		}
	}

	public Pos getPos()
	{
		return pos;
	}

}