package br.pucrio.opus.smells;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

public class SmellDetector implements IApplication {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		Object argsObj = context.getArguments().get("application.args");
		String[] args = (String[])argsObj;
		for (String string : args) {
			System.out.println(string);
		}
		return EXIT_OK;
	}

	@Override
	public void stop() {
		
	}

}
