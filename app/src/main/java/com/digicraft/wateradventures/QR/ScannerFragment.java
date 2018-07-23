package com.digicraft.wateradventures.QR;


import android.content.Context;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.digicraft.wateradventures.R;
import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScannerFragment extends Fragment  implements QRCodeReaderView.OnQRCodeReadListener {


    public ScannerFragment() {
        // Required empty public constructor
    }

    Opener opener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        opener = (Opener) context;
    }

    private QRCodeReaderView qrCodeReaderView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_scanner, container, false); ;

        qrCodeReaderView = (QRCodeReaderView) root.findViewById(R.id.qrdecoderview);
        qrCodeReaderView.setOnQRCodeReadListener(this);

        // Use this function to enable/disable decoding
        qrCodeReaderView.setQRDecodingEnabled(true);

        // Use this function to change the autofocus interval (default is 5 secs)
        qrCodeReaderView.setAutofocusInterval(2000L);

        // Use this function to enable/disable Torch
        qrCodeReaderView.setTorchEnabled(true);

        // Use this function to set front camera preview
      //  qrCodeReaderView.setFrontCamera();

        // Use this function to set back camera preview
        qrCodeReaderView.setBackCamera();

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
            showToast(text);
            opener.goToQrFragment();
    }


    public void showToast(String msg)
    {
        Toast.makeText(getActivity() , msg , Toast.LENGTH_SHORT).show();
    }

}
