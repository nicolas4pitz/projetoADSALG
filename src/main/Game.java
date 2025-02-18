package main;

import javax.swing.*;
import java.awt.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game extends Canvas {
    public static JFrame jframe;

    private JTextField campo1, campo2;
    private JLabel resultado;

    private static int WIDTH = 300;
    private static int HEIGHT = 200;
    private static int SCALE = 1;

    public Game(){
        frameInit();

    }

    public static void main(String[] args) {
        Game game = new Game();
    }

    public void frameInit(){
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        jframe = new JFrame("Space");
        jframe.add(this);
        jframe.setResizable(true);
        jframe.setLayout(new GridLayout(2, 1));



        campo1 = new JTextField();
        campo2 = new JTextField();

        JButton botaoMultiplicador = new JButton("Multiplicar");
        resultado = new JLabel("Resultado");

        botaoMultiplicador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(campo1.getText());
                    double num2 = Double.parseDouble(campo2.getText());
                    double resultadoConta = num1 * num2;
                    resultado.setText("Resultado: " + resultadoConta);
                } catch (Exception ex){
                    resultado.setText("Digite algo válido!");
                }
            }
        });

        jframe.add(campo1);
        jframe.add(campo2);
        jframe.add(botaoMultiplicador);
        jframe.add(resultado);

        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jframe.setVisible(true);
    }
}