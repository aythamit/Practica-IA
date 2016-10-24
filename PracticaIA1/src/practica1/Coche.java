package practica1;

import javax.swing.JOptionPane;

public class Coche extends Bloque
{
	private int x;
	private int y;
	public int choques;
	int pasajeros;

	private boolean terminado;

	Inteligencia smart;

	public Coche(int x, int y)
	{
		super(-1);
		this.x = x;
		this.y = y;
		choques = 0;
		pasajeros = 0;
		terminado = false;

		smart = new Inteligencia();
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public void setPos(int x, int y)
	{
		this.x = x;
		this.y = y;
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

		matriz.insertar(x, y, 0); // inserta fondo

		if (x == Matriz.N)
		{
			if (y == 1)
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
		} else if (x == 1)
		{
			if (y == Matriz.M)
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
		if (y == Matriz.M)
		{
			if (x == Matriz.N)
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
		} else if (y == 1)
		{
			if (x == 1)
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
			if (smart.getSensor(0) != 1)
			{
				setPos(x, y - 1);
				matriz.insertarCoche(this);

				if (smart.getSensor(0) == -2)
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
			if (smart.getSensor(1) != 1)
			{
				setPos(x + 1, y);
				matriz.insertarCoche(this);

				if (smart.getSensor(1) == -2)
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
			if (smart.getSensor(2) != 1)
			{
				setPos(x, y + 1);
				matriz.insertarCoche(this);

				if (smart.getSensor(2) == -2)
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
			if (smart.getSensor(3) != 1)
			{

				setPos(x - 1, y);
				matriz.insertarCoche(this);

				if (smart.getSensor(3) == -2)
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

		Mover(mov, m);

	}

}