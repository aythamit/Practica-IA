package practica1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class Panel extends JPanel implements ActionListener, KeyListener
{
	JButton btniniciar, btnparar;
	JLabel mn;
	JTextField ancho, alto;
	JSlider slider;

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
		btnparar = new JButton("Pausar");
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

	public int get_m()
	{
		int m = 12;

		System.out.println("El texto es: " + ancho.getText());
		if (ancho.getText().length() != 0)
		{
			System.out.println("Se convierte " + ancho.getText() + " a int");
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

		System.out.println("El texto es: " + alto.getText());
		if (alto.getText().length() != 0)
		{
			System.out.println("Se convierte " + alto.getText() + " a int");
			n = Integer.parseInt(alto.getText());
		} else
		{
			alto.setText("12");
		}

		return n;
	}

	public void keyPressed(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
	}

	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
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
	}
}
