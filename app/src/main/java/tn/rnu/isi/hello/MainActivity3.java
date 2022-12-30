package tn.rnu.isi.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity3 extends AppCompatActivity {
    EditText username;
    EditText fname;
    EditText lname;
    EditText password;
    EditText reppassword;
    Button mybtn;
    Button mybtn2;
    TextView mytext;
    ArrayList<String> DB;
    boolean testmail = false;
    boolean testpass = false;
    boolean testpass2 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        fname = (EditText)findViewById(R.id.editTextTextPersonName3);
        lname = (EditText)findViewById(R.id.editTextTextPersonName2);
        username = (EditText)findViewById(R.id.editTextTextPersonName8);
        mybtn = (Button)findViewById(R.id.button4);
        mybtn2 = (Button)findViewById(R.id.button5);
        password = (EditText)findViewById(R.id.editTextTextPassword4);
        reppassword = (EditText)findViewById(R.id.editTextTextPassword5);
        mytext=(TextView)findViewById(R.id.textView12);
        Bundle Data = getIntent().getExtras();
        DB = Data.getStringArrayList("Database");
        mybtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String User =  username.getText().toString();
                String Pass =  password.getText().toString();
                String Pass2 =  reppassword.getText().toString();
                String firstname = fname.getText().toString();
                String lastname = lname.getText().toString();
                int x=0;

                if (User.charAt(0) == '@') {
                    testmail = false;
                }
                if (User.charAt(User.length() - 1) == '@') {
                    testmail = false;
                }
                if (User.indexOf('@') != -1) {
                    testmail = true;
                }


                if (Pass.matches("(0|[1-9]\\d+)") && Pass.length() == 8) {
                    testpass = true;
                }
                if (Pass2.matches("(0|[1-9]\\d+)") && Pass.length() == 8) {
                    testpass2 = true;
                }

                for (int i=0 ;i<DB.size();i++)
                            {
                                String[] credentials = DB.get(i).split(":");
                if (Objects.equals(credentials[0], User)) {
                    x=1;
                }
                if (x == 0 && Objects.equals(Pass, Pass2)){
                    Intent intent = new Intent(MainActivity3.this,MainActivity.class);
                    Bundle myData = new Bundle();
                    DB.add(User+":"+Pass+":"+firstname+":"+lastname);
                    intent.putStringArrayListExtra("Database",DB);

                    startActivity(intent);
                }
                if (x == 0 && !Objects.equals(Pass, Pass2)){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                             if (testpass == false) {
                                mytext.setVisibility(view.VISIBLE);
                                mytext.setText("Password should be 8 numeric numbers");
                            }else {
                                mytext.setVisibility(view.VISIBLE);
                                mytext.setText("Password Should Match");
                            }
                        }
                    });
                }
                if (x==1)
                {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (testmail == false) {
                                mytext.setVisibility(view.VISIBLE);
                                mytext.setText("ERROR, Check Email Format");
                            }else {
                                mytext.setVisibility(view.VISIBLE);
                                mytext.setText("Email already exist");
                            }
                        }
                    });
                }
                }



            }
        });

        mybtn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);

                startActivity(intent);


            }
        });
    }
}