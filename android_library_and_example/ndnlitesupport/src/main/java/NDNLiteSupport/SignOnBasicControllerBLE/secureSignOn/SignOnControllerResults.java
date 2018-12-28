package NDNLiteSupport.SignOnBasicControllerBLE.secureSignOn;

public class SignOnControllerResults {

    public static class ProcessSignOnMessageResult {
        public ProcessSignOnMessageResult(SignOnControllerResultCodes.SignOnControllerResultCode resultCode,
                                          int signOnMessageTlvType,
                                          String deviceIdentifierHexString) {
            this.resultCode = resultCode;
            this.signOnMessageTlvType = signOnMessageTlvType;
            this.deviceIdentifierHexString = deviceIdentifierHexString;
        }

        public ProcessSignOnMessageResult(SignOnControllerResultCodes.SignOnControllerResultCode resultCode,
                                          int signOnMessageTlvType) {
            this.resultCode = resultCode;
            this.signOnMessageTlvType = signOnMessageTlvType;
        }

        public ProcessSignOnMessageResult(SignOnControllerResultCodes.SignOnControllerResultCode resultCode,
                                          String deviceIdentifierHexString) {
            this.resultCode = resultCode;
            this.deviceIdentifierHexString = deviceIdentifierHexString;
        }

        public ProcessSignOnMessageResult(SignOnControllerResultCodes.SignOnControllerResultCode resultCode) {
            this.resultCode = resultCode;
        }

        public SignOnControllerResultCodes.SignOnControllerResultCode resultCode;
        public int signOnMessageTlvType;
        public String deviceIdentifierHexString;
    }

    public static class ConstructBootstrappingRequestResponseResult {
        public ConstructBootstrappingRequestResponseResult(
                SignOnControllerResultCodes.SignOnControllerResultCode resultCode,
                                                           String deviceIdentifierHexString,
                                                           byte[] bootstrappingRequestResult) {
            this.resultCode = resultCode;
            this.deviceIdentifierHexString = deviceIdentifierHexString;
            this.bootstrappingRequestResponse = bootstrappingRequestResult;
        }

        public ConstructBootstrappingRequestResponseResult(
                SignOnControllerResultCodes.SignOnControllerResultCode resultCode) {
            this.resultCode = resultCode;
        }

        public SignOnControllerResultCodes.SignOnControllerResultCode resultCode;
        public byte[] bootstrappingRequestResponse;
        public String deviceIdentifierHexString;

    }

    public static class ConstructCertificateRequestResponseResult {
        public ConstructCertificateRequestResponseResult(
                SignOnControllerResultCodes.SignOnControllerResultCode resultCode,
                                                         String deviceIdentifierHexString,
                                                         byte[] certificateRequestResponse) {
            this.resultCode = resultCode;
            this.deviceIdentifierHexString = deviceIdentifierHexString;
            this.certificateRequestResponse = certificateRequestResponse;
        }

        public ConstructCertificateRequestResponseResult(
                SignOnControllerResultCodes.SignOnControllerResultCode resultCode) {
            this.resultCode = resultCode;
        }

        public SignOnControllerResultCodes.SignOnControllerResultCode resultCode;
        public String deviceIdentifierHexString;
        public byte[] certificateRequestResponse;
    }

}
