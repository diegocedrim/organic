package br.pucrio.opus.smells.tests.dummy;

public @interface AnnotationDecl {
	String author();

	String date();

	int currentRevision() default 1;

	String lastModified() default "N/A";

	String lastModifiedBy() default "N/A";

	String[] reviewers();
}
