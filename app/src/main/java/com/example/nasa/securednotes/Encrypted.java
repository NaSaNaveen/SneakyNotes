package com.example.nasa.securednotes;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.print.PrintAttributes;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Encrypted extends AppCompatActivity
{

    Context context = this;
    Button decrypit;
    TextView econtent;
    TextView msg;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypted);

        decrypit=(Button)findViewById(R.id.decrypit);
        econtent=(TextView)findViewById(R.id.econtent);
        msg = (TextView)findViewById(R.id.message);

        final Intent i = getIntent();
        final String x = i.getExtras().getString("key");
        ContentAdapter obj = new ContentAdapter();
        value = obj.getContent();
        System.out.println("Bow "+obj.getContent());


        decrypit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View view)
            {
//                Toast.makeText(Encrypted.this, "Decrypt", Toast.LENGTH_SHORT).show();

                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.decryptpassworddialog);
                dialog.setTitle("Enter Password");

                final EditText pass = (EditText)dialog.findViewById(R.id.password);

                Button ok = (Button)dialog.findViewById(R.id.done);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        String p = pass.getText().toString();
                        if(p.equals("123"))
                        {
                            int n = i.getExtras().getInt("dkey");
//
                            int encrypt=0;
                            String c="";
                            int length = x.length();
                            for(int i = 0;i<length;i++)
                            {
                                encrypt = (int)x.charAt(i)-n;
                                c = c+(char)encrypt;
                                System.out.println(c);
                            }
                            econtent.setText(c);
                            dialog.dismiss();
                        }

                        else if(p.equals(""))
                        {
                            Toast.makeText(Encrypted.this, "Enter Password", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            Toast.makeText(Encrypted.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });

//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Encrypted.this);
//                alertDialog.setTitle("Enter Password");
//
//                final EditText input = new EditText(Encrypted.this);
//                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
//                        LinearLayout.LayoutParams.WRAP_CONTENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT);
//
//                input.setLayoutParams(lp);
//                alertDialog.setView(input);
//
//                alertDialog.setPositiveButton("YES",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog,int which)
//                            {
////                                Intent myIntent1 = new Intent(view.getContext(), Encrypted.class);
////                                startActivityForResult(myIntent1, 0);
//                                String Pass = input.getText().toString();
//                                if(Pass.equals("123"))
//                                {
//                                    int n = i.getExtras().getInt("dkey");
//
//                                    int encrypt=0;
//                                    String c="";
//                                    int length = x.length();
//                                    for(int i = 0;i<length;i++)
//                                    {
//                                        encrypt = (int)x.charAt(i)-n;
//                                        c = c+(char)encrypt;
//                                        System.out.println(c);
//
//                                    }
//                                    econtent.setText(c);
////                                    System.out.println(value+"Bow");
////                                    msg.setVisibility(View.VISIBLE);
////                                    econtent.setVisibility(View.GONE);
//                                    Toast.makeText(getApplicationContext(),"Password Matched", Toast.LENGTH_SHORT).show();
//                                }
//                                else
//                                {
//                                    Toast.makeText(Encrypted.this, "Enter Valid Password", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
////                Toast.makeText(Encrypted.this, value, Toast.LENGTH_SHORT).show();
//                alertDialog.show();
                dialog.show();
            }
        });

        econtent.setText(x);
    }
}
