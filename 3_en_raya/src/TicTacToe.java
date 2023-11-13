import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  private JButton[] buttons;
  private boolean xTurn;

  public TicTacToe() {
    setLayout(new GridLayout(3, 3));
    buttons = new JButton[9];
    xTurn = true;

    for (int i = 0; i < 9; i++) {
      buttons[i] = new JButton();
      buttons[i].addActionListener(this);
      add(buttons[i]);
    }

    setTitle("Tic Tac Toe");
    setSize(300, 300);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  public static void main(String[] args) {
    new TicTacToe();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton clickedButton = (JButton) e.getSource();
    if (clickedButton.getText().equals("")) {
      if (xTurn) {
        clickedButton.setText("X");
      } else {
        clickedButton.setText("O");
      }
      xTurn = !xTurn;
    }

    if (checkForWin()) {
      JOptionPane.showMessageDialog(this, "You win!");
      System.exit(0);
    }
  }

  private boolean checkForWin() {
    for (int i = 0; i < 3; i++) {
      if (checkRow(i) || checkColumn(i) || checkDiagonal(i)) {
        return true;
      }
    }
    return false;
  }

  private boolean checkRow(int row) {
    return checkThree(row * 3, row * 3 + 1, row * 3 + 2);
  }

  private boolean checkColumn(int col) {
    return checkThree(col, col + 3, col + 6);
  }

  private boolean checkDiagonal(int diag) {
    if (diag == 0) {
      return checkThree(0, 4, 8);
    } else {
      return checkThree(2, 4, 6);
    }
  }

  private boolean checkThree(int a, int b, int c) {
    return buttons[a].getText().equals(buttons[b].getText())
        && buttons[b].getText().equals(buttons[c].getText())
        && !buttons[a].getText().equals("");
  }
}
