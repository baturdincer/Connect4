import javax.swing.*;
import java.awt.*;

public class Board extends JFrame{
    Game game;
    Board(){
        setTitle("Connect 4");
        game= new Game();
        this.setBackground(new Color(15,76,100));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(game);
        pack();
        setVisible(true);
    }


}
