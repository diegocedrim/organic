package br.pucrio.opus.smells.tests.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import br.pucrio.opus.smells.metrics.TypeMetricValueCollector;
import br.pucrio.opus.smells.resources.JavaFilesFinder;
import br.pucrio.opus.smells.resources.SourceFile;
import br.pucrio.opus.smells.resources.SourceFilesLoader;
import br.pucrio.opus.smells.resources.Type;

public class TypeLoader {

	public static Type loadOneWithMetrics(File file) throws IOException {
		Type type = loadAllWithMetrics(file).get(0);
		TypeMetricValueCollector collector = new TypeMetricValueCollector();
		collector.collect(type);
		return type;
	}
	
	public static List<Type> loadAllWithMetrics(File file) throws IOException {
		JavaFilesFinder finder = new JavaFilesFinder(new File("test").getAbsolutePath());
		SourceFilesLoader loader = new SourceFilesLoader(finder, file);
		SourceFile source = loader.getLoadedSourceFiles().get(0);
		return source.getTypes();
	}
}
