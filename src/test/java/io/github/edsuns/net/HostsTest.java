package io.github.edsuns.net;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Edsuns@qq.com on 2022/6/24.
 */
class HostsTest {

    public static void main(String[] args) {
        System.out.println(Hosts.getSiteLocalAddress());
    }

    @Test
    void getSiteLocalAddress() throws IOException {
        long start = System.nanoTime();
        InetAddress address = Hosts.getSiteLocalAddress();
        assertNotNull(address);
        assertTrue((System.nanoTime() - start) < TimeUnit.MILLISECONDS.toNanos(100L));
        assertTrue(address.isReachable(1000));
    }

    @Test
    void ipv4ToInt() {
        assertEquals(0, Hosts.ipv4ToInt("0.0.0.0"));
        assertEquals(1, Hosts.ipv4ToInt("0.0.0.1"));
        assertEquals(10 << 8, Hosts.ipv4ToInt("0.0.10.0"));
        assertEquals((172 << 24) | (60 << 16) | 7, Hosts.ipv4ToInt("172.60.0.7"));
        assertEquals((255 << 24) | (60 << 16) | 7, Hosts.ipv4ToInt("255.60.0.7"));
    }

    @Test
    void intToIpv4() {
        assertEquals("0.0.0.0", Hosts.intToIpv4(0));
        assertEquals("0.0.0.1", Hosts.intToIpv4(1));
        assertEquals("0.0.10.0", Hosts.intToIpv4(10 << 8));
        assertEquals("172.60.0.7", Hosts.intToIpv4((172 << 24) | (60 << 16) | 7));
        assertEquals("255.60.0.7", Hosts.intToIpv4((255 << 24) | (60 << 16) | 7));
    }
}