package practica1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class Panel extends JPanel implements ActionListener, KeyListener
{
	private static final long serialVersionUID = 1L;

	JButton btniniciar, btnparar;
	JLabel mn;
	JTextField ancho, alto;
	JSlider slider;
	JRadioButton b1, b2, b3, b4, br, bs;

	private boolean random = true;
	private int boton = 0;

	Practica1 main;

	public Panel(Practica1 prac)
	{
		main = prac;

		mn = new JLabel("MxN");

		ancho = new JTextField("12", 2);
		ancho.addKeyListener(this);
		alto = new JTextField("12", 2);
		alto.addKeyListener(this);

		btniniciar = new JButton("Iniciar");
		btniniciar.addActionListener(this);
		btnparar = new JButton("Reset");
		btnparar.addActionListener(this);

		slider = new JSlider();
		slider.setMinimum(1);
		slider.setValue(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		this.add(btniniciar);
		this.add(btnparar);
		this.add(mn);
		this.add(alto);
		this.add(ancho);
		this.add(slider);
	}

	public Panel()
	{

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		mn = new JLabel("\t");
		mn.setFont(new Font("serief", Font.ITALIC, 15));

		b1 = new JRadioButton("F");
		b1.setForeground(new Color(195, 195, 195));
		b1.setSelected(true);
		b1.addActionListener(this);
		b2 = new JRadioButton("O");
		b2.setForeground(new Color(153, 217, 234));
		b2.addActionListener(this);
		b3 = new JRadioButton("M");
		b3.setForeground(new Color(237, 28, 36));
		b3.addActionListener(this);
		b4 = new JRadioButton("C");
		// b4.setForeground(new Color(255, 255, 255));
		b4.addActionListener(this);

		br = new JRadioButton("R");
		br.setSelected(true);
		br.addActionListener(this);
		bs = new JRadioButton("S");
		bs.addActionListener(this);

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(br);
		grupo.add(bs);

		ButtonGroup group = new ButtonGroup();
		group.add(b1);
		group.add(b2);
		group.add(b3);
		group.add(b4);

		this.add(mn);
		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);

		this.add(mn);
		this.add(br);
		this.add(bs);

		// System.out.println("Accedido al constructor vertical");

	}

	public int get_m()
	{
		int m = 12;

		// System.out.println("El texto es: " + ancho.getText());
		if (ancho.getText().length() != 0)
		{
			// System.out.println("Se convierte " + ancho.getText() + " a int");
			m = Integer.parseInt(ancho.getText());
		} else
		{
			ancho.setText("12");
		}

		return m;
	}

	public int get_n()
	{
		int n = 12;

		// System.out.println("El texto es: " + alto.getText());
		if (alto.getText().length() != 0)
		{
			// System.out.println("Se convierte " + alto.getText() + " a int");
			n = Integer.parseInt(alto.getText());
		} else
		{
			alto.setText("12");
		}

		return n;
	}

	public int getBoton()
	{
		return boton;
	}

	public boolean isMovRandom()
	{
		return random;
	}

	public void keyPressed(KeyEvent arg0)
	{
	}

	public void keyReleased(KeyEvent arg0)
	{
	}

	public void keyTyped(KeyEvent e)
	{
		char c = e.getKeyChar();

		if (!Character.isDigit(c))
		{
			e.consume();
		}
		if (e.getSource() == ancho)
		{
			if (ancho.getText().length() >= 2)
			{
				e.consume();
			}
		}
		if (e.getSource() == alto)
		{
			if (alto.getText().length() >= 2)
			{
				e.consume();
			}
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btniniciar)
		{
			main.iniciar();
			main.enPausa = false;

			btnparar.setText("Pausar");
			btniniciar.setText("Iniciar");
		}

		if (e.getSource() == btnparar)
		{
			if (!main.enPausa)
			{
				main.enPausa = true;
				btnparar.setText("Reset");
				btniniciar.setText("Continuar");
			} else
			{
				try
				{
					main.reset();
				} catch (InterruptedException e1)
				{
					e1.printStackTrace();
				}
				btniniciar.setText("Iniciar");
			}
		}

		if (e.getSource() == b1)
		{
			boton = 0;
		} else if (e.getSource() == b2)
		{
			boton = 1;
		} else if (e.getSource() == b3)
		{
			boton = -2;
		} else if (e.getSource() == b4)
		{
			boton = -1;
		}

		if (e.getSource() == br)
		{
			random = true;
		} else if (e.getSource() == bs)
		{
			random = false;
		}

	}
}