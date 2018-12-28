package com.example.edwar.ndniotcontroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import net.named_data.jndn.Face;
import net.named_data.jndn.Interest;
import net.named_data.jndn.InterestFilter;
import net.named_data.jndn.Name;
import net.named_data.jndn.OnInterestCallback;
import net.named_data.jndn.security.v2.CertificateV2;
import net.named_data.jndn.util.Blob;

import java.io.IOException;

import NDNLiteSupport.BLEFace.BLEFace;
import NDNLiteSupport.BLEUnicastConnectionMaintainer.BLEUnicastConnectionMaintainer;
import NDNLiteSupport.NDNLiteSupportInit;
import NDNLiteSupport.SignOnBasicControllerBLE.SignOnBasicControllerBLE;
import NDNLiteSupport.SignOnBasicControllerBLE.secureSignOn.SignOnControllerResultCodes;

import static NDNLiteSupport.SignOnBasicControllerBLE.secureSignOn.SignOnControllerConsts.KD_PUB_CERTIFICATE_NAME_PREFIX;
import static NDNLiteSupport.SignOnBasicControllerBLE.secureSignOn.secureSignOnVariants.SecureSignOnVariantStrings.SIGN_ON_VARIANT_BASIC_ECC_256;
import static NDNLiteSupport.SignOnBasicControllerBLE.secureSignOn.utils.SecurityHelpers.asnEncodeRawECPublicKeyBytes;
import static com.example.edwar.ndniotcontroller.HARDCODED_EXPERIMENTATION_SIGN_ON_BLE_ECC_256.*;

public class MainActivity extends AppCompatActivity {

    // Log for tagging.
    private final String TAG = MainActivity.class.getSimpleName();

    // Reference to interact with the secure sign on controller over BLE singleton.
    private SignOnBasicControllerBLE m_SignOnBasicControllerBLE;

    // Reference to interact with the ble unicast connection maintainer. Both the
    // BLEFace and the SignOnBasicControllerBLE object depend on this to proactively
    // maintain connections to devices using the ndn lite library. You MUST initialize
    // this for the SignOnBasicControllerBLE and BLEFace to work; if you do not initialize
    // this, then you will never actually connect to any devices over BLE.
    private BLEUnicastConnectionMaintainer m_BLEUnicastConnectionMaintainer;

    // Reference to manage a BLE face that is created to interact with a device after sign on.
    private BLEFace m_bleFace;

    // References to UI objects.
    private TextView m_log;

    // The device identifier of the example nRF52840, in hex string format.
    private String m_expectedDeviceIdentifierHexString = "010101010101010101010101";

    // Callback for when an interest is received. In this example, the nRf52840 sends an interest to
    // us after sign on is complete, and triggers this callback.
    OnInterestCallback onInterest = new OnInterestCallback() {
        @Override
        public void onInterest(Name prefix, Interest interest, Face face, long interestFilterId,
                               InterestFilter filter) {
            logMessage(TAG, "onInterest got called, prefix of interest: " + prefix.toUri());

            if (prefix.toUri().equals(KD_PUB_CERTIFICATE_NAME_PREFIX + m_expectedDeviceIdentifierHexString)) {
                logMessage(TAG, "Got interest for certificate of device with device identifier: " +
                        m_expectedDeviceIdentifierHexString);

                try {
                    logMessage(TAG, "Responding to interest from device with its certificate...");
                    face.putData(
                            SignOnBasicControllerBLE.getInstance().
                                    getKDPubCertificateOfDevice(m_expectedDeviceIdentifierHexString)
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    SignOnBasicControllerBLE.SecureSignOnBasicControllerBLECallbacks m_secureSignOnBasicControllerBLECallbacks =
            new SignOnBasicControllerBLE.SecureSignOnBasicControllerBLECallbacks() {

                @Override
                public void onDeviceSignOnComplete(String deviceIdentifierHexString) {
                    logMessage(TAG, "Onboarding was successful for device with device identifier hex string : " +
                            deviceIdentifierHexString);
                    logMessage(TAG, "Mac address of device succesfully onboarded: " +
                            m_SignOnBasicControllerBLE.getMacAddressOfDevice(deviceIdentifierHexString));
                    logMessage(TAG, "Name of device's KDPubCertificate: " +
                            m_SignOnBasicControllerBLE.getKDPubCertificateOfDevice(deviceIdentifierHexString)
                            .getName().toUri()
                    );

                    // Create a BLE face to the device that onboarding completed successfully for.
                    m_bleFace = new BLEFace(m_SignOnBasicControllerBLE.getMacAddressOfDevice(deviceIdentifierHexString),
                            onInterest);
                }

                @Override
                public void onDeviceSignOnError(String deviceIdentifierHexString,
                                                       SignOnControllerResultCodes.SignOnControllerResultCode resultCode) {
                    if (deviceIdentifierHexString != null) {
                        logMessage(TAG, "Sign on error for device with device identifier hex string : " + deviceIdentifierHexString +
                                " and mac address " + m_SignOnBasicControllerBLE.getMacAddressOfDevice(deviceIdentifierHexString) + "\n" +
                                "SignOnControllerResultCode: " + resultCode);
                    }
                    else {
                        Log.w(TAG, "Sign on error for unknown device." + "\n" +
                        "SignOnControllerResultCode: " + resultCode);
                    }
                }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Iniitalize elements of the UI.
        initializeUI();

        logMessage(TAG, "Initializing " + getString(R.string.app_name) + " example...");

        NDNLiteSupportInit.NDNLiteSupportInit();

        CertificateV2 trustAnchorCertificate = new CertificateV2();

        // initializing the BLEUnicastConnectionMaintainer
        // (YOU MUST DO THIS FOR SecureSignOnControllerBLE AND BLEFace TO FUNCTION AT ALL)
        m_BLEUnicastConnectionMaintainer = BLEUnicastConnectionMaintainer.getInstance();
        m_BLEUnicastConnectionMaintainer.initialize(this);

        // initializing the SignOnControllerBLE
        m_SignOnBasicControllerBLE = SignOnBasicControllerBLE.getInstance();
        m_SignOnBasicControllerBLE.initialize(SIGN_ON_VARIANT_BASIC_ECC_256,
                m_secureSignOnBasicControllerBLECallbacks, trustAnchorCertificate);

        // Creating a certificate from the device's preknown KS key pair public key.
        CertificateV2 KSpubCertificateDevice1 = new CertificateV2();
        try {
            KSpubCertificateDevice1.setContent(
                    new Blob(asnEncodeRawECPublicKeyBytes(BOOTSTRAP_ECC_PUBLIC_NO_POINT_IDENTIFIER))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Adding the device to the SignOnControllerBLE's list of devices pending onboarding; if
        // this is not done, the SignOnControllerBLE would ignore bootstrapping requests from the
        // device.
        m_SignOnBasicControllerBLE.addDevicePendingSignOn(KSpubCertificateDevice1, DEVICE_IDENTIFIER,
                SECURE_SIGN_ON_CODE);

    }

    private void initializeUI() {
        m_log = (TextView) findViewById(R.id.ui_log);

    }

    private void logMessage(String TAG, String msg) {
        Log.d(TAG, msg);
        logMessageUI(TAG, msg);
    }

    private void logMessageUI(final String TAG, final String msg) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                m_log.append(TAG + ":" + "\n");
                m_log.append(msg + "\n");
                m_log.append("------------------------------" + "\n");
            }
        });
    }

}
