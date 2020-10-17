package com.example.demo_printer;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.graphics.Typeface;

import com.sewoo.jpos.command.CPCLConst;
import com.sewoo.jpos.command.ESCPOSConst;
import com.sewoo.jpos.printer.CPCLPrinter;



public class CPCL
{
    protected CPCLPrinter cpclPrinter;
    private int paperType;

    public CPCL()
    {
        cpclPrinter = new CPCLPrinter();
//		cpclPrinter = new CPCLPrinter("EUC-KR");
        paperType = CPCLConst.LK_CPCL_CONTINUOUS;
    }

    public int printerCheck(){
        return cpclPrinter.printerCheck();
    }


    public int status(){
        return cpclPrinter.status();
    }

    public void selectGapPaper()
    {
        paperType = CPCLConst.LK_CPCL_LABEL;
    }

    public void selectBlackMarkPaper()
    {
        paperType = CPCLConst.LK_CPCL_BLACKMARK;
    }

    public void selectContinuousPaper()
    {
        paperType = CPCLConst.LK_CPCL_CONTINUOUS;
    }


    public void personal(int count) throws IOException {

        int saltos=25, saltotitulo=60,saltonuevo=80;

        String[][] lista ={
                {"Sep/2020","489","573","37"},
                {"Sep/2020","489","573","37"},
                {"Sep/2020","489","573","37"},
                {"Sep/2020","489","573","37"}
        };

        int height=2050+(25*(lista.length+1));
       //cpclPrinter.setForm(0, 200, 200, 400, count);
       cpclPrinter.setForm(0, 200, 200, height, count);
        cpclPrinter.setMedia(paperType);

        cpclPrinter.setJustification(CPCLConst.LK_CPCL_CENTER);
        multilie(0,50,"Comicion muntermunicipal de agua potable y alcantarillado de los Municipio de colima y villa de alvares",25);

        simpletitle(0,200,"Estado de Cuneta");

        int y1=260;
        titleText(0,y1,"Usuario");
        doble(0,(y1+=saltotitulo),"Jose Antonio Martinez Aguilar","");
        multilie(0,(y1+=saltos),"Manuel velazquez 422 COl.El Diezmo mercedez zamora y gral jose juan ortega colima, col",25);
        multilie(0,(y1+=saltos*3),"Mercedez zamora y gral jose juan ortega colima, col",25);

        int y2=y1+saltonuevo;
        titleText(0,y2,"Datos Del Predio");
        doble(0,(y2+=saltotitulo),"Clave Catastral: ","");
        textleft(25,(y2+saltos),"");
        doble(0,(y2+=saltos*2),"Localizacion: ","");
        textleft(25,(y2+saltos),"00-000-000");
        doble(0,(y2+=saltos*2),"Grupo Facuracion: ","");
        textleft(25,(y2+saltos),"3");
        doble(0,(y2+=saltos*2),"Tipo de Cobro: ","");
        textleft(25,(y2+saltos),"Servicio medido");
        doble(0,(y2+=saltos*2),"No.Medidor: ","");
        textleft(25,(y2+saltos),"000000000");
        doble(0,(y2+=saltos*2),"tipo Descuento: ","");
        textleft(25,(y2+saltos),"______________________");
        doble(0,(y2+=saltos*2),"Perido de adeudo: ","");
        textleft(25,(y2+saltos),"Dic 2019-Sep 2020");
        doble(0,(y2+=saltos*2),"Bimestre Actual: ","");
        textleft(25,(y2+saltos),"Sep 2020-Oct 2020");

        int y3=y2+saltonuevo;
        cpclPrinter.setJustification(CPCLConst.LK_CPCL_LEFT);
        titleText(0,y3,"Facturacion");
        titulotable((y3+=saltotitulo),"periodo","la m3", "la m3", "co m3");
        for (int i = 0; i < lista.length ; i++) {
            conttable((y3+=saltos),lista[i][0],lista[i][1],lista[i][2],lista[i][3]);
        }


        int y4=y3+saltonuevo;
        titleText(0,y4,"Concepto");
        textleft(0,(y4+=saltotitulo),"Cargos por ");
        textleft(0,(y4+=saltos),"derondeo");
        textright(0,(y4),"0.10");
        textleft(0,(y4+=saltos+10),"servicio de");
        textleft(0,(y4+=saltos),"agua potable");
        textright(0,(y4),"216.08");
        textleft(0,(y4+=saltos+10),"servicio de");
        textleft(0,(y4+=saltos),"drenaje");
        textright(0,(y4),"108.04");
        textleft(0,(y4+=saltos+10),"servicio de");
        textleft(0,(y4+=saltos),"saneamiento");
        textright(0,(y4),"53.83");
        textleft(0,(y4+=saltos+10),"Descuento por");
        textleft(0,(y4+=saltos),"pago anticipado");
        textright(0,(y4),"-30.88");
        textleft(0,(y4+=saltos+10),"Aplicacion saldo");
        textleft(0,(y4+=saltos),"a favor");
        textright(0,(y4),"-347.17");
        textleft(0,(y4+=saltos+10),"IVA en su pago");
        textright(0,(y4),"-23.83");
        textleft(0,(y4+=saltos+10),"Crédito por");
        textleft(0,(y4+=saltos),"redondeo siguinte ");
        textleft(0,(y4+=saltos),"recivo");
        textright(0,(y4),"-0.06");
        textleft(0,(y4+=saltos+10),"IVA Factura");
        textright(0,(y4),"23.83");

        int y5=y4+50;
        cpclPrinter.setJustification(CPCLConst.LK_CPCL_LEFT);
        doble(0,y5,"Audedo del periodo","");
        simpletitle(0,(y5+=saltos),"$0.00");

        int y6=y5+75;
        cpclPrinter.setJustification(CPCLConst.LK_CPCL_LEFT);
        doble(0,y6,"pagar antes del","");
        cpclPrinter.setJustification(CPCLConst.LK_CPCL_RIGHT);
        textright(0,(y6),"06/11/2020");


        int y7=y6+35;
        cpclPrinter.setJustification(CPCLConst.LK_CPCL_LEFT);
        doble(0,y7,"CIE: ","1220845");
        doble(0,(y7+=saltos),"Contrato: ","0000000000000");


        int y8=y7+50;
        qr(100,y8,"hola");
        cpclPrinter.printForm();
    }

    int sizefont=3;

    void doble(int x, int y, String s1, String s2){
        cpclPrinter.setJustification(CPCLConst.LK_CPCL_LEFT);
        cpclPrinter.setConcat(CPCLConst.LK_CPCL_CONCAT, x, y);
        cpclPrinter.concatText(5, 0, 0, s1);
        cpclPrinter.concatText(0, sizefont, 0, s2);
        cpclPrinter.resetConcat();
    }


    void doble2(int x, int y, String s1, String s2){
        cpclPrinter.setJustification(CPCLConst.LK_CPCL_LEFT);
        multilie(x,y,s1,15);
        cpclPrinter.setJustification(CPCLConst.LK_CPCL_RIGHT);
        text(x,y,s2);
    }

    void multilie(int x, int y,String s, int leng){
        String [] tex = s.split(" ");
        String nuevo="";
        int cont =0,cont2=0;
        for (int i = 0; i < tex.length; i++) {

            cont+=tex[i].length()+1;
            if(cont > leng){
                nuevo+="\n\n\n\n\n\n";
                cont2++;
                cont=tex[i].length()+1;
            }
            nuevo+=tex[i]+" ";
        }
        cpclPrinter.setMultiLine(cont2);
        cpclPrinter.multiLineText(0,0,sizefont,x,y);
        cpclPrinter.multiLineData(nuevo);
        cpclPrinter.resetMultiLine();
    }

    void text(int x, int y,String s){
        cpclPrinter.setJustification(CPCLConst.LK_CPCL_CENTER);
        cpclPrinter.printCPCLText(0, 0,sizefont,x, y, s, 0);
    }
    void textleft(int x, int y,String s){
        cpclPrinter.setJustification(CPCLConst.LK_CPCL_LEFT);
        cpclPrinter.printCPCLText(0, 0,sizefont,x, y, s, 0);
    }
    void textright(int x, int y,String s){
        cpclPrinter.setJustification(CPCLConst.LK_CPCL_RIGHT);
        cpclPrinter.printCPCLText(0, 0,sizefont,x, y, s, 0);
    }

    void titleText(int x, int y, String s){
        cpclPrinter.setJustification(CPCLConst.LK_CPCL_CENTER);
        cpclPrinter.printCPCLText(0, 5,1,x, y, s, 0);
        cpclPrinter.setJustification(CPCLConst.LK_CPCL_LEFT);
        //cpclPrinter.printLine(50,y+40,350,y+40,2);
        cpclPrinter.printBox(0,y-5,380,y+45,2);

    }

    void simpletitle(int x, int y, String s){
        cpclPrinter.setJustification(CPCLConst.LK_CPCL_CENTER);
        cpclPrinter.printCPCLText(0, 5,1,x, y, s, 0);
    }



    void titulotable(int y,String s1,String s2, String s3, String s4){
        cpclPrinter.printCPCLText(0, 5,0,0, y, s1, 0);
        cpclPrinter.printCPCLText(0, 5,0,140, y, s2, 0);
        cpclPrinter.printCPCLText(0, 5,0,210, y, s3, 0);
        cpclPrinter.printCPCLText(0, 5,0,280, y, s4, 0);
    }

    void conttable(int y,String s1,String s2, String s3, String s4){
        cpclPrinter.printCPCLText(0, 0,sizefont,0, y, s1, 0);
        cpclPrinter.printCPCLText(0, 0,sizefont,140, y, s2, 0);
        cpclPrinter.printCPCLText(0, 0,sizefont,210, y, s3, 0);
        cpclPrinter.printCPCLText(0, 0,sizefont,280, y, s4, 0);
    }

    void qr(int x, int y, String s){
        cpclPrinter.printCPCL2DBarCode(0, CPCLConst.LK_CPCL_BCS_QRCODE, x, y, 5, 0, 1, 0, s);
    }









    public void image3(int count) throws IOException
    {
        cpclPrinter.setForm(0, 200, 200, 576, count);
        cpclPrinter.setMedia(paperType);
        cpclPrinter.setCPCLBarcode(0, 2, 0);
        cpclPrinter.printBitmap("//sdcard//temp//test//Sewoo_bw_m.jpg", 1, 1);
//    	cpclPrinter.printCPCLImage("SEWOO.PCX", 1, 50);
        cpclPrinter.printBitmap("//sdcard//temp//test//car_s.jpg", 100, 140);
//		cpclPrinter.printCPCL2DBarCode(0, CPCLConst.LK_CPCL_BCS_PDF417, 150, 440, 2, 7, 2, 1, "http://www.miniprinter.com");
        cpclPrinter.printCPCL2DBarCode(0, CPCLConst.LK_CPCL_BCS_QRCODE, 150, 400, 5, 0, 1, 0, "http://www.miniprinter.com");
        // Print
        cpclPrinter.printForm();
    }

    public void barcode4(int count) throws UnsupportedEncodingException
    {
        final int laca = cpclPrinter.getModel();
        cpclPrinter.setForm(0, 200, 200, 670, count);
        cpclPrinter.setMedia(paperType);
        cpclPrinter.setCPCLBarcode(0, 2, 0);
        // CODABAR
//		cpclPrinter.printCPCLBarcode(CPCLConst.LK_CPCL_0_ROTATION, CPCLConst.LK_CPCL_BCS_CODABAR, 2, CPCLConst.LK_CPCL_BCS_0RATIO, 50, 209, 45, "A1234567890B", 0);
        // Code 39
        cpclPrinter.printCPCLBarcode(CPCLConst.LK_CPCL_0_ROTATION, CPCLConst.LK_CPCL_BCS_39, 2, CPCLConst.LK_CPCL_BCS_1RATIO, 50, 115, 25, "01234567890", 0);
        // Code 93
        cpclPrinter.printCPCLBarcode(CPCLConst.LK_CPCL_0_ROTATION, CPCLConst.LK_CPCL_BCS_93, 2, CPCLConst.LK_CPCL_BCS_1RATIO, 50, 160, 130, "01234567890", 0);
        // Code 128
        cpclPrinter.printCPCLBarcode(CPCLConst.LK_CPCL_0_ROTATION, CPCLConst.LK_CPCL_BCS_128, 2, CPCLConst.LK_CPCL_BCS_1RATIO, 50, 209, 235, "01234567890", 0);
        // PDF 417
        cpclPrinter.printCPCL2DBarCode(0, CPCLConst.LK_CPCL_BCS_PDF417, 200, 320, 3, 7, 2, 1, "SEWOO TECH\r\nLK-P11");
        // QRCODE
        cpclPrinter.printCPCL2DBarCode(0, CPCLConst.LK_CPCL_BCS_QRCODE, 250, 410, 5, 0, 1, 0, "http://www.miniprinter.com");
        // Print
        cpclPrinter.printForm();
    }

    public void sewooTech4(int count) throws IOException
    {
        cpclPrinter.setForm(0, 200, 200, 600, count);
        cpclPrinter.setMedia(paperType);
        // Image
        //cpclPrinter.printBitmap("", 130, 10);
        cpclPrinter.printCPCLText(0, 5, 2, 130, 140, "SEWOO TECH CO.,LTD.", 0);
        cpclPrinter.printCPCLText(0, 0, 3, 130, 210, "Dalim Plaza 304, 1027-20,", 0);
        cpclPrinter.printCPCLText(0, 0, 3, 130, 250, "Hogye-dong, Dongan-gu, Anyang-si,", 0);
        cpclPrinter.printCPCLText(0, 0, 3, 130, 290, "Gyeonggi-do, 431-848, Korea", 0);
        // Telephone
        cpclPrinter.printCPCLText(CPCLConst.LK_CPCL_0_ROTATION, 7, 1, 130, 340, "TEL : 82-31-387-0101", 0);
        // Homepage
        cpclPrinter.printCPCL2DBarCode(0, CPCLConst.LK_CPCL_BCS_QRCODE, 130, 400, 5, 0, 1, 0, "http://www.miniprinter.com");
        cpclPrinter.printCPCLText(CPCLConst.LK_CPCL_0_ROTATION, 7, 1, 310, 400, "www.miniprinter.com", 0);
        cpclPrinter.printCPCLText(CPCLConst.LK_CPCL_0_ROTATION, 1, 1, 310, 470, "<-- Check This.", 0);

    }

    public void koreanFontTest4(int count) throws UnsupportedEncodingException
    {
        String data = "세우테크";
        cpclPrinter.setForm(0, 200, 200, 600, count);
        cpclPrinter.setMedia(paperType);
        cpclPrinter.setMagnify(CPCLConst.LK_CPCL_TXT_1WIDTH, CPCLConst.LK_CPCL_TXT_1HEIGHT);
        cpclPrinter.printCPCLText(0, 0, 0, 1, 1,   data, 0);
        cpclPrinter.setMagnify(CPCLConst.LK_CPCL_TXT_2WIDTH, CPCLConst.LK_CPCL_TXT_1HEIGHT);
        cpclPrinter.printCPCLText(0, 0, 0, 1, 90,  data, 0);
        cpclPrinter.setMagnify(CPCLConst.LK_CPCL_TXT_2WIDTH, CPCLConst.LK_CPCL_TXT_2HEIGHT);
        cpclPrinter.printCPCLText(0, 0, 0, 1, 150, data, 0);
        cpclPrinter.setMagnify(CPCLConst.LK_CPCL_TXT_3WIDTH, CPCLConst.LK_CPCL_TXT_3HEIGHT);
        cpclPrinter.printCPCLText(0, 0, 0, 1, 250,  data, 0);
        cpclPrinter.setMagnify(CPCLConst.LK_CPCL_TXT_4WIDTH, CPCLConst.LK_CPCL_TXT_4HEIGHT);
        cpclPrinter.printCPCLText(0, 0, 0, 1, 360,  data, 0);
        // Reset
        cpclPrinter.resetMagnify();
        //BARCODE 128 1 1 50 150 10 HORIZ.
        //LKPrint.printCPCLBarcode(CPCLConst.LK_CPCL_0_ROTATION, CPCLConst.LK_CPCL_BCS_128, 2, 1, 30, 19, 45, "A37859B", 0);
        cpclPrinter.printForm();
    }

    public void KFDA(int count) throws UnsupportedEncodingException
    {
        CPCLPrinter cpclPrinter = new CPCLPrinter("EUC-KR");

        cpclPrinter.setForm(0, 200, 200, 400, count);
        cpclPrinter.setMedia(paperType);
        cpclPrinter.setPageWidth(864);
        cpclPrinter.setMagnify(1, 2);
        cpclPrinter.printCPCLText(CPCLConst.LK_CPCL_0_ROTATION,
                CPCLConst.LK_CPCL_FONT_0, 0, 289, 40, "수 거 증", 0);
        cpclPrinter.resetMagnify();
        cpclPrinter.printCPCLText(CPCLConst.LK_CPCL_0_ROTATION,
                CPCLConst.LK_CPCL_FONT_0, 0, 81, 108, "사업장", 0);
        cpclPrinter.printCPCLText(CPCLConst.LK_CPCL_0_ROTATION,
                CPCLConst.LK_CPCL_FONT_0, 0, 237, 108, "곰돌이 식품", 0);
        cpclPrinter.printCPCLText(CPCLConst.LK_CPCL_0_ROTATION,
                CPCLConst.LK_CPCL_FONT_0, 0, 81, 152, "주  소", 0);
        cpclPrinter.printCPCLText(CPCLConst.LK_CPCL_0_ROTATION,
                CPCLConst.LK_CPCL_FONT_0, 0, 231, 155, "경기도 곰사랑시 팽이동 999-9 번지", 0);
        cpclPrinter.printCPCLText(CPCLConst.LK_CPCL_0_ROTATION,
                CPCLConst.LK_CPCL_FONT_0, 0, 81, 205, "수 량", 0);
        cpclPrinter.printCPCLText(CPCLConst.LK_CPCL_0_ROTATION,
                CPCLConst.LK_CPCL_FONT_0, 3, 234, 205, "1000 BOX", 0);
        cpclPrinter.printCPCLText(CPCLConst.LK_CPCL_0_ROTATION,
                CPCLConst.LK_CPCL_FONT_0, 0, 437, 205, "일  자  2011.  7.  26.", 0);
        cpclPrinter.printCPCLText(CPCLConst.LK_CPCL_0_ROTATION,
                CPCLConst.LK_CPCL_FONT_0, 5, 58, 256, "수거 내용", 0);
        cpclPrinter.printCPCLText(CPCLConst.LK_CPCL_0_ROTATION,
                CPCLConst.LK_CPCL_FONT_0, 5, 231, 256, "위반", 0);

        cpclPrinter.printBox(18, 22, 810, 338, 2);
        cpclPrinter.printLine(18, 143, 810, 143, 2);
        cpclPrinter.printLine(18, 241, 810, 241, 2);
        cpclPrinter.printLine(18, 97, 810, 97, 2);
        cpclPrinter.printLine(18, 190, 810, 190, 2);
        cpclPrinter.printLine(204, 97, 204, 338, 2);
        cpclPrinter.printLine(404, 191, 404, 241, 2);

		/* QRCODE
		cpclPrinter.printCPCL2DBarCode(0, CPCLConst.LK_CPCL_BCS_QRCODE,
				330, 320, 5, 0, 1, 0, "http://www.miniprinter.com");
    	*/
        cpclPrinter.printForm();
    }

    public void printAndroidFont(int count) throws UnsupportedEncodingException
    {
        int nLineWidth = 832;
        String data = "Receipt";
//    	String data = "영수증";
        Typeface typeface = null;

        try
        {
            cpclPrinter.setForm(0, 200, 200, 406, count);
            cpclPrinter.setMedia(paperType);

            cpclPrinter.printAndroidFont(data, nLineWidth, 100, 0, ESCPOSConst.LK_ALIGNMENT_CENTER);
            cpclPrinter.printAndroidFont("Left Alignment", nLineWidth, 24, 120, ESCPOSConst.LK_ALIGNMENT_LEFT);
            cpclPrinter.printAndroidFont("Center Alignment", nLineWidth, 24, 150, ESCPOSConst.LK_ALIGNMENT_CENTER);
            cpclPrinter.printAndroidFont("Right Alignment", nLineWidth, 24, 180, ESCPOSConst.LK_ALIGNMENT_RIGHT);

            cpclPrinter.printAndroidFont(Typeface.SANS_SERIF, "SANS_SERIF : 1234iwIW", nLineWidth, 24, 210, ESCPOSConst.LK_ALIGNMENT_LEFT);
            cpclPrinter.printAndroidFont(Typeface.SERIF, "SERIF : 1234iwIW", nLineWidth, 24, 240, ESCPOSConst.LK_ALIGNMENT_LEFT);
            cpclPrinter.printAndroidFont(typeface.MONOSPACE, "MONOSPACE : 1234iwIW", nLineWidth, 24, 270, ESCPOSConst.LK_ALIGNMENT_LEFT);

            // Print
            cpclPrinter.printForm();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void printMultilingualFont(int count) throws UnsupportedEncodingException
    {
        int nLineWidth = 832;
        String Koreandata = "영수증";
        String Turkishdata = "Turkish(İ,Ş,Ğ)";
        String Russiandata = "Получение";
        String Arabicdata = "الإيصال";
        String Greekdata = "Παραλαβή";
        String Japanesedata = "領収書";
        String GB2312data = "收据";
        String BIG5data = "收據";

        try
        {
            cpclPrinter.setForm(0, 200, 200, 1000, count);
            cpclPrinter.setMedia(paperType);

            cpclPrinter.printAndroidFont("Korean Font", nLineWidth, 24, 0, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // Korean 100-dot size font in android device.
            cpclPrinter.printAndroidFont(Koreandata, nLineWidth, 100, 30, ESCPOSConst.LK_ALIGNMENT_CENTER);

            cpclPrinter.printAndroidFont("Turkish Font", nLineWidth, 24, 140, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // Turkish 50-dot size font in android device.
            cpclPrinter.printAndroidFont(Turkishdata, nLineWidth, 50, 170, ESCPOSConst.LK_ALIGNMENT_CENTER);

            cpclPrinter.printAndroidFont("Russian Font", nLineWidth, 24, 230, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // Russian 60-dot size font in android device.
            cpclPrinter.printAndroidFont(Russiandata, nLineWidth, 60, 260, ESCPOSConst.LK_ALIGNMENT_CENTER);

            cpclPrinter.printAndroidFont("Arabic Font", nLineWidth, 24, 330, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // Arabic 100-dot size font in android device.
            cpclPrinter.printAndroidFont(Arabicdata, nLineWidth, 100, 360, ESCPOSConst.LK_ALIGNMENT_CENTER);

            cpclPrinter.printAndroidFont("Greek Font", nLineWidth, 24, 470, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // Greek 60-dot size font in android device.
            cpclPrinter.printAndroidFont(Greekdata, nLineWidth, 60, 500, ESCPOSConst.LK_ALIGNMENT_CENTER);

            cpclPrinter.printAndroidFont("Japanese Font", nLineWidth, 24, 570, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // Japanese 100-dot size font in android device.
            cpclPrinter.printAndroidFont(Japanesedata, nLineWidth, 100, 600, ESCPOSConst.LK_ALIGNMENT_CENTER);

            cpclPrinter.printAndroidFont("GB2312 Font", nLineWidth, 24, 710, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // GB2312 100-dot size font in android device.
            cpclPrinter.printAndroidFont(GB2312data, nLineWidth, 100, 740, ESCPOSConst.LK_ALIGNMENT_CENTER);

            cpclPrinter.printAndroidFont("BIG5 Font", nLineWidth, 24, 850, ESCPOSConst.LK_ALIGNMENT_LEFT);
            // BIG5 100-dot size font in android device.
            cpclPrinter.printAndroidFont(BIG5data, nLineWidth, 100, 880, ESCPOSConst.LK_ALIGNMENT_CENTER);

            // Print
            cpclPrinter.printForm();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
