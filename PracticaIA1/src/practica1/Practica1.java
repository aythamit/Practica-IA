package practica1;

///// Grupo 7
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Practica1 extends Canvas implements Runnable, MouseListener
{
	private static final long serialVersionUID = 1L; // Numero de serial por si la clase se repite en otro archivo (pasteles de Java)

	public static int ALTO;
	public static int ANCHO; // Ancho y alto de la pantalla principal (en funcion del tamaño de la matriz)
	private static String NOMBRE; // Nombre de la ventana
	public volatile boolean enFuncionamiento; // Bool para el bucle principal
	public volatile boolean enPausa;
	private int segs; // Extra temporal para comprobar que funciona correctamente

	private static BufferedImage imagen; // Imagen donde cargar los pixeles de la matriz
	private static int[] pixeles; // Se relaciona la imagen con un array (por pixeles)

	private JFrame ventana; // Objeto ventana para mostrar el canvas dentro
	private Matriz matriz; // La matriz principal donde los datos son almacenados
	private Thread thread; // Objeto thread para ejecutar el programa en varias lineas simultaneamente
	private Panel panel, or; // Se crean los paneles de configuracion

	private Practica1()
	{
		ALTO = Bloque.lado * Matriz.M;
		ANCHO = Bloque.lado * Matriz.N;
		NOMBRE = "Practica";
		enFuncionamiento = false;
		enPausa = true;
		segs = 0;

		imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
		pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();

		matriz = new Matriz(); // Crear el objeto matriz en memoria
		panel = new Panel(this);
		or = new Panel();
		thread = new Thread(this, "Graficos"); // Crear el objeto thread en memoria;

		setPreferredSize(new Dimension(ANCHO, ALTO)); // dar dimenasion al canvas
		IniciarVentana();
		this.addMouseListener(this);
	}

	private void IniciarVentana()
	{
		ventana = new JFrame(NOMBRE); // Crear el objeto ventana en memoria
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Para que al clicar la cruz roja se cierren los procesos
		ventana.setResizable(false); // Para que no se pueda cambiar el tamaño de la ventana
		ventana.setLayout(new BorderLayout()); // Asignar una manera de organizar los datos para sacarlos por pantalla
		ventana.add(this, BorderLayout.CENTER); // Hacer que el canvas este en el centro de la imagen (de la ventana)
		ventana.add(panel, BorderLayout.NORTH);
		ventana.add(or, BorderLayout.WEST);
		ventana.pack(); // Para que la ventana adquiera el mismo tamaño que el canvas de dentro (por si acaso)
		ventana.setLocationRelativeTo(null); // Hacer que la ventana aparezca en el centro de la pantalla
		ventana.setVisible(true); // Para que la ventana se vea
	}

	private void resizeVentana(Matriz m)
	{
		matriz = m;

		ALTO = Bloque.lado * Matriz.M;
		ANCHO = Bloque.lado * Matriz.N;

		imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
		pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();

		setPreferredSize(new Dimension(ANCHO, ALTO)); // dar dimenasion al canvas
		ventana.pack();
	}

	public void iniciar()
	{
		enFuncionamiento = true; // Activar el bool que pone a funcionar el while del bucle principal
		if (!thread.isAlive())
		{
			thread.start(); // Empezar el segundo hilo de ejecucion que hemos llamado "Graficos"
		}
	}

	public void reset() throws InterruptedException
	{

		enFuncionamiento = false;
		thread.join();
		thread = new Thread(this, "Graficos 2");
		enPausa = true;

		// System.out.println("Los valores a pasar al constructor son: m = " + panel.get_m() + "; n = " + panel.get_n());
		resizeVentana(new Matriz(panel.get_n(), panel.get_m()));
		segs = 0;
		iniciar();
	}

	private void mostrar() // Metodo que muestra la matriz por pantalla
	{

		BufferStrategy estrategia = getBufferStrategy(); // Metodo del canvas para seguir una estrategia de cargado de graficos

		if (estrategia == null) // Si no hay estrategia asignada asignamos una
		{
			createBufferStrategy(1);
			return;
		}

		System.arraycopy(matriz.pixeles, 0, pixeles, 0, pixeles.length); // Copiamos el array de la matriz a uno de esta clase para imprimirlo

		Graphics g = estrategia.getDrawGraphics(); // Se crea el objeto de graficos

		int desde = ((ventana.getWidth() - ANCHO) / 2) - 32;
		int hasta = ((ventana.getHeight() - panel.getHeight() - ALTO) / 2) - 14;
		int anchete = ANCHO;

		if (desde < 0)
		{
			anchete = ANCHO + (-desde);
			ventana.setSize(ventana.getWidth() + (-desde), ventana.getHeight());
			desde = 0;
		}

		g.drawImage(imagen, desde, hasta, anchete, ALTO, null); // Se dibujan los graficos (de la imagen)
		g.dispose(); // Cuando g dibuje la imagen destruye g para que sea mas eficiente

		estrategia.show(); // Se muestran los graficos

	}

	public void run() // Metodo del segundo thread
	{
		long iniciotiempo = System.nanoTime(); // Referencias para contar el tiempo
		long actualizar;
		requestFocus();

		while (enFuncionamiento) // Bucle principals
		{

			actualizar = System.nanoTime();

			if ((actualizar - iniciotiempo) >= (1000000000 / panel.slider.getValue()))
			{
				mostrar();
				if (!enPausa)
				{
					matriz.cochito.Terminado();
					// System.out.println("");
					// System.out.println("");
					// matriz.mostrardatosmatriz();

					matriz.cochito.smart.actSensores(matriz.cochito.getX(), matriz.cochito.getY(), matriz);

					if (or.isMovRandom())
					{
						matriz.cochito.moveraleatorio(matriz);
					} else
					{
						matriz.cochito.moverSmart(matriz);
					}
					segs++;
					ventana.setTitle("Practica || " + segs + " Choques: " + matriz.cochito.choques);
				}
				iniciotiempo = System.nanoTime();
			}
		}
	}

	public static void main(String[] args) // metodo main para empezar a ejecutar
	{
		Practica1 practica = new Practica1(); // se llama al constructor y se crea el objeto
		// System.out.println("El programa se ejecuto");

		practica.iniciar(); // Llamada al metodo iniciar para que empiecen los distintos procesos
	}

	public void mouseClicked(MouseEvent arg0)
	{
	}

	public void mouseEntered(MouseEvent arg0)
	{
	}

	public void mouseExited(MouseEvent arg0)
	{
	}

	public void mousePressed(MouseEvent arg0)
	{
		if (enPausa)
		{
			actualizarMatriz();
		}
	}

	public void mouseReleased(MouseEvent arg0)
	{
	}

	public void actualizarMatriz()
	{
		int x = (MouseInfo.getPointerInfo().getLocation().x - this.getLocationOnScreen().x
				- (ventana.getWidth() - ANCHO) / 2 + 32);
		int y = (MouseInfo.getPointerInfo().getLocation().y - this.getLocationOnScreen().y - 1);
		int posx;
		int posy;

		if (x >= 0 && y >= 0 && x < ANCHO && y < ALTO)
		{
			posx = x / Bloque.lado + 1;
			posy = y / Bloque.lado + 1;
		} else
		{
			posx = posy = 0;
		}

		if (posx > 0 && posy > 0)
		{
			// System.out.println("(" + posx + ", " + posy);
			if (or.getBoton() != -1)
			{
				if (matriz.matrizdata[(posx - 1) + (posy - 1) * Matriz.N].getTipo() != -1)
				{
					if (or.getBoton() == -2)
					{
						matriz.insertarMeta(posx, posy);
					} else
						matriz.insertar(posx, posy, or.getBoton());
				}

			} else
			{
				matriz.insertar(matriz.cochito.getX(), matriz.cochito.getY(), 0);
				matriz.cochito.setPos(posx, posy);
				matriz.insertar(posx, posy, or.getBoton());
			}

		}
	}
}