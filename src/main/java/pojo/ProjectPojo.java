package pojo;

import java.util.List;

public class ProjectPojo {
	private String projectId;
	private String environment;
	private List<String> tests;

	public List<String> getTests() {
		return tests;
	}

	public void setTests(List<String> tests) {
		this.tests = tests;
	}

	private String parallelExecution;
	private String retryFailed;
	private String notifyEmail;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public String getParallelExecution() {
		return parallelExecution;
	}

	public void setParallelExecution(String parallelExecution) {
		this.parallelExecution = parallelExecution;
	}

	public String getRetryFailed() {
		return retryFailed;
	}

	public void setRetryFailed(String retryFailed) {
		this.retryFailed = retryFailed;
	}

	public String getNotifyEmail() {
		return notifyEmail;
	}

	public void setNotifyEmail(String notifyEmail) {
		this.notifyEmail = notifyEmail;
	}
}
