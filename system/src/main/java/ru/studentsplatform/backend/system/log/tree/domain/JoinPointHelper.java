package ru.studentsplatform.backend.system.log.tree.domain;

import java.util.Arrays;

/**
 * @author Krylov Sergey (30.07.2020)
 */
public class JoinPointHelper {
	private String className;
	private String methodName;
	private Object[] parameters;

	public JoinPointHelper(String className, String methodName, Object[] parameters) {
		this.className = className;
		this.methodName = methodName;
		this.parameters = parameters;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return "JoinPointHelper{" +
				"className='" + className + '\'' +
				", methodName='" + methodName + '\'' +
				", parameters=" + Arrays.toString(parameters) +
				'}';
	}
}
