package practica1;

public class Pos
{
	public int x;
	public int y;

	public Pos(final int a, final int b)
	{
		x = a;
		y = b;
	}

	public Pos(final Pos p)
	{
		x = p.x;
		y = p.y;
	}

	public boolean comparar(final Pos p)
	{
		if (this.x == p.x && this.y == p.y)
			return true;
		else
			return false;
	}
}
