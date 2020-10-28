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
import android.provider.MediaStore;
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
    private TextView status;

    private void bluetoothSetup()
    {
        // Initialize
        clearBtDevData();
        bluetoothPort = BluetoothPort.getInstance();
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null)
        {
            // Device does not support Bluetooth
            return;
        }
        if (!bluetoothAdapter.isEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

            startActivityForResult(enableBtIntent, 2);
        }
    }


    private void clearBtDevData()
    {
        remoteDevices = new Vector<BluetoothDevice>();
    }


    private void addPairedDevices()
    {
        BluetoothDevice pairedDevice;
        Iterator<BluetoothDevice> iter = (bluetoothAdapter.getBondedDevices()).iterator();
        while(iter.hasNext())
        {
            pairedDevice = iter.next();
            if(bluetoothPort.isValidAddress(pairedDevice.getAddress()))
            {
                remoteDevices.add(pairedDevice);
                adapter.add(pairedDevice.getName() +"\n["+pairedDevice.getAddress()+"] [Paired]");
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect_printer);

        cogigo = (TextView) findViewById(R.id.device_concect);
        status = (TextView) findViewById(R.id.status);
        conectarButton =(Button) findViewById(R.id.decelerate);
        impesoras = findViewById(R.id.impresoras);
        imprimirButton = (Button) findViewById(R.id.imprimir);

        bluetoothSetup();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        impesoras.setAdapter(adapter);
        addPairedDevices();

        impesoras.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                BluetoothDevice btDev = remoteDevices.elementAt(arg2);
                if(bluetoothAdapter.isDiscovering())
                {
                    bluetoothAdapter.cancelDiscovery();
                }
                cogigo.setText(btDev.getAddress());
                btConn(btDev);
            }
        });

        registerReceiver(discoveryResult, new IntentFilter(BluetoothDevice.ACTION_FOUND));
    }

    public BroadcastReceiver discoveryResult = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String key;
            BluetoothDevice remoteDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if(remoteDevice != null)
            {
                if(remoteDevice.getBondState() != BluetoothDevice.BOND_BONDED)
                {
                    key = remoteDevice.getName() +"\n["+remoteDevice.getAddress()+"]";
                }
                else
                {
                    key = remoteDevice.getName() +"\n["+remoteDevice.getAddress()+"] [Pairedd]";
                }
                if(bluetoothPort.isValidAddress(remoteDevice.getAddress()))
                {
                    remoteDevices.add(remoteDevice);
                    adapter.add(key);
                }
            }
        }
    };


    private void btConn(final BluetoothDevice btDev){

        if(bluetoothPort.isConnected()){
            try {
                bluetoothPort.connect(btDev);
                RequestHandlerON();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {

                bluetoothPort.disconnect();
                RequestHandlerOFF();

                bluetoothPort.connect(btDev);
                RequestHandlerON();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public void buscar(View v){
        if (!bluetoothAdapter.isDiscovering())
        {
            clearBtDevData();
            adapter.clear();
            bluetoothAdapter.startDiscovery();
        }
        else
        {
            bluetoothAdapter.cancelDiscovery();
        }
    }

   public void imprimir(View v){
        pirnt();
    }

    public void desconectar(View v){
        if(bluetoothPort.isConnected()){
            try {
                bluetoothPort.disconnect();
                cogigo.setText(" ");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    void RequestHandlerON(){
        RequestHandler rh = new RequestHandler();
        hThread = new Thread(rh);
        hThread.start();
    }

    void RequestHandlerOFF(){
        if((hThread != null) && (hThread.isAlive()))
            hThread.interrupt();
    }


void pirnt(){
    ESCPSample sample = new ESCPSample();
    sample.propio();


     /*try {
        sample.barcodesample();
    } catch (IOException e) {
        e.printStackTrace();
    }*/
}




    @Override
    protected void onDestroy() {
        super.onDestroy();
        RequestHandlerOFF();
        try {
            bluetoothPort.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}