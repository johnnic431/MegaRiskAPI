package com.form2bgames.megarisk.api.json.packets;

import com.form2bgames.megarisk.api.network.NetClient;

public interface JSONPacket {
	public String getJSONPayload(NetClient nc);
	public boolean processJSONPayload(NetClient nc,String payload);
	public String getPacketTypeHandle();
}
