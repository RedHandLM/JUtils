package lsc.netty.sim_demo.main;

import lsc.netty.sim_demo.NettyServer;

public class StartServer {

	public static void main(String[] args) {
		System.out.println("Netty4.0 ");
		System.out.println("Port:53606");
		NettyServer ns = new NettyServer();
		ns.bind();
		
	}
	
}
