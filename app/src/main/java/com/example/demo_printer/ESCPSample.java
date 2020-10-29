package com.example.demo_printer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import com.sewoo.jpos.command.ESCPOS;
import com.sewoo.jpos.command.ESCPOSConst;
import com.sewoo.jpos.printer.ESCPOSImage;
import com.sewoo.jpos.printer.ESCPOSPrinter;
import com.sewoo.jpos.printer.LKPrint;

public class ESCPSample
{
    private ESCPOSPrinter posPtr;
    private ESCPOSImage posImg;

    private int sts;


    public ESCPSample()
    {
        posPtr = new ESCPOSPrinter();
        posImg = new ESCPOSImage();
    }


    void propio(){
        String[][] lista ={
                {"Sep/2020","489","573","37"},
                {"Sep/2020","489","573","38"},
                {"Sep/2020","489","573","39"},
                {"Sep/2020","489","573","40"}
        };

        String[][] lista2 ={
                {"periodo ","la m3","la m3","co m3"},
                {"Sep/2020","489","573","37"},
                {"Sep/2020","489","573","38"},
                {"Sep/2020","489","573","39"},
                {"Sep/2020","489","573","40"}
        };


        try {

            img();
            textCenter("Comicion muntermunicipal de agua potable y alcantarillado de los Municipio de colima y villa de alvares");
            posPtr.lineFeed(1);
            titulo2("Estado de Cuneta");
            posPtr.lineFeed(2);

            titulo("Usuario");
            posPtr.lineFeed(1);
            textBold("Jose Antonio Martinez Aguilar");
            posPtr.lineFeed(1);
            textLeft("Manuel velazquez 422 \nCOl.El Diezmo",32);
            posPtr.lineFeed(1);
            textLeft("Mercedez zamora y gral jose juan ortega \ncolima, col",32);
            posPtr.lineFeed(2);

            titulo("Datos Del Predio");
            posPtr.lineFeed(1);
            doble4("Clave Catastral:","000-000-000");
            posPtr.lineFeed(1);
            doble4("Localizacion:","00-000-000");
            posPtr.lineFeed(1);
            doble4("Grupo Facuracion:","3");
            posPtr.lineFeed(1);
            doble4("Tipo de Cobro:","Servicio medido");
            posPtr.lineFeed(1);
            doble4("No.Medidor:","000000000");
            posPtr.lineFeed(1);
            doble4("tipo Descuento:","________________");
            posPtr.lineFeed(1);
            doble4("Perido de adeudo:","Dic 2019-Sep 2020");
            posPtr.lineFeed(1);
            doble4("Bimestre Actual:","Sep 2020-Oct 2020");
            posPtr.lineFeed(2);

            titulo("Facturacion");
            posPtr.lineFeed(1);
            tablasimple(lista);
//            colum(lista2);
            posPtr.lineFeed(2);

            titulo("Concepto");
            posPtr.lineFeed(2);
            doble2("Cargos por derondeo","0.10");
            posPtr.lineFeed(1);
            doble2("servicio de agua potable","216.08");
            posPtr.lineFeed(1);
            doble2("servicio de drenaje","108.04");
            posPtr.lineFeed(1);
            doble2("servicio de saneamiento","53.83");
            posPtr.lineFeed(1);
            doble2("Descuento por pago anticipado","-30.88");
            posPtr.lineFeed(1);
            doble2("Aplicacion saldo a favor","-347.17");
            posPtr.lineFeed(1);
            doble2("IVA en su pago","-23.83");
            posPtr.lineFeed(1);
            doble2("Crédito por redondeo siguinte recivo","-23.83");
            posPtr.lineFeed(1);
            doble2("Crédito por redondeo siguinte recivo","-0.06");
            posPtr.lineFeed(1);
            doble2("IVA Factura","23.83");


            posPtr.lineFeed(2);
            textBoldCenter("Audedo del periodo");
            posPtr.lineFeed(1);
            textCenterlargo("$0.00");
            posPtr.lineFeed(1);
            doble3("pagar antes del", "06/11/2020");
            posPtr.lineFeed(1);
            doble("CIE: ","1220845");
            posPtr.lineFeed(1);
            doble("Contrato: ","0000000000000");
            posPtr.lineFeed(2);
            textLeft("Mensaje: Gracisa por su pago");
            posPtr.lineFeed(2);
            QRCode("01000642301");
            posPtr.lineFeed(1);
            barcode("01000642301");


            posPtr.lineFeed(5);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void textBold(String s) throws UnsupportedEncodingException {
        posPtr.printText(s,LKPrint.LK_ALIGNMENT_LEFT,LKPrint.LK_FNT_BOLD,LKPrint.LK_TXT_1WIDTH);
    }
    void textBoldCenter(String s) throws UnsupportedEncodingException {
        posPtr.printText(s,LKPrint.LK_ALIGNMENT_CENTER,LKPrint.LK_FNT_BOLD,LKPrint.LK_TXT_1WIDTH);
    }
    void textCenterlargo(String s) throws UnsupportedEncodingException {
        posPtr.printText(s,LKPrint.LK_ALIGNMENT_CENTER,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_2WIDTH);
    }
    void textCenter(String s) throws UnsupportedEncodingException {
        posPtr.printText(s,LKPrint.LK_ALIGNMENT_CENTER,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_1WIDTH);
    }

    void textLeft(String s,int leng) throws UnsupportedEncodingException {
        String text="";
        String [] pal=s.split(" ");
        int cont =0;
        for (int i = 0; i < pal.length; i++) {

            cont+=pal[i].length()+1;
            if(cont > leng){
                text+="\n";
                cont=pal[i].length()+1;
            }
            text+=pal[i]+" ";
        }
        posPtr.printText(text,LKPrint.LK_ALIGNMENT_LEFT,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_1WIDTH);
    }
    void textLeft(String s) throws UnsupportedEncodingException {
        posPtr.printText(s,LKPrint.LK_ALIGNMENT_LEFT,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_1WIDTH);
    }

    void textRight(String s) throws UnsupportedEncodingException {
        posPtr.printText(s,LKPrint.LK_ALIGNMENT_RIGHT,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_1WIDTH);
    }
    void titulo(String s) throws UnsupportedEncodingException {
        String line="********************************";
        posPtr.printText(line,LKPrint.LK_ALIGNMENT_RIGHT,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_1WIDTH);
        posPtr.lineFeed(1);
        posPtr.printText(s,LKPrint.LK_ALIGNMENT_CENTER,LKPrint.LK_FNT_FONTB,LKPrint.LK_TXT_2WIDTH);
        posPtr.lineFeed(1);
        posPtr.printText(line,LKPrint.LK_ALIGNMENT_RIGHT,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_1WIDTH);
    }
    void titulo2(String s) throws UnsupportedEncodingException {
      posPtr.printText(s,LKPrint.LK_ALIGNMENT_CENTER,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_2HEIGHT);
    }
    void doble(String s1,String s2) throws UnsupportedEncodingException {

        posPtr.printText(s1,LKPrint.LK_ALIGNMENT_LEFT,LKPrint.LK_FNT_BOLD,LKPrint.LK_TXT_1WIDTH);
        posPtr.printText(s2,LKPrint.LK_ALIGNMENT_RIGHT,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_1WIDTH);

    }
    void doble2(String s1, String s2)throws UnsupportedEncodingException {
        int leng = 32, maxleng=19;
        int leng1=s1.length();
        int leng2=s2.length();
        String text="";

        if( leng1<=maxleng){
            int aux =leng-(leng1+leng2);
            text+=s1;
            for (int i = 0; i < aux; i++) {
                text+=" ";
            }
            text+=s2;
        }else{
            String [] tex = s1.split(" ");
            int cont =0;
            for (int i = 0; i < tex.length; i++) {
                cont+=tex[i].length()+1;
                if(cont > maxleng){
                    text+="\n";
                    cont=tex[i].length()+1;
                }
                text+=tex[i]+" ";
            }

            int aux =leng-(cont+leng2);
            for (int i = 0; i < aux; i++) {
                text+=" ";
            }
            text+=s2;
        }
        textLeft(text);

    }
    void doble3(String s1, String s2)throws UnsupportedEncodingException {
        int leng = 32, maxleng=19;
        int leng1=s1.length();
        int leng2=s2.length();
        String text="";

        if( leng1<=maxleng){
            int aux =leng-(leng1+leng2);
            textBold(s1);
            for (int i = 0; i < aux; i++) {
                text+=" ";
            }
            text+=s2;
            textLeft(text);

        }else{
            String [] tex = s1.split(" ");
            int cont =0;
            for (int i = 0; i < tex.length; i++) {
                cont+=tex[i].length()+1;
                if(cont > maxleng){
                    text+="\n";
                    cont=tex[i].length()+1;
                }

                text+=tex[i]+" ";
            }
            textBold(text);
            text="";
            int aux =leng-(cont+leng2);
            for (int i = 0; i < aux; i++) {
                text+=" ";
            }
            text+=s2;
            textLeft(text);
        }

    }
    void doble4(String s1,String s2) throws UnsupportedEncodingException {
        posPtr.printText(s1,LKPrint.LK_ALIGNMENT_LEFT,LKPrint.LK_FNT_BOLD,LKPrint.LK_TXT_1WIDTH);

        /*if((s1.length()+s2.length())>32){
            posPtr.lineFeed(1);
            posPtr.printText("  ",LKPrint.LK_ALIGNMENT_LEFT,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_1WIDTH);
        }else{
            posPtr.printText(" ",LKPrint.LK_ALIGNMENT_LEFT,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_1WIDTH);
        }*/

        posPtr.printText(s2,LKPrint.LK_ALIGNMENT_LEFT,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_1WIDTH);

    }
    void barcode(String s) throws UnsupportedEncodingException{
        posPtr.printBarCode("{B"+s, LKPrint.LK_BCS_Code128, 40, 2, LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_HRI_TEXT_BELOW);
    }
    void QRCode(String s)throws UnsupportedEncodingException{
        posPtr.printQRCode(s, s.length(), 10, ESCPOSConst.LK_QRCODE_EC_LEVEL_L, ESCPOSConst.LK_ALIGNMENT_CENTER);
    }
    void img()throws IOException{
        textLeft("\t");
        posPtr.printNVBitmap(3);

    }
    void colum(String[][] s1) throws UnsupportedEncodingException{

        rowtitle(s1[0]);

        for (int i = 1; i <s1.length ; i++) {
            row(s1[i]);
        }

    }
    void row(String[]s) throws UnsupportedEncodingException{
        int leng = 32;
        int colum = s.length;
        int colimleng = leng / colum;

        String text = "";
        String tex2="|";
        String tex3="|";



        posPtr.printText("|",LKPrint.LK_ALIGNMENT_RIGHT,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_1WIDTH);
        for (int i = 1; i < s.length; i++) {
            text += "|";
            text += s[i];

            for (int j = 0; j < (colimleng - s[i].length())-2; j++) {
                text += " ";
                tex2+="_";

            }

            for (int j = 0; j < s[i].length(); j++) {
                tex2+="_";
            }

            if (i == (s.length - 1)){
                text += " |";
                tex2+="_|";
            }else{
                tex2+="|";
            }


        }

        for (int j = 0; j < (s[0].length()+ ((colimleng - s[0].length()))); j++) {
            tex3+="_";
        }

        posPtr.printText(s[0],LKPrint.LK_ALIGNMENT_LEFT,LKPrint.LK_FNT_BOLD,LKPrint.LK_TXT_1WIDTH);
        posPtr.printText(text,LKPrint.LK_ALIGNMENT_RIGHT,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_1WIDTH);
        posPtr.printText(tex3,LKPrint.LK_ALIGNMENT_RIGHT,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_1WIDTH);
        posPtr.printText(tex2,LKPrint.LK_ALIGNMENT_RIGHT,LKPrint.LK_FNT_DEFAULT,LKPrint.LK_TXT_1WIDTH);
    }
    void rowtitle(String[]s) throws UnsupportedEncodingException{
        int leng = 32;
        int colum = s.length;
        int colimleng = leng / colum;

        String text = "";
        String tex2="";



        for (int i = 0; i < s.length; i++) {
            text += "|";
            text += s[i];

            for (int j = 0; j < (colimleng - s[i].length())-2; j++) {
                text += " ";
                tex2+="_";
            }

            for (int j = 0; j < s[i].length(); j++) {
                tex2+="_";
            }


            if (i == (s.length - 1)){
                text += " |";
                tex2+="_|";
            }else{
                tex2+="|";
            }
        }
        text+="|";

        posPtr.printText(text,LKPrint.LK_ALIGNMENT_RIGHT,LKPrint.LK_FNT_BOLD,LKPrint.LK_TXT_1WIDTH);
        posPtr.printText(tex2,LKPrint.LK_ALIGNMENT_RIGHT,LKPrint.LK_FNT_BOLD,LKPrint.LK_TXT_1WIDTH);
    }
    void tablasimple(String[][] s)throws UnsupportedEncodingException{
        textBold("periodo  la m3 \tla m3 \tco m3");
        posPtr.lineFeed(1);
        for (int i = 0; i < s.length ; i++) {
            String s1=s[i][0],s2=s[i][1],s3=s[i][2],s4=s[i][3];
            doble(s1+"  ", s2 + " \t " + s3 + " \t " + s4);
            posPtr.lineFeed(1);
        }
    }
}
