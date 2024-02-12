/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package agentes;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author Lizeth - Gonzalo 
 */
public class Escenario extends JFrame
{
    
    private JLabel[][] tablero;     
    private int[][] matrix;
    private final int dim = 15;
    private int NoObj; 
    private int b=-1; 
    private ImageIcon agente1;
    private ImageIcon agente2;
    private ImageIcon obstacleIcon;
    private ImageIcon sampleIcon;
    private ImageIcon actualIcon;
    private ImageIcon motherIcon;
    private ImageIcon migaja;
    /* 
    private ImageIcon cluster1; 
    private ImageIcon cluster2; 
    private ImageIcon cluster3; 
    */
    private Agente sherk;
    private Agente burro;
   
    private final BackGroundPanel fondo = new BackGroundPanel(new ImageIcon("img/Castillo.png"));
    
    private final JMenu settings = new JMenu("Settigs");
    private final JRadioButtonMenuItem obstacle = new JRadioButtonMenuItem("Obstacle");
    private final JRadioButtonMenuItem sample = new JRadioButtonMenuItem("Fiona's");
    private final JRadioButtonMenuItem motherShip = new JRadioButtonMenuItem("Puente");
    private final JRadioButtonMenuItem camino     = new JRadioButtonMenuItem( "Migaja");
    
    public Escenario()
    {
        this.setContentPane(fondo);
        this.setTitle("Agentes");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setBounds(50,50,dim*50+35,dim*50+85);
        initComponents();
    }
        
    private void initComponents()
    {
        ButtonGroup settingsOptions = new ButtonGroup();
        settingsOptions.add(sample);
        settingsOptions.add(obstacle);       
        settingsOptions.add(motherShip);

        
        JMenuBar barraMenus = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenuItem run  = new JMenuItem("Run");
        
        JMenuItem exit   = new JMenuItem("Exit");
              
        this.setJMenuBar(barraMenus);
        barraMenus.add(file);
        barraMenus.add(settings);
        file.add(run);
        file.add(exit);
        settings.add(motherShip);
        settings.add(obstacle);
        settings.add(sample);
        settings.add(camino);
            
        agente1 = new ImageIcon("img/Burro.jpg");
        agente1 = new ImageIcon(agente1.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH));
        
        agente2 = new ImageIcon("img/Shrek.jpg");
        agente2 = new ImageIcon(agente2.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH));
        
        obstacleIcon = new ImageIcon("img/Dragon.jpg");
        obstacleIcon = new ImageIcon(obstacleIcon.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH));
        
        sampleIcon = new ImageIcon("img/Fiona.jpg");
        sampleIcon = new ImageIcon(sampleIcon.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH));
        
        motherIcon = new ImageIcon("img/Puente.PNG");
        motherIcon = new ImageIcon(motherIcon.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH));
        /* 
        cluster3 = new ImageIcon("img/Cluster3.png");
        cluster3 = new ImageIcon(cluster3.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH));
        
        cluster2 = new ImageIcon("img/Cluster2.png");
        cluster2 = new ImageIcon(cluster2.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH));
        
        cluster1 = new ImageIcon("img/Cluster1.jpg");
        cluster1 = new ImageIcon(cluster1.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH));
/* */
        migaja = new ImageIcon("img/migaja.jpeg");
        migaja = new ImageIcon(migaja.getImage().getScaledInstance(50,50,  java.awt.Image.SCALE_SMOOTH));
        /*
        HashMap<Integer, ImageIcon> cluster = new HashMap<>(); 
        cluster.put( 9, cluster3); 
        cluster.put(6, cluster2); 
        cluster.put(3, cluster1); 
         */
        
        
        this.setLayout(null);
        formaPlano();  
        

        exit.addActionListener(evt -> gestionaSalir(evt));
        run.addActionListener(evt -> gestionaRun(evt));
        obstacle.addItemListener(evt -> gestionaObstacle(evt));
        sample.addItemListener(evt -> gestionaSample(evt));
        motherShip.addItemListener(evt -> gestionaMotherShip(evt));
        camino.addItemListener(evt -> gestionacamino(evt));
              
            
        class MyWindowAdapter extends WindowAdapter
        {
            public void windowClosing(WindowEvent eventObject)
            {
		goodBye();
            }
        }
        addWindowListener(new MyWindowAdapter());
        
        // Crea 2 agentes
        sherk = new Agente("Shrek",agente1, matrix, tablero, actualIcon, null); 
        burro = new Agente("Burro",agente2, matrix, tablero, actualIcon, null); 

        sherk.partner = burro;
        burro.partner = sherk; 
        
    }
        
    private void gestionaSalir(ActionEvent eventObject)
    {
        goodBye();
    }
        
    private void goodBye()
    {
        ImageIcon img = new ImageIcon("Burro1.gif");
        int respuesta = JOptionPane.showConfirmDialog(rootPane, new JLabel("¿Desea salir?", img, JLabel.LEFT),"Aviso",JOptionPane.YES_NO_OPTION);
        if(respuesta==JOptionPane.YES_OPTION) System.exit(0);
    }
  
    private void formaPlano()
    {
        tablero = new JLabel[dim][dim];
        matrix = new int[dim][dim];

        int i, j; 

        for(i=0;i<dim;i++)
            for(j=0;j<dim;j++)
            {
                int row = i; 
                int col = j; 
                matrix[i][j]=0;
                tablero[i][j]=new JLabel();
                tablero[i][j].setBounds(j*50+10,i*50+10,50,50);
                tablero[i][j].setBorder(BorderFactory.createDashedBorder(Color.white));
                tablero[i][j].setOpaque(false);
                this.add(tablero[i][j]);
                
                tablero[i][j].addMouseListener(new MouseAdapter() // Este listener nos ayuda a agregar poner objetos en la rejilla
                    {
                        @Override
                        public void mousePressed(MouseEvent e){
                        insertaObjeto(e);
                        if(actualIcon == obstacleIcon) matrix[row][col] = 1;
                        else if(actualIcon == sampleIcon) {
                            matrix[row][col] = 12;
                            NoObj ++;
                            sherk.obj = NoObj * 4;
                            burro.obj = NoObj * 4;
                        }
                        else if(actualIcon == motherIcon) {
                            matrix[row][col] = 2;
                            //Guardamos la posicion de la casa
                            sherk.yDir = row;
                            sherk.xDir = col;

                            burro.yDir = row;
                            burro.xDir = col;
                        };
                    }   
                
                });           
            }
    }
        
    private void gestionaObstacle(ItemEvent eventObject){
        JRadioButtonMenuItem opt = (JRadioButtonMenuItem) eventObject.getSource();
   
        if(opt.isSelected()){
            b=2;    
            actualIcon = obstacleIcon;
        }
        else actualIcon = null;          
    }
    
    private void gestionaSample(ItemEvent eventObject){
        JRadioButtonMenuItem opt = (JRadioButtonMenuItem) eventObject.getSource();
        
        if(opt.isSelected()){
            b=1;
            actualIcon = sampleIcon;
        }
        else actualIcon = null;   
    }

    private void gestionacamino(ItemEvent eventObject){
        JRadioButtonMenuItem opt = (JRadioButtonMenuItem) eventObject.getSource();
        
        if(opt.isSelected()){
            //b=1;
            actualIcon = migaja;
        }
        else actualIcon = null;   
    }
    
    private void gestionaMotherShip(ItemEvent eventObject){
        JRadioButtonMenuItem opt = (JRadioButtonMenuItem) eventObject.getSource();
        
        if(opt.isSelected()){
            b=0;
            actualIcon = motherIcon; 
        }
        else actualIcon = null;    
    }
    private void gestionaRun(ActionEvent eventObject){
        if(!sherk.isAlive()) sherk.start();
        if(!burro.isAlive()) burro.start();
        settings.setEnabled(false);
    }
       
    public void insertaObjeto(MouseEvent e){
        JLabel casilla = (JLabel) e.getSource();
        if(actualIcon!=null) casilla.setIcon(actualIcon);
    }
}
