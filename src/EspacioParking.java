import java.awt.*;

public class EspacioParking {
    Parking parking;
    Pantalla pantalla;

    public int x;
    public int y;

    int ancho;
    int alto;
    int valor;

    public int xReal;
    public int yReal;

    final int margen=100;

    Color colorCelda;
    Color colorLinea;

    public EspacioParking(Pantalla pantalla,Parking parking,int x, int y, int valor) {
        this.pantalla = pantalla;
        this.parking = parking;
        this.x = x;
        this.y = y;
        this.valor = valor;

        //this.colorCelda = pantalla.generarColorAleatorio(200,255);
        this.colorCelda = Color.BLACK;
        this.colorLinea=Color.WHITE;
        //this.colorLinea = pantalla.generarColorAleatorio(0,255);

    }

    public void escalar() {
        // Ajustar el tamaño de cada celda considerando el margen
        ancho = (pantalla.getWidth()-margen*2)/parking.ancho;
        alto = (pantalla.getHeight()-margen*2)/parking.alto;

        // Posicionar cada celda considerando el margen inicial
        xReal =(ancho*x)+margen;
        yReal =(alto*y)+margen;
    }

    public void dibujar(Graphics2D g2d){
        escalar();

                g2d.setColor(colorCelda);
                g2d.fillRect(xReal, yReal, ancho, alto); // Cuadrado blanco
                g2d.setColor(colorLinea);
                g2d.drawRect(xReal, yReal, ancho, alto); // Contorno negro


    }
}
