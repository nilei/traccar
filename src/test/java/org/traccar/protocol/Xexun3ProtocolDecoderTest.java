package org.traccar.protocol;

import org.junit.jupiter.api.Test;
import org.traccar.ProtocolTest;

public class Xexun3ProtocolDecoderTest extends ProtocolTest {

    @Test
    public void testDecode() throws Exception {

        var decoder = inject(new Xexun3ProtocolDecoder(null));

        verifyNull(decoder, binary(
                "fc000b03200108610450803870158318cf"));

        verifyNotNull(decoder, binary(
                "fc0040032006086104508038701564216913f223403693012f635344405c829142b302f7427f33331a2e000000a40011046a1055ffff1f0000000000ffffff04ff09ff1a30cf"));

        verifyAttribute(decoder, binary(
                "fc00490320e8086259608092620164226aa92f624049b01fff79c842401c97788f16414443084ccd400e19001c009f000e006a18630000173a2f0001a3ffffffffffffff6aa92f622101000030f4cf"),
                "course", 15.9);

    }

}
