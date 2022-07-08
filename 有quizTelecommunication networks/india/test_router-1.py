#!/usr/bin/python
from mininet.net import Mininet
from mininet.cli import CLI
from mininet.link import Link, TCLink, Intf
from mininet.log import setLogLevel, info
from mininet.node import CPULimitedHost, Host, Node
def myNetwork():
	net = Mininet( topo=None,
			build=False,
			ipBase='10.0.0.0/8',
			link=TCLink)
	info( '*** Adding controller\n' )
	info( '*** Add switches\n')
	info( '*** Add hosts\n')
	h1 = net.addHost('h1', cls=Host, ip='10.0.10.2', defaultRoute=None)
	h2 = net.addHost('h2', cls=Host, ip='10.0.10.1', defaultRoute=None)
	h3 = net.addHost('h3', cls=Host, ip='10.0.20.2', defaultRoute=None)
	info( '*** Add links\n')
	Link(h1, h2)
	Link(h2,h3,intfName1='h2-eth1')
	info( '*** Starting network\n')
	net.build()
	info( '*** Starting controllers\n')
	for controller in net.controllers:
		controller.start()
	info( '*** Starting switches\n')
	info( '*** Post configure switches and hosts\n')
	CLI(net)
	net.stop()

if __name__ == '__main__':
	setLogLevel( 'info' )
	myNetwork() 
