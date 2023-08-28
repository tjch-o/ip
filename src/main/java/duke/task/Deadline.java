package duke.task;
import java.time.LocalDateTime;

public class Deadline extends Task {
    // private String deadline;
    private LocalDateTime deadline;
    private static String NO_DESC_ERROR_MSG = "OOPS!!! The description of a deadline cannot be empty.";

    public Deadline(String task) {
        super(getTask(task));
        this.deadline = this.convertToDateTime(this.getDeadline(task));
    }

    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = this.convertToDateTime(deadline);
    }

    /*
     * Returns an array of 2 elements, the first element is the task, the second is the deadline. 
     * Worth noting that the task and deadline strings here still have whitespaces that need to be
     * trimmed for use.
     * 
     * @param taskString the string that contains the task and deadline
     * @return           an array of 2 strings
     */
    public static String[] splitDeadlineString(String taskString) {
        if (checkTaskNoDescription(taskString, "deadline")) {
            throw new IllegalArgumentException(NO_DESC_ERROR_MSG);
        }

        // removes "deadline "  from the task string
        String removeCmd = taskString.substring(9);
        if (checkAllWhiteSpace(removeCmd)) {
            throw new IllegalArgumentException(NO_DESC_ERROR_MSG);
        }

        // we know the array has 2 elements 
        String[] arr = removeCmd.split("/by");
        if (arr.length == 1) {
            throw new IllegalArgumentException("Invalid deadline format: missing /by");
        }
        return arr;
    }

    /**
     * Returns the task from a input string that starts with "deadline".
     * 
     * @param taskString the input string that starts with "deadline"
     * @return           the task
     */
    public static String getTask(String taskString) {
        String[] strings = splitDeadlineString(taskString);
        String task = strings[0];

        if (checkAllWhiteSpace(task)) {
            throw new IllegalArgumentException(NO_DESC_ERROR_MSG);
        }
        // we remove the white space behind the task
        return task.trim();
    }

    /**
     * Returns the deadline from a input string that starts with "deadline".
     * 
     * @param taskString the input string that starts with "deadline"
     * @return           the deadline
     */
    public String getDeadline(String taskString) {
        String[] strings = splitDeadlineString(taskString);
        String deadline = strings[1];

        if (checkAllWhiteSpace(deadline)) {
            throw new IllegalArgumentException("OOPS!!! The deadline of a deadline cannot be empty.");
        }
        // remove the whitespace in front 
        return deadline.trim();
    }

    public String displayDeadline() {
        return this.displayTime(this.deadline);
    }

    public String saveDeadline() {
        return this.saveTime(this.deadline);
    }

    @Override
    public String saveStringToFile() {
        return "D" + super.saveStringToFile() + " | " + this.saveDeadline(); 
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.displayDeadline() + ")";
    }
}