package com.magadistudio.SwingDemo.FlashCard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class FlashCardApplication {
       public JTextArea question;
       public JTextArea answer;
       public ArrayList<FlashCard> cardList;
       private JFrame frame;


       public FlashCardApplication() {
           frame = new JFrame("FlashCard");
           frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

           //create a Jpanel
           JPanel jPanel = new JPanel();

           //Creating font
           Font greatfont = new Font("San serif ", Font.BOLD, 21);
           question = new JTextArea(6, 20);
           question.setLineWrap(true);
           question.setWrapStyleWord(true);
           question.setFont(greatfont);

           ///Question JscollPane

           JScrollPane qjscollPane = new JScrollPane(question);
           qjscollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
           qjscollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

           /// JscrollPane answer
           answer = new JTextArea(6, 20);
           answer.setLineWrap(true);
           answer.setWrapStyleWord(true);
           answer.setFont(greatfont);

           JScrollPane ajScrollPane = new JScrollPane(answer);
           ajScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
           ajScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


           JButton nextButton = new JButton("Next Card");

           ///Create few alabel

           JLabel jLabel = new JLabel("Questions");
           JLabel jLabel1 = new JLabel("Answer");


           //Instiating the ArrayList
           cardList = new ArrayList<FlashCard>();

           //// Creating Menu Bar
           JMenuBar jMenuBar = new JMenuBar();
           JMenu fileMenu = new JMenu("File");
           JMenuItem newMenuItem = new JMenuItem("New");
           JMenuItem saveMenuItem = new JMenuItem("Save");


           ///EventListener for the MenuBar
           newMenuItem.addActionListener(new NewMenuListner());
           saveMenuItem.addActionListener(new SaveMenuListener());


           /// Adding the menuitem to the file bar
           fileMenu.add(newMenuItem);
           fileMenu.add(saveMenuItem);

           /// Adding the fileMenu to the MenuBar
           jMenuBar.add(fileMenu);


           ///Add component to mainPanel

           // jPanel.add(jMenuBar);
           jPanel.add(jLabel);
           jPanel.add(qjscollPane);

           jPanel.add(jLabel1);
           jPanel.add(ajScrollPane);
           jPanel.add(nextButton);
           nextButton.addActionListener(new NextCardListener());

           //Add to the frame

           frame.setJMenuBar(jMenuBar);
           frame.getContentPane().add(BorderLayout.CENTER, jPanel);
           frame.setSize(500, 600);
           frame.setVisible(true);


       }
         /// NextCardLister class that detials the functionality of the arrayList
        class NextCardListener implements ActionListener {


           @Override
           public void actionPerformed(ActionEvent e) {
               //Create a flash card object
               FlashCard card = new FlashCard(question.getText(), answer.getText());
               cardList.add(card);

               clearCard();
           }

           private void clearCard() {
               question.setText("");
               answer.setText("");
               question.requestFocus();
           }
       }

            class SaveMenuListener implements ActionListener {
               @Override
               public void actionPerformed(ActionEvent e) {
                   FlashCard card = new FlashCard(question.getText(), answer.getText());
                   cardList.add(card);

                   // Create a file dialog with file chooser

                   JFileChooser fileChooser = new JFileChooser();
                   fileChooser.showSaveDialog(frame);
                   saveFile(fileChooser.getSelectedFile());
               }
           }

       private void saveFile(File selectedFile) {
           try
           {
               BufferedWriter writer = new BufferedWriter(new FileWriter(selectedFile));
               Iterator <FlashCard> cardIterator= cardList.iterator();

               while (cardIterator.hasNext())
               {
                  FlashCard card = (FlashCard) cardIterator.next();
                  writer.write(card.getQuestions() + "/");
                  writer.write(card.getAnswer()+ "\n");
               }

              writer.close();

           }catch (Exception e)
           {
               System.out.println("Could not write to file " );
               e.printStackTrace();
           }
       }



}
