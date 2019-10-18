package IP_07.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterTest {
    private Register register = new Register();

    @Test
    public void testRandomID() {
        // Act
        // Populating taskIds list for test of random
        for (int i = 0; i < 900; i++)
            register.getTasksIds().add("task" + String.format("%03d", i));
        // Adding new task
        Task testTask00 = new Task("testTask00", "20201010");
        register.addTask(testTask00);
        // Assert
        assertFalse(Integer.parseInt(testTask00.getId().substring(4)) < 900);
    }

    @Test
    public void testAddTask() {
        // Act
        // Adding task to an empty list
        Task testTask00 = new Task("testTask00", "20201010");
        register.addTask(testTask00);
        // Assert
        assertEquals(testTask00, register.getTasks().get(0));
        assertEquals("testTask00", register.getTasks().get(0).getTitle());
        assertEquals("20201010", register.getTasks().get(0).getDueDate());
        assertEquals(register.getTasks().get(0).getId(), register.getTasksIds().get(0));

        // Act
        // Adding task to not empty list
        Task testTask01 = new Task("task001", "testTask01", "20200101", true);
        register.addTask(testTask01);
        // Assert
        assertEquals(testTask01, register.getTasks().get(1));
        assertEquals("task001", register.getTasks().get(1).getId());
        assertTrue(register.getTasks().get(1).ifDone());
        assertEquals(register.getTasks().get(1).getId(), register.getTasksIds().get(1));

        // Act
        // Adding task to not empty list
        Task testTask02 = new Task("task002", "testTask02", "20200202", false, "proj001");
        register.addTask(testTask02);
        // Assert
        assertEquals(testTask02, register.getTasks().get(2));
        assertEquals("proj001", register.getTasks().get(2).getAssignedToProject());
        assertEquals(register.getTasks().get(2).getId(), register.getTasksIds().get(2));
    }

    @Test
    public void testAddProject() {
        // Act
        // Adding project to an empty list
        Project testProject00 = new Project("testProject00", "20201010");
        register.addProject(testProject00);
        // Assert
        assertEquals(testProject00, register.getProjects().get(0));
        assertEquals("testProject00", register.getProjects().get(0).getTitle());
        assertEquals("20201010", register.getProjects().get(0).getDueDate());
        assertEquals(register.getProjects().get(0).getId(), register.getProjectsIds().get(0));

        // Act
        // Adding project to a not empty list
        Project testProject01 = new Project("proj001", "testProject01", "20201111", false);
        register.addProject(testProject01);
        // Assert
        assertEquals(testProject01, register.getProjects().get(1));
        assertEquals("proj001", register.getProjects().get(1).getId());
        assertFalse(register.getProjects().get(1).ifDone());
        assertEquals(register.getProjects().get(1).getId(), register.getProjectsIds().get(1));
    }

    @Test
    public void addTaskToProject() {
        // Act
        // Prepare one task and one project
        Project testProject00 = new Project("testProject00", "20201010");
        register.addProject(testProject00);
        Task testTask00 = new Task("testTask00", "20201010");
        register.addTask(testTask00);
        // Adding task to project
        String taskId = register.getTasks().get(0).getId();
        String projectId = register.getProjects().get(0).getId();
        register.addTaskToProject(taskId, projectId);
        // Assert
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