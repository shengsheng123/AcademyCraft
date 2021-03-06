package cn.academy.api.ctrl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;

/**
 * This class manages all SkillStates, both on client and on server.
 * @author acaly
 *
 */
public class SkillStateManager {
	
	private static Map<EntityPlayer, List<SkillState>> stateMap = new HashMap(); 

	/**
	 * Called by EventHandler.
	 * @param player
	 */
	static void removePlayer(EntityPlayer player) {
		if (stateMap.containsKey(player)) {
			stateMap.remove(player);
		}
	}
	
	/**
	 * Add a new state. Called by SkillState.
	 * @param state
	 */
	static void addState(SkillState state) {
		if (stateMap.containsKey(state.player)) {
			stateMap.get(state.player).add(state);
		} else {
			List<SkillState> list = new ArrayList();
			list.add(state);
			stateMap.put(state.player, list);
		}
	}

	/**
	 * Remove a finished state. Called by SkillState.
	 * @param state
	 */
	static void removeState(SkillState state) {
		if (stateMap.containsKey(state.player)) {
			stateMap.get(state.player).remove(state);
		}
	}
	
	/**
	 * Get all skill states of the given player. The result can not be modified.
	 * @param player
	 * @return
	 */
	public static List<SkillState> getStateForPlayer(EntityPlayer player) {
		if (stateMap.containsKey(player)) {
			return Collections.unmodifiableList(stateMap.get(player));
		} else {
			return Collections.unmodifiableList(new ArrayList<SkillState>());
		}
	}
}
