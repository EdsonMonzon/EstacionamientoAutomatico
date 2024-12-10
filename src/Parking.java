import java.awt.*;
import java.util.Random;

public class Parking {

    Pantalla pantalla;

    Random random=new Random();
    public int xEntrada;
    public int yEntrada;
    public int xSalida;
    public int ySalida;

    int ancho;
    int alto;
    protected EspacioParking[][]estacionamiento;

    public Parking(Pantalla pantalla, int alto, int ancho) {
        this.pantalla=pantalla;
        this.ancho=ancho;
        this.alto=alto;

        this.estacionamiento=new EspacioParking[alto][ancho];
        generaEstacionamiento();
    }

    public void generaEstacionamiento() {
        for(int i=0;i<alto;i++) {
            for(int j=0;j<ancho;j++) {

                if ((i == 0 || i == alto - 1 || j == 0 || j == ancho - 1) &&
                        !(i == 0 && j == 1) &&
                        !(i == alto - 1 && j == ancho - 2)) {
                    estacionamiento[i][j] = new EspacioParking(pantalla,this,j,i,1); // Espacio para estacionar
                }else{
                    estacionamiento[i][j]=new EspacioParking(pantalla,this,j,i,0);
                }
            }
        }

        int contadorAncho=0;
        int contadorAlto=0;

        int anchoP=1;
        int altoP=2;

        for(int i=2;i<alto-2;i++){
            contadorAlto++;
            contadorAncho=0;
            if(contadorAlto>altoP){
                contadorAlto=0;
            }else {
                for (int j = 2; j < ancho - 2; j++) {
                    if (contadorAncho < anchoP) {
                        estacionamiento[i][j] = new EspacioParking(pantalla, this, j,i, 1);
                        contadorAncho++;
                    } else {
                        contadorAncho = 0;
                    }
                }
            }
        }
    }

    public EspacioParking getSiguienteVacio() {
        int iRandom=random.nextInt(alto);
        int jRandom=random.nextInt(ancho);

        if(estacionamiento[iRandom][jRandom].valor==1){
            return estacionamiento[iRandom][jRandom];
        }
        return getSiguienteVacio();
    }
    public void ocuparLugar(EspacioParking e){
        for(int i=0;i<alto;i++) {
            for(int j=0;j<ancho;j++) {
                if(j==e.x && i==e.y){
                    estacionamiento[i][j].valor=2;
                }
            }
        }
    }
    public void desOcuparLugar(EspacioParking e){
        for(int i=0;i<alto;i++) {
            for(int j=0;j<ancho;j++) {
                if(estacionamiento[i][j].equals(e)){
                    estacionamiento[i][j].valor=1;
                }
            }
        }
    }

    public void dibujar(Graphics2D g2d) {
        for(int i=0;i<alto;i++) {
            for(int j=0;j<ancho;j++) {
                estacionamiento[i][j].escalar();
                if(estacionamiento[i][j].valor!=0) {
                    estacionamiento[i][j].dibujar(g2d);
                }
                if(i==0 && j==1){
                    xEntrada=estacionamiento[i][j].xReal+estacionamiento[i][j].ancho/2;
                    yEntrada=estacionamiento[i][j].yReal+estacionamiento[i][j].alto;
                }
                if(i==alto-1 && j==ancho-2){
                    xSalida=estacionamiento[i][j].xReal+estacionamiento[i][j].ancho/2;
                    ySalida=pantalla.getHeight()+10;
                }
            }
        }
    }
}
