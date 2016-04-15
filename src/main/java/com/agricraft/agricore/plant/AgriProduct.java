/*
 */
package com.agricraft.agricore.plant;

import com.agricraft.agricore.core.AgriCore;
import java.util.Random;

/**
 *
 * @author RlonRyan
 */
public class AgriProduct {
	
	public static final int MAX_CHANCE = 1000;
	
	private static final String STRING_FORMAT = "\n" +
			"Product:\n" +
			"\t- Item: %s\n" +
			"\t- Meta: %d\n" + 
			"\t- Base Amount: %d\n" + 
			"\t- Range Amount: %d\n" +
			"\t- Chance: %d out of %d\n";

	public final String item;

	public final int meta;

	public final int base;
	public final int range;
	
	public final int chance;

	public int getAmount(final Random rand) {
		return base + rand.nextInt(range);
	}

	public AgriProduct() {
		this.item = "minecraft:wheat";
		this.meta = 0;
		this.base = 5;
		this.range = 5;
		this.chance = 1000;
	}

	public AgriProduct(String item, int meta, int base, int range, int chance) {
		this.item = item;
		this.meta = meta;
		this.base = base;
		this.range = range;
		this.chance = chance;
	}

	public boolean validate() {
		if (!AgriCore.getValidator().isValidItem(item)) {
			AgriCore.getLogger().info("Invalid Product: Invalid Item: " + item + "!");
			return false;
		} else {
			return true;
		}
	}
	
	public Object toStack() {
		return AgriCore.getConverter().toStack(this.item, this.base + 1, this.meta);
	}
	
	public Object toStack(Random rand) {
		return AgriCore.getConverter().toStack(this.item, this.getAmount(rand), this.meta);
	}

	@Override
	public String toString() {
		return String.format(STRING_FORMAT, item, meta, base, range, chance, MAX_CHANCE);
	}

}
