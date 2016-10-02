package br.pucrio.opus.smells.ast;

import java.util.Arrays;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;

public class ASTBuilder {

	private Map<String, String> options;
	
	private String[] sourcePaths;
	
	private String[] encoding;
	
	public String[] getEncoding() {
		return encoding;
	}
	
	@SuppressWarnings("unchecked")
	public ASTBuilder(String[] sourcePaths) {
		this.sourcePaths = sourcePaths;
		this.encoding = new String[this.sourcePaths.length];
		Arrays.fill(this.encoding, "UTF-8");
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
		parser.setEnvironment(null, sourcePaths, this.encoding, false);
		parser.setUnitName("any_name");
		return parser;
	}
}
