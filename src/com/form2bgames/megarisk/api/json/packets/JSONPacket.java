package com.form2bgames.megarisk.api.json.packets;

import com.afollestad.json.Ason;
import com.form2bgames.megarisk.api.network.NetClient;

public interface JSONPacket {
	public String getJSONPayload(NetClient nc);
	public boolean processJSONPayload(NetClient nc,Ason as);
	public String getPacketTypeHandle();
}
