package practica1;

import javax.swing.JOptionPane;

public class Coche extends Bloque
{
	private int x;
	private int y;
	public int choques = 0;

	int pasajeros;

	public Coche(int x, int y)
	{
		super(-1);
		setPos(x, y);
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
			if (matriz.matrizdata[(x - 1) + (y - 2) * Matriz.N].getTipo() == -2)
			{
				matriz.insertarCoche(this);
				JOptionPane.showMessageDialog(null, "¡Ha llegado a la meta!");
				System.exit(0);
			}

			else if (matriz.matrizdata[(x - 1) + (y - 2) * Matriz.N].getTipo() != 1)
			{
				setPos(x, y - 1);
				matriz.insertarCoche(this);
			} else
			{
				System.out.println("Me he chocaoooo");
				choques++;
				matriz.insertarCoche(this);
			}
		}
			break;
		case 1:
		{
			if (matriz.matrizdata[(x) + (y - 1) * Matriz.N].getTipo() == -2)
			{
				matriz.insertarCoche(this);
				JOptionPane.showMessageDialog(null, "¡Ha llegado a la meta!");
				System.exit(0);
			}

			else if (matriz.matrizdata[(x) + (y - 1) * Matriz.N].getTipo() != 1)
			{
				setPos(x + 1, y);
				matriz.insertarCoche(this);
			} else
			{
				System.out.println("Me he chocaoooo");
				choques++;
				matriz.insertarCoche(this);
			}
		}
			break;
		case 2:
		{
			if (matriz.matrizdata[(x - 1) + (y) * Matriz.N].getTipo() == -2)
			{
				matriz.insertarCoche(this);
				JOptionPane.showMessageDialog(null, "¡Ha llegado a la meta!");
				System.exit(0);
			}

			else if (matriz.matrizdata[(x - 1) + (y) * Matriz.N].getTipo() != 1)
			{
				setPos(x, y + 1);
				matriz.insertarCoche(this);
			} else
			{
				System.out.println("Me he chocaoooo");
				choques++;
				matriz.insertarCoche(this);
			}
		}
			break;
		case 3:
		{
			if (matriz.matrizdata[(x - 2) + (y - 1) * Matriz.N].getTipo() == -2)
			{
				matriz.insertarCoche(this);
				JOptionPane.showMessageDialog(null, "¡Ha llegado a la meta!");
				System.exit(0);
			}

			else if (matriz.matrizdata[(x - 2) + (y - 1) * Matriz.N].getTipo() != 1)
			{
				setPos(x - 1, y);
				matriz.insertarCoche(this);
			} else
			{
				System.out.println("Me he chocaoooo");
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

}
