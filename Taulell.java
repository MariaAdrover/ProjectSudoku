package projectsudoku;

import java.awt.Color;
import static java.awt.Color.BLACK;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Taulell extends JPanel implements ActionListener, DocumentListener, KeyListener {

    private Casella[][] caselles;
    private int casellesCorrectes;
    private Joc joc;

    public Taulell(Joc joc) {
        super();
        this.joc = joc;
        this.caselles = new Casella[9][9];
        this.setLayout(new GridBagLayout());
        this.addSectors();
    }

    public void actionPerformed(ActionEvent e) {
        if (!this.validarText(e.getActionCommand())) {
            ((Casella) e.getSource()).setText("");
        }

        /*if (((Casella) e.getSource()).requestFocusInWindow()) {
            ((Casella) e.getSource()).selectAll();
        }*/
        //this.joc.setEstatSemafor(this.comprovarEstat());
    }
    
    public void resizeText(int width) {
        for (int f = 0; f < 9; f++) {
            for (int c = 0; c < 9; c++) {
                this.caselles[f][c].setFont(new Font("Courier New", Font.BOLD, width / 25));
            }
        }
        
    }

    private void addSectors() {
        String[][] sudoku = (new Sudoku().getRandomSudoku());
        GridBagConstraints settings = new GridBagConstraints();
        settings.weightx = 0.5;
        settings.weighty = 0.5;
        settings.fill = 1; // REVISAR ------------------------------------------
        settings.insets = new Insets(0, 0, 0, 0);
        for (int f = 0; f < 9; f = f + 3) {
            for (int c = 0; c < 9; c = c + 3) {
                JPanel sector = new JPanel(new GridBagLayout());
                sector.setBorder(BorderFactory.createRaisedBevelBorder());
                settings.gridx = c;
                settings.gridy = f;
                settings.gridwidth = 3;
                settings.gridheight = 3;
                this.omplirSector(sector, sudoku, f, c);
                add(sector, settings);
            }
        }
    }

    private boolean comprovarColumna(int fila, int col) {
        for (int f = 0; f < 9; f++) {
            if (f != fila && this.caselles[f][col].getText().equals(this.caselles[fila][col].getText())) {
                return true; // ---------------- valor repetit ---------------->
            }
        }

        return false;
    }

    private int comprovarEstat() {
        if (this.comprovarRepeticio()) {
            return -1; // ------------------- Hi ha fallos -------------------->
        }

        if (this.casellesCorrectes != 81) {
            return 0; // ------------------ No hi ha fallos ------------------->
        }
        this.joc.pararCrono();
        
        return 1; // ------------------- SOLUCIO CORRECTA --------------------->
    }

    private boolean comprovarFila(int fila, int col) {
        for (int c = 0; c < 9; c++) {
            if (c != col && this.caselles[fila][c].getText().equals(this.caselles[fila][col].getText())) {
                return true; // ---------------- valor repetit ---------------->
            }
        }

        return false;
    }

    private boolean comprovarRepeticio() {
        this.casellesCorrectes = 0;
        for (int fila = 0; fila < 9; fila++) {
            for (int col = 0; col < 9; col++) {
                if (!this.caselles[fila][col].getText().equals("")) { // No comprovar ses caselles buides
                    if (this.comprovarFila(fila, col) || this.comprovarColumna(fila, col)
                            || this.comprovarSector(fila, col)) {
                        return true; // ---------- el valor està repetit ---------->
                    } else {
                        this.casellesCorrectes++;
                    }
                }
            }
        }

        return false;
    }

    private boolean comprovarSector(int fila, int col) {
        for (int f = ((fila / 3) * 3); f < (((fila / 3) * 3) + 3); f++) {
            for (int c = ((col / 3) * 3); c < (((col / 3) * 3) + 3); c++) {
                if (!((f == fila) && (c == col))
                        && (this.caselles[f][c].getText().equals(this.caselles[fila][col].getText()))) {
                    return true; // -------------- valor repetit -------------->
                }
            }
        }

        return false;
    }

    private void omplirSector(JPanel sector, String[][] sudoku, int f, int c) {
        GridBagConstraints settings = new GridBagConstraints();
        settings.weightx = 0.5; // REVISAR -------------------------------------
        settings.weighty = 0.5; // REVISAR -------------------------------------
        settings.fill = 1; // REVISAR -------------------------------------
        Casella casella;
        int y = 0;
        for (int fila = f; fila < f + 3; fila++) {
            int x = 0;
            for (int col = c; col < c + 3; col++) {
                settings.gridx = x;
                settings.gridy = y;
                if (sudoku[fila][col].equals("0")) {
                    casella = new CasellaVariable(fila, col);
                    casella.addActionListener(this);
                    casella.addKeyListener(this);
                    casella.getDocument().addDocumentListener(this);
                    this.caselles[fila][col] = casella;
                    sector.add(casella, settings);
                } else {
                    casella = new CasellaFixa(fila, col, sudoku[fila][col]);
                    //casella.addActionListener(this);
                    //casella.addPropertyChangeListener(this);
                    //casella.getDocument().addDocumentListener(this);
                    this.caselles[fila][col] = casella;
                    sector.add(casella, settings);
                }
                x++;
            }
            y++;
        }
    }

    private boolean validarText(String text) {
        if (text.equals("1") || text.equals("2") || text.equals("3")
                || text.equals("4") || text.equals("5") || text.equals("6")
                || text.equals("7") || text.equals("8") || text.equals("9")) {
            return true;
        }

        return false;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        this.joc.setEstatSemafor(this.comprovarEstat());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        this.joc.setEstatSemafor(this.comprovarEstat());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        //this.joc.setEstatSemafor(this.comprovarEstat()); 
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char tecla = e.getKeyChar();
        // Sólo acepta números del 1-9 y la tecla para borrar
        if (((tecla < '1' || tecla > '9') && tecla != '\b') || ((Casella)e.getSource()).getDocument().getLength()>0) {
            e.consume();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char tecla = e.getKeyChar();
        if ((tecla < '1' || tecla > '9') && tecla != '\b') {
            e.consume();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
