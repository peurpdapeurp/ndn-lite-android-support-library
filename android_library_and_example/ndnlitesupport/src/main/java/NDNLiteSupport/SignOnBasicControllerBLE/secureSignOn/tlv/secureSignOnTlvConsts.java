package NDNLiteSupport.SignOnBasicControllerBLE.secureSignOn.tlv;

public class secureSignOnTlvConsts {

    // since the implementation assumes no TLV block will exceed length 252
    public static final int SECURE_SIGN_ON_TLV_TYPE_SIZE = 1;
    public static final int SECURE_SIGN_ON_TLV_LENGTH_SIZE = 1;
    public static final int SECURE_SIGN_ON_TLV_TYPE_AND_LENGTH_SIZE =
            (SECURE_SIGN_ON_TLV_TYPE_SIZE + SECURE_SIGN_ON_TLV_LENGTH_SIZE);
    public static final int SECURE_SIGN_ON_MAX_TLV_LENGTH = 252;

    // sign-on TLV types for basic
    public static final int SECURE_SIGN_ON_BLE_BOOTSTRAPPING_REQUEST_RESPONSE_TLV_TYPE = 0x00;
    public static final int SECURE_SIGN_ON_BLE_CERTIFICATE_REQUEST_RESPONSE_TLV_TYPE = 0x01;
    public static final int SECURE_SIGN_ON_BLE_BOOTSTRAPPING_REQUEST_TLV_TYPE = 0x02;
    public static final int SECURE_SIGN_ON_BLE_CERTIFICATE_REQUEST_TLV_TYPE = 0x03;

    public static final int SECURE_SIGN_ON_BLE_DEVICE_IDENTIFIER_TLV_TYPE = 0x04;
    public static final int SECURE_SIGN_ON_BLE_DEVICE_CAPABILITIES_TLV_TYPE = 0x05;
    public static final int SECURE_SIGN_ON_BLE_N1_PUB_TLV_TYPE = 0x06;

    public static final int SECURE_SIGN_ON_BLE_SIGNATURE_TLV_TYPE = 0x07;

    public static final int SECURE_SIGN_ON_BLE_N2_PUB_TLV_TYPE = 0x08;
    public static final int SECURE_SIGN_ON_BLE_ANCHOR_CERTIFICATE_TLV_TYPE = 0x09;

    public static final int SECURE_SIGN_ON_BLE_TRUST_ANCHOR_CERTIFICATE_DIGEST_TLV_TYPE = 0x10;
    public static final int SECURE_SIGN_ON_BLE_N2_PUB_DIGEST_TLV_TYPE = 0x11;

    public static final int SECURE_SIGN_ON_BLE_KD_PRI_ENCRYPTED_TLV_TYPE = 0x12;
    public static final int SECURE_SIGN_ON_BLE_KD_PUB_CERTIFICATE_TLV_TYPE = 0x13;

    public static final int SECURE_SIGN_ON_BLE_FINISH_MESSAGE_TLV_TYPE = 0x14;
    public static final int SECURE_SIGN_ON_BLE_FINISH_CODE_TLV_TYPE = 0x15;

}
