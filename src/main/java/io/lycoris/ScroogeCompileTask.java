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

    private File _dest = new File("/src/gen/java/");
    private Iterable<File> _files = Collections.singletonList(new File("/src/main/thrift/"));
    private List<String> _opts = Collections.singletonList("-v");
    private String _lang = "java";

    @OutputDirectory
    public File getDest() {
        return _dest;
    }

    public void setDest(File destinationDirectory) {
        _dest = destinationDirectory;
    }


    @InputFiles
    public Iterable<File> getThriftFiles() {
        return _files;
    }

    public void setThriftFiles(Iterable<File> files) {
        _files = files;
    }

    @Input
    @Optional
    public List<String> getOpts() {
        return _opts;
    }

    public void setOpts(List<String> opts) {
        _opts = opts;
    }

    @TaskAction
    public void compile() {
        String destination = getDest().getAbsolutePath();
        List<String> thriftFiles = new ArrayList<>();

        for (File item : _files) {
            thriftFiles.add(item.getAbsolutePath());
        }

        thriftFiles.forEach(System.out::println);

        Compiler compiler = new Compiler();
        compiler.destFolder_$eq(destination);
        compiler.language_$eq(_lang);

        List<String> args = new ArrayList<>();
        args.addAll(_opts);
        args.addAll(thriftFiles);

        Main.parseOptions(compiler, JavaConverters.asScalaIteratorConverter(args.iterator()).asScala().toSeq());

        compiler.run();
    }
}
