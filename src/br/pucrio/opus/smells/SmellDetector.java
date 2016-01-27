package br.pucrio.opus.smells;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import com.google.gson.Gson;

import br.pucrio.opus.smells.metrics.MethodMetricValueCollector;
import br.pucrio.opus.smells.metrics.TypeMetricValueCollector;
import br.pucrio.opus.smells.resources.JavaFilesFinder;
import br.pucrio.opus.smells.resources.Method;
import br.pucrio.opus.smells.resources.ParenthoodRegistry;
import br.pucrio.opus.smells.resources.SourceFile;
import br.pucrio.opus.smells.resources.SourceFilesLoader;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.util.ArgumentsIntepreter;

public class SmellDetector implements IApplication {
	
	private void collectMethodMetrics(Type type) {
		for (Method method: type.getMethods()) {
			MethodMetricValueCollector methodCollector = new MethodMetricValueCollector(type.getNode());
			methodCollector.calculate(method);
		}
	}
	
	private List<Type> collectTypeMetrics(List<String> sourcePaths) throws IOException {
		ParenthoodRegistry registry = ParenthoodRegistry.getInstance();
		List<Type> allTypes = new ArrayList<>();
		
		JavaFilesFinder sourceLoader = new JavaFilesFinder(sourcePaths);
		SourceFilesLoader compUnitLoader = new SourceFilesLoader(sourceLoader);
		List<SourceFile> sourceFiles = compUnitLoader.getLoadedSourceFiles();
		for (SourceFile sourceFile : sourceFiles) {
			for (Type type : sourceFile.getTypes()) {
				registry.registerChild(type);
				allTypes.add(type);
				
				TypeMetricValueCollector typeCollector = new TypeMetricValueCollector();
				typeCollector.calculate(type);
				System.out.println("Calculating metric values for " + sourceFile.getFile().getName());
				this.collectMethodMetrics(type);
			}
		}
		
		return allTypes;
	}

	@Override
	public Object start(IApplicationContext context) throws Exception {
		ArgumentsIntepreter interpreter = new ArgumentsIntepreter(context);
		List<String> sourcePaths = interpreter.getSourcePaths();
		
		List<Type> allTypes = this.collectTypeMetrics(sourcePaths);
		BufferedWriter writer = new BufferedWriter(new FileWriter(interpreter.getOutputFile()));
		System.out.println("Serializing...");
		Gson gson = new Gson();
		gson.toJson(allTypes, writer);
		writer.close();
		return EXIT_OK;
	}
	
	@Override
	public void stop() {

	}

}
