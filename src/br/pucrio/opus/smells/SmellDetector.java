package br.pucrio.opus.smells;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.pucrio.opus.smells.collector.ClassLevelSmellDetector;
import br.pucrio.opus.smells.collector.MethodLevelSmellDetector;
import br.pucrio.opus.smells.collector.Smell;
import br.pucrio.opus.smells.gson.ObservableExclusionStrategy;
import br.pucrio.opus.smells.metrics.MethodMetricValueCollector;
import br.pucrio.opus.smells.metrics.TypeMetricValueCollector;
import br.pucrio.opus.smells.resources.JavaFilesFinder;
import br.pucrio.opus.smells.resources.Method;
import br.pucrio.opus.smells.resources.SourceFile;
import br.pucrio.opus.smells.resources.SourceFilesLoader;
import br.pucrio.opus.smells.resources.Type;
import br.pucrio.opus.smells.util.ArgumentsIntepreter;

public class SmellDetector implements IApplication {

	private void collectMethodMetrics(Type type) {
		for (Method method: type.getMethods()) {
			MethodMetricValueCollector methodCollector = new MethodMetricValueCollector(type.getNodeAsTypeDeclaration());
			methodCollector.collect(method);
		}
	}

	private void collectTypeMetrics(List<Type> types) throws IOException {
		for (Type type : types) {
			TypeMetricValueCollector typeCollector = new TypeMetricValueCollector();
			typeCollector.collect(type);
			System.out.println("Calculating metric values for " + type.getSourceFile().getFile().getName());
			this.collectMethodMetrics(type);
		}
	}

	private void detectSmells(List<Type> allTypes) {
		for (Type type : allTypes) {
			for (Method method: type.getMethods()) {
				MethodLevelSmellDetector methodSmellDetector = new MethodLevelSmellDetector();
				List<Smell> smells = methodSmellDetector.detect(method);
				method.addAllSmells(smells);
			}
			ClassLevelSmellDetector classSmellDetector = new ClassLevelSmellDetector();
			List<Smell> smells = classSmellDetector.detect(type);
			type.addAllSmells(smells);
		}
	}

	private List<Type> loadAllTypes(List<String> sourcePaths) throws IOException {
		List<Type> allTypes = new ArrayList<>();
		JavaFilesFinder sourceLoader = new JavaFilesFinder(sourcePaths);
		SourceFilesLoader compUnitLoader = new SourceFilesLoader(sourceLoader);
		List<SourceFile> sourceFiles = compUnitLoader.getLoadedSourceFiles();
		for (SourceFile sourceFile : sourceFiles) {
			for (Type type : sourceFile.getTypes()) {
				allTypes.add(type);
				System.out.println("Loading " + sourceFile.getFile().getName());
			}
		}
		return allTypes;
	}

	@Override
	public Object start(IApplicationContext context) throws Exception {
		System.out.println(new Date());
		ArgumentsIntepreter interpreter = new ArgumentsIntepreter(context);
		List<String> sourcePaths = interpreter.getSourcePaths();

		List<Type> allTypes = this.loadAllTypes(sourcePaths);
		this.collectTypeMetrics(allTypes);
		this.detectSmells(allTypes);

		BufferedWriter writer = new BufferedWriter(new FileWriter(interpreter.getOutputFile()));
		System.out.println("Serializing...");

		GsonBuilder builder = new GsonBuilder();
		builder.addSerializationExclusionStrategy(new ObservableExclusionStrategy());
		builder.serializeNulls();

		Gson gson = builder.create();
		gson.toJson(allTypes, writer);
		writer.close();
		System.out.println(new Date());
		return EXIT_OK;
	}

	@Override
	public void stop() {

	}

}
