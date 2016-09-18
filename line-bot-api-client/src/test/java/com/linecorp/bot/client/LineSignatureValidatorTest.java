/*
 * Copyright 2016 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.bot.client;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

public class LineSignatureValidatorTest {
    private static final String channelSecret = "SECRET";

    @Test
    public void validateSignature() throws Exception {
        LineSignatureValidator lineSignatureValidator = new LineSignatureValidator(
                channelSecret.getBytes(StandardCharsets.UTF_8));

        String httpRequestBody = "{}";
        assertTrue(lineSignatureValidator
                           .validateSignature(httpRequestBody.getBytes(StandardCharsets.UTF_8),
                                              "3q8QXTAGaey18yL8FWTqdVlbMr6hcuNvM4tefa0o9nA="
                           ));
        assertFalse(lineSignatureValidator
                            .validateSignature(httpRequestBody.getBytes(StandardCharsets.UTF_8),
                                               "596359635963"
                            ));
    }

    @Test
    public void generateSignature() throws Exception {
        LineSignatureValidator lineSignatureValidator = new LineSignatureValidator(
                channelSecret.getBytes(StandardCharsets.UTF_8));

        String httpRequestBody = "{}";
        byte[] headerSignature = lineSignatureValidator
                .generateSignature(httpRequestBody.getBytes(StandardCharsets.UTF_8));

        assertEquals("3q8QXTAGaey18yL8FWTqdVlbMr6hcuNvM4tefa0o9nA=",
                     Base64.encodeBase64String(headerSignature));
    }

}