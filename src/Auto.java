import java.awt.*;
import java.util.Random;

class Auto {
    Pantalla pantalla;
    Random random=new Random();
    private int ancho, alto, xReal, yReal;
    private int anchoReal, altoReal;
    private int xObjetivo, yObjetivo;
    private final Color color;
    EspacioParking lugar;

    int espera;
    int contador=0;

    int fase=1;

    int TAMANIO_MAXIMO_ANCHO=1600;
    int TAMANIO_MAXIMO_ALTO=900;

    public Auto(Pantalla pantalla, int xReal, int yReal, Color color) {
        this.pantalla=pantalla;
        this.xReal = xReal;
        this.yReal = yReal;

        colocarTamaño();

        this.color = color;
        lugar=pantalla.parking.getSiguienteVacio();
        pantalla.parking.ocuparLugar(lugar);
    }

    void colocarTamaño(){
        if(35-pantalla.parking.ancho<35-pantalla.parking.alto){
            this.ancho = 35-pantalla.parking.ancho;
            this.alto = 35-pantalla.parking.ancho;
        }else{
            this.ancho = 35-pantalla.parking.alto;
            this.alto = 35-pantalla.parking.alto;
        }
    }

    public void mover() {
        switch (fase) {
            case 1->{
                espera=random.nextInt(1000,10000);
                xObjetivo=lugar.xReal+ lugar.ancho/2;
                yObjetivo=lugar.yReal+ lugar.alto/2;

            }
            case 2->{
                espera=0;
                xObjetivo=pantalla.parking.xSalida;
                yObjetivo=pantalla.parking.ySalida;
            }
        }
        moverHacia(xObjetivo,yObjetivo,espera);
    }
    private void moverHacia(int xObjetivo, int yObjetivo, int espera) {
        if(xReal!=xObjetivo){
            if(xReal<xObjetivo){
                xReal++;
            }else{
                xReal--;
            }
        } else if (yReal!=yObjetivo) {
            if(yReal<yObjetivo){
                yReal++;
            }else{
                yReal--;
            }
        }else{
            contador++;
        }
        if(contador>=espera){
            fase++;
            contador=0;
            if(fase==3){
                pantalla.parking.desOcuparLugar(lugar);
            }
        }
    }

    void escalar(){
        anchoReal=ancho*pantalla.getWidth()/TAMANIO_MAXIMO_ANCHO;
        altoReal=alto*pantalla.getWidth()/TAMANIO_MAXIMO_ANCHO;
    }

    public void dibujar(Graphics2D g2d) {
        if(fase!=3){
            escalar();
            g2d.setColor(Color.BLACK);
            g2d.fillOval(xReal-anchoReal/2-2, yReal-altoReal/2-2, anchoReal+4, altoReal+4);
            g2d.setColor(color);
            g2d.fillOval(xReal-anchoReal/2, yReal-altoReal/2, anchoReal, altoReal);
        }
    }
}