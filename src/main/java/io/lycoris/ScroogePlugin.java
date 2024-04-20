package io.lycoris;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class ScroogePlugin implements Plugin<Project> {
    public void apply(Project project) {
        project.getTasks().create("compileScrooge", ScroogeCompileTask.class);
    }
}
