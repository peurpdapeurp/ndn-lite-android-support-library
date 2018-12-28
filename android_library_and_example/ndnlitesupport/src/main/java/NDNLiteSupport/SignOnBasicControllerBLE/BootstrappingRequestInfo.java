package NDNLiteSupport.SignOnBasicControllerBLE;

import NDNLiteSupport.SignOnBasicControllerBLE.secureSignOn.SignOnControllerResults;

import java.util.UUID;

public class BootstrappingRequestInfo {

    BootstrappingRequestInfo(byte[] bootstrappingRequest,
                             SignOnControllerResults.ProcessSignOnMessageResult processResult,
                             UUID serviceUuid) {
        this.bootstrappingRequest = bootstrappingRequest;
        this.processResult = processResult;
        this.serviceUuid = serviceUuid;
    }

    byte[] bootstrappingRequest;
    SignOnControllerResults.ProcessSignOnMessageResult processResult;
    UUID serviceUuid;
}
