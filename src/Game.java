import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game extends JPanel implements MouseListener {
    private int column;
    private int turn=-1;
    //-1 for red
    //1 for yellow
    private boolean gameover=false;

    private int x;
    int[][] gameboard= new int[8][9];

    Game(){
        setPreferredSize(new Dimension(700,600));
        for (int n=0;n<8;n++){
            for (int m=0;m<9;m++){
                gameboard[n][m]=0;
            }
        }
        addMouseListener(this);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(15,76,100));
        g2d.fillRect(0,0,700,600);
        g2d.setColor(Color.lightGray);
        for (int i=0; i<=6;i++) {
            for (int j=0;j<=5;j++){
                g2d.fillOval((i*100)+25, (j*100)+25, 50, 50);
            }
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        x= getMousePosition().x;
        for (int s=0, a=1;s<=600;s+=100 ,a++){
            if(x>s&&x<s+100){
                column=a;
            }
        }
        for (int j=6, b=1; j>=1;j--,b++){
            if (gameboard[j][column]==0){
                gameboard[j][column]=turn;
                Graphics g= getGraphics();
                if (turn==-1){
                    turn=1;
                    g.setColor(Color.red);
                    g.fillOval(((column-1)*100)+25, ((j-1)*100)+25, 50, 50);
                    g.setColor(Color.black);
                    g.drawOval(((column-1)*100)+25, ((j-1)*100)+25, 50, 50);
                }
                else {
                    turn=-1;
                    g.setColor(Color.yellow);
                    g.fillOval(((column-1)*100)+25, ((j-1)*100)+25, 50, 50);
                    g.setColor(Color.black);
                    g.drawOval(((column-1)*100)+25, ((j-1)*100)+25, 50, 50);
                }
                winchecker();
                break;
            }

        }

    }


    public void winchecker(){
        Graphics winnerpainter= getGraphics();
        winnerpainter.setColor(Color.orange);
        ((Graphics2D) winnerpainter).setStroke(new BasicStroke(4));
        for (int r=1;r<=6;r++){
            for (int c=1;c<=7;c++){
                if(c<5){
                    if (gameboard[r][c]+gameboard[r][c+1]+gameboard[r][c+2]+gameboard[r][c+3]==-4||gameboard[r][c]+gameboard[r][c+1]+gameboard[r][c+2]+gameboard[r][c+3]==4){
                        //System.out.println(gameboard[r][c]+" wins");
                        for (int w=-1;w<3;w++){
                            winnerpainter.drawOval(((w+c)*100)+25, ((r-1)*100)+25, 50, 50);

                        }
                        gameover=true;
                    }
                }
                if(r<4){
                    if (gameboard[r][c]+gameboard[r+1][c]+gameboard[r+2][c]+gameboard[r+3][c]==-4||gameboard[r][c]+gameboard[r+1][c]+gameboard[r+2][c]+gameboard[r+3][c]==4){
                        //System.out.println(gameboard[r][c]+" wins");
                        for (int w=-1;w<3;w++){
                            winnerpainter.drawOval(((c-1)*100)+25, ((r+w)*100)+25, 50, 50);

                        }
                        gameover=true;
                    }
                }
                if (r<4&&c<5){
                    if (gameboard[r][c]+gameboard[r+1][c+1]+gameboard[r+2][c+2]+gameboard[r+3][c+3]==-4||gameboard[r][c]+gameboard[r+1][c+1]+gameboard[r+2][c+2]+gameboard[r+3][c+3]==4){
                        //System.out.println(gameboard[r][c]+" wins");
                        //diagonal
                        for (int w=-1;w<3;w++){
                            winnerpainter.drawOval(((w+c)*100)+25, ((w+r)*100)+25, 50, 50);

                        }
                        gameover=true;
                    }
                }
                if (c>=4&&r<=3){
                    //diagonal
                    if (gameboard[r][c]+gameboard[r+1][c-1]+gameboard[r+2][c-2]+gameboard[r+3][c-3]==-4||gameboard[r][c]+gameboard[r+1][c-1]+gameboard[r+2][c-2]+gameboard[r+3][c-3]==4){
                        System.out.println(gameboard[r][c]+" wins");
                        for (int w=-1;w<3;w++){
                            winnerpainter.drawOval(((c-w-2)*100)+25, ((r+w)*100)+25, 50, 50);

                        }
                        gameover=true;
                    }
                }
                if (gameover){
                    for (int n=0;n<8;n++){
                        for (int m=0;m<9;m++){
                            gameboard[n][m]=2;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
