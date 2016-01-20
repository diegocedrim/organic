package br.pucrio.opus.smells;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import br.pucrio.opus.smells.ast.ASTBuilder;
import br.pucrio.opus.smells.files.SourceFilesLoader;
import br.pucrio.opus.smells.visitors.MethodCount;

public class SmellDetector implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		SourceFilesLoader loader = new SourceFilesLoader(context);
		List<File> sourceFiles = loader.findAll();
		
		ASTBuilder builder = new ASTBuilder(loader.getSourcePaths());
		for (File file : sourceFiles) {
			ASTParser parser = builder.create();
			String source = FileUtils.readFileToString(file);
			parser.setSource(source.toCharArray());
			CompilationUnit compilationUnit = (CompilationUnit)parser.createAST(null);
			compilationUnit.accept(new MethodCount());
		}
				
		return EXIT_OK;
	}

	@Override
	public void stop() {

	}

}
