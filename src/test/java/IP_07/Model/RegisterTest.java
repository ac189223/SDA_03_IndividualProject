package IP_07.Model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for register
 *
 * @author andrzejcalka
 * @author =-_-=
 */
public class RegisterTest {
    private Register register = new Register();

    /**
     * Test of creation of random and unique Id for test
     * Test of creation of random and unique Id for project
     */
    @Test
    public void testRandomID() {
        // Arrange
        // Populating tasksIds list for test of random
        for (int i = 0; i < 900; i++)
            register.getTasksIds().add("task" + String.format("%03d", i));
        // Creation of task
        Task testTask00 = new Task("testTask00", "20201010");
        // Act
        // Adding new task to register with random Id
        register.addTask(testTask00);
        // Assert
        assertFalse(Integer.parseInt(testTask00.getId().substring(4)) < 900);

        // Arrange
        // Populating projectsIds list for test of random
        for (int i = 0; i < 900; i++)
            register.getProjectsIds().add("proj" + String.format("%03d", i));
        // Creation of project
        Project testProj00 = new Project("testProj00", "20201010");
        // Act
        // Adding new project to register with random Id
        register.addProject(testProj00);
        // Assert
        assertFalse(Integer.parseInt(testProj00.getId().substring(4)) < 900);
    }

    /**
     * Test of adding new task to an empty list and to a populated one
     * Test of adding new task with two, four or all fields filled in
     */
    @Test
    public void testAddTask() {
        // Arrange
        Task testTask00 = new Task("testTask00", "20201010");
        // Act
        // Adding task to an empty list
        register.addTask(testTask00);
        // Assert
        assertEquals(testTask00, register.getTasks().get(0));
        assertEquals("testTask00", register.getTasks().get(0).getTitle());
        assertEquals("20201010", register.getTasks().get(0).getDueDate());
        assertFalse(register.getTasks().get(0).ifDone());
        assertEquals("", register.getTasks().get(0).getAssignedToProject());
        assertEquals(register.getTasks().get(0).getId(), register.getTasksIds().get(0));

        // Arrange
        Task testTask01 = new Task("task001", "testTask01", "20201111", true);
        // Act
        // Adding task to not empty list
        register.addTask(testTask01);
        // Assert
        assertEquals(testTask01, register.getTasks().get(1));
        assertEquals("task001", register.getTasks().get(1).getId());
        assertEquals("testTask01", register.getTasks().get(1).getTitle());
        assertEquals("20201111", register.getTasks().get(1).getDueDate());
        assertTrue(register.getTasks().get(1).ifDone());
        assertEquals("", register.getTasks().get(1).getAssignedToProject());
        assertEquals(register.getTasks().get(1).getId(), register.getTasksIds().get(1));

        // Arrange
        Task testTask02 = new Task("task002", "testTask02", "20201212", false, "proj001");
        // Act
        // Adding task to not empty list
        register.addTask(testTask02);
        // Assert
        assertEquals(testTask02, register.getTasks().get(2));
        assertEquals("task002", register.getTasks().get(2).getId());
        assertEquals("testTask02", register.getTasks().get(2).getTitle());
        assertEquals("20201212", register.getTasks().get(2).getDueDate());
        assertFalse(register.getTasks().get(2).ifDone());
        assertEquals("proj001", register.getTasks().get(2).getAssignedToProject());
        assertEquals(register.getTasks().get(2).getId(), register.getTasksIds().get(2));
    }

    /**
     * Test of adding new oproject to an empty list and to a populated one
     * Test of adding new project with two or all fields filled in
     */
    @Test
    public void testAddProject() {
        // Arrange
        Project testProject00 = new Project("testProject00", "20201010");
        // Act
        // Adding project to an empty list
        register.addProject(testProject00);
        // Assert
        assertEquals(testProject00, register.getProjects().get(0));
        assertEquals("testProject00", register.getProjects().get(0).getTitle());
        assertEquals("20201010", register.getProjects().get(0).getDueDate());
        assertFalse(register.getProjects().get(0).ifDone());
        assertEquals(register.getProjects().get(0).getId(), register.getProjectsIds().get(0));

        // Arrange
        Project testProject01 = new Project("proj001", "testProject01", "20201111", false);
        // Act
        // Adding project to a not empty list
        register.addProject(testProject01);
        // Assert
        assertEquals(testProject01, register.getProjects().get(1));
        assertEquals("proj001", register.getProjects().get(1).getId());
        assertEquals("testProject01", register.getProjects().get(1).getTitle());
        assertEquals("20201111", register.getProjects().get(1).getDueDate());
        assertFalse(register.getProjects().get(1).ifDone());
        assertEquals(register.getProjects().get(1).getId(), register.getProjectsIds().get(1));
    }

    /**
     * Test of assigning new task to the project, and of adding the second one
     * Test of assigning task to non-existing project
     */
    @Test
    public void addTaskToProject() {
        // Arrange
        // Prepare two tasks and one project
        Project testProject00 = new Project("testProject00", "20201010");
        register.addProject(testProject00);
        Task testTask00 = new Task("testTask00", "20201010");
        Task testTask01 = new Task("testTask01", "20201111");
        register.addTask(testTask00);
        register.addTask(testTask01);
        String taskId00 = register.getTasks().get(0).getId();
        String taskId01 = register.getTasks().get(1).getId();
        String projectId = register.getProjects().get(0).getId();
        // Act
        // Adding task to project
        register.addTaskToProject(taskId00, projectId);
        register.addTaskToProject(taskId01, projectId);
        // Assert
        assertEquals(projectId, register.getTasks().get(0).getAssignedToProject());
        assertEquals(taskId00, register.getProjects().get(0).getAssignedTasks().get(0));
        assertEquals(projectId, register.getTasks().get(1).getAssignedToProject());
        assertEquals(taskId01, register.getProjects().get(0).getAssignedTasks().get(1));
        assertEquals(2, register.getProjects().get(0).getAssignedTasks().size());

        try {
            // Act
            // Adding task to non-existing project
            register.addTaskToProject(taskId01, register.getProjects().get(1).getId());
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ignored) {
        }
    }

    /**
     * Test of removing one task from the project
     * Test of removing last task and leaving project empty
     */
    @Test
    public void removeTaskFromProject() {
        // Arrange
        // Prepare project with two tasks assigned
        Project testProject00 = new Project("testProject00", "20201010");
        register.addProject(testProject00);
        Task testTask00 = new Task("testTask00", "20201010");
        Task testTask01 = new Task("testTask01", "20201111");
        register.addTask(testTask00);
        register.addTask(testTask01);
        String taskId00 = register.getTasks().get(0).getId();
        String taskId01 = register.getTasks().get(1).getId();
        String projectId = register.getProjects().get(0).getId();
        register.addTaskToProject(taskId00, projectId);
        register.addTaskToProject(taskId01, projectId);
        // Act
        // Removing first task from project, trying to call it from the project, and removing the second one
        register.removeTaskFromProject(taskId00);
        try {
            String testId = register.getProjects().get(0).getAssignedTasks().get(1);
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ignored) {
        }
        register.removeTaskFromProject(taskId01);
        // Assert
        assertEquals("", register.getTasks().get(0).getAssignedToProject());
        assertEquals("", register.getTasks().get(1).getAssignedToProject());
        assertEquals(0, register.getProjects().get(0).getAssignedTasks().size());
    }

    /**
     * Test of removing all tasks (two) from the project
     * Test of removing tasks when they do not exist
     */
    @Test
    public void removeAllTasksFromProject() {
        // Arrange
        // Prepare project with two tasks assigned
        Project testProject00 = new Project("testProject00", "20201010");
        register.addProject(testProject00);
        Task testTask00 = new Task("testTask00", "20201010");
        Task testTask01 = new Task("testTask01", "20201111");
        register.addTask(testTask00);
        register.addTask(testTask01);
        String taskId00 = register.getTasks().get(0).getId();
        String taskId01 = register.getTasks().get(1).getId();
        String projectId = register.getProjects().get(0).getId();
        register.addTaskToProject(taskId00, projectId);
        register.addTaskToProject(taskId01, projectId);
        // Act
        // Removing two tasks from project and trying to call them from the project
        register.removeAllTasksFromProject(projectId);
        try {
            String testId = register.getProjects().get(0).getAssignedTasks().get(1);
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ignored) {
        }
        try {
            String testId = register.getProjects().get(0).getAssignedTasks().get(0);
            fail("Should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ignored) {
        }
        // Try to removing task that do not exist from project
        try {
            register.removeAllTasksFromProject(projectId);
        } catch (Exception e) {
            fail("Should not throw Exception");
        }
        // Assert
        assertEquals("", register.getTasks().get(0).getAssignedToProject());
        assertEquals("", register.getTasks().get(1).getAssignedToProject());
        assertEquals(0, register.getProjects().get(0).getAssignedTasks().size());
    }

    /**
     * Test of marking unfinished task as finished
     * Test of marking already finished one
     */
    @Test
    public void markTaskAsDone() {
        // Arrange
        Task testTask00 = new Task("testTask00", "20201010");
        register.addTask(testTask00);
        // Act
        register.markTaskAsDone(register.getTasks().get(0).getId());
        try {
            register.markTaskAsDone(register.getTasks().get(0).getId());
        } catch (Exception e) {
            fail("Should not throw Exception");
        }
        // Assert
        assertTrue(register.getTasks().get(0).ifDone());
    }

    /**
     * Test of marking unfinished project with unfinished task as finished
     * Test of marking unfinished project with finished tasks as finished
     * Test of marking already finished one as finished
     */
    @Test
    public void markProjectAsDoneDependent() {
        // Arrange
        // Prepare project with two tasks assigned
        Project testProject00 = new Project("testProject00", "20201010");
        register.addProject(testProject00);
        Task testTask00 = new Task("testTask00", "20201010");
        Task testTask01 = new Task("testTask01", "20201111");
        register.addTask(testTask00);
        register.addTask(testTask01);
        String taskId00 = register.getTasks().get(0).getId();
        String taskId01 = register.getTasks().get(1).getId();
        String projectId = register.getProjects().get(0).getId();
        register.addTaskToProject(taskId00, projectId);
        register.addTaskToProject(taskId01, projectId);
        // Act
        // Try to mark project as finished (unsuccessful)
        register.markProjectAsDoneDependent(projectId);
        // Assert
        assertFalse(register.getTasks().get(0).ifDone() || register.getTasks().get(1).ifDone() ||
                register.getProjects().get(0).ifDone());

        // Arrange
        // Mark both tasks as finished
        register.markTaskAsDone(taskId00);
        register.markTaskAsDone(taskId01);
        // Act
        // Mark project as finished
        register.markProjectAsDoneDependent(projectId);
        // Assert
        assertTrue(register.getProjects().get(0).ifDone());

        // Act
        // Try to mark already finished project as finished (nothing happens)
        try {
            register.markProjectAsDoneDependent(projectId);
        } catch (Exception e) {
            fail("Should not throw Exception");
        }
    }

    /**
     * Test of marking unfinished project with unfinished and finished task as finished
     * Test of marking already finished one as finished
     */
    @Test
    public void markProjectAsDoneAlways() {
        // Arrange
        // Prepare project with two tasks assigned
        Project testProject00 = new Project("testProject00", "20201010");
        register.addProject(testProject00);
        Task testTask00 = new Task("testTask00", "20201010");
        Task testTask01 = new Task("testTask01", "20201111");
        register.addTask(testTask00);
        register.addTask(testTask01);
        String taskId00 = register.getTasks().get(0).getId();
        String taskId01 = register.getTasks().get(1).getId();
        String projectId = register.getProjects().get(0).getId();
        register.addTaskToProject(taskId00, projectId);
        register.addTaskToProject(taskId01, projectId);
        register.markTaskAsDone(taskId00);
        // Act
        // Try to mark project as finished (unsuccessful)
        register.markProjectAsDoneAlways(projectId);
        // Assert
        assertTrue(register.getTasks().get(0).ifDone() && register.getTasks().get(1).ifDone() &&
                register.getProjects().get(0).ifDone());

        // Act
        // Try to mark already finished project as finished (nothing happens)
        try {
            register.markProjectAsDoneAlways(projectId);
        } catch (Exception e) {
            fail("Should not throw Exception");
        }
    }


    /**
     * Test of finding existing task
     * Test of finding not existing task
     */
    @Test
    public void findTask() {
        // Arrange
        Task testTask00 = new Task("testTask00", "20201010");
        register.addTask(testTask00);
        String taskId = testTask00.getId();
        // Act
        Task foundTask = register.findTask(taskId);
        Task notFoundTask = register.findTask("id");
        // Assert
        assertEquals(testTask00, foundTask);
        assertNull(notFoundTask);
    }

    /**
     * Test of finding existing project
     * Test of finding not existing project
     */
    @Test
    public void findProject() {
        // Arrange
        Project testProject00 = new Project("testProject00", "20201010");
        register.addProject(testProject00);
        String projectId = testProject00.getId();
        // Act
        Project foundProject = register.findProject(projectId);
        Project notFoundProject = register.findProject("id");
        // Assert
        assertEquals(testProject00, foundProject);
        assertNull(notFoundProject);
    }

    /**
     * Test of removing existing task
     * Test of removing not existing task
     */
    @Test
    public void removeTask() {
        // Arrange
        Task testTask00 = new Task("testTask00", "20201010");
        register.addTask(testTask00);
        String taskId = testTask00.getId();
        // Act
        register.removeTask(taskId);
        try {
            register.removeTask(taskId);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
        }
        // Assert
        assertNull(register.findTask(taskId));
    }

    /**
     * Test of removing existing project without tasks
     * Test of removing existing project with task
     * Test of removing not existing task
     */
    @Test
    public void removeProjectAlways() {
        // Arrange
        // Prepare project with two tasks assigned and empty project
        Project testProject00 = new Project("testProject00", "20201010");
        Project testProject01 = new Project("testProject01", "20201111");
        register.addProject(testProject00);
        register.addProject(testProject01);
        Task testTask00 = new Task("testTask00", "20201010");
        Task testTask01 = new Task("testTask01", "20201111");
        register.addTask(testTask00);
        register.addTask(testTask01);
        String taskId00 = register.getTasks().get(0).getId();
        String taskId01 = register.getTasks().get(1).getId();
        String projectId = register.getProjects().get(0).getId();
        String projectId01 = register.getProjects().get(1).getId();
        register.addTaskToProject(taskId00, projectId);
        register.addTaskToProject(taskId01, projectId);
        register.markTaskAsDone(taskId00);
        // Act
        // Remove projects
        register.removeProjectAlways(projectId);
        register.removeProjectAlways(projectId01);
        try {
            register.removeProjectAlways(projectId);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
        }
        // Assert
        assertNull(register.findProject(projectId));
        assertNull(register.findProject(projectId01));
    }

    /**
     * Test of removing existing project without tasks
     * Test of removing existing project with task
     * Test of removing not existing task
     */
    @Test
    public void removeProjectDependent() {
        // Arrange
        // Prepare project with two tasks assigned and empty project
        Project testProject00 = new Project("testProject00", "20201010");
        Project testProject01 = new Project("testProject01", "20201111");
        register.addProject(testProject00);
        register.addProject(testProject01);
        Task testTask00 = new Task("testTask00", "20201010");
        Task testTask01 = new Task("testTask01", "20201111");
        register.addTask(testTask00);
        register.addTask(testTask01);
        String taskId00 = register.getTasks().get(0).getId();
        String taskId01 = register.getTasks().get(1).getId();
        String projectId = register.getProjects().get(0).getId();
        String projectId01 = register.getProjects().get(1).getId();
        register.addTaskToProject(taskId00, projectId);
        register.addTaskToProject(taskId01, projectId);
        register.markTaskAsDone(taskId00);
        // Act
        // Remove projects
        register.removeProjectDependent(projectId);
        register.removeProjectDependent(projectId01);
        try {
            register.removeProjectDependent(projectId01);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
        }
        // Assert
        assertEquals(testProject00, register.findProject(projectId));
        assertNull(register.findProject(projectId01));
    }

    /*
    @Test
    public void setTaskStatus() {
    }

    @Test
    public void setTaskDueDate() {
    }

    @Test
    public void setTaskTitle() {
    }

    @Test
    public void setProjectStatus() {
    }

    @Test
    public void setProjectDueDate() {
    }

    @Test
    public void setProjectTitle() {
    }

     */
}