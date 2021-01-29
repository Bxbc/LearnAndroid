package www.bxbc.me.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {

    int player = 0;
    // record the board state
    int[] board = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    // to check the board state
    int[][] winBoard = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void onClick(View view){
        ImageView tictac = (ImageView) view;
        int cuTag = Integer.parseInt(tictac.getTag().toString());
        if(board[cuTag] == -1) {
            tictac.setTranslationY(-1500);
            board[cuTag] = player;
            if (player == 0) {
                tictac.setImageResource(R.drawable.tic);
                player = 1;
            } else {
                tictac.setImageResource(R.drawable.tac);
                player = 0;
            }
            tictac.animate().translationYBy(1500).rotation(3600).setDuration(300);
            int gameState = checkWin(winBoard);
            Log.i("State", Integer.toString(gameState));
            if (gameState != -1) {
                Toast.makeText(this, "The player " + Integer.toString(gameState) + " win!", Toast.LENGTH_LONG).show();
                GridLayout grids = (GridLayout) findViewById(R.id.board);
                for(int i=0;i<grids.getChildCount();i++){
                    ImageView child = (ImageView) grids.getChildAt(i);
                    child.setClickable(false);
                }
            }
        }else if(board[cuTag] == 0) {
            tictac.setImageResource(R.drawable.tic);
        }else{
            tictac.setImageResource(R.drawable.tac);
            }

    }
    // no winner:-1; player0 win:0; player1 win: 1
    public int checkWin(int[][] currentBoard) {
        for (int[] winP:currentBoard) {
            if(board[winP[0]] != -1 && board[winP[0]] == board[winP[1]] && board[winP[1]] == board[winP[2]]) {
                return board[winP[0]];
            }
        }
        return -1;
    }
    // clear the board and reset the board state
    public void playAgain (View view){
        // Button newGame = (Button) findViewById(R.id.button);
        GridLayout grids = (GridLayout) findViewById(R.id.board);
        for(int i=0;i<grids.getChildCount();i++){
            ImageView child = (ImageView) grids.getChildAt(i);
            child.setImageDrawable(null);
            child.setClickable(true);
        }
        for(int i=0;i<board.length;i++){
            board[i] = -1;
        }
        player = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}