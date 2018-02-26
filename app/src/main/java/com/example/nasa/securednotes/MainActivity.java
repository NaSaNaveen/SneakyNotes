package com.example.nasa.securednotes;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    EditText note;
    Button saveit, encryptit;
    String content1,content2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        note=(EditText)findViewById(R.id.notes);
        saveit=(Button)findViewById(R.id.save);
        encryptit=(Button)findViewById(R.id.encrypt);

        saveit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                content1 = note.getText().toString();
                String[] heading= content1.split(" ");
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
                Date now = new Date();
                String fileName = formatter.format(now) + ".txt";//like 2016_01_12.txt

                try
                {
                    File root = new File(Environment.getExternalStorageDirectory()+File.separator+"sneaky");
                    if (!root.exists())
                    {
                        root.mkdirs();
                    }
                    File gpxfile = new File(root, heading[0]+fileName);

                    FileWriter writer = new FileWriter(gpxfile, true);
                    writer.append(content1 + "\n\n");
                    writer.flush();
                    writer.close();

                    Toast.makeText(MainActivity.this, "File Saved", Toast.LENGTH_SHORT).show();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        });

        encryptit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                content2=note.getText().toString();
                Toast.makeText(MainActivity.this, ""+content2, Toast.LENGTH_SHORT).show();

                Random random = new Random();
                int n = 1+random.nextInt(5);
                System.out.println(n+"random");
                int encrypt=0;
                String c="";
                int length = content2.length();
                for(int i = 0;i<length;i++)
                {
                    encrypt = (int)content2.charAt(i)+n;
                    System.out.println("encrypt"+encrypt);
                    c = c+(char)encrypt;

                }
                System.out.println(c+"Decrypt");

                ContentAdapter obj = new ContentAdapter();
                obj.setContent(content2);
                System.out.println(obj.getContent()+"boww");

                Intent i = new Intent(MainActivity.this,Encrypted.class);
                i.putExtra("dkey",n);
                i.putExtra("key",c);
                startActivity(i);
            }
        });
    }
}
