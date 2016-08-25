package com.example.estudiante.myapplication;

/**
 * Created by estudiante on 25/08/16.
 */

import android.graphics.Bitmap;
import android.os.Vibrator;
import android.view.View;

/**
 * Created by Majo on 19/08/2016.
 */
public class Numeros {

    private Bitmap img;
    private Bitmap imgFinal;

    private float x;
    private float y;

    private int controlador=(int) (30 * Math.random()) + 1;
    private int punto=0;
    private int vida=0;
    private float retroY;

    public void mosco() {
    }

    public Numeros(Bitmap bmpImage, float x, float y) {
        this.img = bmpImage;
        this.x=x;
        this.y=y;

    }

    public void setImgFinalTamaño(int ancho, int alto) {
        this.imgFinal = Bitmap.createScaledBitmap(this.img, ancho, alto, true);

    }

    public float getRetroY() {
        return retroY;
    }

    public int getVida() {
        return vida;
    }

    public int getPunto() {
        return punto;
    }

    public void setX(float x) {this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Bitmap getImgFinal() {
        return imgFinal;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void controlador(int piso, float yMosco, float xMosco, int anchoMosco, int altoMosco) {


        this.vida=0;
        this.punto=0;

        if(controlador !=1) {
            controlador = (int) (30 * Math.random()) + 1;
        }
        if(getY() > piso){
            setY(-50);
            this.controlador =2;
            this.vida=1;
        }
        if(((getY() >= (yMosco + (altoMosco/3))) && (getY() <= (yMosco + (altoMosco/3)+getImgFinal().getHeight())) ) && ( (getX() >= (xMosco + (anchoMosco/3))) && ( getX() <= ((xMosco + anchoMosco) - (anchoMosco/3))))){
            this.retroY=(altoMosco/4)+yMosco;
            //----------------------------------------
            setY(-50);
            setX(-50);
            this.controlador =2;
            this.punto=1;
        }


        //---------------------------------------------------------------------------
        if(controlador ==1){
            setY(getY() + (int) (30 * Math.random()) + 1);
            setX(getX() + (int) (50 * Math.random()) + 1);
        }else {
            setY(60);
            setX(60);
        }

    }

}