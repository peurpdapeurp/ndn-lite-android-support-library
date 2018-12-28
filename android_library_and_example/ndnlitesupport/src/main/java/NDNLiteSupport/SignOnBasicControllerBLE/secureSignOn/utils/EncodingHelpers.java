package NDNLiteSupport.SignOnBasicControllerBLE.secureSignOn.utils;

public class EncodingHelpers {

    private final static String TAG = EncodingHelpers.class.getSimpleName();

    public static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];
        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }
        return b;
    }

}
