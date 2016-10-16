package practica1;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Practica1 extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L; // Numero de serial por si la clase se repite en otro archivo (pasteles de Java)

	public static final int ALTO = Bloque.lado * Matriz.M;
	public static final int ANCHO = Bloque.lado * Matriz.N; // Ancho y alto de la pantalla principal (en funcion del tamaño de la matriz)
	private static final String NOMBRE = "Practica"; // Nombre de la ventana
	private static volatile boolean enFuncionamiento = false; // Bool para el bucle principal

	private int segs = 0; // Extra temporal para comprobar que funciona correctamente

	private static JFrame ventana; // Objeto ventana para mostrar el canvas dentro
	private static Thread thread; // Objeto thread para ejecutar el programa en varias lineas simultaneamente
	private static Matriz matriz; // La matriz principal donde los datos son almacenados

	private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB); // Imagen donde cargar los pixeles de la matriz
	private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData(); // Se relaciona la imagen con un array (por pixeles)

	private Practica1() {
		setPreferredSize(new Dimension(ANCHO, ALTO)); // dar dimenasion al canvas

		matriz = new Matriz(); // Crear el objeto matriz en memoria

		ventana = new JFrame(NOMBRE); // Crear el objeto ventana en memoria
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para que al clicar la cruz roja se cierren los procesos
		ventana.setResizable(false); // Para que no se pueda cambiar el tamaño de la ventana
		ventana.setLayout(new BorderLayout()); // Asignar una manera de organizar los datos para sacarlos por pantalla
		ventana.add(this, BorderLayout.CENTER); // Hacer que el canvas este en el centro de la imagen (de la ventana)
		ventana.pack(); // Para que la ventana adquiera el mismo tamaño que el canvas de dentro (por si acaso)
		ventana.setLocationRelativeTo(null); // Hacer que la ventana aparezca en el centro de la pantalla
		ventana.setVisible(true); // Para que la ventana se vea 
	}

	public static void main(String[] args) { // metodo main para empezar a ejecutar
		Practica1 practica = new Practica1(); // se llama al constructor y se crea el objeto
		System.out.println("El programa se ejecuto");

		practica.iniciar(); // Llamada al metodo iniciar para que empiecen los distintos procesos
	}

	private void iniciar() {
		mostrar();
		enFuncionamiento = true; // Activar el bool que pone a funcionar el while del bucle principal
		thread = new Thread(this, "Graficos"); // Crear el objeto thread en memoria 
		thread.start(); // Empezar el segundo hilo de ejecucion que hemos llamado "Graficos"
	}

	private void mostrar() { // Metodo que muestra la matriz por pantalla

		BufferStrategy estrategia = getBufferStrategy(); // Metodo del canvas para seguir una estrategia de cargado de graficos

		if (estrategia == null) { // Si no hay estrategia asignada asignamos una
			createBufferStrategy(2);
			return;
		}

		System.arraycopy(matriz.pixeles, 0, pixeles, 0, pixeles.length); // Copiamos el array de la matriz a uno de esta clase para imprimirlo

		Graphics g = estrategia.getDrawGraphics(); // Se crea el objeto de graficos

		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null); // Se dibujan los graficos (de la imagen)
		g.dispose(); // Cuando g dibuje la imagen destruye g para que sea mas eficiente

		estrategia.show(); // Se muestran los graficos

	}

	public void run() { // Metodo del segundo thread
		long iniciotiempo = System.nanoTime(); // Referencias para contar el tiempo
		long actualizar;
		requestFocus();

		while (enFuncionamiento) { // Bucle principal
			actualizar = System.nanoTime();
			if ((actualizar - iniciotiempo) >= 1000000000) {
				mostrar();
				matriz.moveraleatorio();
				segs++;
				ventana.setTitle("Practica || " + segs);
				iniciotiempo = System.nanoTime();
			}
		}
	}
}
