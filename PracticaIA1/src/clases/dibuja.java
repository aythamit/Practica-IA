package clases;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class dibuja extends Canvas
{
	BufferedImage imagen;
	public void paint(Graphics g)
	{
		
		// hola k 
		try {
			URL ruta = getClass().getResource("../fondo.jpg");
			imagen = ImageIO.read(ruta);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		g.drawImage(imagen, 0,0,this);
		
	}
}
