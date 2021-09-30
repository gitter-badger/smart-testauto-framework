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
package org.uitnet.testing.smartfwk.ui.standard.imgobj;

import org.sikuli.script.Region;
import org.testng.Assert;
import org.uitnet.testing.smartfwk.ui.core.appdriver.SmartAppDriver;
import org.uitnet.testing.smartfwk.ui.core.commons.LocatorType;
import org.uitnet.testing.smartfwk.ui.core.config.AppConfig;
import org.uitnet.testing.smartfwk.ui.core.objects.ObjectLocation;
import org.uitnet.testing.smartfwk.ui.core.objects.button.Button;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class ButtonSI extends Button {
	protected String buttonImg;
	protected ObjectLocation buttonImgLocation;

	public ButtonSI(String displayName, String buttonImg, ObjectLocation buttonImgLocation) {
		super(LocatorType.IMAGE, displayName);
		this.buttonImg = buttonImg;
		this.buttonImgLocation = buttonImgLocation;
	}

	public String getButtonImage() {
		return buttonImg;
	}

	public ObjectLocation getButtonImageLocation() {
		return buttonImgLocation;
	}

	@Override
	public ButtonValidatorSI getValidator(SmartAppDriver appDriver, Region region) {
		return new ButtonValidatorSI(appDriver, this, region);
	}

	@Override
	public ButtonSI clone() {
		return null;
	}

	@Override
	public ButtonSI updateLocatorParameterWithValue(AppConfig appConfig, String paramName, String paramValue) {
		Assert.fail("updateLocatorParameterWithValue() API is not implemented.");
		return this;
	}

}
