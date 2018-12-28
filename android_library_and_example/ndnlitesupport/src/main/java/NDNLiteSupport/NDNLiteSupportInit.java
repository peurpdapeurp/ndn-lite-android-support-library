package NDNLiteSupport;

import java.security.Security;

public class NDNLiteSupportInit {
    public static void NDNLiteSupportInit() {
        // this line is to allow for the use of various spongycastle API's in the SecurityHelpers
        // see https://stackoverflow.com/questions/6898801/how-to-include-the-spongy-castle-jar-in-android
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);

    }
}
