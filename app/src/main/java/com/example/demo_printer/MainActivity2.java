package com.example.demo_printer;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.sewoo.port.android.BluetoothPort;

import java.util.Iterator;
import java.util.Vector;

public class MainActivity2 extends AppCompatActivity {


    // Intent request codes
    // private static final int REQUEST_CONNECT_DEVICE = 1;
    private static final int REQUEST_ENABLE_BT = 2;

    ArrayAdapter<String> adapter;
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothPort bluetoothPort;
    private Vector<BluetoothDevice> remoteDevices;
    // Set up Bluetooth.
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

            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }

    // clear device data used list.
    private void clearBtDevData()
    {
        remoteDevices = new Vector<BluetoothDevice>();
    }
    // add paired device to list
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
    }

}