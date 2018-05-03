package projectsudoku;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;

public class Cronometre extends JButton {

    int hores;
    int minuts;
    int segons;
    java.util.Timer timer;

    public Cronometre() {
        super("00:00:00");
        this.hores = 0;
        this.minuts = 0;
        this.segons = 0;
        this.setBackground(new Color(213, 128, 255));
        //this.setFont(new Font("Courier New", Font.BOLD, 45));
    }

    public void startCrono() {
        this.timer = new java.util.Timer();
        java.util.TimerTask task = new java.util.TimerTask() {
            @Override
            public void run() {
                segons++;
                actualitzarText();
            }

        };

        timer.schedule(task, 1000L, 1000L);
    }
    
    public void stopCrono() {
        this.timer.cancel();
        this.timer.purge();
    }

    private void actualitzarText() {
        if (this.segons > 59) {
            this.segons = 0;
            this.minuts++;

            if (this.minuts > 59) {
                this.minuts = 0;
                this.hores++;
            }

            if (this.hores > 99) {
                this.hores = 0;
            }
        }
        
            //this.setText("0"+hores + ":0" + minuts + ":0" + segons);
        if (this.segons < 10 && this.minuts < 10 && this.segons < 10) {
            this.setText("0"+hores + ":0" + minuts + ":0" + segons);
        } else if (this.segons < 10 && this.minuts < 10 && this.segons > 9) {
            this.setText("0"+hores + ":0" + minuts + ":" + segons);
            
        } else if (this.segons < 10 && this.minuts > 9 && this.segons < 10) {
            
        } else if (this.segons > 9 && this.minuts < 10 && this.segons < 10) {
            
        }
    }
}
