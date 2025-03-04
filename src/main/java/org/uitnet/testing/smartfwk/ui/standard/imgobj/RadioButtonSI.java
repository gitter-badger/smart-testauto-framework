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

import java.util.HashMap;
import java.util.Map;

import org.sikuli.script.Region;
import org.testng.Assert;
import org.uitnet.testing.smartfwk.SmartCucumberScenarioContext;
import org.uitnet.testing.smartfwk.ui.core.SmartConstants;
import org.uitnet.testing.smartfwk.ui.core.appdriver.SmartAppDriver;
import org.uitnet.testing.smartfwk.ui.core.commons.LocatorType;
import org.uitnet.testing.smartfwk.ui.core.config.ApplicationType;
import org.uitnet.testing.smartfwk.ui.core.config.PlatformType;
import org.uitnet.testing.smartfwk.ui.core.config.WebBrowserType;
import org.uitnet.testing.smartfwk.ui.core.objects.ObjectLocation;
import org.uitnet.testing.smartfwk.ui.core.objects.radio.RadioButton;
import org.uitnet.testing.smartfwk.ui.core.utils.LocatorUtil;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class RadioButtonSI extends RadioButton {
	protected Map<String, String> platformImages = new HashMap<>();
	protected ObjectLocation radioButtonImgLocation;

	public RadioButtonSI(String displayName, String radioButtonImg, ObjectLocation radioButtonImgLocation) {
		super(LocatorType.IMAGE, displayName);
		platformImages.put(SmartConstants.DEFAULT_IMAGE_LOCATOR, radioButtonImg);
		this.radioButtonImgLocation = radioButtonImgLocation;
	}

	public RadioButtonSI addPlatformImageForNativeApp(PlatformType platform, String radioButtonImg) {
		LocatorUtil.setPlatformImageForNativeApp(platformImages, platform, radioButtonImg);
		return this;
	}

	public RadioButtonSI addPlatformImageForWebApp(PlatformType platform, WebBrowserType browserType,
			String radioButtonImg) {
		LocatorUtil.setPlatformImageForWebApp(platformImages, platform, browserType, radioButtonImg);
		return this;
	}

	public String getRadioButtonImage(PlatformType platform, ApplicationType appType, WebBrowserType browserType) {
		return LocatorUtil.findImage(platformImages, platform, appType, browserType);
	}

	public ObjectLocation getRadioButtonImageLocation() {
		return radioButtonImgLocation;
	}

	@Override
	public RadioButtonValidatorSI getValidator(SmartAppDriver appDriver, Region region) {
		return new RadioButtonValidatorSI(appDriver, this, region);
	}

	@Override
	public RadioButtonValidatorSI getValidator(SmartCucumberScenarioContext scenarioContext, Region region) {
		return getValidator(scenarioContext.getActiveAppDriver(), region);
	}

	@Override
	public RadioButtonSI clone() {
		return null;
	}

	@Override
	public RadioButtonSI updateLocatorParameterWithValue(String paramName, String paramValue) {
		Assert.fail("updateLocatorParameterWithValue() API is not implemented.");
		return this;
	}

}
