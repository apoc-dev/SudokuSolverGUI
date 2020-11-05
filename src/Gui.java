import java.util.ArrayList;
import javafx.animation.PauseTransition;
import java.util.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.util.Duration;

public class Gui extends Application{
    

    static ArrayList<Rectangle> rects = new ArrayList<>();
    static ArrayList<Text> texts = new ArrayList<>();

    @Override
    public void start(Stage stage) {

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 297, 297);
        

        createBoard();

        
        for (int box = 0; box < rects.size(); box++) {
            pane.getChildren().addAll(rects.get(box), texts.get(box));
        }


        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            
            public void handle(final KeyEvent keyEvent){
                handleEvent(keyEvent);
            }
            
        });


        stage.setTitle("SudokuSolver");
        stage.setScene(scene);
        stage.show();
        
    }

    public void handleEvent(KeyEvent key){
        System.out.println("event triggered");
        callSudokuSolver();
    }

    public void createBoard(){
        for (int x = 0; x < 288; x+=32) {
            for (int y = 0; y < 288; y+=32) {

                Rectangle r = new Rectangle(x, y, 32, 32);
                Text   text   = createText("0");
                text.setX(x);
                text.setY(y+32);
                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);
                r.setOpacity(0.5);
                

                rects.add(r);
                texts.add(text);
            }
        }
    }
    
    public static void setTextOfRect(String s, int pos){
        texts.get(pos).setText(s);
    }

    public static void setColorOfRect(Color col, int pos){
        rects.get(pos).setFill(col);
    }

    private Text createText(String string) {
        Text text = new Text(string);
        text.setBoundsType(TextBoundsType.VISUAL);
        text.setStyle(
                "-fx-font-family: \"Times New Roman\";" +
                "-fx-font-size: 16px;"
        );

        return text;
    }

    private void callSudokuSolver(){
        //test
        int[][] board =
        {{2,0,5,0,0,0,0,0,0},
        {3,0,8,6,0,0,9,0,0},
        {0,0,0,1,0,0,4,0,0},
        {0,0,0,0,5,0,0,1,0},
        {0,0,0,0,9,0,0,2,0},
        {8,7,0,0,2,0,0,0,0},
        {0,0,0,0,8,9,0,0,3},
        {0,0,6,0,0,3,0,0,5},
        {5,0,4,0,0,0,0,0,1}};
        
        
        new Thread() {
            public void run() {
                SudokuSolver sudokuSolver = new SudokuSolver();
                sudokuSolver.startSolving(board);
            }
        }.start();
        
        
        /*
        if(!sudokuSolver.startSolving(board)){
            System.out.println("No solution");
        }else{
            System.out.println("Solved");
        }
        */
    }


    public static void main(String[] args) throws Exception {
        launch();
    }

    
}
