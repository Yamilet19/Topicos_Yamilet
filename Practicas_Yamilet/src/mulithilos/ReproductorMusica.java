package mulithilos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class ReproductorMusica extends JFrame {
    private JButton btnPlay;
    private JButton btnPause;
    private JButton btnStop;
    private JButton btnRestart;
    private JLabel lblStatus;
    private Clip clip;
    private String rutaCancion;
    private JProgressBar progressBar;
   
public ReproductorMusica() {
    JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(this);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            rutaCancion = fileChooser.getSelectedFile().getAbsolutePath();
        }

        initComponents();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                detenerReproduccion();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Reproductor de Música/Audio");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

  private void initComponents() {
    // Configurar la interfaz gráfica
        btnPlay = new JButton("Abrir");
        btnPause = new JButton("Pausar");
        btnStop = new JButton("Continuar");
        btnRestart = new JButton("Reiniciar");
        lblStatus = new JLabel("Estado: ");
        lblStatus.setBackground(new Color(0, 0, 51)); // Azul oscuro
        lblStatus.setForeground(Color.WHITE); // Letras blancas
        lblStatus.setOpaque(true); // Hacer que el fondo se pinte con el color especificado

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true); // Mostrar porcentaje en la barra
        progressBar.setBackground(new Color(0, 0, 51)); // Azul oscuro
        progressBar.setForeground(new Color(0, 204, 204)); // Turquesa

        Container contenedor = getContentPane();
        contenedor.setLayout(new BorderLayout());
        contenedor.setBackground(new Color(0, 0, 51));

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(new Color(0, 0, 51)); // Azul oscuro
        panelBotones.add(btnPlay);
        panelBotones.add(btnPause);
        panelBotones.add(btnStop);
        panelBotones.add(btnRestart);

        contenedor.add(panelBotones, BorderLayout.SOUTH);
        contenedor.add(progressBar, BorderLayout.CENTER);
        contenedor.add(lblStatus, BorderLayout.NORTH);

        // Establecer los colores para los botones
        btnPlay.setBackground(new Color(0, 0, 51)); // Azul oscuro
        btnPlay.setForeground(new Color(0, 204, 204)); // Turquesa
        btnPause.setBackground(new Color(0, 0, 51)); // Azul oscuro
        btnPause.setForeground(new Color(0, 204, 204)); // Turquesa
        btnStop.setBackground(new Color(0, 0, 51)); // Azul oscuro
        btnStop.setForeground(new Color(0, 204, 204)); // Turquesa
        btnRestart.setBackground(new Color(0, 0, 51)); // Azul oscuro
        btnRestart.setForeground(new Color(0, 204, 204)); // Turquesa

        // Agregar los listeners de eventos a los botones
        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reproducir();
            }
        });

        btnPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pausar();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                continuar();
            }
        });

        btnRestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reiniciar();
            }
        });

        pack();
    }
  
private void reproducir() {
    detenerReproduccion(); // Detener la reproducción anterior antes de iniciar una nueva

        Thread hiloReproduccion = new Thread(new Runnable() {
            public void run() {
                try {
                    File archivoMusica = new File(rutaCancion);
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoMusica);
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);

                    int duracion = (int) (clip.getMicrosecondLength() / 1000);
                    progressBar.setMaximum(duracion); // Establecer la duración máxima de la barra de progreso

                    clip.start();
                    lblStatus.setText("Estado: Reproducción iniciada");

                    while (clip.isRunning()) {
                        int posicionActual = (int) (clip.getMicrosecondPosition() / 1000);
                        progressBar.setValue(posicionActual); // Actualizar el valor de la barra de progreso
                        Thread.sleep(100); // Esperar un poco antes de volver a actualizar la barra
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        hiloReproduccion.start();
    }

    private void pausar() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            lblStatus.setText("Estado: Reproducción pausada");
        }
    }

    private void continuar() {
        if (clip != null && !clip.isRunning()) { // Comprobar si el clip no se está reproduciendo
            clip.start();
            lblStatus.setText("Estado: Reproducción reanudada");
        }
    }

    private void reiniciar() {
        if (clip != null) {
            clip.stop();
            clip.setFramePosition(0);
            clip.start();
        }
        lblStatus.setText("Estado: Reproducción reiniciada");
    }

    private void detenerReproduccion() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
            lblStatus.setText("Estado: Reproducción detenida");
        }
    }
   
 /*   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
*/
public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ReproductorMusica();
            }
        });
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
