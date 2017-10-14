package com.example.jmush.android_session16_assignment_162;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    TextView tvRead;
    EditText etWrite;
    Button add, delete;
    static String FILENAME = "test.txt";
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initID();
        file = new File(getFilesDir(), FILENAME);
        try {
            if (file.createNewFile()) {
                Toast.makeText(getApplicationContext(), "File Created",
                        Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = etWrite.getText().toString();
                etWrite.setText("");

                readFromFile rf = new readFromFile(file);
                rf.execute(value);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                tvRead.setText(" ");

            }
        });

    }

    private void initID() {

        tvRead= (TextView) findViewById(R.id.tvRead);
        etWrite= (EditText) findViewById(R.id.etWrite);
        add= (Button) findViewById(R.id.btnAdd);
        delete= (Button) findViewById(R.id.btnDelete);
    }

    private class readFromFile extends AsyncTask<String, Integer, String > {

        // static String FILENAME = "test.txt";
        File f;

        public readFromFile(File f) {
            super();
            this.f = f;
            // TODO Auto-generated constructor stub
        }

        @Override
        protected String doInBackground(String... str) {
            String enter = "\n";
            FileWriter writer = null;
            try {
                writer = new FileWriter(f,true);
                writer.append(str[0].toString());
                writer.append(enter);
                writer.flush();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return null;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String name = "";
            StringBuilder sb = new StringBuilder();
            FileReader fr = null;

            try {
                fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                while ((name = br.readLine()) != null) {
                    sb.append(name);
                    sb.append("\n");
                }
                br.close();
                fr.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            tvRead.setText(sb.toString());
        }

    }

}
