package miltonsmind.snake;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class ClassicSnake extends AppCompatActivity {

    // Music variables
    private boolean playMusic;
    private MediaPlayer musicPlayer;

    private RelativeLayout classicSnakeLayout;
    private boolean isInitialized;

    // Tracking events
    private GestureDetector gestureDetector;
    private boolean isPaused;

    // Direction variables
    private boolean isGoingLeft = false;
    private boolean isGoingRight = false;
    private boolean isGoingUp = false;
    private boolean isGoingDown = false;

    // Direction Clicks
    private boolean clickLeft;
    private boolean clickRight;
    private boolean clickUp;
    private boolean clickDown;

    // Control Buttons
    private ImageView btnRight, btnLeft, btnDown, btnUp;
    private boolean useButtons;

    // Extras Score
    private int playerScore;

    // COLLISION DETECTION: VERY IMPORTANT
    private boolean gameOver = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_snake);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        View decorView = getWindow().getDecorView();

        // Hide Extras such as activity bar etc
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);

        // If support bar is visable then hide
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        // CHANGE MUSIC!
        // On screen initialize decide on music on or off
        musicOnOff();
        classicSnakeLayout = (RelativeLayout) findViewById(R.id.classic_snake_layout);
        classicSnakeLayout.setBackgroundResource(R.mipmap.background_for_snake);
        classicSnakeLayout.setPaddingRelative(GameSettings.LAYOUT_PADDING, GameSettings.LAYOUT_PADDING,
                GameSettings.LAYOUT_PADDING, GameSettings.LAYOUT_PADDING);
        isInitialized = false;
    }

    // Music on/off method
    private void musicOnOff(){

        // Get mode for this game
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GameSettings.PREFS_NAME, Context.MODE_PRIVATE);

        // Default true, preferences will be created hear and music will play
        playMusic = preferences.getBoolean("PlayMusic", true);
        musicPlayer = MediaPlayer.create(ClassicSnake.this, R.raw.music);

        if(playMusic){

            // Makes music seamless
            musicPlayer.setLooping(true);
            musicPlayer.start();

        } else {
            musicPlayer.stop();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(gestureDetector.onTouchEvent(event)){
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isPaused = true;

        // Release sound resources if paused
        musicPlayer.release();
    }

    // MOVEMENT METHODS

    // On user swipe right
    private void onSwipeRight(){

        if(isGoingRight == false && isGoingLeft == false){

            isGoingRight = true;
            isGoingLeft = false;
            isGoingDown = false;
            isGoingUp = false;
        }

    }

    // On user swipe left
    private void onSwipeLeft(){

        if(isGoingLeft == false && isGoingRight == false){

            isGoingLeft = true;
            isGoingRight = false;
            isGoingDown = false;
            isGoingUp = false;
        }

    }

    // On user swipe down
    private void onSwipeDown(){

        if(isGoingDown == false && isGoingUp == false){

            isGoingDown = true;
            isGoingLeft = false;
            isGoingRight = false;
            isGoingUp = false;
        }

    }

    // On user swipe right
    private void onSwipeUp(){

        if(isGoingUp == false && isGoingDown == false){

            isGoingUp = true;
            isGoingLeft = false;
            isGoingDown = false;
            isGoingRight = false;
        }

    }

    // MOVEMENT CLICKS

    private void clickRight(){

        if(clickRight == false && clickLeft == false){

            clickRight = true;
            clickLeft = false;
            clickDown = false;
            clickUp = false;

        }
    }

    private void clickLeft(){

        if(clickLeft == false && clickRight == false){

            clickLeft = true;
            clickRight = false;
            clickDown = false;
            clickUp = false;

        }
    }

    private void clickDown(){

        if(clickDown == false && clickUp == false){

            clickDown = true;
            clickLeft = false;
            clickRight = false;
            clickUp = false;

        }
    }

    private void clickUp(){

        if(clickUp == false && clickDown == false){

            clickUp = true;
            clickLeft = false;
            clickDown = false;
            clickRight = false;

        }
    }

    // Button initializer
    private void buttonsDirectionInit(){

        btnRight = (ImageView) findViewById(R.id.btn_right);
        btnLeft = (ImageView) findViewById(R.id.btn_left);
        btnDown = (ImageView) findViewById(R.id.btn_down);
        btnUp = (ImageView) findViewById(R.id.btn_up);

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRight();
            }
        });

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLeft();
            }
        });

        btnDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDown();
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickUp();;
            }
        });

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GameSettings.PREFS_NAME, Context.MODE_PRIVATE);
        useButtons = preferences.getBoolean("UseButtonControls", true);

        // Shows button icons
        if(useButtons){

            btnRight.setVisibility(View.VISIBLE);
            btnLeft.setVisibility(View.VISIBLE);
            btnDown.setVisibility(View.VISIBLE);
            btnUp.setVisibility(View.VISIBLE);

        } else {

            btnRight.setVisibility(View.INVISIBLE);
            btnLeft.setVisibility(View.INVISIBLE);
            btnDown.setVisibility(View.INVISIBLE);
            btnUp.setVisibility(View.INVISIBLE);

        }
    }

    // Shake on food eaten or snake bites itself
    private void shake(){

        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        shake.setDuration(GameSettings.SHAKE_DURATION);
        classicSnakeLayout = (RelativeLayout) findViewById(R.id.classic_snake_layout);
        classicSnakeLayout.setBackgroundResource(R.mipmap.background_for_snake);
        classicSnakeLayout.startAnimation(shake);
    }

    // Method to allow a fade in and out every 4 points
    private void fadeAnim(){

        if(playerScore % GameSettings.POINTS_ANIMATION == 0){

            Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
            classicSnakeLayout = (RelativeLayout) findViewById(R.id.classic_snake_layout);
            classicSnakeLayout.setBackgroundResource(R.mipmap.background_for_snake_change);
            classicSnakeLayout.startAnimation(fadeIn);
            fadeIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    Animation fadeOut = AnimationUtils.loadAnimation(ClassicSnake.this, R.anim.fade_out);
                    classicSnakeLayout = (RelativeLayout) findViewById(R.id.classic_snake_layout);
                    classicSnakeLayout.setBackgroundResource(R.mipmap.background_for_snake);
                    classicSnakeLayout.startAnimation(fadeOut);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        }

    }

    // Collision detection method
    private void collide() {

        gameOver = true;
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(GameSettings.PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("Score", playerScore);
        editor.commit();

        /*
        Intent intentScore = new Intent(ClassicSnake.this, ClassicScore.class);
        intentScore.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intentScore);*/

    }

}
