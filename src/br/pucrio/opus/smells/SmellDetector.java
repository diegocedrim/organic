package br.pucrio.opus.smells;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class SmellDetector implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
//		JavaFilesFinder sourceLoader = new JavaFilesFinder(context);
//		SourceFilesResolver compUnitLoader = new SourceFilesResolver(sourceLoader);
//		List<SourceFile> sourceFiles = compUnitLoader.getResolvedSourceFiles();
//		for (SourceFile sourceFile : sourceFiles) {
//			LinesOfCodeVisitor v = new LinesOfCodeVisitor(sourceFile.getClassMetrics());
//			PublicFieldCount pfc = new PublicFieldCount(sourceFile.getClassMetrics());
//			sourceFile.getCompilationUnit().accept(v);
//			sourceFile.getCompilationUnit().accept(pfc);
//			System.out.println(sourceFile.getFile().getName() + " " + sourceFile.getClassMetrics().getLoc());
//			System.out.println(sourceFile.getFile().getName() + " " + sourceFile.getClassMetrics().getPublicFieldsCount());
//		}
		return EXIT_OK;
	}

	@Override
	public void stop() {

	}

}
