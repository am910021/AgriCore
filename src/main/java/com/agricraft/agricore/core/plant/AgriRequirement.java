/*
 */
package com.agricraft.agricore.core.plant;


import com.agricraft.agricore.core.AgriCore;
import java.util.List;
import java.util.Map;

/**
 *
 * @author RlonRyan
 */
public class AgriRequirement {
	
	private final int min_light;
	private final int max_light;

	private final List<String> soils;
	private final List<String> bases;

	private final Map<String, Integer> nearby;

	public AgriRequirement(List<String> soils, List<String> bases, Map<String, Integer> nearby, int min_light, int max_light) {
		this.soils = soils;
		this.bases = bases;
		this.nearby = nearby;
		this.min_light = 0;
		this.max_light = 10;
	}

	public boolean validate() {
		for (String block : soils) {
			if (!AgriCore.getValidator().isValidBlock(block)) {
				AgriCore.getLogger().info("Invalid Requirement: Invalid Soil!");
				return false;
			}
		}
		for (String block : bases) {
			if (!AgriCore.getValidator().isValidBlock(block)) {
				AgriCore.getLogger().info("Invalid Requirement: Invalid Base!");
				return false;
			}
		}
		for (String block : nearby.keySet()) {
			if (!AgriCore.getValidator().isValidBlock(block)) {
				AgriCore.getLogger().info("Invalid Requirement: Invalid Nearby!");
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nRequirement:\n");
		sb.append("\t- Light:\n");
		sb.append("\t\t- Min: ").append(min_light).append("\n");
		sb.append("\t\t- Max: ").append(max_light).append("\n");
		sb.append("\t- Soil:\n");
		this.soils.forEach((e) -> {
			sb.append("\t\t- Block: ").append(e).append("\n");
		});
		sb.append("\t- Base:\n");
		this.bases.forEach((e) -> {
			sb.append("\t\t- Block: ").append(e).append("\n");
		});
		sb.append("\t- Nearby:\n");
		this.nearby.entrySet().forEach((e) -> {
			sb.append("\t\t- Block: ").append(e.getValue()).append("\n\t");
			sb.append("\t\t- Distance: ").append(e.getValue()).append("\n");
		});
		return sb.toString();
	}

}
