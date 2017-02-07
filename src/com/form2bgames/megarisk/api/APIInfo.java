package com.form2bgames.megarisk.api;

public final class APIInfo {
	public static final Integer MAJOR=0,MINOR=1,REVISION=0;
	public static final BuildType buildType=BuildType.ALPHA;
	public static enum BuildType{
		STABLE,BETA,ALPHA,RELEASE_CANDIDATE1,RELEASE_CANDIDATE2,RELEASE_CANDIDATE3,RELEASE_CANDIDATE4,RELEASE_CANDIDATE5
	}
}
