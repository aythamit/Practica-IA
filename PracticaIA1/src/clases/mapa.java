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

import javax.swing.*;

public class mapa extends JFrame  
{  
   /** 
      Constructor 
   */  
	JPanel contenedor;
	int MAX_M = 5;
	int MAX_N = 5;

   public mapa()  
   {  
	   contenedor = new JPanel();
	  /* contenedor.setLayout(new BoxLayout(contenedor,BoxLayout.Y_AXIS));
	   
	   for(int i=0;i<20;i++)
		   contenedor.add(new JButton("Boton " + (i+1)));*/
	   
	   contenedor.setLayout(new GridLayout(MAX_M,MAX_N));
	   
	   JButton temp = new JButton("h");
	   temp.setBackground(Color.CYAN);
	   for(int i=0;i<(25); i++)
	   {
		   contenedor.add(new JButton("boton "+ (i+1)));
		   //contenedor.add(temp);
	   }
	   add(contenedor);
	   setSize(300,300);
	   setVisible(true);
	   
   }  

   /** 
      The main method creates an instance of the 
      mapa class, causing it to display 
      its window. 
   */  

   public static void main(String[] args)  
   {  
      new mapa();  
   }  
} 