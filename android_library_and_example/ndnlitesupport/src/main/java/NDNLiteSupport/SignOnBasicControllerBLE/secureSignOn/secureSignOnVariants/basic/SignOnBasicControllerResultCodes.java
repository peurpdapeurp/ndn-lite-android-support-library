package NDNLiteSupport.SignOnBasicControllerBLE.secureSignOn.secureSignOnVariants.basic;

public class SignOnBasicControllerResultCodes {

    protected enum ParseSignOnMessageResultCode {
        SUCCESS,
        ERROR_PARSING_PACKET_HEADER,
        ERROR_PARSING_DEVICE_IDENTIFIER,
        ERROR_PARSING_DEVICE_CAPABILITIES,
        ERROR_PARSING_N1_PUB,
        ERROR_PARSING_N2_PUB_DIGEST,
        ERROR_PARSING_TRUST_ANCHOR_CERTIFICATE_DIGEST,
        ERROR_PARSING_SIGNATURE,
    }

}
