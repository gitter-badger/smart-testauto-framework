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
import org.uitnet.testing.smartfwk.ui.core.objects.label.Label;

/**
 * 
 * @author Madhav Krishna
 *
 */
public class LabelSI extends Label {
	protected String labelImg;
	protected ObjectLocation labelImgLocation;

	public LabelSI(String displayName, String labelImg, ObjectLocation labelImgLocation) {
		super(LocatorType.IMAGE, displayName);
		this.labelImg = labelImg;
		this.labelImgLocation = labelImgLocation;
	}

	public String getLabelImage() {
		return labelImg;
	}

	public ObjectLocation getLabelImageLocation() {
		return labelImgLocation;
	}

	@Override
	public LabelValidatorSI getValidator(SmartAppDriver appDriver, Region region) {
		return new LabelValidatorSI(appDriver, this, region);
	}

	@Override
	public LabelSI clone() {
		return null;
	}

	@Override
	public LabelSI updateLocatorParameterWithValue(AppConfig appConfig, String paramName, String paramValue) {
		Assert.fail("updateLocatorParameterWithValue() API is not implemented.");
		return this;
	}

}
