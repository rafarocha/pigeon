package br.eng.mosaic.pigeon.server.controller;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.eng.mosaic.pigeon.common.dto.UserInfo;

@Controller
public class HomeController extends AbstractController{
	
	@RequestMapping("userInfo.do")
	public void userInfo( HttpServletRequest request, 
			HttpSession session, HttpServletResponse response ){
		UserInfo info=(UserInfo) session.getAttribute("userInfo");
		JSONObject json=new JSONObject(info);
		ack_ok(response, json.toString());
	}
	
	@RequestMapping("{user_id}/welcome.do")
	public void welcome( @PathVariable String user_id, HttpServletRequest request, 
			HttpSession session, HttpServletResponse response ) throws UnknownHostException {
 		
		ack_ok(response, user_id + " : " + request.getRemoteAddr() 
				+ " : "
				+ InetAddress.getLocalHost().getHostName() + " : " 
				+ getMacAddress( request.getRemoteAddr() )
				);
	}
	
	public String getMacAddress(String ip) {
		StringBuilder macaddress = new StringBuilder();
		try {
            //InetAddress address = InetAddress.getLocalHost();
            InetAddress address = InetAddress.getByName(ip);

            /*
             * Get NetworkInterface for the current host and then read the
             * hardware address.
             */
            NetworkInterface ni = NetworkInterface.getByInetAddress(address);
            if (ni != null) {
                byte[] mac = ni.getHardwareAddress();
                if (mac != null) {
                    /*
                     * Extract each array of mac address and convert it to hexa with the
                     * following format 08-00-27-DC-4A-9E.
                     */
                    for (int i = 0; i < mac.length; i++) {
                    	String part = String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
                        macaddress.append(part);
                    }
                } else {
                	macaddress.append("Address doesn't exist or is not accessible.");
                }
            } else {
            	macaddress.append("Network Interface for the specified address is not found.");
            }
        } catch (UnknownHostException e) {
        	macaddress.append("error:" + e.getMessage());
            e.printStackTrace();
        } catch (SocketException e) {
        	macaddress.append("error:" + e.getMessage());
        }
        return macaddress.toString();
	}
	
}