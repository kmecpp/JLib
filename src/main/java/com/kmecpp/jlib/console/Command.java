package com.kmecpp.jlib.console;

import java.util.HashSet;

import com.kmecpp.jlib.Validate;
import com.kmecpp.jlib.utils.StringUtil;

public abstract class Command implements CommandExecutor {

	private final String[] aliases;

	private String[] args = new String[0];
	private String description = "";
	private boolean matchArgs = false;

	public Command(String[] aliases) {
		Validate.length(aliases, 1, "Command must have at least one alias!");

		HashSet<String> set = new HashSet<>();
		for (String alias : aliases) {
			set.add(alias);
		}
		this.aliases = set.toArray(new String[set.size()]);
		configure();
	}

	public void configure() {
	}

	public String getPrimaryAlias() {
		return aliases[0];
	}

	public String[] getAliases() {
		return aliases;
	}

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsage() {
		return getPrimaryAlias().toUpperCase() + " " + StringUtil.join(args);
	}

	public boolean matchArgs() {
		return matchArgs;
	}

	public void setMatchArgs(boolean matchArgs) {
		this.matchArgs = matchArgs;
	}

	public boolean hasAlias(String alias) {
		for (String str : aliases) {
			if (str.equalsIgnoreCase(alias)) {
				return true;
			}
		}
		return false;
	}

	public void register() {
		Console.registerCommand(this);
	}

}
