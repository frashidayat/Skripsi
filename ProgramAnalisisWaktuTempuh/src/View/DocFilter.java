package View;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class DocFilter extends DocumentFilter {
   @Override
   public void insertString(DocumentFilter.FilterBypass fb, int offset, String string,
         AttributeSet attr) throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.insert(offset, string);

      if (parse(sb.toString())) {
         super.insertString(fb, offset, string, attr);
      } else {
          JOptionPane.showMessageDialog(null, "Masukan Angka", "ERROR", JOptionPane.ERROR_MESSAGE);
      }
   }

   private boolean parse(String text) {
      try {
         Double.parseDouble(text);
         return true;
      } catch (NumberFormatException e) {
         return false;
      }
   }

   @Override
   public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text,
         AttributeSet attrs) throws BadLocationException {

      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.replace(offset, offset + length, text);

      if (parse(sb.toString())) {
         super.replace(fb, offset, length, text, attrs);
      } else {
         JOptionPane.showMessageDialog(null, "Masukan Angka", "ERROR", JOptionPane.ERROR_MESSAGE);
      }

   }

   @Override
   public void remove(DocumentFilter.FilterBypass fb, int offset, int length)
         throws BadLocationException {
      Document doc = fb.getDocument();
      StringBuilder sb = new StringBuilder();
      sb.append(doc.getText(0, doc.getLength()));
      sb.delete(offset, offset + length);

      if (parse(sb.toString())) {
         super.remove(fb, offset, length);
      } else {
         super.remove(fb, offset, length);
      }

   }
}