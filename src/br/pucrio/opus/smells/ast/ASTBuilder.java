package br.pucrio.opus.smells.ast;

import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

public class ASTBuilder {

	private Map<String, String> options;
	
	private String[] sourcePaths;
	
	@SuppressWarnings("unchecked")
	public ASTBuilder(String[] sourcePaths) {
		this.sourcePaths = sourcePaths;
		this.sourcePaths.clone();
		options = JavaCore.getOptions();
		options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM, JavaCore.VERSION_1_8);
		options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_1_8);
		JavaCore.setComplianceOptions(JavaCore.VERSION_1_8, options);
	}

	public ASTParser create() {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setCompilerOptions(this.options);
		parser.setResolveBindings(true);
		parser.setBindingsRecovery(true);
		parser.setStatementsRecovery(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setEnvironment(null, sourcePaths, new String[] { "UTF-8" }, true);
		parser.setUnitName("any_name");
		return parser;
	}
}
