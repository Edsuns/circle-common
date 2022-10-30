package io.github.edsuns.net;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

/**
 * Created by Edsuns@qq.com on 2022/6/24.
 */
@ParametersAreNonnullByDefault
public final class Hosts {
    private Hosts() {
        // private
    }

    @Nullable
    public static InetAddress getSiteLocalAddress() {
        DatagramSocket datagramSocket = null;
        Socket socket = null;
        try {
            // If the device is completely offline, the code after this line of code is redundant,
            // and this line of code will throw a UnknownHostException to break them.
            InetAddress remote = InetAddress.getByName("connectivitycheck.gstatic.com");

            InetAddress address;

            // get SiteLocalAddress by DatagramSocket
            datagramSocket = new DatagramSocket();
            datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 53);
            address = datagramSocket.getLocalAddress();
            if (address.isSiteLocalAddress())
                return address;

            SocketAddress socketAddress = new InetSocketAddress(remote, 80);
            // get SiteLocalAddress by Socket
            socket = new Socket();
            socket.connect(socketAddress, 600);
            address = socket.getLocalAddress();
            if (address.isSiteLocalAddress())
                return address;

            // looking for SiteLocalAddress in NetworkInterfaces
            // notice: this solution has poor performance because all NetworkInterfaces are traversed
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = allNetInterfaces.nextElement();

                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                }

                Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    address = addresses.nextElement();
                    if (address.isSiteLocalAddress()) {
                        return address;
                    }
                }
            }
        } catch (IOException ignored) {
        } finally {
            if (datagramSocket != null) {
                datagramSocket.close();
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ignored) {
                }
            }
        }

        try {
            InetAddress fallback = InetAddress.getLocalHost();
            if (fallback.isSiteLocalAddress())
                return fallback;
        } catch (UnknownHostException ignored) {
        }
        return null;
    }

    public static int ipv4ToInt(String ip) throws NumberFormatException {
        int result = 0;
        int i = 0, j = 0;
        while (i < ip.length()) {
            if (j == ip.length() || ip.charAt(j) == '.') {
                result <<= 8;
                result |= Integer.parseInt(ip, i, j, 10);
                i = j + 1;
            }
            j++;
        }
        return result;
    }

    public static String intToIpv4(int ip) {
        StringBuilder builder = new StringBuilder(15);
        int i = 32;
        while ((i -= 8) >= 0) {
            if (builder.length() > 0) builder.append('.');
            builder.append((ip & (0xff << i)) >>> i);
        }
        return builder.toString();
    }
}
