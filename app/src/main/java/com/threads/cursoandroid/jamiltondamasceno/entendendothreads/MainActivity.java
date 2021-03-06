package com.threads.cursoandroid.jamiltondamasceno.entendendothreads;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnIniciar;
    private int numero;
    private Handler handler = new Handler();
    private boolean paraExecucao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iniciarThread(View view){
        /*
        MyThread thread = new MyThread();
        thread.start();*/
        paraExecucao = false;
        MyRunnable runnable = new MyRunnable();
        new Thread( runnable ).start();

        btnIniciar = findViewById(R.id.buttonIniciar);
    }

    class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i=0; i <= 15; i++ ){

                if (paraExecucao){
                    return;
                }

                numero = i;
                Log.d("Thread", "contador: " + i );


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        btnIniciar.setText("contador: " + numero );
                    }
                });
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        btnIniciar.setText("contador: " + numero );
//                    }
//                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void paraThread(View view){
        paraExecucao = true;
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            for (int i=0; i <= 15; i++ ){

                Log.d("Thread", "contador: " + i );

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
