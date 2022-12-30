package tn.rnu.isi.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {
    Button mybtn;
    Button mybtn2;
    ImageButton mybtn3;
    ImageButton mybtn4;
    TextView mytext;
    EditText username;
    EditText password;
    ArrayList<String> DB;
    ProgressBar pb;
    ProgressBar pb2;
    ProgressBar pbmain;
    boolean paused = false;
    boolean testmail = false;
    boolean testpass = false;
    private boolean mPaused;
    private boolean mFinished;
    int x = 0;
    int counter = 0;
    int countermain = 0;


    public void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
        username = (EditText) findViewById(R.id.editTextTextPersonName);
        mybtn = (Button) findViewById(R.id.button);
        mybtn2 = (Button) findViewById(R.id.button2);
        mybtn3 = (ImageButton) findViewById(R.id.button6) ;
        mybtn4 = (ImageButton) findViewById(R.id.button7) ;

        password = (EditText) findViewById(R.id.editTextTextPassword);
        mytext = (TextView) findViewById(R.id.textView5);
        pbmain = (ProgressBar) findViewById(R.id.progressBar3);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb2 = (ProgressBar) findViewById(R.id.progressBar2);
        DB = new ArrayList<>();
        DB.add("ghassen@gmail.com:12312312:ghassen:tmimi");
        DB.add("admin@admin.org:32132132:ghassen:tmimi");

        ContentValues v = new ContentValues();
        Timer timer2 = new Timer();
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {

                    if (!paused) {
                        countermain++;
                        pbmain.setProgress(countermain);
                        if (countermain == 100) {
                            runOnUiThread(new Runnable() {
                                @Override

                                public void run() {
                                    mybtn.setEnabled(false);
                                }
                            });
                        }
                    }

            }


        };

        timer2.schedule(timerTask2, 350, 100);


        try {
            Bundle Data = getIntent().getExtras();
            DB = Data.getStringArrayList("Database");
        }catch (Exception e){
            System.out.println("problem");
        }



                mybtn.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View view) {
                        String UserEmail = username.getText().toString();
                        String Pass = password.getText().toString();
                        mytext.setVisibility(view.INVISIBLE);
                        pb.setVisibility(view.VISIBLE);
                        pb2.setVisibility(view.VISIBLE);

                        if (UserEmail.charAt(0) == '@') {
                            testmail = false;
                        }
                        if (UserEmail.charAt(UserEmail.length() - 1) == '@') {
                            testmail = false;
                        }
                        if (UserEmail.indexOf('@') != -1) {
                            testmail = true;
                        }


                        if (Pass.matches("(0|[1-9]\\d+)") && Pass.length() == 8) {
                            testpass = true;
                        }
                        Timer timer = new Timer();
                        TimerTask timerTask = new TimerTask() {
                            @Override
                            public void run() {



                                counter = counter+1;
                                pb.setProgress(counter);
                                pb2.setProgress(counter);


                                if (counter == 100) {

                                    timer.cancel();




                                     for (int i = 0; i < DB.size(); i++) {
                                     String[] credentials = DB.get(i).split(":");

                                     if (Objects.equals(credentials[1], Pass) && Objects.equals(credentials[0], UserEmail)) {
                                     Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                     intent.putExtra("name", credentials[2]+" "+credentials[3]);
                                         intent.putExtra("mail", credentials[0]);
                                         intent.putExtra("password", credentials[1]);

                                         startActivity(intent);
                                     x = 1;
                                     }
                                     }


                                    if (x == 0) {
                                        pb.setVisibility(view.INVISIBLE);
                                        pb2.setVisibility(view.INVISIBLE);
                                        counter = 0;
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                if (testmail == false) {
                                                    mytext.setVisibility(view.VISIBLE);
                                                    mytext.setText("ERROR, Check Email Format");
                                                } else if (testpass == false) {
                                                    mytext.setVisibility(view.VISIBLE);
                                                    mytext.setText("Password should be 8 numeric numbers");
                                                } else {
                                                    mytext.setVisibility(view.VISIBLE);
                                                    mytext.setText("ERROR, Check credentials");
                                                }
                                            }
                                        });
                                    }
                                }
                            }

                        };
                        timer.schedule(timerTask, 350, 100);

                    }



                });


                mybtn2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view){

                        Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                        Bundle myData = new Bundle();
                        intent.putStringArrayListExtra("Database",DB);
                        startActivity(intent);


                    }

                });
        mybtn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                onResume();


            }

        });
        mybtn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){


                onPause();

            }

        });




    }
    @Override
    protected void onPause() {
        super.onPause();
        paused = true;
    }
    @Override
    protected void onResume() {
        super.onResume();
        paused = false;
    }

}