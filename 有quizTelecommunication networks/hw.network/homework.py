#  sudo apt-get install bridge-utils

'''

1
st task (1 point): The s1-s5 switches use STP-t. After starting the emulation, it needs about 30 seconds
to set up. Draw the spanning tree and write down the commands used to get this information!
Help: brctl with the correct parameters

2
nd task (2 points): Configure r6-r8 routers’ interfaces in the following way:
r6-s4 direction: 10.0.0.254/8
r6-h5 direction: 12.0.0.254/8
r6-r8 direction: 192.168.1.1/24
r7-s5 direction: 10.0.0.253/8
r7-h6 direction: 13.0.0.254/8
r7-r8 direction: 192.168.2.1/24
r8-r6 direction: 192.168.1.2/24
r8-r7 direction: 192.168.2.2/24
r8-h4 direction: 11.0.0.254/8
1) What commands did you use?
2) Attach the interface configurations (ifconfig).
3) Ping the routers from h1! Which ones answers, and which ones doesn’t? Why?

mininet> links
h1-eth0<->s1-eth1 (OK OK) 
h2-eth0<->s2-eth1 (OK OK) 
h3-eth0<->s3-eth1 (OK OK) 
s1-eth2<->s4-eth1 (OK OK) 
s2-eth2<->s4-eth2 (OK OK) 
s3-eth2<->s5-eth1 (OK OK) 
s2-eth3<->s5-eth2 (OK OK) 
s4-eth3<->s5-eth3 (OK OK) 
s4-eth4<->r6-eth0 (OK OK) 
s5-eth4<->r7-eth0 (OK OK) 
r6-eth1<->r8-eth0 (OK OK) 
r7-eth1<->r8-eth1 (OK OK) 
h5-eth0<->r6-eth2 (OK OK) 
h6-eth0<->r7-eth2 (OK OK) 
h4-eth0<->r8-eth2 (OK OK) 

1)
r6 ifconfig r6-eth0 10.0.0.254 netmask 255.0.0.0
r6 ifconfig r6-eth2 12.0.0.254 netmask 255.0.0.0
r6 ifconfig r6-eth1 192.168.1.1 netmask 255.255.255.0

r7 ifconfig  r7-eth0  10.0.0.253 netmask 255.0.0.0
r7 ifconfig r7-eth2 13.0.0.254 netmask 255.0.0.0
r7 ifconfig r7-eth1 192.168.2.1 netmask 255.255.255.0

r8 ifconfig r8-eth0  192.168.1.2 netmask 255.255.255.0
r8 ifconfig r8-eth1 192.168.2.2 netmask 255.255.255.0
r8 ifconfig r8-eth2 11.0.0.254  netmask 255.0.0.0

2)
mininet> r6 ifconfig 
lo        Link encap:Local Loopback  
          inet addr:127.0.0.1  Mask:255.0.0.0
          inet6 addr: ::1/128 Scope:Host
          UP LOOPBACK RUNNING  MTU:65536  Metric:1
          RX packets:0 errors:0 dropped:0 overruns:0 frame:0
          TX packets:0 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1 
          RX bytes:0 (0.0 B)  TX bytes:0 (0.0 B)

r6-eth0   Link encap:Ethernet  HWaddr c6:38:af:25:d9:b1  
          inet addr:10.0.0.254  Bcast:10.255.255.255  Mask:255.0.0.0
          inet6 addr: fe80::c438:afff:fe25:d9b1/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:533 errors:0 dropped:0 overruns:0 frame:0
          TX packets:8 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:39080 (39.0 KB)  TX bytes:648 (648.0 B)

r6-eth1   Link encap:Ethernet  HWaddr 2e:5d:84:80:bc:a6  
          inet addr:192.168.1.1  Bcast:192.168.1.255  Mask:255.255.255.0
          inet6 addr: fe80::2c5d:84ff:fe80:bca6/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:8 errors:0 dropped:0 overruns:0 frame:0
          TX packets:8 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:648 (648.0 B)  TX bytes:648 (648.0 B)

r6-eth2   Link encap:Ethernet  HWaddr 06:e5:eb:5d:c9:7c  
          inet addr:12.0.0.254  Bcast:12.255.255.255  Mask:255.0.0.0
          inet6 addr: fe80::4e5:ebff:fe5d:c97c/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:8 errors:0 dropped:0 overruns:0 frame:0
          TX packets:8 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:648 (648.0 B)  TX bytes:648 (648.0 B)
mininet> r7 ifconfig 
lo        Link encap:Local Loopback  
          inet addr:127.0.0.1  Mask:255.0.0.0
          inet6 addr: ::1/128 Scope:Host
          UP LOOPBACK RUNNING  MTU:65536  Metric:1
          RX packets:0 errors:0 dropped:0 overruns:0 frame:0
          TX packets:0 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1 
          RX bytes:0 (0.0 B)  TX bytes:0 (0.0 B)

r7-eth0   Link encap:Ethernet  HWaddr ea:72:05:00:d0:87  
          inet addr:10.0.0.253  Bcast:10.255.255.255  Mask:255.0.0.0
          inet6 addr: fe80::e872:5ff:fe00:d087/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:543 errors:0 dropped:0 overruns:0 frame:0
          TX packets:8 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:39524 (39.5 KB)  TX bytes:648 (648.0 B)

r7-eth1   Link encap:Ethernet  HWaddr 76:33:b3:00:49:a0  
          inet addr:192.168.2.1  Bcast:192.168.2.255  Mask:255.255.255.0
          inet6 addr: fe80::7433:b3ff:fe00:49a0/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:10 errors:0 dropped:0 overruns:0 frame:0
          TX packets:8 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:828 (828.0 B)  TX bytes:648 (648.0 B)

r7-eth2   Link encap:Ethernet  HWaddr ba:f6:4d:fe:c1:76  
          inet addr:13.0.0.254  Bcast:13.255.255.255  Mask:255.0.0.0
          inet6 addr: fe80::b8f6:4dff:fefe:c176/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:8 errors:0 dropped:0 overruns:0 frame:0
          TX packets:8 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:648 (648.0 B)  TX bytes:648 (648.0 B)

mininet> r7 ifconfig 
lo        Link encap:Local Loopback  
          inet addr:127.0.0.1  Mask:255.0.0.0
          inet6 addr: ::1/128 Scope:Host
          UP LOOPBACK RUNNING  MTU:65536  Metric:1
          RX packets:0 errors:0 dropped:0 overruns:0 frame:0
          TX packets:0 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1 
          RX bytes:0 (0.0 B)  TX bytes:0 (0.0 B)

r7-eth0   Link encap:Ethernet  HWaddr ea:72:05:00:d0:87  
          inet addr:10.0.0.253  Bcast:10.255.255.255  Mask:255.0.0.0
          inet6 addr: fe80::e872:5ff:fe00:d087/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:543 errors:0 dropped:0 overruns:0 frame:0
          TX packets:8 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:39524 (39.5 KB)  TX bytes:648 (648.0 B)

r7-eth1   Link encap:Ethernet  HWaddr 76:33:b3:00:49:a0  
          inet addr:192.168.2.1  Bcast:192.168.2.255  Mask:255.255.255.0
          inet6 addr: fe80::7433:b3ff:fe00:49a0/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:10 errors:0 dropped:0 overruns:0 frame:0
          TX packets:8 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:828 (828.0 B)  TX bytes:648 (648.0 B)

r7-eth2   Link encap:Ethernet  HWaddr ba:f6:4d:fe:c1:76  
          inet addr:13.0.0.254  Bcast:13.255.255.255  Mask:255.0.0.0
          inet6 addr: fe80::b8f6:4dff:fefe:c176/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:8 errors:0 dropped:0 overruns:0 frame:0
          TX packets:8 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:648 (648.0 B)  TX bytes:648 (648.0 B)

mininet> r8 ifconfig 
lo        Link encap:Local Loopback  
          inet addr:127.0.0.1  Mask:255.0.0.0
          inet6 addr: ::1/128 Scope:Host
          UP LOOPBACK RUNNING  MTU:65536  Metric:1
          RX packets:0 errors:0 dropped:0 overruns:0 frame:0
          TX packets:0 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1 
          RX bytes:0 (0.0 B)  TX bytes:0 (0.0 B)

r8-eth0   Link encap:Ethernet  HWaddr 6a:70:f6:72:51:d1  
          inet addr:192.168.1.2  Bcast:192.168.1.255  Mask:255.255.255.0
          inet6 addr: fe80::6870:f6ff:fe72:51d1/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:8 errors:0 dropped:0 overruns:0 frame:0
          TX packets:8 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:648 (648.0 B)  TX bytes:648 (648.0 B)

r8-eth1   Link encap:Ethernet  HWaddr 72:76:93:8e:e7:aa  
          inet addr:192.168.2.2  Bcast:192.168.2.255  Mask:255.255.255.0
          inet6 addr: fe80::7076:93ff:fe8e:e7aa/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:8 errors:0 dropped:0 overruns:0 frame:0
          TX packets:10 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:648 (648.0 B)  TX bytes:828 (828.0 B)

r8-eth2   Link encap:Ethernet  HWaddr b2:04:cb:3a:46:54  
          inet addr:11.0.0.254  Bcast:11.255.255.255  Mask:255.0.0.0
          inet6 addr: fe80::b004:cbff:fe3a:4654/64 Scope:Link
          UP BROADCAST RUNNING MULTICAST  MTU:1500  Metric:1
          RX packets:8 errors:0 dropped:0 overruns:0 frame:0
          TX packets:8 errors:0 dropped:0 overruns:0 carrier:0
          collisions:0 txqueuelen:1000 
          RX bytes:648 (648.0 B)  TX bytes:648 (648.0 B)

3)



mininet> h1 ping r6
PING 10.0.0.254 (10.0.0.254) 56(84) bytes of data.
64 bytes from 10.0.0.254: icmp_seq=1 ttl=64 time=0.219 ms
64 bytes from 10.0.0.254: icmp_seq=2 ttl=64 time=0.111 ms
^C
--- 10.0.0.254 ping statistics ---
2 packets transmitted, 2 received, 0% packet loss, time 1007ms
rtt min/avg/max/mdev = 0.111/0.165/0.219/0.054 ms
mininet> h1 ping r7
PING 10.0.0.253 (10.0.0.253) 56(84) bytes of data.
64 bytes from 10.0.0.253: icmp_seq=1 ttl=64 time=0.089 ms
64 bytes from 10.0.0.253: icmp_seq=2 ttl=64 time=0.132 ms
^C
--- 10.0.0.253 ping statistics ---
2 packets transmitted, 2 received, 0% packet loss, time 1009ms
rtt min/avg/max/mdev = 0.089/0.110/0.132/0.023 ms
mininet> h1 ping r8
connect: Network is unreachable



3
rd task (1 point): Set up the routing tables to be able to ping anything from anywhere. (In mininet
terminal pingall command does the full test)!
1) The h1 and h2 use the r6 router for all outgoing traffic, while the h3 uses the r7 router! r8-
router routes the 10.0.0.0/8 traffic to r6 direction! For the others the table setups are trivial.
2) What are the given commands? Attach the routing tables for each host and router. (route –n).




'''