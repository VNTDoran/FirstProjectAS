package tn.rnu.isi.hello;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    Button mybtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView mytext = findViewById(R.id.titleUsername);
        TextView mytext2 = findViewById(R.id.profileName);
        TextView mytext3 = findViewById(R.id.profileEmail);
        TextView mytext4 = findViewById(R.id.profilePassword);

        mybtn = (Button) findViewById(R.id.button3);
        Bundle data = getIntent().getExtras();
        mytext.setText(data.getString("name"));
        mytext2.setText(data.getString("name"));
        mytext3.setText(data.getString("mail"));
        mytext4.setText(data.getString("password"));

        mybtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);

                startActivity(intent);


            }
        });
    }
}