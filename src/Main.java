import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame ventana=new JFrame();
        ventana.setSize(500,500);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);

        Pantalla pantalla=new Pantalla(15,15);
        ventana.add(pantalla);
        pantalla.setSize(500,500);

        while(true){
            pantalla. manejarEventos();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
