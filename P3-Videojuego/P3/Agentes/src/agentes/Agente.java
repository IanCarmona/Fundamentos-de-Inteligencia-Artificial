/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import static java.lang.Thread.sleep;
import javax.swing.JFrame;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.HashMap;


/**
 *
 * @author ianca
 */
public class Agente extends Thread
{
    String nombre;
    int i;
    int j;
    ImageIcon icon, ship;
    ImageIcon mother; 
    ImageIcon migaja; 
    ImageIcon img;
    HashMap <Integer, ImageIcon> cluster = new HashMap<>(); 
    int[][] matrix;
    JLabel tablero[][];
    JLabel casillaAnterior;
    Random aleatorio = new Random(System.currentTimeMillis());
    int objEnc;
    int obj;
    int  Camino; 
    int xDir; 
    int yDir; 
    Agente partner;
    int previus_row; 
    int previus_col; 
    boolean sample=false;
    Frame cont;
   

    public Agente(String nombre, ImageIcon icon, int[][] matrix, JLabel tablero[][], ImageIcon ship, Frame cont ) //HashMap<Integer, ImageIcon>cluster 
    {
        this.nombre = nombre;
        this.icon = icon;
        this.ship = ship;
        this.matrix = matrix;
        this.tablero = tablero;
        this.cluster=cluster; 

        this.i = aleatorio.nextInt(matrix.length);
        this.j = aleatorio.nextInt(matrix.length);
        tablero[i][j].setIcon(icon);
    }
   
    public void Encontrar_camino(){
        
         sample = true;

        while( true ) {

            int YCam = yDir - i;
            int XCam = xDir - j;
            
            if( YCam == 0 && XCam == 0 ) {
                matrix[i][j] = 2;
                break;
            }
        //Verificamos que en le plano x como y el agente no choque con un obstaculo 
            while( (yDir - i) > 0 ) {

                casillaAnterior = tablero[i][j];
                if( matrix[i+1][j] == 0 || matrix[i+1][j] == 2 || matrix[i+1][j] == 4 ) {

                    previus_row = i;
                    previus_col = j;
                    i += 1;
                    matrix[i][j] = 4;
                    actualizarPosicion();

                    try{
                        sleep(100+aleatorio.nextInt(100));
                     }
                     catch (Exception ex){
                         ex.printStackTrace();
                     }

                }
                else {
                    break;
                }

            }
            //Se realizan los mismos pasos anteriores, pero ahora considerando las posiciones que ya hemos pasado 
            while( (xDir - j) > 0 ) {

                casillaAnterior = tablero[i][j];
                if(  matrix[i][j+1] == 0 || matrix[i][j+1] == 2 || matrix[i][j+1] == 4 ) {
                    
                    previus_row = i;
                    previus_col = j;
                    j += 1;
                    matrix[i][j] = 4;
                    actualizarPosicion();

                    try{
                        sleep(100+aleatorio.nextInt(100));
                     }
                     catch (Exception ex){
                         ex.printStackTrace();
                     }

                }
          
                else {
                    break;
                }

            }

            while( (yDir - i) < 0 ) {

                casillaAnterior = tablero[i][j];
        
                if( matrix[i-1][j] == 0 || matrix[i-1][j] == 2 || matrix[i-1][j] == 4 ) {

                    previus_row = i;
                    previus_col = j;
                    i -= 1;
                    matrix[i][j] = 4;
                    actualizarPosicion();

                    try{
                        sleep(100+aleatorio.nextInt(100));//Define la velocidad con la que se mueven
                     }
                     catch (Exception ex){
                         ex.printStackTrace();
                     }

                }
                
                else {
                    break;
                }

            }

            while( (xDir - j) < 0 ) {

                casillaAnterior = tablero[i][j];
                if( matrix[i][j-1] == 0 || matrix[i][j-1] == 2 || matrix[i][j-1] == 4 ) {
                    
                    previus_row = i;
                    previus_col = j;
                    j -= 1;
                    matrix[i][j] = 4;
                    actualizarPosicion();

                    try{
                        sleep(100+aleatorio.nextInt(100));
                     }
                     catch (Exception ex){
                         ex.printStackTrace();
                     }

                }
                else {
                    break;
                }

            }

        }

        Camino ++;
        objEnc --;
        System.out.println("Objetivos Logrados!!!");

    }

    //FunciÃ³n para recoger objetos

    public void follow() {

        matrix[i][j] = 0;

        int last_movement = 0;

        while( true ) {

            casillaAnterior = tablero[i][j];
            previus_row = i;
            previus_col = j;

            //Arriba
            if( matrix[i - 1][j] == 4 &&  i > 0 ) {

                i--;
                matrix[i][j] = 0;
                actualizarPosicion();
                last_movement = 1;

                try{
                    sleep(100+aleatorio.nextInt(100));
                 }
                 catch (Exception ex){
                     ex.printStackTrace();
                 }
                
            }
            
            //Abajo
            else if( matrix[i + 1][j] == 4 && i <= matrix.length - 2 ) {

                i++;
                matrix[i][j] = 0;
                actualizarPosicion();
                last_movement = 2;

                try{
                    sleep(100+aleatorio.nextInt(100));
                 }
                 catch (Exception ex){
                     ex.printStackTrace();
                 }

            }

            //Derecha
            else if( matrix[i][j + 1] == 4 && j <= matrix.length - 2 ) {

                j++;
                matrix[i][j] = 0;
                actualizarPosicion();
                last_movement = 3;

                try{
                    sleep(100+aleatorio.nextInt(100));
                 }
                 catch (Exception ex){
                     ex.printStackTrace();
                 }

            }

            //Izquierda
            else if( matrix[i][j - 1] == 4 &&  j > 0 ) {

                j--;
                matrix[i][j] = 0;
                actualizarPosicion();
                last_movement = 4;

                try{
                    sleep(100+aleatorio.nextInt(100));
                 }
                 catch (Exception ex){
                     ex.printStackTrace();
                 }
                
            }

            else {

                if( last_movement == 1 ) {
                    i--;
                    break;
                }

                else if( last_movement == 2 ) {
                    i++;
                    break;
                }

                else if( last_movement == 3 ) {
                    j++;
                    break;
                }

                else if( last_movement == 4 ) {
                    j--;
                    break;
                }

            }

        }

    }

    //Funcion principal
    public void run(){

        int next_move_row = 0;
        int next_move_col = 0;

        while(true){

            casillaAnterior = tablero[i][j];
            previus_row = i;
            previus_col = j;

            //Obtener el movimiento aleatorio evitando las diagonales 
            int flag = 1;
            while(flag == 1) {
                next_move_row = aleatorio.nextInt((1 - (-1)) + 1) + (-1);
                next_move_col = aleatorio.nextInt((1 - (-1)) + 1) + (-1);
                if( ( next_move_col == 0 && ( next_move_row == 1 || next_move_row == -1 ) ) || ( next_move_row == 0 && ( next_move_col == 1 || next_move_col == -1 ) )) {
                    flag = 0;
                }
            }

            //Evitamos que se salga de los bordes o intente cruzar obstaculos
            if( ( i > matrix.length-2 || matrix[i+1][j] == 1 ) && next_move_row == 1 ) {
                next_move_row = -1;
                next_move_col = 0;
            }

            if( ( i < 1 || matrix[i-1][j] == 1 ) && next_move_row == -1 ){
                next_move_row = 1;
                next_move_col = 0;
            }
            
            if( ( j > matrix.length-2 || matrix[i][j+1] == 1 ) && next_move_col == 1 ) {
                next_move_col = -1;
                next_move_row = 0;
            }

            if( ( j < 1 || matrix[i][j-1] == 1 ) && next_move_col == -1 ) {
                next_move_col = 1;
                next_move_row = 0;
            }
            if( ( i < matrix.length - 1 && i > 1 ) && (matrix[i+1][j] == 1 && matrix[i-1][j] == 1) ) next_move_row = 0;
         
            if( ( j < matrix.length - 1 && j > 1 ) && (matrix[i][j+1] == 1 && matrix[i][j-1] == 1)  ) next_move_col = 0;

            if( ( i == matrix.length -1 && matrix[i-1][j] == 1 ) || ( i == 0 && matrix[i+1][j] == 1 ) ) next_move_row = 0;
            if( ( j == matrix.length -1 && matrix[i][j-1] == 1 ) || ( j == 0 && matrix[i][j+1] == 1 ) ) next_move_col = 0;

            //Realizamos los movimientos
            i += next_move_row;
            j += next_move_col;

            //Si encuentra objetivo ejecutar el seguir el objetivo 
            if( matrix[i][j] == 4 ) {
 
                actualizarPosicion();
                follow();
            }

            //Atrapar los objetos 
            if( matrix[i][j] % 3 == 0 && matrix[i][j] > 0 ) {
                System.out.println( matrix[i][j] );
                objEnc++;
                matrix[i][j] -= 3;
                actualizarPosicion();
                Encontrar_camino();
                sample = false;
            } 

            else {
                actualizarPosicion();
            }

            if( (Camino  + partner.Camino )  == obj && objEnc == 0) {
                JOptionPane.showMessageDialog(new JFrame(), "Se ha rescatado a nemo! ", "Finalizo el juego", JOptionPane.WARNING_MESSAGE);
                break;
            } 
    
            try{
               sleep(100+aleatorio.nextInt(100));
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }            
    }

    public synchronized void actualizarPosicion(){
        casillaAnterior.setIcon(null); // Elimina su figura de la casilla anterior
        
        //Colocar las migajas 
        if( sample ) {

            casillaAnterior.setIcon(migaja);

        }
        if( matrix[previus_row][previus_col] == 2 ) {

            casillaAnterior.setIcon( mother ); 

        }

        if( matrix[previus_row][previus_col] % 3 == 0 && matrix[previus_row][previus_col] > 0 ) {
            
            Integer matrix_value = matrix[previus_row][previus_col];
            casillaAnterior.setIcon( cluster.get( matrix_value ) ); 

        } 

        tablero[i][j].setIcon(icon);
    } 
    private void samplesEmpty()
    {
        //ImageIcon img = new ImageIcon("img/Burro1.gif");
        //String answer="<html><body><p>Gracias por salvar a la princesa!!</p></body></html>";
        //int javax.swing;
        JOptionPane.showConfirmDialog(null, "MÃ©todo con 2 parÃ¡metros");
        //JOptionPane.showConfirmDialog(new JLabel(answer, img, JLabel.RIGHT),"", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE); trows HeadlessException;
        //JOptionPane.showConfirmDialog(cont, new JLabel(answer, img, JLabel.RIGHT),"No hay mas princesas", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        
        /*ImageIcon img = new ImageIcon("imagenes/byeWalle.gif");
        String answer="<html><body><p>No hay mÃ¡s plantas.</p> <p>Se encontro un total de "+wE.contadorS+" botas.</p> <p>Gracias por salvarnos!!</p></body></html>";
        JOptionPane.showConfirmDialog(cont, new JLabel(answer, img, JLabel.RIGHT),"No hay mas rupias", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE); */
        System.exit(0);
    }
}