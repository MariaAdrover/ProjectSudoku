package projectsudoku;

import java.awt.Font;
import javax.swing.JTextField;

public class Casella extends JTextField {
    private int fila;
    private int columna;

    public Casella(int fila, int columna) {
        super(1);
        this.fila = fila;
        this.columna = columna;
        this.setHorizontalAlignment(CENTER);
        //this.setFont(new Font("Courier New", Font.BOLD, 50));
    }
    
    public int getColumna() {
        return this.columna;
    }    
    
    public int getFila() {
        return this.fila;
    }
}
