package practica1;
import java.util.Random;

import javax.swing.JOptionPane;
public class Matriz {
private static final int ladobloque = Bloque.lado;
	public static final int N = 12;
	public static final int M = 12;
	Bloque[] matrizdata = new Bloque[N * M];
	public int[] pixeles;
	Coche cochito;
	
public static Random numrandom = new Random();
public Matriz() {
		pixeles = new int[Practica1.ANCHO * Practica1.ALTO];
System.out.println("La matriz tiene " + N + " columnas y " + M + " filas");
llenarfondo();
		cochito = new Coche(1,M);
		insertarCoche(cochito);
		insertarMeta(N,1);
		//insertarMeta(2,M-1);
mostrardatosmatriz();
}
void insertar(final int x, final int y, final int tipo) {
		assert ((x <= N) && (y <= M) && (x > 0) && (y > 0));
		Bloque bloque = new Bloque(tipo);
		matrizdata[(x - 1) + (y - 1) * N] = bloque;
		final int ncol = (x - 1) * ladobloque;
		final int ncolum = (y - 1) * ladobloque;
for (int j = 0; j < ladobloque; j++) {
			for (int i = 0; i < ladobloque; i++) {
				pixeles[(i + ncol) + (j + ncolum) * Practica1.ANCHO] = bloque.pixeles[i + j * ladobloque];
			}
		}
	}
private final void llenarfondo() {
		for (int j = 1; j <= M; j++) {
			for (int i = 1; i <= N; i++) {
				insertar(i, j, numrandom.nextInt(6));
			}
		}
	}
final void insertarCoche(Coche c) {
		if ((c.getX() > N) || (c.getY() > M) || (c.getX() <= 0) || (c.getY() <= 0)) 
		{
			c.setPos(1, 1);
		}
		insertar(c.getX(), c.getY(), -1);
	}
private final void insertarMeta(int x, int y) {
	if ((x > N) || (y > M) || (x <= 0) || (y <= 0)) {
		x = N;
		y = 1;
	}
	insertar(x, y, -2);
}
public final void mostrardatosmatriz() {
		for (int j = 0; j < M; j++) {
			for (int i = 0; i < N; i++) {
				System.out.print(matrizdata[i + j * N].getTipo() + " ");
			}
			System.out.print('\n');
		}
	}
public void moverRojo(int dir) {/*
		int[] pos = new int[3];
		for (int i = 0; i < N * M; i++) {
			if (matrizdata[i].getTipo() == -1) {
				matrizdata[i].setTipo(0);
				pos[0] = i;
			}
		}
		pos[1] = (pos[0] % N) + 1; // pos x
		pos[2] = (pos[0] / N) + 1; // pos y
		insertar(pos[1], pos[2], 0); //inserta fondo
if (pos[1] == N) {
			if (pos[2] == 1) {
				while (dir == 1 || dir == 0) {
					dir = numrandom.nextInt(4);
				}
			}
			while (dir == 1) {
				dir = numrandom.nextInt(4);
			}
		} else if (pos[1] == 1) {
			if (pos[2] == M) {
				while (dir == 3 || dir == 2) {
					dir = numrandom.nextInt(4);
				}
			}
			while (dir == 3) {
				dir = numrandom.nextInt(4);
			}
		}
		if (pos[2] == M) {
			if (pos[1] == N) {
				while (dir == 1 || dir == 2) {
					dir = numrandom.nextInt(4);
				}
			}
			while (dir == 2) {
				dir = numrandom.nextInt(4);
			}
		} else if (pos[2] == 1) {
			if (pos[1] == 1) {
				while (dir == 3 || dir == 0) {
					dir = numrandom.nextInt(4);
				}
			}
			while (dir == 0) {
				dir = numrandom.nextInt(4);
			}
		}
switch (dir) {
		case 0:
		{
			if( matrizdata[(pos[1] -1) + (pos[2] - 2) * N].getTipo() == -2)
			{
				insertarRojo(pos[1], pos[2] - 1);
				JOptionPane.showMessageDialog(null, "�Ha llegado a la meta!");
				System.exit(0);
			}
		
			else if( matrizdata[(pos[1] -1) + (pos[2] - 2) * N].getTipo() != 1)
				insertarRojo(pos[1], pos[2] - 1);
			else
			{
				System.out.println("Me he chocaoooo");
				insertarRojo(pos[1], pos[2]);
			}
		}break;
		case 1:
		{	if( matrizdata[(pos[1]) + (pos[2] - 1) * N].getTipo() == -2)
		{
			insertarRojo(pos[1] + 1, pos[2]);
				JOptionPane.showMessageDialog(null, "�Ha llegado a la meta!");
				System.exit(0);
		}
				
			else if( matrizdata[(pos[1]) + (pos[2] - 1) * N].getTipo() != 1)
				insertarRojo(pos[1] + 1, pos[2]);
			else
			{
				System.out.println("Me he chocaoooo");
				insertarRojo(pos[1], pos[2]);
			}
		}	break;
		case 2:
		{	if(matrizdata[(pos[1] - 1) + (pos[2]) * N].getTipo() == -2)
		{
			insertarRojo(pos[1], pos[2] + 1);
			JOptionPane.showMessageDialog(null, "�Ha llegado a la meta!");
			System.exit(0);
		}
	
			else if( matrizdata[(pos[1] - 1) + (pos[2]) * N].getTipo() != 1)
				insertarRojo(pos[1], pos[2] + 1);
			else
			{
				System.out.println("Me he chocaoooo");
				insertarRojo(pos[1], pos[2]);
			}
		}	break;
		case 3:
		{	if( matrizdata[(pos[1] - 2) + (pos[2]-1) * N].getTipo() == -2)
		{
			insertarRojo(pos[1] - 1, pos[2]);
			JOptionPane.showMessageDialog(null, "�Ha llegado a la meta!");
			System.exit(0);
		}
	
			else if( matrizdata[(pos[1] - 2) + (pos[2]-1) * N].getTipo() != 1)
				insertarRojo(pos[1] - 1, pos[2]);
			else
			{
				System.out.println("Me he chocaoooo");
				insertarRojo(pos[1], pos[2]);
			}
		}	break;
		default:
			insertarRojo(pos[1], pos[2]);
			break;
		}
	*/}
public void moveraleatorio() {
		cochito.Mover(numrandom.nextInt(4), this);
	}
}
// pepe aa