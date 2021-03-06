/**
 * 
 */
package cn.academy.core.command;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import cn.academy.api.ability.Category;
import cn.academy.api.ability.Level;
import cn.academy.api.data.AbilityData;
import cn.academy.api.data.AbilityDataMain;
import cn.liutils.api.command.LICommandBase;
import cn.liutils.api.util.DebugUtils;

/**
 * 显示玩家当前的能力信息（服务端）
 * @author WeathFolD
 */
public class CmdDataView extends LICommandBase {

	public CmdDataView() {
	}

	@Override
	public String getCommandName() {
		return "aview";
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "/aview <full>";
	}

	@Override
	public void processCommand(ICommandSender ics, String[] args) {
		EntityPlayer player = this.getCommandSenderAsPlayer(ics);
		if(player == null) return;
		AbilityData data = AbilityDataMain.getData(player);
		Category cat = data.getCategory();
		Level lv = data.getLevel();
		
		sendChat(ics, player.getCommandSenderName() + " Ability Data:");
		sendChat(ics, "cat: " + data.getCategoryID() + " (" + cat.getInternalName() + ")");
		sendChat(ics, "lv : " + data.getLevelID());
		sendChat(ics, "cp : " + data.getCurrentCP() + "/" + data.getMaxCP());
		if(args.length >= 1 && args[0].equalsIgnoreCase("full")) {
			sendChat(ics, "opn: " + DebugUtils.formatArray(data.getSkillOpenArray()));
			sendChat(ics, "exp: " + DebugUtils.formatArray(data.getSkillExpArray()));
		}
	}

}
