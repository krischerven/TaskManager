import java.util.Vector;
import java.util.Scanner;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.io.IOException;

public class TaskManager {

	private static void finish(Vector<Task> tasks) {
		saveData(tasks);
		System.out.println("Goodbye.");
	}

	private static void loadData(Vector<Task> tasks) {
		// objects (immutable)
		Scanner sc;
		try {
			sc = new Scanner(new FileReader(System.getProperty("user.dir")+"/src/tasks"));
		} catch (FileNotFoundException e) {
			try {
				sc = new Scanner(new FileReader(System.getProperty("user.dir")+"/tasks"));
			} catch (FileNotFoundException ee) {
				System.out.println("Tasks savefile (src/tasks) not found.");
				return;
			}
		}
		while (sc.hasNextLine()) {
			tasks.add(Task.From(sc.nextLine()));
		}
		sc.close();
	}

	private static void saveData(Vector<Task> tasks) {
		FileWriter f;
		try {
			f = new FileWriter(System.getProperty("user.dir")+"/tasks");
		} catch (IOException e) {
			System.out.println("Failed to save src/tasks.");
			return;
		}
		for (Task task : tasks) {
			try {
				f.write(task.Parse()+"\n");
			} catch (IOException e) {
				System.out.println("Failed to save src/tasks.");
				return;
			}
		}
		try {
			f.close();
		} catch (IOException e) {
			System.out.println("Failed to save src/tasks.");
			return;
		}
	}

	public static void main(String[] args) {
		// objects (immutable)
		final Vector<Task> tasks = new Vector<>();
		final Scanner sc = new Scanner(System.in);
		final Validator vd = new Validator();
		// load initial tasks
		loadData(tasks);
		// logic
		boolean first = true;
		while (first || vd.getYesNo(sc)) {
			first = false;
			System.out.println("Welcome to the Task Manager! What do you want to do today?\n");
			System.out.println("1. List tasks\n2. Add task\n3. Delete task\n4. Mark task as complete\n5. Quit\n");
			System.out.println("Please select one of the above options:");
			switch (vd.getChoice(sc, 1, 5)) {
				case 1: {

					// choose a specific task member if you want
					ArrayList<String> names = new ArrayList<>();
					for (Task task : tasks) {
						if (!names.contains(task.getName())) {
							names.add(task.getName());
						}
					}

					String options = "  0. Cancel\n";
					for (int i = 0; i < names.size(); ++i) {
						options += "  " + (i+1) + ". " + names.get(i);
						options += "\n";
					}

					options += "  " + (names.size()+1) + ". " + "All Tasks" + "\n";
					System.out.println("Would you like to select a specific task member or view all tasks?");
					
					int tmp = vd.getChoicePrompt(sc, 0, names.size()+1, "\nSelect an option:\n" + options)-1;
					String option = "ERROR";
					if (tmp == -1) {
						option = "CANCEL";
						System.out.println("Cancelled.");
					} else if (tmp == names.size()) {
						option = "YES";
					} else {
						option = names.get(tmp);
					}

					// main logic
					if (option != "CANCEL") {
						if (tasks.size() > 0) {
							for (int i = 0; i < tasks.size(); ++i) {
								if (option == "YES" || tasks.get(i).getName() == option) {
									System.out.println((i+1) + ". " + tasks.get(i).toString());
								}
							}
						} else {
							System.out.println("No tasks to list.");
						}
					}

					break;
				}
				case 2: {
					Task newTask = new Task();
					System.out.println("Please fill in the following fields: \n");
					System.out.println("Team Member Name: ");
					newTask.setName(sc.nextLine());
					System.out.println("Task Description: ");
					newTask.setDesc(sc.nextLine());
					System.out.println("Due Date: ");
					newTask.setDue(sc.nextLine());
					tasks.add(newTask);
					break;
				}
				case 3: {
					if (tasks.size() > 0) {
						System.out.println("Delete which task? (0 to cancel)");
						int choice = vd.getChoiceZero(sc, 1, tasks.size()+1);
						if (choice > 0) {
							System.out.println(tasks.get(choice-1));
							System.out.println("Are you sure you want to delete this task?");
							if (vd.getYesNo(sc)) {
								tasks.remove(choice-1);
								System.out.println("Successfully deleted task #"+choice);
							} else {
								System.out.println("Operation aborted.");
							}
						}
					} else {
						System.out.println("No tasks to delete.");
					}
					break;
				}
				case 4: {
					if (tasks.size() > 0) {
						System.out.println("Mark which task as complete? (0 to cancel)");
						int choice = vd.getChoiceZero(sc, 1, tasks.size()+1);
						if (choice > 0) {
							System.out.println(tasks.get(choice-1));
							System.out.println("Are you sure you want to mark this task as complete?");
							if (vd.getYesNo(sc)) {
								tasks.get(choice-1).setCompleted(true);
								System.out.println("Successfully marked task #"+choice + " as complete.");
							} else {
								System.out.println("Operation aborted.");
							}
						}
					} else {
						System.out.println("No tasks to mark as complete.");
					}
					break;
				}
				case 5: {
					System.out.println("Are you sure you want to quit Task Manager? (y/n)");
					if (vd.getYesNo(sc)) {
						finish(tasks);
						return;
					} else {
						break;
					}
				}
			}
			System.out.println("Continue? (y/n)");
		}
		finish(tasks);
	}
}