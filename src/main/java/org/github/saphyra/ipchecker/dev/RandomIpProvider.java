package org.github.saphyra.ipchecker.dev;

import org.github.saphyra.ipchecker.checker.IpProvider;

public class RandomIpProvider implements IpProvider {
    private static final String IP_1 = "ip_1";
    private static final String IP_2 = "ip_2";

    @Override
    public String getIp() {
        return Math.random() < 0.8 ? IP_1 : IP_2;
    }
}
