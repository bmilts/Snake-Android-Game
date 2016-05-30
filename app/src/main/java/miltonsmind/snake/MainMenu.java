package miltonsmind.snake;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

// Draw layout, full screen mode, set up ad, request ad layout, test ad
// Create image views for game modes
public class MainMenu extends AppCompatActivity {

    private RelativeLayout snakeLayout; // Reference to layout
    private Animation compileAnim; // Animation field
    private AdView adView; // Advertisement field

    private ImageView classicBtn;
    private ImageView noWallsBtn;
    private ImageView bombBtn;
    private TextView titleLeft;
    private TextView titleMiddle;
    private TextView titleRight;

    private ImageView settingsBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        snakeLayout = (RelativeLayout) findViewById(R.id.snake_layout);

        // Set screen to fullscreen and hide action bars
        View decorView = getWindow().getDecorView();

        // Tell system which mode
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;

        decorView.setSystemUiVisibility(uiOptions);

        if (getSupportActionBar() != null) {

            getSupportActionBar().hide();

        }

        // Define our admob advertisment that will display on the screen
        adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);

        // Set unit ID so that when addmob is clicked google adds knows who to send the revenue too
        adView.setAdUnitId(GameSettings.MY_AD_UNIT_ID);

        // Add adview to layout
        snakeLayout.addView(adView);

        // Initiate generic test request to display ad (Tell admob to fill with ads)
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();

        // Load ad
        adView.loadAd(adRequest);

        // Call button animation initialize methods
        initClassic();
        initNoWalls();
        initBomb();
        initTitle();
        initSettings();
    }

    // Initialize classic mode
    private void initClassic() {

        classicBtn = (ImageView) findViewById(R.id.classic);
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_classic_button); // Load animation and how will work on screen
        compileAnim.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION); // Set duration to animation

        // Add listener to animation button
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                // Set up initial animation
                classicBtn.setImageResource(R.mipmap.classic);

                // If classic button has been clicked to select game mode
                classicBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Go to ClassicSnake class
                        Intent intentClassic = new Intent(MainMenu.this, ClassicSnake.class);

                        // Set flags for when button is clicked
                        intentClassic.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentClassic);

                    }
                });

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // Start animation, show button on screen
        classicBtn.startAnimation(compileAnim);
    }

    // No walls mode
    private void initNoWalls() {

        noWallsBtn = (ImageView) findViewById(R.id.no_walls);
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_no_button);
        compileAnim.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);

        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                // Set default image
                noWallsBtn.setImageResource(R.mipmap.no_walls);

                // Set onclick listener
                noWallsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Create intent to link to NoWallsSnake class
                        Intent intentNoWalls = new Intent(MainMenu.this, NoWallsSnake.class);
                        intentNoWalls.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intentNoWalls);

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // Starts animation and sets on clickLister to start game
        noWallsBtn.startAnimation(compileAnim);
    }

    // Game mode Bomb method
    private void initBomb() {

        bombBtn = (ImageView) findViewById(R.id.bomb);
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_bomb_button);
        compileAnim.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);

        compileAnim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                // Set Image
                bombBtn.setImageResource(R.mipmap.bombsnake);
                bombBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent bombSnakeIntent = new Intent(MainMenu.this, BombSnake.class);
                        bombSnakeIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(bombSnakeIntent);

                    }
                });

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        bombBtn.startAnimation(compileAnim);
    }

    private void initTitle(){

        titleLeft = (TextView) findViewById(R.id.snake_left);
        titleMiddle = (TextView) findViewById(R.id.snake_middle);
        titleRight = (TextView) findViewById(R.id.snake_right);


        // Set up and Compile animation for left title
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.back_anim_for_title_left);
        compileAnim.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);

        // Just for text views so code is not needed
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        titleLeft.startAnimation(compileAnim);


        // Set Up and Compile animation for Middle title
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.back_anim_for_title_middle);
        compileAnim.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);

        // Just for text views so code is not needed
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        titleMiddle.startAnimation(compileAnim);

        // Set up and Compile animation for Right title
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.back_anim_for_title_right);
        compileAnim.setDuration(GameSettings.ANIMATION_HIDE_TITLE_DURATION);

        // Just for text views so code is not needed
        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        titleRight.startAnimation(compileAnim);
    }

    // Initialize Settings Mode, Change button settings etc
    private void initSettings(){

        settingsBtn = (ImageView) findViewById(R.id.settings);
        compileAnim = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_settings_button);
        compileAnim.setDuration(GameSettings.ANIMATION_OPEN_BUTTON_DURATION);

        compileAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            // When button pressed animation shown then moves to setting screen
            @Override
            public void onAnimationEnd(Animation animation) {

                settingsBtn.setImageResource(R.mipmap.settings);

                // Set on click listener
                settingsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // When Settings button tapped make sure tap is briefly disabled for animation
                        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                        // Reset settings buttons to all black
                        settingsBtn.setImageResource(R.mipmap.menu_options);
                        classicBtn.setImageResource(R.mipmap.menu_options);
                        noWallsBtn.setImageResource(R.mipmap.menu_options);
                        bombBtn.setImageResource(R.mipmap.menu_options);

                        // Animation to make classic button appear to move
                        Animation animation = AnimationUtils.loadAnimation(MainMenu.this, R.anim.reverse_for_classic_button);
                        animation.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);

                        // Animation to make no walls button appear to move
                        Animation animation2 = AnimationUtils.loadAnimation(MainMenu.this, R.anim.reverse_for_no_button);
                        animation2.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);

                        // Animation to make bomb button appear to move
                        Animation animation3 = AnimationUtils.loadAnimation(MainMenu.this, R.anim.reverse_for_bomb_button);
                        animation3.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);

                        // Animation to make settings button appear to move
                        Animation animation4 = AnimationUtils.loadAnimation(MainMenu.this, R.anim.reverse_for_settings_button);
                        animation4.setDuration(GameSettings.ANIMATION_CLOSE_BUTTON_DURATION);

                        // Animation to make left title text appear to move
                        Animation animationTitleLeft = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_title_left);
                        animationTitleLeft.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);

                        // Animation to make middle title text appear to move
                        Animation animationTitleMiddle = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_title_middle);
                        animationTitleMiddle.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);

                        // Animation to make right title text appear to move
                        Animation animationTitleRight = AnimationUtils.loadAnimation(MainMenu.this, R.anim.anim_for_title_right);
                        animationTitleRight.setDuration(GameSettings.ANIMATION_SHOW_TITLE_DURATION);

                        // Assign Animation functionality to start animation
                        classicBtn.startAnimation(animation);
                        noWallsBtn.startAnimation(animation2);
                        bombBtn.startAnimation(animation3);
                        settingsBtn.startAnimation(animation4);
                        titleLeft.startAnimation(animationTitleLeft);
                        titleMiddle.startAnimation(animationTitleMiddle);
                        titleRight.startAnimation(animationTitleRight);

                        // Add a handler to delay activity start until animation has finished
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                // Start settings activity class
                                Intent settingsIntent = new Intent(MainMenu.this, Settings.class);
                                settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                startActivity(settingsIntent);

                            }
                        }, GameSettings.START_NEW_ACTIVITY_DURATION);
                    }
                });

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        settingsBtn.setAnimation(compileAnim);
    }
}
