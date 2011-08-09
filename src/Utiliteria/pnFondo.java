package Utiliteria;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Toolkit; 
import javax.swing.ImageIcon;

public class pnFondo extends JPanel {
	private Toolkit tk;
	private String url;
	private int Alto;
	private int Ancho;
	
	public pnFondo(String url){
		tk =  Toolkit.getDefaultToolkit();
		this.url = url;
		 if (tk.getScreenSize().width <= 640){
			 Alto= 640;
			 Ancho = 480;
			 this.url += "640x480";
		 }else if (tk.getScreenSize().width <= 800){
			 Alto= 800;
			 Ancho = 600;
			 this.url += "800x600";
		 }else if (tk.getScreenSize().width <= 1024){
			 Alto= 1024;
			 Ancho = 768;
			 this.url += "1024x768";
		 }else if (tk.getScreenSize().width <= 1280 && tk.getScreenSize().height <= 800){
			 Alto = 1280;
			 Ancho = tk.getScreenSize().height;
			 this.url += "1280x800";
		 }else{
			 Alto = 1280;
			 Ancho = tk.getScreenSize().width;
			 this.url += "1280x1024";
		 }
	}
	
	public int getAlto(){
		return Alto;
	}
	public int getAncho(){
		return Ancho;
	}
	public void paintComponent(Graphics g) {   
		super.paintComponent(g); 
		   g.drawImage(new ImageIcon(url +".png").getImage(), 0,0,this);
		  }
}

