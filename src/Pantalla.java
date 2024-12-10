import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Pantalla extends JPanel {

    int contador=0;
    Random random = new Random();
    private final ArrayList<Auto> autos = new ArrayList<>();
    public final Parking parking;
    int probabilidad=0;

    int xRandom;
    int yRandom;

    public Pantalla(int alto, int ancho) {
        parking=new Parking(this,alto,ancho);
    }

    public void manejarEventos(){

        if (contador >=probabilidad) {
            randomizarPosicion();
            autos.add(new Auto(this, xRandom, yRandom, generarColorAleatorio(0,255)));
            contador=0;
            probabilidad=random.nextInt(autos.size()+1*200);
        }
        contador++;

        for (Auto auto : autos) {
            auto.mover(); // Actualiza la posición de cada auto
        }
        repaint(); // Redibuja el panel
    }

    public Color generarColorAleatorio(int minimo,int limite) {
        // Generar componentes RGB
        int r = random.nextInt(minimo,limite); // Valores entre 128 y 255
        int g = random.nextInt(minimo,limite);
        int b = random.nextInt(minimo,limite);

        return new Color(r, g, b);
    }


    void randomizarPosicion(){
        if(xRandom==-10){
            xRandom=random.nextInt(getWidth());
            yRandom=-10;
        }else if(yRandom==-10){
            xRandom=getWidth()+10;
            yRandom=random.nextInt(getHeight());
        }else if(xRandom==getWidth()+10){
            xRandom=random.nextInt(getWidth());
            yRandom=getHeight()+10;
        }else{
            xRandom=-10;
            yRandom=random.nextInt(getHeight());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Fondo
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        parking.dibujar(g2d);

        // Dibujar autos
        for (Auto auto : autos) {
            auto.dibujar(g2d);
        }
    }
}