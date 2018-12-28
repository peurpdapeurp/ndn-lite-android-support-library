package NDNLiteSupport.BLEFace;

public class BLEFaceConsts {

    public static final long BLE_FACE_FAILURE_RETURN_CODE = -1;
    public static final long BLE_FACE_SUCCESS_RETURN_CODE = 1;

    // this is based on the maximum amount of data that I was able to send between
    // the Android phone and an nRF52840 board, after having done mtu negotiation for the
    // maximum characteristic value size of 512
    // (https://stackoverflow.com/questions/38741928/what-is-the-length-limit-for-ble-characteristics
    public static final int BLE_FACE_MAX_PACKET_SIZE = 509;
}
