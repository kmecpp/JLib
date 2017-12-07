package com.kmecpp.jlib.console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.kmecpp.jlib.utils.StringUtil;

public class Console {

	private static final Scanner scanner = new Scanner(System.in);
	private static ArrayList<Command> commands = new ArrayList<>();

	private static boolean background = true;

	public static boolean isBackground() {
		return background;
	}

	public static void setBackground(boolean background) {
		Console.background = background;
	}

	public static void start() {
		capture((input) -> System.out.println("Unknown command! Type 'help' for a list of commands!"));
	}

	public static String prompt(String prompt) {
		return prompt(prompt, "", null);
	}

	@SuppressWarnings("unchecked")
	public static <T> T prompt(String prompt, String errorMessage, InputParser<T> parser) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine();
			try {
				return parser == null ? (T) input : parser.parse(input);
			} catch (Exception e) {
				System.out.println(errorMessage);
			}
		}
	}

	public static void capture(ConsoleCapture capture) {
		capture("> ", capture);
	}

	public static void capture(String prompt, ConsoleCapture capture) {
		Runnable runnable = () -> capture_impl(prompt, capture);

		if (background) {
			Thread thread = new Thread(runnable);
			thread.setName("Console Reader Thread");
			thread.start();
		} else {
			runnable.run();
		}
	}

	private static void capture_impl(String prompt, ConsoleCapture capture) {
		Scanner scanner = new Scanner(System.in);
		inputLoop: while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				continue;
			}

			if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("stop")) {
				System.out.println("Shutting down application");
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
						if (command.matchArgs() && args.length < command.getArgs().length) {
							System.out.println("Usage: " + command.getUsage());
							continue inputLoop;
						}

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

	public static void registerCommand(String alias, final String description, final SimpleCommandExecutor executor) {
		registerCommand(alias, new String[0], false, description, executor);
	}

	public static void registerCommand(String alias, final String[] args, final String description, final SimpleCommandExecutor executor) {
		registerCommand(alias, args, false, description, executor);
	}

	public static void registerCommand(String alias, final String[] args, final boolean matchArgs, final String description, final SimpleCommandExecutor executor) {
		registerCommand(new Command(new String[] { alias }) {

			@Override
			public void configure() {
				setArgs(args);
				setDescription(description);
				setMatchArgs(matchArgs);
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

	public static interface InputParser<T> {

		T parse(String input);

	}

}
