package projectsudoku;

import java.awt.Color;

public class CasellaFixa extends Casella {

    public CasellaFixa(int fila, int columna, String valor) {
        super(fila, columna);
        this.setText(valor);
        this.setEditable(false);
        this.setForeground(new Color(0, 204, 102));
        this.setBackground(Color.WHITE);
    }
}
