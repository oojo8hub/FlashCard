package com.magadistudio.SwingDemo.FlashCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

public class FlashCardPlayer {
   private   JTextArea display;
   private  JTextArea answer;
   private ArrayList<FlashCard> cardList;
   private Iterator iterator;
   private  FlashCard currentCard;
   private  int currentCardIndex;
   private   JFrame frame;
   private  Boolean showAnswer;
   private JButton displayAnswer;
    public FlashCardPlayer()
    {
       frame = new JFrame("FlashCard Player");
       JPanel mainPanel = new JPanel();
        Font font = new Font("Ornamentum",Font.BOLD, 20);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        display = new JTextArea(10, 20);
        display.setFont(font);

        JScrollPane qJScrollPane = new JScrollPane(display);
        qJScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qJScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        displayAnswer= new JButton("Show Answer");

        answer = new JTextArea(10, 20);
        answer.setFont(font);




          mainPanel.add(qJScrollPane);
          mainPanel.add(displayAnswer);
          displayAnswer.addActionListener(new ShowNextCardListener());
          /// ADD MENUE
           JMenuBar menuBar = new JMenuBar();
           JMenu fileMenu = new JMenu("File");
           JMenuItem loadMenuItem = new JMenuItem("Load Card Set");
           loadMenuItem.addActionListener( new OpenMenuListener());

           fileMenu.add(loadMenuItem);

           menuBar.add(fileMenu);


        /// ADD TO FRAME
         frame.setJMenuBar(menuBar);
         frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
         frame.setSize(640, 500);
         frame.setVisible(true);
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlashCardPlayer();
            }
        });
    }




    // Implemenetation of the ShowNextCardListerner Class
    class ShowNextCardListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (showAnswer)
            {
                display.setText(currentCard.getAnswer());
                displayAnswer.setText("Next Card");
                showAnswer= false;


            }else
            {  /// Show next questions
                if (iterator.hasNext())
                {
                    showNextCard();
                }else
                    /// If arrayList is empty
                {
                    display.setText("That was the last card");
                    displayAnswer.setEnabled(false);

                }
            }
        }
    }

    /// Implementation of the OpenMenuListener Classs
    class OpenMenuListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileOPen = new JFileChooser();
            fileOPen.showOpenDialog(frame);
            loadFile(fileOPen.getSelectedFile());
        }


    }

     ///Implementation of the LoadFile class
    private void loadFile(File selectedFile)
    {
        cardList = new ArrayList<FlashCard>();
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(selectedFile));
            String line =null;
            while ((line= bufferedReader.readLine()) != null)
            {
                makeCard(line);
            }
        }catch (Exception e)
        {
            System.out.println("Couldnt read file");
            e.printStackTrace();
        }
        /// Show the first file
       iterator = cardList.iterator();
        showNextCard();
    }

       /// Implementation of the ShowNextCard
    private void showNextCard() {
        currentCard= (FlashCard)iterator.next();
        display.setText(currentCard.getQuestions());

          displayAnswer.setText("Show Answer");
          showAnswer=true;
    }

     // Implementation of the makeCard class
    private void makeCard(String lineToParse) {
//        String [] result = lineToParse.split("/"); // [question, answer]
//
//        FlashCard card = new FlashCard(result[0], result[1]);
//        cardList.add(card);
//        System.out.println("Made a card");

        StringTokenizer result1 = new StringTokenizer(lineToParse,"/");
        if (result1.hasMoreTokens())
        {
            FlashCard card = new FlashCard(result1.nextToken(), result1.nextToken());
            cardList.add(card);
            System.out.println("Made a card");
        }
    }


}
