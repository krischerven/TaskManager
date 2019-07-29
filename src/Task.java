public class Task {
	final String TAB = "      ";
	private boolean completed;
	private String name;
	private String desc;
	private String due;
	public Task() {
		
	}
	public Task(String _name, String _desc, String _due) {
		name = _name;
		desc = _desc;
		due = _due;
	}
	public static Task From(String line) {
		String[] split = line.split(":");
		Task ret = new Task(split[0], split[1], split[2]);
		if (split[3].equals("true")) {
			ret.setCompleted(true);
		}
		return ret;
	}
	public String Parse() {
		return name + ":" + desc + ":" + due + ":" + (completed ? "true" : "false");
	}
	public boolean getCompleted() {
		return completed;
	}
	public String getName() {
		return name;
	}
	public String getDesc() {
		return desc;
	}
	public String getDue() {
		return due;
	}
	public void setCompleted(boolean b) {
		completed = b;
	}
	public void setName(String _name) {
		name = _name;
	}
	public void setDesc(String _desc) {
		desc = _desc;
	}
	public void setDue(String _due) {
		due = _due;
	}
	@Override
	public String toString() {
		String ret1 = String.format("%-7s %-10s %-15s %-17s", "Done?", "Due Date", "Team Member", "Description");
		String ret2 = String.format("%-10s %-10s %-15s %-17s", completed ? "   Yes" : "   No", due, name, desc);
		return ret1 + "\n\n" + ret2 + "\n";
	}
}