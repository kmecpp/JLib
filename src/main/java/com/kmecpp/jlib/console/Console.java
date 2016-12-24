package com.kmecpp.jlib.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.kmecpp.jlib.utils.StringUtil;

public class Console {

	private static ArrayList<Command> commands = new ArrayList<>();

	private static boolean running;

	public static boolean isRunning() {
		return running;
	}

	public static void start() {
		capture(new ConsoleCapture() {

			@Override
			public void capture(String input) {
				System.out.println("Unknown command! Type 'help' for a list of commands!");
			}

		});
	}

	public static void capture(ConsoleCapture capture) {
		if (Console.running) {
			throw new IllegalStateException("Console already capturing!");
		}
		Console.running = true;

		Scanner scanner = new Scanner(System.in);
		inputLoop: while (true) {
			System.out.print("> ");
			String input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				continue;
			}

			if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("stop")) {
				scanner.close();
				System.exit(0);
			} else if (input.equals("help") || input.equalsIgnoreCase("?")) {
				showHelp();
				continue;
			}

			//Handle commands
			for (Command command : commands) {
				for (String alias : command.getAliases()) {
					String[] parts = input.split(" ");
					if (alias.equalsIgnoreCase(parts[0])) {
						String[] args = Arrays.copyOfRange(parts, 1, parts.length);
						//						if (args.length < command.getArgs().length) {
						//							System.out.println("Usage: " + command.getUsage());
						//							continue inputLoop;
						//						}

						try {
							command.execute(alias, args);
						} catch (Exception e) {
							e.printStackTrace();
						}

						continue inputLoop;
					}
				}
			}

			capture.capture(input);
		}
	}

	public static void registerCommand(String alias, final String[] args, final String description, final SimpleCommandExecutor executor) {
		registerCommand(new Command(new String[] { alias }) {

			@Override
			public void configure() {
				setArgs(args);
				setDescription(description);
			}

			@Override
			public void execute(String label, String[] args) {
				executor.execute(args);
			}

		});
	}

	public static void registerCommand(Command command) {
		commands.add(command);
	}

	public static ArrayList<Command> getCommands() {
		return commands;
	}

	public static void showHelp() {
		if (!commands.isEmpty()) {
			int max = 0;
			for (Command command : commands) {
				int length = command.getPrimaryAlias().length();
				if (command.getPrimaryAlias().length() > max) {
					max = length;
				}
			}

			for (Command command : commands) {
				System.out.println(StringUtil.ensureLength(command.getPrimaryAlias().toUpperCase(), max + 3) + command.getDescription());
			}
		} else {
			System.out.println("There are no registered commands!");
		}
	}

	public static interface ConsoleCapture {

		void capture(String input);

	}

}
