		package Utiliteria;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;

public class RestriccionDeJTextField extends PlainDocument {

private JTextComponent text;
	
	private int num;
	
	public RestriccionDeJTextField(JTextComponent field,int nume)
	{
		text=field;
		num=nume;
	}
	
	@Override
	public void insertString(int arg0,String arg1,AttributeSet a) throws BadLocationException
	{
	
		for(int i=0;i<arg1.length();i++)
			if((text.getText().length() + arg1.length()) >= num )
				return ;
		super.insertString(arg0, arg1, a);
	}
}

