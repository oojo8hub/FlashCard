package com.magadistudio.SwingDemo.FlashCard;

import javax.swing.*;

public class FlashCardOperator {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FlashCardApplication();
            }
        });
    }
}
