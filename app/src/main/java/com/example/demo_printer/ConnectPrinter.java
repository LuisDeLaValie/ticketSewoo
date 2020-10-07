package com.example.demo_printer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sewoo.port.android.BluetoothPort;
import com.sewoo.port.android.DeviceConnection;
import com.sewoo.request.android.RequestHandler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Vector;

public class ConnectPrinter extends AppCompatActivity {

    private static final String TAG = "BluetoothConnectMenu";

    // Intent request codes
    // private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;


    //asd
    private ArrayAdapter<String> adapter;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothPort bluetoothPort;
    private Vector<BluetoothDevice> remoteDevices;
    private Thread hThread;


    //componentes
    private Button buscarButton;
    private Button imprimirButton;
    private Button conectarButton;
    private ListView impesoras;
    private TextView cogigo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_printer);

        bluetoothPort = BluetoothPort.getInstance();
    }

 void pirnt(){
     CPCL cpcl = new CPCL();
     cpcl.selectContinuousPaper();
     boolean con = bluetoothPort.isConnected();
    int chec = cpcl.printerCheck();
    int stat = cpcl.status();
     try {
         cpcl.barcode4(1);
     } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
     }
 }
    public void conct(View v){

        if(bluetoothPort.isConnected()) {
            try {
                bluetoothPort.disconnect();
                bluetoothPort.connect("88:6B:0F:DD:6B:C9");
                int mod=bluetoothPort.getModel();
                pirnt();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }else{
            try {
                bluetoothPort.connect("88:6B:0F:DD:6B:C9");
                int mod=bluetoothPort.getModel();
                pirnt();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        try {
            bluetoothPort.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}