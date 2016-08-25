package com.example.estudiante.myapplication;

/**
 * Created by estudiante on 25/08/16.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;



public class VistaJuego extends View {

    private Activity a;
    int contSegundos = 30;
    public boolean hiloestado = true;
    private int contPuntos = 0, contVidas = 5;

    private Numeros numero1,numero2,numero3;

    private Bitmap bmpFondo,bmpFondoFinal;

    private static final long RATE = 50;

    private int mHeight, mWidth;


    private TareaAsincrona gameThread;
    private int playerSize = 120;
    private int x=0,y=0;
    private int xSpeed, ySpeed;
    private int xMax, yMax;
    private int speed=1;


    public VistaJuego(Context context) {
        super(context);


        try {
            a = (Activity) context;

            numero1 = new Numeros(BitmapFactory.decodeResource(getResources(), R.mipmap.uno), 0, 0);
            numero2 = new Numeros(BitmapFactory.decodeResource(getResources(), R.mipmap.dos), 0, 0);
            numero3 = new Numeros(BitmapFactory.decodeResource(getResources(), R.mipmap.tres), 0, 0);
//            bmpFondo = BitmapFactory.decodeResource(getResources(), R.mipmap.level1);

        } catch (Exception e) {

        }
    }
    @Override
    protected void onSizeChanged(int a, int b, int c, int d) {
        try {


            mHeight = getHeight() / 6;
            mWidth = getWidth() / 6;

//            bmpFondoFinal = Bitmap.createScaledBitmap(bmpFondo, getWidth(), getHeight(), true);

            numero1.setX(20);
            numero1.setImgFinalTamaño(mWidth / 3, mHeight / 3);

            numero2.setX((getWidth() / 5) * 4);
            numero2.setImgFinalTamaño(mWidth / 3, mHeight / 3);

            numero3.setX((getWidth() / 5) * 3);
            numero3.setImgFinalTamaño(mWidth / 3, mHeight / 3);


            gameThread=new TareaAsincrona();
            gameThread.execute();



        } catch (Exception e) {

        }
    }


    @Override
    protected void onDraw(Canvas canvas) {

        try {

            if (contVidas == 1) {
                terminarJuego(true);

            }

            canvas.drawBitmap(bmpFondoFinal, 0, 0, null);
            Paint pincel = new Paint();

            numero1.controlador(getHeight(), numero1.getY(), numero1.getX(), (int)numero1.getX(), (int)numero1.getY());
            canvas.drawBitmap(numero1.getImgFinal(), numero1.getX(), numero1.getY(), null);
            //canvas.translate(mosco.getX(),mosco.getY());

            numero2.controlador(getHeight(), numero2.getY(), numero1.getX(), (int)numero2.getX(), (int)numero2.getY());
            canvas.drawBitmap(numero2.getImgFinal(), numero2.getX(), numero2.getY(), null);

            numero3.controlador(getHeight(), numero3.getY(), numero3.getX(), (int)numero3.getX(), (int)numero3.getY());
            canvas.drawBitmap(numero3.getImgFinal(), numero3.getX(), numero3.getY(), null);


            contPuntos = contPuntos + numero1.getPunto();

            contVidas = contSegundos;
            postInvalidateDelayed(RATE);







        } catch (Exception e) {
            Log.d("Los datos", e.toString());
        }

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if ((keyCode == KeyEvent.KEYCODE_BACK)){
            hiloestado=false;
            gameThread.onCancelled();
            a.finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void terminarJuego(boolean bandera) throws InterruptedException {
        try {
            hiloestado = false;
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }

          //  Intent i = new Intent(a, Ganar.class);
            //i.putExtra("puntos",Integer.toString(contPuntos));
           // a.startActivity(i);
            //a.finish();

        } catch (Exception e) {

        }
    }

    class TareaAsincrona extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                for (int i = 30; i > 0; i--) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    contSegundos = i;

                    if (hiloestado == false) {
                        Thread.interrupted();
                        break;
                    }
                    if(isCancelled()) {
                        Thread.interrupted();
                        break;
                    }
                    if(a.hasWindowFocus()==false){
                        Thread.interrupted();
                        a.finish();
                        break;
                    }
                }
            } catch (Exception e) { }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if(result){}

        }

        @Override
        protected void onCancelled() {
            hiloestado=false;
        }

        public void execute() {
        }
    }


}