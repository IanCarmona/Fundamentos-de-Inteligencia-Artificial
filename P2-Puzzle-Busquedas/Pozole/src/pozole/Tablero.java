/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pozole;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author
 */
public class Tablero extends JFrame {
    private final JButton[][] jBoard = new JButton[4][4];
    private final LinkedHashMap puzzle = new LinkedHashMap();
    private BufferedImage empty;
    private boolean deep = false;

    private final String start = "12C4EDB5AF369870";
    private final String goal = "12C4EDB5F036A987";

    private final JMenuItem solveB = new JMenuItem("Solve BFS");
    private final JMenuItem solveD = new JMenuItem("Solve DFS");

    private final int maxDeep = 100000; // Para limitar la profundidad del árbol

    public Tablero() // Constructor
    {
        initComponents();
    }

    private void initComponents() {
        this.setTitle("8-Puzzle");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        int width = pantalla.width;
        int height = pantalla.height;
        this.setBounds((width - 516) / 2, (height - 563) / 2, 516, 563);

        JMenuBar mainMenu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");

        mainMenu.add(file);
        file.add(solveB);
        file.add(solveD);
        file.add(exit);
        this.setJMenuBar(mainMenu);

        this.setLayout(null);
        this.imagePieces("imagenes/alonenuget.jpg");
        paintPieces();
        exit.addActionListener(evt -> gestionarExit(evt));
        solveB.addActionListener(evt -> whichMethod(evt));
        solveD.addActionListener(evt -> whichMethod(evt));

        // Handle the X-Button
        class MyWindowAdapter extends WindowAdapter {
            @Override
            public void windowClosing(WindowEvent eventObject) {
                goodBye();
            }
        }
        addWindowListener(new MyWindowAdapter());
    }

    private void goodBye() {
        int respuesta = JOptionPane.showConfirmDialog(rootPane, "Are you sure?", "Exit", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    private void gestionarExit(ActionEvent e) {
        goodBye();
    }

    // Parte la imagen en piezas
    private void imagePieces(String pathName) {
        try // Bloque "try" por que hay una tarea de lectura de archivo
        {
            BufferedImage buffer = ImageIO.read(new File(pathName));
            BufferedImage subImage;
            int n = 0;
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++) {
                    subImage = buffer.getSubimage((j) * 125, (i) * 125, 125, 125); // Extrae un fragmento de la imagen
                    String k = goal.substring(n, n + 1);
                    puzzle.put(k, subImage); // Almacena las piezas etiquetándolas con base en el estado final
                    n++;
                }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }

    }

    public void paintPieces() {
        int n = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                String k = start.substring(n, n + 1);
                BufferedImage subImage = (BufferedImage) puzzle.get(k);
                jBoard[i][j] = new JButton();
                jBoard[i][j].setBounds(j * 125, i * 125, 125, 125); // Calcula la posición del botón i,j
                this.add(jBoard[i][j]);
                
                if(!k.equals("0"))jBoard[i][j].setIcon(new ImageIcon(subImage));
                else empty = subImage;
                n++; 
            }

    }

    // Este es el método que realmente busca mediante le técnica en anchura

    private void whichMethod(ActionEvent e) {
        if (e.getSource() == solveD)
            deep = true; // En caso de que se trate de búsqueda en profundidad
        solve();
    }

    private void solve() {
        boolean success = false;
        int deadEnds = 0;
        int totalNodes = 0;
        State startState = new State(start);
        State goalState = new State(goal);
        ArrayDeque queue = new ArrayDeque();
        ArrayList<State> first = new ArrayList();
        ArrayList<State> path = null;
        solveB.setEnabled(false);
        solveD.setEnabled(false);

        first.add(startState);
        queue.add(first);

        // Search loop

        int m = 0;
        long startTime = System.currentTimeMillis();
        while (!queue.isEmpty() && !success && m < maxDeep) {
            int validStates = 0;
            m++;
            // System.out.println("Ciclo " + m);
            ArrayList<State> l = (ArrayList<State>) queue.getFirst();
            // System.out.println("Analizando Ruta de :" + l.size());
            // muestraEstados(l);
            State last = (State) l.get(l.size() - 1);
            // last.show();
            ArrayList<State> next = last.nextStates();
            // System.out.println("Se encontraron " + next.size()+ " estados sucesores
            // posibles");
            totalNodes += next.size();

            queue.removeFirst(); // Se elimina el primer camino de la estrutura

            for (State ns : next) {
                if (!repetido(l, ns)) // Se escribió un método propio para verificar repetidos
                {
                    validStates++;
                    ArrayList<State> nl = (ArrayList<State>) l.clone();
                    if (ns.goalFunction(goalState)) {
                        success = true;
                        path = nl;
                    }
                    nl.add(ns);
                    // muestraEstados(nl);
                    if (deep)
                        queue.addFirst(nl); // Si es en profundidad agrega al principio la nueva ruta
                    else
                        queue.addLast(nl); // Si es en anchura agrega al final
                    // System.out.println("Agregé un nuevo camino con "+nl.size()+ " nodos");
                }
                // else System.out.println("Un nodo repetido descartado");
            }
            if (validStates == 0)
                deadEnds++; // Un callejón sin salida
        }

        if (success) // Si hubo éxito
        {
            long elapsed = System.currentTimeMillis() - startTime;
            if (deep)
                this.setTitle("8-Puzzle (Deep-First Search)");
            else
                this.setTitle("8-Puzzle (Breadth-First Search)");
            JOptionPane.showMessageDialog(rootPane,
                    "Success!! \nPath with " + path.size() + " nodes" + "\nGenerated nodes: " + totalNodes
                            + "\nDead ends: " + deadEnds + "\nLoops: " + m + "\nElapsed time: " + elapsed
                            + " milliseconds",
                    "Good News!!!", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Success!");
            String thePath = "";
            int n = 0;
            int i = startState.getI();
            int j = startState.getJ();
            for (State st : path) {
                st.show();
                if (n > 0)
                    thePath = thePath + st.getMovement();
                n++;
            }
            Executor exec = new Executor(jBoard, i, j, thePath, empty);
            exec.start();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Path not found", "Sorry!!!", JOptionPane.WARNING_MESSAGE);
            System.out.println("Path not found");
        }
    }

    private void muestraEstados(ArrayList<State> ruta) {
        System.out.println("======");
        for (State s : ruta)
            s.show();
        System.out.println("======");

    }

    // Compara para evitar nodos repetidos
    public boolean repetido(ArrayList<State> l, State s) {
        boolean exist = false;
        for (State ns : l) {
            if (ns.isEqual(s)) // Un método propio para compaarar estados
            {
                exist = true;
                break;
            }
        }
        return exist;
    }
}
