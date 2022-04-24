package com.mstc.gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.PlainDocument;
import java.util.Arrays;
import java.util.List;

/**
 * Used to only allow decimal amounts in the fields (speed, angle, distance).
 * +/- Decimals are allowed.
 */
public class FixedSizeDecimalDocument extends PlainDocument {
    private JTextComponent owner;
    private int fixedSize;

    public FixedSizeDecimalDocument(JTextComponent owner, int fixedSize) {
        this.owner = owner;
        this.fixedSize = fixedSize;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {
        List<String> numbers = Arrays.asList("1" , "2" , "3", "4", "5", "6", "7", "8", "9", "0", "-", ".");

        // System.out.println("typed: " + str + ", length: " + getLength());

        if (getLength() + str.length() > fixedSize) {
            str = str.substring(0, fixedSize - getLength());
            // System.out.println("length");
            this.owner.getToolkit().beep();
            return;
        }

        if (!numbers.contains(str)) {
            // System.out.println("not contained");
            this.owner.getToolkit().beep();
            return;
        }

        if (getLength() > 0) { // minus
            try {
                // System.out.println(getText(0, getLength()) + str);
                Double.parseDouble(getText(0, getLength()) + str);

            } catch (NumberFormatException e) {
                // System.out.println("not double");
                // inserted text is not a number
                this.owner.getToolkit().beep();
                return;
            }
        }

        super.insertString(offs, str, a);
    }
}
