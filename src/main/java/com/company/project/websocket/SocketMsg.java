
package com.company.project.websocket;

import lombok.Data;

/**
 * @author JHL
 *   2019-08-10 9:55
 */
@Data
public class SocketMsg {
	private String msg;
	private MsgType msgType;

	public SocketMsg(String msg, MsgType msgType) {
		this.msg = msg;
		this.msgType = msgType;
	}
}
