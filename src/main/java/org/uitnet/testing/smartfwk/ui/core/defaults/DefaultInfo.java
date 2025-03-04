/*
 * SmartTestAutoFramework
 * Copyright 2021 and beyond
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.uitnet.testing.smartfwk.ui.core.defaults;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class DefaultInfo {
	public static final String DEFAULT_APP_NAME = "DEFAULT-APP";
	public static final String DEFAULT_APP_LOGIN_PAGE_VALIDATOR = DefaultAppLoginPageValidator.class.getPackageName() 
			+ "." + DefaultAppLoginPageValidator.class.getSimpleName();
	public static final String DEFAULT_APP_LOGIN_SUCCESS_PAGE_VALIDATOR = DefaultAppLoginSuccessPageValidator.class
			.getPackageName() + "." + DefaultAppLoginSuccessPageValidator.class.getSimpleName();
	public static final String DEFAULT_USER_PROFILE_NAME = "DEFAULT-USER-PROFILE";
	public static final String DEFAULT_PAGE_OBJECTS_PACKAGE = "page_objects";
	
	public static String YAML_FILE_VARIABLE_PROJECT_ROOT_DIR = "${project.root.directory}";
}
