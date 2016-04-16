/*
 */
package com.agricraft.agricore.registry;

import com.agricraft.agricore.plant.AgriMutation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author RlonRyan
 */
public class AgriMutations {

	private final Set<AgriMutation> mutations;

	public AgriMutations() {
		this.mutations = new HashSet<>();
	}

	public boolean hasMutation(AgriMutation mutation) {
		return this.mutations.contains(mutation);
	}

	public boolean hasMutation(String child) {
		for (AgriMutation m : mutations) {
			if (m.child.equals(child)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasMutation(String parent1, String parent2) {
		for (AgriMutation m : mutations) {
			if (m.parent1.equals(parent1) && m.parent2.equals(parent2)) {
				return true;
			} else if (m.parent1.equals(parent2) && m.parent2.equals(parent1)) {
				return true;
			}
		}
		return false;
	}

	public boolean addMutation(AgriMutation mutation) {
		return this.mutations.add(mutation);
	}
	
	public List<AgriMutation> getMutation(String child) {
		final List<AgriMutation> results = new ArrayList<>();
		this.mutations.forEach((m) -> {
			if (m.getChild().id.equals(child)) {
				results.add(m);
			}
		});
		return results;
	}

	public List<AgriMutation> getMutation(String parent1, String parent2) {
		final List<AgriMutation> results = new ArrayList<>();
		for (AgriMutation mutation : mutations) {
			if ((mutation.parent1.equals(parent1) || mutation.parent1.equals(parent2)) && (mutation.parent2.equals(parent1) || mutation.parent2.equals(parent2))) {
				results.add(mutation);
			}
		}
		return results;
	}

	public List<AgriMutation> getAll() {
		return new ArrayList<>(this.mutations);
	}

	public void validate() {
		mutations.removeIf((e) -> (!e.validate()));
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\nMutations:");
		for (AgriMutation mutation : mutations) {
			sb.append("\n\t- ").append(mutation).append("\n\t");
		}
		return sb.append("\n").toString();
	}

}
