package mulithilos;
/** @author YAMILET */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.Timer;
import java.awt.FontMetrics;

public class AnalogicoClock extends JPanel {
    private final int WIDTH = 400;
    private final int HEIGHT = 400;
    private final int RADIUS = 150;
    private final int STROKE_WIDTH = 3; // Grosor de la circunferencia
    
    public AnalogicoClock() {
       setPreferredSize(new java.awt.Dimension(WIDTH, HEIGHT));
       setBackground(new Color(0, 51, 51)); // Establecer el color de fondo en azul oscuro

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Obtener la hora actual
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, -1); // Restar una hora a la hora actual
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        // Dibujar el marco del reloj
        g2d.setColor(new Color(204, 204, 204)); // Turquesa
        g2d.setStroke(new java.awt.BasicStroke(STROKE_WIDTH));
        g2d.drawOval(WIDTH / 2 - RADIUS, HEIGHT / 2 - RADIUS, 2 * RADIUS, 2 * RADIUS);

        // Dibujar los números en el reloj
        g2d.setColor(new Color(204, 204, 204)); // Gris
        g2d.setFont(g2d.getFont().deriveFont(24f));
        FontMetrics fm = g2d.getFontMetrics();
        for (int i = 1; i <= 12; i++) {
            double angle = Math.PI / 6 * i;
            int x = (int) (WIDTH / 2 + (RADIUS - 30) * Math.sin(angle));
            int y = (int) (HEIGHT / 2 - (RADIUS - 30) * Math.cos(angle));
            int textWidth = fm.stringWidth(String.valueOf(i));
            int textHeight = fm.getHeight();
            g2d.drawString(String.valueOf(i), x - textWidth / 2, y + textHeight / 4);
        }

        // Dibujar las manecillas del reloj
        drawHand(g2d, (hour % 12 + minute / 60.0) * 5, RADIUS * 0.5, 6, new Color(0, 153, 153)); // Turquesa
        drawHand(g2d, minute, RADIUS * 0.8, 4, new Color(0, 153, 153)); // Turquesa
        drawHand(g2d, second, RADIUS * 0.9, 2, new Color(51, 102, 0)); //Verde/ Manecilla de los segundos

        // Dibujar el punto central del reloj
        g2d.setColor(new Color(0, 204, 204)); // Turquesa
        g2d.fillOval(WIDTH / 2 - 5, HEIGHT / 2 - 5, 10, 10);

        // Mostrar la hora actual en formato HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(cal.getTime());
        g2d.drawString(time, WIDTH / 2 - 45, HEIGHT / 2 + RADIUS + 20);
    }

    private void drawHand(Graphics2D g2d, double angle, double length, int width, Color color) {
        int x = (int) (WIDTH / 2 + length * Math.sin(Math.toRadians(angle * 6)));
        int y = (int) (HEIGHT / 2 - length * Math.cos(Math.toRadians(angle * 6)));
        g2d.setColor(color);
        g2d.setStroke(new java.awt.BasicStroke(width));
        g2d.drawLine(WIDTH / 2, HEIGHT / 2, x, y);
    }
   
    //@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        JFrame frame = new JFrame("Reloj Analogico");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        AnalogicoClock clock = new AnalogicoClock();
        frame.add(clock);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

