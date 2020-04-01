package com.rnd;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Mono;

public class SampleRScoketClient {

	public static void main(String[] args) {
		System.out.println("Started");

		// Connect to a server
		RSocket client =
				RSocketFactory.connect()	        
				//.dataMimeType("application/json")
				.transport(TcpClientTransport.create(7000))
				.start()
				.block();


		//invoke a Mapping Message controller
		Mono<Payload>  i=client.requestResponse(DefaultPayload.create("request-dummy-response",""));
		//Whatever message mapping I provide here , it gives the same error,
		//even if the message mapping doesnt exist the error is same. 
		//seems the client requester request does not reach the responder
		System.out.println("Received from RSocket Responder "+i.toString());
	}

}
