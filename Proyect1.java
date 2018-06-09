/*
 * @author: Alexander Almengor
 * @Group:1LS221
 * @ID:8-923-938
 */

import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Proyect1 implements ActionListener {
   
   //Field
   JFrame ventana;
   JButton btn_boton, btn_tmp, btn_winner;
   JLabel lbl_este, lbl_oeste, lbl_norte, lbl_sur,lbl_num, lbl_num2, lbl_orientation;
   JTextField tf_orientation;
   ArrayList<JButton> winnerbutton = new ArrayList<>();
   Random rand = new Random();
   int x,y;
   int contador = 0;
   
   //Main
   public static void main (String[] args)
	{
		
	//Reglas e Introducción al Juego
   JOptionPane notification = new JOptionPane();
   notification.showMessageDialog(null, "¡Bienvenido al Juego del Caza Recompensas! \n \n Instrucciones: \n -Busca en el mapa el cuadrante ganador y trata de buscarlo con la menor cantidad de intentos posibles. \n -Apoyate del \"Detector de Tesoros\" para orientartate en tu camino hacia el tesoro.", "Bienvenido",JOptionPane.INFORMATION_MESSAGE);
       int datos = 0;
	//Constructor
	new Proyect1(); 
	}
	Proyect1()
	{
		//Ventana Principal del Juego
		
		ventana = new JFrame ("El Caza Recompensas");
		ventana.setBounds (700,350,700,700);
		ventana.setLayout(null);
		ventana.setResizable (false);
		ventana.setDefaultCloseOperation(ventana.EXIT_ON_CLOSE);
		
		//Botones
		
		int i;
		for (i=0;i<100;i++)
		{
         btn_boton = new JButton(); 
         btn_boton.setBounds(165+35*(i%10),200+20*(i/10),35,20); //Mapa del tesoro
         btn_boton.addActionListener(this);
         ventana.add(btn_boton);
		 winnerbutton.add(btn_boton); //Estructura de Datos Dinámica
		}
		
		//Método Random 
		
		btn_winner = winnerbutton.get(rand.nextInt(winnerbutton.size()));
		
		//
	  
	  //Etiquetas
	  
			lbl_norte = new JLabel("Norte");
			lbl_norte.setBounds(320,125,80,40);
			ventana.add(lbl_norte);

			lbl_oeste = new JLabel("Oeste");
			lbl_oeste.setBounds(80,285,80,40);
			ventana.add(lbl_oeste);
			
			lbl_sur = new JLabel("Sur");
			lbl_sur.setBounds(320,450,80,40);
			ventana.add(lbl_sur);

			lbl_este = new JLabel("Este");
			lbl_este.setBounds(555,285,80,40);
			ventana.add(lbl_este);
			
			lbl_orientation = new JLabel("Detector de Tesoros:");
			lbl_orientation.setBounds(165,520,200,40);
			ventana.add(lbl_orientation);
			
			tf_orientation = new JTextField();
		    tf_orientation.setBounds(165,550,280,20);
		    ventana.add(tf_orientation);
			
		//Númeración de Filas y Columnas
		
			int x,y;
	
			for(x=1;x<11;x++){ //Lista de valores en el eje "X"
				
				String num2 = String.valueOf(x); //Conversión de tipo de dato y asignación
				lbl_num2 = new JLabel(num2);
				lbl_num2.setBounds(150+35*(x%11), 400+20*(x/11),35,20);
				ventana.add(lbl_num2); 

			}
			
			for(y=10; y>0; y--){ //Listado de valores en el eje "Y"
			
				String num = String.valueOf(y);//Conversión de tipo de dato y asignación
				lbl_num = new JLabel(num); 
				lbl_num.setBounds(145+30*(y%1), 400-20*(y/1),35,20);
				ventana.add(lbl_num);
			}

		 
		ventana.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		this.contador++;
		btn_tmp = (JButton)e.getSource();
		this.x = btn_tmp.getLocation().x;
		this.y = btn_tmp.getLocation().y;
		
		comparativaFinal();
	}
	
	private void comparativaFinal() {
        //Si se encontadorró el tesoro
		int xTesoro,yTesoro;
		xTesoro = btn_winner.getLocation().x;
		yTesoro = btn_winner.getLocation().y;
		
        if ((x == xTesoro) && (y == yTesoro)){
			
			JOptionPane notification = new JOptionPane();
			notification.showMessageDialog(null, "¡Felicidades has encontrado el gran tesoro! \n"+ "-Has hecho "+String.valueOf(this.contador)+ " Intentos. \n \n Creditos: \n Autor: Alexander Almengor \n Grupo: 1LS221 \n ID: 8-923-938.", "¡Has Ganado!" ,JOptionPane.INFORMATION_MESSAGE );
			int i = notification.showConfirmDialog(null, "¿Deseas emprender nuevamente la aventura de encontrar el gran tesoro?", "¡Alerta!", JOptionPane.YES_NO_OPTION );
		
		//Reiniciar ventana
        if (i == 0) {
            ventana.setVisible(false); //Vuelve invisible el proceso y lo depura
			ventana.dispose(); //Método reservado para depurar el proceso
            new Proyect1();
        }

        if (i == 1)
            ventana.dispose();

        if (i == 2) {
            System.out.println("*Cancel*");
        }
		}
		//Validaciones
		
		if ((x < xTesoro) && (y == yTesoro))
            tf_orientation.setText("\"Hacia el Este se encuentra un gran tesoro\"");
		
		if ((x == xTesoro) && (y > yTesoro))
            tf_orientation.setText("\"Hacia el Norte se encuentra un gran tesoro\"");

        if ((x == xTesoro) && (y < yTesoro))
            tf_orientation.setText("\"Hacia el Sur se encuentra un gran tesoro\"");
		
        if ((x > xTesoro) && (y == yTesoro))
            tf_orientation.setText("\"Hacia el Oeste se encuentra un gran tesoro\"");
		

        if ((x > xTesoro) && (y < yTesoro))
            tf_orientation.setText("\"Hacia el Suroeste se encuentra un gran tesoro\"");

        if ((x > xTesoro) && (y > yTesoro))
            tf_orientation.setText("\"Hacia el Noroeste se encuentra un gran tesoro\"");
		
		 if ((x < xTesoro) && (y > yTesoro))
            tf_orientation.setText("\"Hacia el Noreste se encuentra un gran tesoro\"");

        if ((x < xTesoro) && (y < yTesoro))
            tf_orientation.setText("\"Hacia el Sureste se encuentra un gran tesoro\"");
    }

}