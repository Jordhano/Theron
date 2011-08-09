package Utiliteria;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class RestriccionANumerico extends PlainDocument implements KeyListener {

private JTextComponent text;
private int num;
private boolean estado;


	public RestriccionANumerico (JTextComponent field,int num)
	{
		text=field;
		this.num=num;
		
	}
	public RestriccionANumerico (JTextComponent field,boolean estado)
	{
		text=field;
		this.num=num;
		this.estado = estado;
	}
	@Override
	public void insertString(int arg0,String arg1,AttributeSet a) throws BadLocationException
	{
	int n = 0;
	for(int i=0;i < arg1.length();i++)
		n=(int) arg1.charAt(i);
	if((text.getText().length() + arg1.length()) >num )
		return ;
		if(!(n >45 && n< 58))
			return ;
		super.insertString(arg0, arg1, a);
	
}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		String tempCaja = text.getText();
		String tempKeyChar = String.valueOf(arg0.getKeyChar());
		String tempDos="";
		try{
			tempDos = tempCaja.substring(tempCaja.indexOf("."),tempCaja.length());
		}catch(Exception e){
			
		}
		
		if (estado == true){
			if (tempKeyChar.equals(".") && tempCaja.indexOf(".") != -1){
				text.setText(tempCaja.substring(0, tempCaja.indexOf(".")));
			}
		}else if (estado == false){
			if (tempKeyChar.equals(".") && tempDos.indexOf(".") != -1){
				text.setText(tempCaja.substring(0, tempCaja.indexOf(".")+1));
			}
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

