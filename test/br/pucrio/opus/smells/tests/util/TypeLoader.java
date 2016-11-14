package br.pucrio.opus.smells.tests.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.pucrio.opus.smells.resources.JavaFilesFinder;
import br.pucrio.opus.smells.resources.SourceFile;
import br.pucrio.opus.smells.resources.SourceFilesLoader;
import br.pucrio.opus.smells.resources.Type;

public class TypeLoader {

	public static Type loadOne(File file) throws IOException {
		Type type = loadAll(file).get(0);
		return type;
	}
	
	public static Type loadOneDummyClass(String dummyClass) throws IOException {
		Type type = loadAllDummyClass(dummyClass).get(0);
		return type;
	}
	
	public static List<Type> loadAll(File file) throws IOException {
		JavaFilesFinder finder = new JavaFilesFinder(new File("test").getAbsolutePath());
		SourceFilesLoader loader = new SourceFilesLoader(finder, file);
		SourceFile source = loader.getLoadedSourceFiles().get(0);
		return source.getTypes();
	}
	
	public static List<Type> loadAllFromDir(File sourcePath) throws IOException {
		JavaFilesFinder finder = new JavaFilesFinder(sourcePath.getAbsolutePath());
		SourceFilesLoader loader = new SourceFilesLoader(finder);
		List<Type> types = new ArrayList<>();
		for (SourceFile source : loader.getLoadedSourceFiles()) {
			types.addAll(source.getTypes());
		}
		return types;
	}
	
	public static List<Type> loadAllDummyClass(String dummyClassPath) throws IOException {
		String path = "test/br/pucrio/opus/smells/tests/dummy/";
		return loadAll(new File(path + dummyClassPath));
	}
}
