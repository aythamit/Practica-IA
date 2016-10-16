package practica1;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bloque {
	private int tipo;
	public final static int lado = 32;
	public final int[] pixeles;

	private final String fondo = "/imagenes/cuadro.png";
	private final String main = "/imagenes/cuadro_res.png";
	private final String obsta = "/imagenes/obstaculo.png";

	public Bloque(final int tipo) {
		this.tipo = tipo;

		pixeles = new int[lado * lado];
		String bloque;
		switch (this.tipo) {
		case 1:
			bloque = obsta;
			break;
		case -1:
			bloque = main;
			break;
		default:
			bloque = fondo;
			break;
		}

		BufferedImage imagen;
		try {
			imagen = ImageIO.read(Bloque.class.getResource(bloque));
			imagen.getRGB(0, 0, lado, lado, pixeles, 0, lado);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public final int getTipo() {
		return tipo;
	}

	public void setTipo(int var) {
		this.tipo = var;
	}
}
