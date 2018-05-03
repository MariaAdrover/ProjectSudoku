package projectsudoku;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Semafor extends JButton {
    public Semafor () {
        super("¡SUERTE!");
        //this.setFont(new Font("Courier New", Font.BOLD, 45));
        this.setBackground(Color.ORANGE);
    }
    
    public void setEstatError() {
            this.setBackground(Color.RED); 
            this.setText("MAL :(");
    }
    
    public void setEstatNoError() {
            this.setBackground(Color.ORANGE); 
            this.setText("VAS BIEN :)");        
    }
    
    public void setEstatSolucionat() {
            this.setBackground(Color.GREEN); 
            this.setText("CONSEGUIDO :D");        
    }
}
