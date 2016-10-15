/*package clases;
import java.awt.*;

public class mapa 
{
	
}
*/

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 //eyyyyyy
package clases;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class mapa 
{  
   /** 
      Constructor 
   */  

	 static int MAX_M = 19;
	 static int MAX_N = 18;
	int medida = 32;
	private final JPanel gui = new JPanel(new BorderLayout(3, 3));
	Graphics[][] matriz2 = new Graphics[MAX_M][MAX_N];
	private static JButton[][] matriz = new JButton[MAX_M][MAX_N];
	private JPanel chessBoard;

   public mapa()  
   {  
	   initializeGui();
   }  
   public final void initializeGui()
   {
	   gui.setBorder(new EmptyBorder(2, 2, 2, 2));
	   
	   chessBoard = new JPanel(new GridLayout(0, MAX_N));
	   chessBoard.setBorder(new LineBorder(Color.BLACK));
	   gui.add(chessBoard);
	   
	   Insets buttonMargin = new Insets(0,0,0,0);
	   
	   for (int ii = 0; ii < MAX_M; ii++) {
           for (int jj = 0; jj < MAX_N; jj++) {
               JButton b = new JButton();
              // JLabel prueba = new JLabel("p");
               b.setMargin(buttonMargin);
               // our chess pieces are 64x64 px in size, so we'll
               // 'fill this in' using a transparent icon..
               ImageIcon icon = new ImageIcon(
                       new BufferedImage(medida, medida, BufferedImage.TYPE_INT_ARGB));
               b.setIcon(icon);
               
               
               matriz[jj][ii] = b;
               
           }
       }
	   
	   for (int ii = 0; ii < MAX_M; ii++) 
	   {
           for (int jj = 0; jj < MAX_N; jj++) 
           {
        	   chessBoard.add(matriz[jj][ii]);
           }
         }
   }
   
   public final JComponent getChessBoard() {
       return chessBoard;
   }

   public final JComponent getGui() {
       return gui;
   }
   
   public static void SetCoche(int x , int y, ImageIcon coche)
   {
	   matriz[x][y].setIcon(coche);
   }
   
		   
   /** 
      The main method creates an instance of the 
      mapa class, causing it to display 
      its window. 
   */  

   public static void main(String[] args)  
   {  

       Runnable r = new Runnable() {
    	   URL ruta = getClass().getResource("../cochito_icon_32x32.png");
    	   ImageIcon coche = new ImageIcon(ruta);
           @Override
           public void run() {
               mapa cb =
                       new mapa();

               JFrame f = new JFrame("ChessChamp");
               f.add(cb.getGui());
               f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
               f.setLocationByPlatform(true);

               // ensures the frame is the minimum size it needs to be
               // in order display the components within it
               f.pack();
               // ensures the minimum size is enforced.
               f.setMinimumSize(f.getSize());
               f.setVisible(true);
               mapa.SetCoche(0,0,coche);
           }
       };
       SwingUtilities.invokeLater(r);
   
   }  
} 


/*
 * contenedor = new JPanel();
	   

	 
	   contenedor.setLayout(new GridLayout(MAX_M,MAX_N));
	   int ancho =0;
	   int alto = 0;
	   int medida = 20;
	   
	   Rectangle2D p = new Rectangle2D.Double(ancho,alto,medida,medida);
	   
	   Graphics p2 = getGraphics();
	   p2.drawRect(ancho,alto,medida,medida);
		   for(int i=0;i<MAX_M; i++)
		   {
			   for(int j=0;j<MAX_N; j++)
			   {
				   matriz2[i][j] = p2;
				   ancho+=medida;
				   p2.drawRect(ancho,alto,medida,medida);
				   
			   }
			   ancho = 0;
			   alto += medida;
			   
		   }

		   
	   add(contenedor);
	   setSize(300,300);
	   setVisible(true); 
	   
	   *
	   *switch(x)
	   *case '1'
	   *{
	   *	area()
	   *}break;
	   *
	   *case '2'
	   *{
	   *	perimetro()
	   *}break;
	   *
	   *case '0'
	   *{
	   *	salida = true
	   *}break;
	   *
	   *
	   *
	   *
	   *
	   *
	   *
	   *
	   *
	   *
	   */
 