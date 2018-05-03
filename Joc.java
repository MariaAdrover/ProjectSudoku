package projectsudoku;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Joc extends JFrame implements ActionListener, ComponentListener {

    private Container contenedor;
    private Cronometre crono;
    private GridBagConstraints settings;
    private JButton start;
    private Semafor semafor;
    private Taulell taulell;

    public Joc() {
        super("ProjectSudoku");
        this.setSize(1300, 810);
        //this.setMinimumSize(new Dimension(950, 620)); // tamaño mínimo
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addComponentListener(this);

        this.contenedor = this.getContentPane();
        this.contenedor.setLayout(new GridBagLayout());
        this.settings = new GridBagConstraints();
        this.settings.fill = 1; // REVISAR -------------------------------------

        this.addNewTaulell();
        this.addCrono();
        this.addBotonStart();
        this.addSemafor();
        this.crono.startCrono();
        this.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        this.iniciarNouJoc();
    }

    @Override
    public void componentResized(ComponentEvent e) {
        int width = this.getWidth();
        this.taulell.resizeText(width);
        this.crono.setFont(new Font("Courier New", Font.BOLD, width / 25));
        this.start.setFont(new Font("Courier New", Font.BOLD, width / 25));
        this.semafor.setFont(new Font("Courier New", Font.BOLD, width / 25));
        this.getContentPane().revalidate();
    }

    @Override
    public void componentMoved(ComponentEvent e) {
    }

    @Override
    public void componentShown(ComponentEvent e) {
    }

    @Override
    public void componentHidden(ComponentEvent e) {
    }
    
    public void pararCrono() {
        this.crono.stopCrono();
    }

    public void setEstatSemafor(int estat) {
        switch (estat) {
            case -1:
                this.semafor.setEstatError();
                break;
            case 0:
                this.semafor.setEstatNoError();
                break;
            case 1:
                this.semafor.setEstatSolucionat();
                break;
        }
    }

    private void addBotonStart() {
        this.start = new JButton("Nueva Partida");
        this.start.setBackground(new Color(255, 153, 51));
        this.setBasicConstraints(3, 1, 1, 1, 0.15, 0.33);
        this.start.addActionListener(this);
        this.contenedor.add(this.start, this.settings);
    }

    private void addCrono() {
        this.crono = new Cronometre();
        this.setBasicConstraints(3, 0, 1, 1, 0.15, 0.33);
        this.settings.insets = new Insets(20, 0, 0, 20);
        this.contenedor.add(crono, this.settings);
    }

    private void addNewTaulell() {
        this.taulell = new Taulell(this);
        this.setBasicConstraints(0, 0, 3, 3, 0.85, 1);
        this.settings.insets = new Insets(20, 20, 20, 20);
        this.contenedor.add(this.taulell, this.settings);
    }

    private void addSemafor() {
        this.semafor = new Semafor();
        this.setBasicConstraints(3, 2, 1, 1, 0.15, 0.33);
        this.settings.insets = new Insets(20, 0, 20, 20);
        this.contenedor.add(this.semafor, this.settings);
    }

    private void iniciarNouJoc() {
        // Llevar es taulell
        this.contenedor.remove(this.taulell);

        // Crear-ne un de nou i afegir-lo al contenidor
        this.addNewTaulell();
        int width = this.getWidth();
        this.taulell.resizeText(width);
        
        // Crono
        this.contenedor.remove(this.crono);
        this.addCrono();// --------------CONTINUAR--------------------------------
        this.crono.resi

        // Revalidar i repintar es JFrame
        this.revalidate();
        this.repaint();

        // Actualitzar estat del semàfor
        this.semafor.setEstatNoError();
        
        // Iniciar el cronómetre
        //this.crono.startCrono();
    }

    private void setBasicConstraints(int x, int y, int ancho, int alto, double pesoX, double pesoY) {
        this.settings.gridx = x;
        this.settings.gridy = y;
        this.settings.gridwidth = ancho;
        this.settings.gridheight = alto;
        this.settings.weightx = pesoX;
        this.settings.weighty = pesoY;
    }
}
