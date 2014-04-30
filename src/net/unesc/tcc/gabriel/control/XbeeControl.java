package net.unesc.tcc.gabriel.control;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.rapplogic.xbee.api.ApiId;
import com.rapplogic.xbee.api.AtCommand;
import com.rapplogic.xbee.api.AtCommandResponse;
import com.rapplogic.xbee.api.XBee;
import com.rapplogic.xbee.api.XBeeAddress16;
import com.rapplogic.xbee.api.XBeeAddress64;
import com.rapplogic.xbee.api.XBeeException;
import com.rapplogic.xbee.api.XBeeResponse;
import com.rapplogic.xbee.api.zigbee.ZBNodeDiscover;
import com.rapplogic.xbee.api.zigbee.ZNetRxResponse;
import com.rapplogic.xbee.api.zigbee.ZNetTxRequest;
import com.rapplogic.xbee.api.zigbee.ZNetTxStatusResponse;
import com.rapplogic.xbee.util.ByteUtils;

public class XbeeControl {
	private final static Logger log = Logger.getLogger(XbeeControl.class);

	private int[] encrypt(String acomando) {
		int[] icomando = new int[acomando.length()];
		for(int i=0; i<acomando.length();i++){
			icomando[i] = acomando.charAt(i);
		}
		return icomando;
	}
	
	private String decrypt(int[] payload) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < payload.length; i++) {
			sb.append((char) payload[i]);
		}

		return sb.toString();
	}
	
	protected ArrayList<ZBNodeDiscover> descobreNos() throws XBeeException, InterruptedException {
		PropertyConfigurator.configure("log4j.properties");
		XBee xbee = new XBee();
		ArrayList<ZBNodeDiscover> retorno = null;

		try {
			xbee.open("COM4", 9600);
			xbee.sendAsynchronous(new AtCommand("NT"));
			AtCommandResponse nodeTimeout = (AtCommandResponse) xbee.getResponse();

			// default is 6 seconds
			int nodeDiscoveryTimeout = ByteUtils.convertMultiByteToInt(nodeTimeout.getValue()) * 100;
			log.info("Sending Node Discover command");
			xbee.sendAsynchronous(new AtCommand("ND"));
			List<? extends XBeeResponse> responses = xbee.collectResponses(nodeDiscoveryTimeout);

			for (XBeeResponse response : responses) {
				if (response instanceof AtCommandResponse) {
					AtCommandResponse atResponse = (AtCommandResponse) response;

					if (atResponse.getCommand().equals("ND")
					 && atResponse.getValue() != null
					 && atResponse.getValue().length > 0) {
						ZBNodeDiscover nd = ZBNodeDiscover.parse((AtCommandResponse) response);
						if (retorno == null){
							retorno = new ArrayList<ZBNodeDiscover>();
						}
						retorno.add(nd);
						log.info("Node Discover is " + nd);
					}
				}
			}
		} finally {
			xbee.close();
		}
		return retorno;
	}

	protected void enviarComando(String acomando) throws XBeeException, InterruptedException {
		PropertyConfigurator.configure("log4j.properties");
		XBee xbee = new XBee();

		try {
			xbee.open("COM4", 9600);
			int[] payload = encrypt(acomando);
			XBeeAddress16 addr16 = new XBeeAddress16(0xff, 0xff);
			XBeeAddress64 addr64 = new XBeeAddress64(0x00, 0x13, 0xa2, 0x00, 0x40, 0xac, 0xb1, 0x4e);
			ZNetTxRequest request = new ZNetTxRequest(1, addr64, addr16, 0x00, ZNetTxRequest.Option.UNICAST, payload);
			//log.info("Pacote bytes >> " + Arrays.toString(request.getPayload()));
			log.info("Pacote String >> " + decrypt(request.getPayload()));
			log.debug("Pacote xbee >> " + request);
			
			
			xbee.sendSynchronous(request);
			ArrayList<XBeeResponse> lista = (ArrayList<XBeeResponse>) xbee.collectResponses(5*1000);
			for(XBeeResponse response : lista){
				//log.info("ID >> " + response.getApiId() );
				ApiId lapiId = response.getApiId();
				switch(lapiId){
				case ZNET_RX_RESPONSE:{
					ZNetRxResponse rxresponse = (ZNetRxResponse) response;
					log.debug(lapiId+" >> " + rxresponse);
					//log.info("Resposta rx bytes >> " + Arrays.toString(rxresponse.getData()));
					log.info("Resposta rx String >> " + decrypt(rxresponse.getData()));
				}
					break;
				case ZNET_TX_REQUEST:
					break;
				case ZNET_TX_STATUS_RESPONSE:{
					ZNetTxStatusResponse statusresponse = (ZNetTxStatusResponse) response;
					log.debug(lapiId+" >> " + statusresponse);
					log.info("Resposta status >> " + statusresponse.getDeliveryStatus());
				}
					break;
				default: log.error("Não tratado: " + lapiId);
					break;
				}
			}
		} finally {
			xbee.close();
		}
	}

	public static void main(String[] args) {
		XbeeControl t = new XbeeControl();
		try {
			ArrayList<ZBNodeDiscover> retornolist = t.descobreNos();
			for(ZBNodeDiscover nos : retornolist){
				System.out.println("LIST: " + nos);
			}
			//t.enviarComando("ROLAR");
		} catch (XBeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
}
