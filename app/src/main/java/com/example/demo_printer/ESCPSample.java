package com.example.demo_printer;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;

import com.sewoo.jpos.command.ESCPOS;
import com.sewoo.jpos.command.ESCPOSConst;
import com.sewoo.jpos.printer.ESCPOSImage;
import com.sewoo.jpos.printer.ESCPOSPrinter;
import com.sewoo.jpos.printer.LKPrint;

public class ESCPSample
{
    private ESCPOSPrinter posPtr;
    private ESCPOSImage posImg;
    private final char ESC = ESCPOS.ESC;
    private final char LF = ESCPOS.LF;

    private int sts;


    public ESCPSample()
    {
        posPtr = new ESCPOSPrinter();
//		posPtr = new ESCPOSPrinter("EUC-KR"); // Korean.
//		posPtr = new ESCPOSPrinter("BIG5"); // Big5
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











/*
    public void receipt() throws UnsupportedEncodingException
    {
        posPtr.printNormal(ESC + "|cA" + ESC + "|bC" + ESC + "|2C" + "Receipt" + LF + LF);
        posPtr.printNormal(ESC + "|rA" + ESC + "|bC" + "TEL (123)-456-7890" + LF);
        posPtr.printNormal(ESC + "|cA" + ESC + "|bC" + "Thank you for coming to our shop!" + LF + LF);

        posPtr.printNormal(ESC + "|cA" +"Chicken                   $10.00" + LF);
        posPtr.printNormal(ESC + "|cA" +"Hamburger                 $20.00" + LF);
        posPtr.printNormal(ESC + "|cA" +"Pizza                     $30.00" + LF);
        posPtr.printNormal(ESC + "|cA" +"Lemons                    $40.00" + LF);
        posPtr.printNormal(ESC + "|cA" +"Drink                     $50.00" + LF + LF);
        posPtr.printNormal(ESC + "|cA" +"Excluded tax             $150.00" + LF);

        posPtr.printNormal( ESC + "|cA" +ESC + "|2uC" + "Audedo del periodo" + LF);
        posPtr.printNormal( ESC + "|cA" +ESC + "|bC" + ESC + "|2C" + "Total   $157.50" + LF + LF);
        posPtr.printNormal( ESC + "|cA" +ESC + "|bC" + "Payment                  $200.00" + LF);
        posPtr.printNormal( ESC + "|cA" +ESC + "|bC" + "Change                    $42.50" + LF);
    }

    public int sample() throws UnsupportedEncodingException
    {
        int iSize = 0;

        sts = chkStatus.PrinterStatus(posPtr);
        if(sts != ESCPOSConst.LK_SUCCESS) return sts;

        receipt();
        try {
            iSize = posImg.printBitmap("//sdcard//temp//test//sign.bmp");
            if(iSize > 0)
            {
                byte [] iImageBuffer = new byte[iSize];
                int iLen = posImg.getImage(iImageBuffer);
                if(iLen > 0)
                {
                    posPtr.sendByte(iImageBuffer);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        posPtr.printNormal(ESC + "|cA" + ESC + "|bC" + ESC + "|4C" + "Thank you" + LF);
        posPtr.lineFeed(3);

        return 0;
    }

    public int barcode2d() throws UnsupportedEncodingException
    {
        String data = "ABCDEFGHIJKLMN";

        sts = chkStatus.PrinterStatus(posPtr);
        if(sts != ESCPOSConst.LK_SUCCESS) return sts;

        posPtr.printString("PDF417\r\n");
        posPtr.printPDF417(data, data.length(), 0, 10, ESCPOSConst.LK_ALIGNMENT_CENTER);
        posPtr.printPDF417(data, data.length(), 4, 3, ESCPOSConst.LK_ALIGNMENT_CENTER);
        posPtr.printString("QRCode\r\n");
        posPtr.printQRCode(data, data.length(), 3, ESCPOSConst.LK_QRCODE_EC_LEVEL_L, ESCPOSConst.LK_ALIGNMENT_CENTER);
        posPtr.printNormal(ESC + "|cA" + ESC + "|4C" + ESC + "|bC" + "Thank you" + LF);
        posPtr.lineFeed(4);

        return 0;
    }
    public int barcodesample() throws UnsupportedEncodingException
    {
        sts = chkStatus.PrinterStatus(posPtr);
        if(sts != ESCPOSConst.LK_SUCCESS) return sts;

        posPtr.printBarCode("1234567890", LKPrint.LK_BCS_Code39, 40, 2, LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_HRI_TEXT_BELOW);
        posPtr.printBarCode("0123498765", LKPrint.LK_BCS_Code93, 40, 2, LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_HRI_TEXT_BELOW);
        posPtr.printBarCode("0987654321", LKPrint.LK_BCS_ITF, 40, 2, LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_HRI_TEXT_BELOW);
        posPtr.printBarCode("{ACODE 128", LKPrint.LK_BCS_Code128, 40, 2, LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_HRI_TEXT_BELOW);
        posPtr.printBarCode("{BCode 128", LKPrint.LK_BCS_Code128, 40, 2, LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_HRI_TEXT_BELOW);
        posPtr.printBarCode("{C12345", LKPrint.LK_BCS_Code128, 40, 2, LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_HRI_TEXT_BELOW);
        posPtr.printBarCode("A1029384756A", LKPrint.LK_BCS_Codabar, 40, 2, LKPrint.LK_ALIGNMENT_CENTER, LKPrint.LK_HRI_TEXT_BELOW);
        posPtr.printNormal(ESC + "|cA" + ESC + "|4C" + ESC + "|bC" + "Thank you" + LF);
        posPtr.lineFeed(3);

        return 0;
    }

    public int imageTest() throws IOException
    {
        sts = chkStatus.PrinterStatus(posPtr);
        if(sts != ESCPOSConst.LK_SUCCESS) return sts;

        posPtr.printBitmap("//sdcard//temp//test//logo_s.jpg", LKPrint.LK_ALIGNMENT_CENTER);
        posPtr.printBitmap("//sdcard//temp//test//sample_2.jpg", LKPrint.LK_ALIGNMENT_CENTER);
        posPtr.printBitmap("//sdcard//temp//test//sample_3.jpg", LKPrint.LK_ALIGNMENT_LEFT);
        posPtr.printBitmap("//sdcard//temp//test//sample_4.jpg", LKPrint.LK_ALIGNMENT_RIGHT);

        Bitmap _bitmap = BitmapFactory.decodeFile("//sdcard//temp//test//logo_s.jpg");
        posPtr.printBitmap(_bitmap, LKPrint.LK_ALIGNMENT_CENTER);
        posPtr.printBitmap(_bitmap, LKPrint.LK_ALIGNMENT_CENTER, 0, 1);

        posPtr.printBitmap(_bitmap, LKPrint.LK_ALIGNMENT_LEFT, 1);
        posPtr.printBitmap(_bitmap, LKPrint.LK_ALIGNMENT_LEFT, 2);
        posPtr.printBitmap(_bitmap, LKPrint.LK_ALIGNMENT_LEFT, 3);

        posPtr.printNormal(ESC + "|cA" + ESC + "|bC" + ESC + "|4C" + "Thank you" + LF);
        posPtr.lineFeed(3);

        return 0;
    }

    public int invoice() throws UnsupportedEncodingException
    {
        sts = chkStatus.PrinterStatus(posPtr);
        if(sts != ESCPOSConst.LK_SUCCESS) return sts;

        posPtr.setCharSet("Big5");

        // Setting PageMode
        posPtr.setPageMode(true);
        // 180 DPI or 203 DPI
        // 180 DPI - 7 dot per 1mm
        // 203 DPI - 8 dot per 1mm
        posPtr.setDPI(203);
        // Print direction.
        posPtr.setPrintDirection(ESCPOSConst.DIRECTION_LEFT_RIGHT);
        // 399 dot x 630 dot.
        posPtr.setPrintingArea(399, 730); // al

        // Data
        // Medium Text (20, 20)
        posPtr.setAbsoluteVertical(20);
        posPtr.setAbsoluteHorizontal(20);
        posPtr.printText("丟並乾亂佔佪亙", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_2WIDTH | LKPrint.LK_TXT_2HEIGHT);

        // Large Text
        posPtr.setAbsoluteVertical(90);
        posPtr.setAbsoluteHorizontal(20);
        posPtr.printText("伋伕佇佈", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_3WIDTH | LKPrint.LK_TXT_3HEIGHT);

        // Must be Off Unicode when print Alphabet or print barcode.
        posPtr.setCharSet("Big5");

        posPtr.setAbsoluteVertical(190);
        posPtr.setAbsoluteHorizontal(20);
        posPtr.printText("ABCDE", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_3WIDTH | LKPrint.LK_TXT_3HEIGHT);

        posPtr.setCharSet("Big5");

        // Small Text
        posPtr.setAbsoluteVertical(300);
        posPtr.setAbsoluteHorizontal(20);
        posPtr.printText("壓壘壙壚壞壟壢壩壯壺桌號菇", LKPrint.LK_ALIGNMENT_LEFT, LKPrint.LK_FNT_DEFAULT, LKPrint.LK_TXT_1WIDTH | LKPrint.LK_TXT_1HEIGHT);

        // Must be Off Unicode when print Alphabet or print barcode.
        //posPtr.setCharSet("Big5");

        // 1D Barcode
        posPtr.setAbsoluteVertical(380);
        posPtr.setAbsoluteHorizontal(0);
        posPtr.printBarCode("0123456789012345678901", ESCPOSConst.LK_BCS_Code39, 40, 1, ESCPOSConst.LK_ALIGNMENT_CENTER, ESCPOSConst.LK_HRI_TEXT_NONE);
//    	    	posPtr.printBarCode("0123498765", ESCPOSConst.LK_BCS_Code93, 40, 2, ESCPOSConst.LK_ALIGNMENT_CENTER, ESCPOSConst.LK_HRI_TEXT_NONE);

        // QRCODE
        String data = "中華民國萬萬稅1234567890123456789012345678901234567890123456789012345678";
        posPtr.setAbsoluteVertical(450);
        posPtr.setAbsoluteHorizontal(40);
        posPtr.printQRCode(data, data.length(), 5, ESCPOSConst.LK_QRCODE_EC_LEVEL_L, ESCPOSConst.LK_ALIGNMENT_CENTER);
        posPtr.setAbsoluteVertical(450);
        posPtr.setAbsoluteHorizontal(240);
        posPtr.printQRCode(data, data.length(), 3, ESCPOSConst.LK_QRCODE_EC_LEVEL_L, ESCPOSConst.LK_ALIGNMENT_CENTER);

        // Data
        posPtr.printPageModeData();
        posPtr.setPageMode(false);
        posPtr.lineFeed(4);

        return 0;

    }

    public int printDataMatrix() throws UnsupportedEncodingException
    {
        sts = chkStatus.PrinterStatus(posPtr);
        if(sts != ESCPOSConst.LK_SUCCESS) return sts;

        String data = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        // DataMatrix
        posPtr.printDataMatrix(data, data.length(), 6, ESCPOSConst.LK_ALIGNMENT_CENTER);
        posPtr.lineFeed(4);

        return 0;
    }

    public int printAndroidFont() throws UnsupportedEncodingException
    {
        int nLineWidth = 384;
        String data = "Receipt";
//    	String data = "영수증";
        Typeface typeface = null;

        sts = chkStatus.PrinterStatus(posPtr);
        if(sts != ESCPOSConst.LK_SUCCESS) return sts;

        try
        {
            posPtr.printAndroidFont(data, nLineWidth, 100, ESCPOSConst.LK_ALIGNMENT_CENTER);
            posPtr.lineFeed(2);
            posPtr.printAndroidFont("Left Alignment", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont("Center Alignment", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_CENTER);
            posPtr.printAndroidFont("Right Alignment", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_RIGHT);

            posPtr.lineFeed(2);
            posPtr.printAndroidFont(Typeface.SANS_SERIF, "SANS_SERIF : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.SERIF, "SERIF : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(typeface.MONOSPACE, "MONOSPACE : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);

            posPtr.lineFeed(2);
            posPtr.printAndroidFont(Typeface.SANS_SERIF, "SANS : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.SANS_SERIF, true, "SANS BOLD : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.SANS_SERIF, true, false, "SANS BOLD : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.SANS_SERIF, false, true, "SANS ITALIC : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.SANS_SERIF, true, true, "SANS BOLD ITALIC : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.SANS_SERIF, true, true, true, "SANS B/I/U : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);

            posPtr.lineFeed(2);
            posPtr.printAndroidFont(Typeface.SERIF, "SERIF : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.SERIF, true, "SERIF BOLD : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.SERIF, true, false, "SERIF BOLD : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.SERIF, false, true, "SERIF ITALIC : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.SERIF, true, true, "SERIF BOLD ITALIC : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.SERIF, true, true, true, "SERIF B/I/U : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);

            posPtr.lineFeed(2);
            posPtr.printAndroidFont(Typeface.MONOSPACE, "MONOSPACE : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.MONOSPACE, true, "MONO BOLD : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.MONOSPACE, true, false, "MONO BOLD : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.MONOSPACE, false, true, "MONO ITALIC : 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.MONOSPACE, true, true, "MONO BOLD ITALIC: 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            posPtr.printAndroidFont(Typeface.MONOSPACE, true, true, true, "MONO B/I/U: 1234iwIW", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);

            posPtr.lineFeed(4);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }

    public int printMultilingualFont() throws UnsupportedEncodingException
    {
        int nLineWidth = 384;
        String Koreandata = "영수증";
        String Turkishdata = "Turkish(İ,Ş,Ğ)";
        String Russiandata = "Получение";
        String Arabicdata = "الإيصال";
        String Greekdata = "Παραλαβή";
        String Japanesedata = "領収書";
        String GB2312data = "收据";
        String BIG5data = "收據";

        sts = chkStatus.PrinterStatus(posPtr);
        if(sts != ESCPOSConst.LK_SUCCESS) return sts;

        try
        {
            posPtr.printAndroidFont("Korean Font", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // Korean 100-dot size font in android device.
            posPtr.printAndroidFont(Koreandata, nLineWidth, 100, ESCPOSConst.LK_ALIGNMENT_CENTER);

            posPtr.printAndroidFont("Turkish Font", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // Turkish 50-dot size font in android device.
            posPtr.printAndroidFont(Turkishdata, nLineWidth, 50, ESCPOSConst.LK_ALIGNMENT_CENTER);

            posPtr.printAndroidFont("Russian Font", 384, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // Russian 60-dot size font in android device.
            posPtr.printAndroidFont(Russiandata, nLineWidth, 60, ESCPOSConst.LK_ALIGNMENT_CENTER);

            posPtr.printAndroidFont("Arabic Font", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // Arabic 100-dot size font in android device.
            posPtr.printAndroidFont(Arabicdata, nLineWidth, 100, ESCPOSConst.LK_ALIGNMENT_CENTER);

            posPtr.printAndroidFont("Greek Font", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // Greek 60-dot size font in android device.
            posPtr.printAndroidFont(Greekdata, nLineWidth, 60, ESCPOSConst.LK_ALIGNMENT_CENTER);

            posPtr.printAndroidFont("Japanese Font", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // Japanese 100-dot size font in android device.
            posPtr.printAndroidFont(Japanesedata, nLineWidth, 100, ESCPOSConst.LK_ALIGNMENT_CENTER);

            posPtr.printAndroidFont("GB2312 Font", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // GB2312 100-dot size font in android device.
            posPtr.printAndroidFont(GB2312data, nLineWidth, 100, ESCPOSConst.LK_ALIGNMENT_CENTER);

            posPtr.printAndroidFont("BIG5 Font", nLineWidth, 24, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // BIG5 100-dot size font in android device.
            posPtr.printAndroidFont(BIG5data, nLineWidth, 100, ESCPOSConst.LK_ALIGNMENT_CENTER);

            posPtr.lineFeed(4);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }

    public int imageDithering() throws IOException
    {
        int pWidth = 384; // 2-inch
        // int pWidth = 576; // 3-inch
        // int pWidth = 832; // 4-inch

        sts = chkStatus.PrinterStatus(posPtr);
        if(sts != ESCPOSConst.LK_SUCCESS) return sts;

        posPtr.printString("Not using dithering\r\n");
        posPtr.setDithering(ESCPOSConst.LK_BITMAP_NO_DITHER);
        posPtr.printBitmap("//sdcard//temp//test//parking.jpg", LKPrint.LK_ALIGNMENT_CENTER, pWidth);
        posPtr.lineFeed(1);
        posPtr.printString("Using dithering\r\n");
        posPtr.setDithering(ESCPOSConst.LK_BITMAP_ERROR_DIFFUSION);
        posPtr.printBitmap("//sdcard//temp//test//parking.jpg", LKPrint.LK_ALIGNMENT_CENTER, pWidth);

        posPtr.lineFeed(3);
        return 0;
    }
*/
}
