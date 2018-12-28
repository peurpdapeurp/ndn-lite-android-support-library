package NDNLiteSupport.SignOnBasicControllerBLE.secureSignOn.tlv;

public class secureSignOnTlvResults {

    public static class ParseTlvValueResult {

        public ParseTlvValueResult(secureSignOnTlvResultCodes.ParseTlvValueResultCode resultCode, byte[] tlvValue, int tlvLength) {
            this.resultCode = resultCode;
            this.tlvValue = tlvValue;
            this.tlvLength = tlvLength;
        }

        public ParseTlvValueResult(secureSignOnTlvResultCodes.ParseTlvValueResultCode resultCode) {
            this.resultCode = resultCode;
        }

        public int tlvLength;
        public byte[] tlvValue;
        public secureSignOnTlvResultCodes.ParseTlvValueResultCode resultCode;

    }

}
