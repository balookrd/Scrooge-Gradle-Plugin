package io.lycoris;

import com.twitter.scrooge.Compiler;
import com.twitter.scrooge.Main;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.*;
import scala.collection.JavaConverters;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScroogeCompileTask extends DefaultTask {

    private Iterable<File> files = Collections.singletonList(new File("src/main/thrift"));
    private File dest = new File("build/generated-sources/gen-java");
    private List<String> opts = Collections.singletonList("-v");

    @OutputDirectory
    public File getDest() {
        return dest;
    }

    public void setDest(File destinationDirectory) {
        dest = destinationDirectory;
    }

    @InputFiles
    public Iterable<File> getThriftFiles() {
        return files;
    }

    public void setThriftFiles(Iterable<File> files) {
        this.files = files;
    }

    @Input
    @Optional
    public List<String> getOpts() {
        return opts;
    }

    public void setOpts(List<String> opts) {
        this.opts = opts;
    }

    @TaskAction
    public void compile() {

        Compiler compiler = new Compiler();
        compiler.destFolder_$eq(dest.getAbsolutePath());
        compiler.language_$eq("java");

        List<String> thriftFiles = new ArrayList<>();
        for (File item : files) {
            thriftFiles.add(item.getAbsolutePath());
        }
        thriftFiles.forEach(System.out::println);

        List<String> args = new ArrayList<>();
        args.addAll(opts);
        args.addAll(thriftFiles);

        Main.parseOptions(compiler,
                JavaConverters.asScalaIteratorConverter(args.iterator()).asScala().toSeq());

        compiler.run();
    }
}
