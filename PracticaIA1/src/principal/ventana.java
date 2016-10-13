package principal;

import javax.swing.*;

import clases.dibuja;

public class ventana 
{
	JFrame miventana;
	
	public ventana()
	{
		dibuja Odibuja = new dibuja();
		miventana = new JFrame("Crear mi juego");
		miventana.setSize(800, 800);
		miventana.add(Odibuja);
		miventana.setVisible(true);
	}
	public static void main(String arg[])
	{
		new ventana();
		System.out.println("hola socio");
	}
}
