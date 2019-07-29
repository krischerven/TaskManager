import java.util.Vector;
import java.util.Scanner;

public class TaskManager {
	
	private static void sayGoodbye() {
		System.out.println("Goodbye.");
	}

	public static void main(String[] args) {
		// objects (immutable)
		final Vector<Task> tasks = new Vector<>();
		final Scanner sc = new Scanner(System.in);
		final Validator vd = new Validator();
		// logic
		boolean first = true;
		while (first || vd.getYesNo(sc)) {
			first = false;
			System.out.println("Welcome to the Task Manager! What do you want to do today?\n");
			System.out.println("1. List tasks\n2. Add task\n3. Delete task\n4. Mark task as complete\n5. Quit\n");
			System.out.println("Please select one of the above options:");
			switch (vd.getChoice(sc, 1, 5)) {
				case 1: {
					if (tasks.size() > 0) {
						for (int i = 0; i < tasks.size(); ++i) {
							System.out.println((i+1) + ". " + tasks.get(i).toString());
						}
					} else {
						System.out.println("No tasks to list.");
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
						int index = vd.getChoiceZero(sc, 1, tasks.size()+1);
						if (index > 0) {
							System.out.println(tasks.get(index-1));
							System.out.println("Are you sure you want to delete this task?");
							if (vd.getYesNo(sc)) {
								tasks.remove(index-1);
								System.out.println("Successfully deleted task #"+index);
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
						int index = vd.getChoiceZero(sc, 1, tasks.size()+1);
						if (index > 0) {
							System.out.println(tasks.get(index-1));
							System.out.println("Are you sure you want to mark this task as complete?");
							if (vd.getYesNo(sc)) {
								tasks.get(index-1).setCompleted(true);
								System.out.println("Successfully marked task #"+index + " as complete.");
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
						sayGoodbye();
						return;
					} else {
						break;
					}
				}
			}
			System.out.println("Continue? (y/n)");
		}
		sayGoodbye();
	}
}