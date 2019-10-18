package IP_07.Model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RegisterTest {

    @Test
    public void testAddTask() {
        Register register = new Register();

        // Populating for test of random
        for (int i = 0; i < 900; i++)
            register.getProjectsIds().add("task" + String.format("%03d", i));
        // Adding to empty list
        Task testTask00 = new Task("testTask00", "20201010");
        register.addTask(testTask00);
        assertEquals(testTask00, register.getTasks().get(0));
        assertFalse(Integer.parseInt(testTask00.getId().substring(4)) < 900);
        assertEquals("testTask00", register.getTasks().get(0).getTitle());
        assertEquals("20201010", register.getTasks().get(0).getDueDate());
        assertEquals(register.getTasks().get(0).getId(), register.getTasksIds().get(0));

        // Place for task
        register.getProjectsIds().remove(2);
        // Adding to not empty list
        Task testTask01 = new Task("task001", "testTask01", "20200101", true);
        register.addTask(testTask01);
        assertEquals(testTask01, register.getTasks().get(1));
        assertEquals("task001", register.getTasks().get(1).getId());
        assertEquals(true, register.getTasks().get(1).ifDone());
        assertEquals(register.getTasks().get(1).getId(), register.getTasksIds().get(1));

        // Place for task
        register.getProjectsIds().remove(2);
        // Adding to not empty list
        Task testTask02 = new Task("task002", "testTask02", "20200202", false, "proj001");
        register.addTask(testTask02);
        assertEquals(testTask02, register.getTasks().get(2));
        assertEquals("proj001", register.getTasks().get(2).getAssignedToProject());
        assertEquals(register.getTasks().get(2).getId(), register.getTasksIds().get(2));
    }

    @Test
    public void testAddProject() {
        Register register = new Register();

        // Adding to empty list
        Project testProject00 = new Project("testProject00", "20201010");
        register.addProject(testProject00);
        assertEquals(testProject00, register.getProjects().get(0));
        assertEquals("testProject00", register.getProjects().get(0).getTitle());
        assertEquals("20201010", register.getProjects().get(0).getDueDate());
        assertEquals(register.getProjects().get(0).getId(), register.getProjectsIds().get(0));

        // Adding to not empty list
        Project testProject01 = new Project("proj001", "testProject01", "20201111", false);
        register.addProject(testProject01);
        assertEquals(testProject01, register.getProjects().get(1));
        assertEquals("proj001", register.getProjects().get(1).getId());
        assertEquals(false, register.getProjects().get(1).ifDone());
        assertEquals(register.getProjects().get(1).getId(), register.getProjectsIds().get(1));
    }

    @Test
    public void addTaskToProject() {
        Register register = new Register();
        Project testProject00 = new Project("testProject00", "20201010");
        register.addProject(testProject00);
        Task testTask00 = new Task("testTask00", "20201010");
        register.addTask(testTask00);

        // Adding task to project
        String taskId = register.getTasks().get(0).getId();
        String projectId = register.getProjects().get(0).getId();
        register.addTaskToProject(taskId, projectId);
        assertEquals(projectId, register.getTasks().get(0).getAssignedToProject());
        assertEquals(taskId, register.getProjects().get(0).getAssignedTasks().get(0));
    }


    /*@Test
    void removeTaskFromProject() {
    }

    @Test
    void removeAllTasksFromProject() {
    }

    @Test
    void printStatus() {
    }

    @Test
    void markTaskAsDone() {
    }

    @Test
    void findTask() {
    }

    @Test
    void removeTask() {
    }

    @Test
    void setTaskStatus() {
    }

    @Test
    void setTaskDueDate() {
    }

    @Test
    void setTaskTitle() {
    }

    @Test
    void markProjectAsDoneDependent() {
    }

    @Test
    void markProjectAsDoneAlways() {
    }

    @Test
    void findProject() {
    }

    @Test
    void removeProjectAlways() {
    }

    @Test
    void removeProjectDependent() {
    }

    @Test
    void setProjectStatus() {
    }

    @Test
    void setProjectDueDate() {
    }

    @Test
    void setProjectTitle() {
    }

     */
}